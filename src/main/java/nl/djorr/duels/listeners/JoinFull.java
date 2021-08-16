package nl.djorr.duels.listeners;

import nl.djorr.duels.Duels;
import nl.djorr.duels.utils.GameState;
import nl.djorr.duels.utils.Prefix;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class JoinFull implements Listener {

    private Duels plugin;

    public JoinFull(Duels Duels) {
        plugin = Duels;
    }


    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e) {

        int playercount = Bukkit.getOnlinePlayers().size();

        if (Duels.GameState == GameState.BUSY) {
            if (playercount == 2) {
                e.disallow(PlayerLoginEvent.Result.KICK_FULL, Prefix.prefix + "Game is vol!");
            }
        }

        if (Duels.GameState == GameState.BUSY) {
            if (e.getPlayer().hasPermission("duels.vip")) {
                e.allow();
            } else {
                e.disallow(PlayerLoginEvent.Result.KICK_FULL, Prefix.prefix + "Deze game is al bezig!");
            }
        }
    }
}
