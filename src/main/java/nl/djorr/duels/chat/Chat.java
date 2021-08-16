package nl.djorr.duels.chat;

import nl.djorr.duels.Duels;
import nl.djorr.duels.utils.GameState;
import nl.djorr.duels.utils.Prefix;
import nl.djorr.duels.utils.database.PlayerStatsSQL;
import nl.djorr.duels.utils.teams.Teams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat implements Listener {

    private Duels plugin;
    public Chat (Duels Duels) { this.plugin = Duels; }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (Duels.GameState == GameState.OPEN) {
            Bukkit.broadcastMessage(Prefix.getRank(player) + ChatColor.GRAY + player.getDisplayName() + Prefix.prefix + event.getMessage());
        } else if (Duels.GameState == GameState.BUSY) {
            //TODO MAAK SPELER 1 MESSAGE:
            Bukkit.broadcastMessage(Prefix.getRank(player) + ChatColor.GRAY + player.getDisplayName() + Prefix.prefix + event.getMessage());

            Player player2;
            if (Teams.isPlayerInTeam1(player)) {
                player2 = Bukkit.getPlayer(Teams.getTeam2().get(0));
            } else {
                player2 = Bukkit.getPlayer(Teams.getTeam2().get(0));
            }

            //TODO MAAK SPELER 2 MESSAGE:
            Bukkit.broadcastMessage(Prefix.getRank(player) + ChatColor.GRAY + player2.getDisplayName() + Prefix.prefix + event.getMessage());

            //TODO MAAK SPECTATOR MESSAGE:
            Bukkit.broadcastMessage(Prefix.getRank(player) + ChatColor.GRAY + player.getDisplayName() + Prefix.prefix + event.getMessage());

        }
    }

    @EventHandler
    public void spectatorChat(AsyncPlayerChatEvent event) {
        if (Duels.GameState != GameState.BUSY || Duels.GameState != GameState.STOPPING || Duels.GameState != GameState.STOPPED) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (Duels.GameState == GameState.STOPPING) {
            if (event.getMessage().contains("gg") || (event.getMessage().contains("gj") || (event.getMessage().contains("wp") || (event.getMessage().contains("gg wp"))))) {
                player.sendMessage(ChatColor.LIGHT_PURPLE + "+5 Karma");
                PlayerStatsSQL.setKarma(player, PlayerStatsSQL.getKarma(player) + 5);
            }
        }
    }
}
