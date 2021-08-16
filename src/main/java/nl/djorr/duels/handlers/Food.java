package nl.djorr.duels.handlers;

import nl.djorr.duels.Duels;
import nl.djorr.duels.utils.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class Food implements Listener {

    private Duels plugin;

    public Food(Duels Duels) {
        this.plugin = Duels;
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent event) {
        if (Duels.GameState == GameState.OPEN || Duels.GameState == GameState.STARTING) {
            event.setCancelled(true);
        } else {
            if (Duels.GameState == GameState.STARTING || Duels.GameState == GameState.BUSY) {
                event.setCancelled(false);
            }
        }
    }
}
