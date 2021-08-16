package nl.djorr.duels.listeners;

import nl.djorr.duels.Duels;
import nl.djorr.duels.events.SendPlayerToServer;
import nl.djorr.duels.scoreboard.boards.ScoreBoard;
import nl.djorr.duels.utils.GameState;
import nl.djorr.duels.utils.Title;
import nl.djorr.duels.utils.database.PlayerStatsSQL;
import nl.djorr.duels.utils.effects.KillEffect;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class DeathEvent implements Listener {

    private Duels plugin;

    public DeathEvent(Duels Duels) {
        this.plugin = Duels;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (Duels.GameState == GameState.BUSY) {
            if ((e.getEntity() instanceof Player)) {
                final Player death = e.getEntity();
                Player killer = null;
                if (!e.getDrops().isEmpty()) {
                    e.getDrops().clear();
                }
                e.setKeepInventory(true);

                killer = death.getKiller();

                for (Player p : Bukkit.getOnlinePlayers()) {
                    killer.spigot().respawn();
                    spectateMode(p);
                    e.getDrops().clear();
                    deathEffect(p);
                    new ScoreBoard(plugin).setupEndScoreboard();
                }

                PlayerStatsSQL.setWins(killer, PlayerStatsSQL.getWins(killer) + 1);
            }
        }
    }

    private void deathEffect(Player killer) {
        new BukkitRunnable() {
            int timer = 0;

            @Override
            public void run() {
                if (Duels.GameState == GameState.BUSY) {
                    if (timer <= 0) {
                        KillEffect.fwStarEffect(killer);
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

    private void spectateMode(Player player) {
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.setGameMode(GameMode.SPECTATOR);
                    p.getInventory().clear();
                }
            }
        }, 10);
    }

    private void wonTitle(Player player1) {
        Title title = new Title(ChatColor.RED + "Gewonnen!", '&' + "a" + " " + player1.getName() + ChatColor.YELLOW + "heeft gewonnen!", 1, 1, 1);
        title.broadcast();
        new SendPlayerToServer(plugin).sendPlayerToServer();
    }

    private void loseTitle(Player player2) {
        Title title = new Title(ChatColor.GREEN + "Verloren!", '&' + "a" + " " + player2.getName() + ChatColor.YELLOW + "heeft gewonnen!", 1, 1, 1);
        title.broadcast();
        new SendPlayerToServer(plugin).sendPlayerToServer();
    }
}
