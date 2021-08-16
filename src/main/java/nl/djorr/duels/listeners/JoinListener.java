package nl.djorr.duels.listeners;

import nl.djorr.duels.Duels;
import nl.djorr.duels.scoreboard.boards.ScoreBoard;
import nl.djorr.duels.utils.GameState;
import nl.djorr.duels.utils.SettingsManager;
import nl.djorr.duels.utils.Title;
import nl.djorr.duels.utils.database.PlayerStatsSQL;
import nl.djorr.duels.utils.teams.TeamType;
import nl.djorr.duels.utils.teams.Teams;
import nl.djorr.duels.utils.Prefix;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class JoinListener implements Listener {

    private Duels plugin;

    public JoinListener(Duels Duels) {
        plugin = Duels;
    }

    private SettingsManager settings = SettingsManager.getInstance();

    private boolean isCounting = false;
    private boolean isCanceled = false;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage("");

        int playercount = Bukkit.getOnlinePlayers().size();

        if (Duels.GameState == GameState.OPEN) {
            Player player = event.getPlayer();

            double x = settings.getGameLocations().getDouble("Lobby." + "x");
            double y = settings.getGameLocations().getDouble("Lobby." + "y");
            double z = settings.getGameLocations().getDouble("Lobby." + "z");
            World world = Bukkit.getWorld(settings.getGameLocations().getString("Lobby." + "world"));
            Vector dir = settings.getGameLocations().getVector("Lobby." + "dir");

            Location loc = new Location(world, x, y, z);
            loc.setDirection(dir);
            player.teleport(loc);
            player.setHealth(20);
            player.setFoodLevel(20);
            player.getActivePotionEffects().clear();
            player.getInventory().clear();
            player.setGameMode(GameMode.SURVIVAL);
            player.getInventory().setArmorContents(null);
            player.setPlayerWeather(WeatherType.CLEAR);

            String join = Prefix.prefix + ChatColor.YELLOW + player.getDisplayName() + ChatColor.GRAY + " is de server betreden." + ChatColor.BLUE + " (" + playercount + "/2)";
            event.setJoinMessage(join);

            if (!PlayerStatsSQL.isRegistered(player)) {
                PlayerStatsSQL.register(player);
            }

            new ScoreBoard(plugin).setupScoreboard();
        }


        if (Duels.GameState == GameState.OPEN) {
            if (playercount == 2) {
                Player p1 = event.getPlayer();
                p1.getScoreboard().getTeam("status").setSuffix(ChatColor.GOLD + "Starten..");

                Duels.GameState = GameState.STARTING;
                startCountdown();
            }
        }

        if (Duels.GameState == GameState.BUSY) {

            Player player = event.getPlayer();
            Teams.addToTeam(TeamType.spectator, player);

            player.setGameMode(GameMode.ADVENTURE);
            player.setAllowFlight(true);
            player.setFlying(true);
            player.setPlayerWeather(WeatherType.CLEAR);
            player.setNoDamageTicks(999999);
            player.getInventory().clear();
            player.getInventory().setArmorContents(null);

            player.sendMessage(Prefix.prefix + "Je zit nu in spectator modus.");

            for (Player other : Bukkit.getOnlinePlayers()) {
                other.hidePlayer(player);
                player.setPlayerListName(player.getName() + "");
            }

        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

        Player p = e.getPlayer();

        int playercount = Bukkit.getOnlinePlayers().size();

        String quit = Prefix.prefix + ChatColor.YELLOW + p.getDisplayName() + ChatColor.GRAY + " is de server verlaten." + ChatColor.RED + " (" + "1" + "/2)";
        e.setQuitMessage(quit);

        if (Duels.GameState == GameState.STARTING) {
            if (playercount <= 2 && isCounting) {
                isCanceled = true;
                isCounting = false;

                Duels.GameState = GameState.OPEN;

                p.getScoreboard().getTeam("status").setSuffix(ChatColor.GREEN + "Wachten..");

                Bukkit.broadcastMessage(Prefix.prefix + "De countdown van de game is gestopt wegens te weinig spelers!");
            }
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                if (Duels.GameState == GameState.BUSY) {
                    Teams.clearTeams();

                    if (Bukkit.getOnlinePlayers().size() == 1) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            spectateMode(player);
                            //TODO CREATE WIN/LOSE
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 20);
    }

    public void startCountdown() {
        isCounting = true;
        isCanceled = false;

        new BukkitRunnable() {

            int i = 11;
            int playercount = 2;

            @Override
            public void run() {
                if (Duels.GameState == GameState.STARTING) {
                    if (isCanceled) {
                        cancel();
                        Duels.GameState = GameState.OPEN;

                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.getScoreboard().getTeam("status").setSuffix(ChatColor.GREEN + "Wachten..");
                        }
                    }
                }

                i--;

                if (i == 0) {
                    if (Duels.GameState == GameState.STARTING) {
                        cancel();
                        Duels.GameState = GameState.BUSY;
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (playercount < Bukkit.getOnlinePlayers().size() / 2) {
                                Teams.addToTeam(TeamType.team1, player);
                                plugin.GameUtils.defaultKit(player);
                                plugin.GameHandler.team1Spawn();
                            } else {
                                Teams.addToTeam(TeamType.team2, player);
                                plugin.GameUtils.defaultKit(player);
                                plugin.GameHandler.team2Spawn();
                            }
                        }

                        for (Player player : Bukkit.getOnlinePlayers()) {
                            playercount++;

                            new ScoreBoard(plugin).setupGameScoreboard();
                        }
                    }
                    plugin.GameTimer.beginTimer();
                }

                if (i != 0) {
                    if (Duels.GameState == GameState.STARTING) {
                        Bukkit.broadcastMessage(Prefix.prefix + "De game start over " + ChatColor.YELLOW + i + ChatColor.GRAY + " seconde(n)!");

                        Title title1 = new Title( "&a" + i, "", 1, 1, 1);
                        title1.broadcast();
                        if (i == 1) {
                            Title title2 = new Title( "&c" + i, "", 1, 1, 1);
                            title2.broadcast();
                        } else if (i == 2) {
                            Title title3 = new Title( "&e" + i, "", 1, 1, 1);
                            title3.broadcast();
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }


    public void spectateMode(Player player) {
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                player.getInventory().clear();
                player.setGameMode(GameMode.SPECTATOR);
            }
        }, 5);
    }

    private void teleportPlayersToLobby() {
        if (Bukkit.getOnlinePlayers().size() > 0) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                plugin.teleportServer(p, plugin.lobbyServerName);
                p.sendMessage("Je word verzonden naar de lobby..");
            }
        } else {
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "stop");
        }
    }
}
