package nl.djorr.duels.tasks;

import nl.djorr.duels.Duels;
import nl.djorr.duels.utils.GameState;
import nl.djorr.duels.utils.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public class GameTimer implements Listener {

    private Duels plugin;

    public GameTimer(Duels Duels) {
        this.plugin = Duels;
    }

    private int timer = 480;
    private int endtimer = 490;

    public void beginTimer() {

        Title begin = new Title('&' + "e" + "Succes!", "", 1, 1, 1);
        begin.broadcast();

        new BukkitRunnable() {
            @Override
            public void run() {

                timer--;
                endtimer--;

                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.getScoreboard().getTeam("time").setSuffix(ChatColor.YELLOW + time(timer));
                }


                if (timer == 1) {
                    cancel();
                    Duels.GameState = GameState.STOPPING;
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        spectateMode(player);
                        dueTitle(player);
                    }
                }

                if (Duels.GameState == GameState.STOPPING) {
                    cancel();
                    plugin.ScoreBoard.setupEndScoreboard();

                    if (endtimer == 1) {
                        teleportPlayersToLobby();
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }


    private void dueTitle(Player p) {
        Title title = new Title(ChatColor.GOLD + "GELIJK SPEL!", ChatColor.BLUE + "Jullie zijn beiden te goed!", 1, 1, 1);
        title.broadcast();
    }

    //TODO GAME TIMER STOPPEN ALS SPELER LEAVED

    private String time(int seconds) {
        int second =  Math.round(seconds);
        int minute = Math.round(second/60);
        int hour =  Math.round(minute/60);

        int s = second - minute * 60;
        int m = minute - hour * 60;

        int length = (int)(Math.log10(s)+ 1);

        if (length == 1) {
            String time = "" + m + ":0" + s;
            return time;
        } else if (s == 0) {
            String time = "" + m + ":00";
            return time;
        }

        String time = "" + m + ":" + s;
        return time;
    }

    public void spectateMode(Player player) {
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                player.getInventory().clear();
                player.setGameMode(GameMode.SPECTATOR);
            }
        }, 5L);
    }

    private void teleportPlayersToLobby() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (Bukkit.getOnlinePlayers().size() > 0) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        plugin.teleportServer(p, plugin.lobbyServerName);
                        p.sendMessage("Teleporting to lobby...");
                    }
                } else {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "stop");
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 100L);
    }
}
