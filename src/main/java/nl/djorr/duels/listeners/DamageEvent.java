package nl.djorr.duels.listeners;

import nl.djorr.duels.Duels;
import nl.djorr.duels.utils.GameState;
import nl.djorr.duels.utils.teams.Teams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageEvent implements Listener {

    private Duels plugin;

    public DamageEvent(Duels Duels) {
        plugin = Duels;
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {

        if (e.getEntity() instanceof Player) {
            updatePlayer((Player) e.getEntity());

            Player player2;
            if (Teams.isPlayerInTeam1((Player) e.getEntity())) {
                player2 = Bukkit.getPlayer(Teams.getTeam2().get(0));
            } else {
                player2 = Bukkit.getPlayer(Teams.getTeam1().get(0));
            }

            updatePlayer(player2);
        }
    }

    private void updatePlayer(Player player) {
        int health = (int) Math.round(player.getHealth());
        player.getScoreboard().getTeam("speler1").setSuffix("" + ChatColor.WHITE + health + ChatColor.GOLD + "❤");

        Player player2;
        if (Teams.isPlayerInTeam1(player)) {
            player2 = Bukkit.getPlayer(Teams.getTeam2().get(0));
        } else {
            player2 = Bukkit.getPlayer(Teams.getTeam1().get(0));
        }
        int health2 = (int) Math.round(player2.getHealth());
        player2.getScoreboard().getTeam("speler2").setSuffix("" + ChatColor.WHITE + health2 + ChatColor.GOLD + "❤");
    }


    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            Player player = (Player) event.getEntity();
            Player damager = (Player) event.getDamager();

            if (Duels.GameState == GameState.OPEN) {
                if (Teams.getTeam1().contains(player.getName()) && Teams.getTeam2().contains(damager.getName())) {
                    event.setCancelled(true);
                } else if (Teams.getTeam2().contains(player.getName()) && Teams.getTeam1().contains(damager.getName())) {
                    event.setCancelled(true);
                }
                if (Teams.spectator().contains(player.getName())) {
                    event.setCancelled(true);
                }
            }

            if (Duels.GameState == GameState.STARTING) {
                if (Teams.getTeam1().contains(player.getName()) && Teams.getTeam2().contains(damager.getName())) {
                    event.setCancelled(true);
                } else if (Teams.getTeam2().contains(player.getName()) && Teams.getTeam1().contains(damager.getName())) {
                    event.setCancelled(true);
                }
                if (Teams.spectator().contains(player.getName())) {
                    event.setCancelled(true);
                }
            }

            if (Duels.GameState == GameState.BUSY) {
                if (Teams.getTeam1().contains(player.getName()) && Teams.getTeam2().contains(damager.getName())) {
                    event.setCancelled(false);
                } else if (Teams.getTeam2().contains(player.getName()) && Teams.getTeam1().contains(damager.getName())) {
                    event.setCancelled(false);
                }
                if (Teams.spectator().contains(player.getName())) {
                    event.setCancelled(true);
                }
            }

            if (Duels.GameState == GameState.STOPPING) {
                if (Teams.getTeam1().contains(player.getName()) && Teams.getTeam2().contains(damager.getName())) {
                    event.setCancelled(true);
                } else if (Teams.getTeam2().contains(player.getName()) && Teams.getTeam1().contains(damager.getName())) {
                    event.setCancelled(true);
                }
                if (Teams.spectator().contains(player.getName())) {
                    event.setCancelled(true);
                }
            }

            if (Duels.GameState == GameState.STOPPED) {
                if (Teams.getTeam1().contains(player.getName()) && Teams.getTeam2().contains(damager.getName())) {
                    event.setCancelled(true);
                } else if (Teams.getTeam2().contains(player.getName()) && Teams.getTeam1().contains(damager.getName())) {
                    event.setCancelled(true);
                }
                if (Teams.spectator().contains(player.getName())) {
                    event.setCancelled(true);
                }
            }
        }
    }
}