package nl.djorr.duels.handlers;

import nl.djorr.duels.Duels;
import nl.djorr.duels.utils.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ItemDrops implements Listener {

    private Duels plugin;

    public ItemDrops(Duels Duels) {
        this.plugin = Duels;
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (Duels.GameState == GameState.OPEN) {
            event.setCancelled(true);
        }
    }
}
