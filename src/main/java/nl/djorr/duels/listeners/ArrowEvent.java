package nl.djorr.duels.listeners;

import nl.djorr.duels.Duels;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ArrowEvent implements Listener {

    private Duels plugin;

    public ArrowEvent(Duels Duels) {
        this.plugin = Duels;
    }

    @EventHandler
    public void arrowDropEvent(ProjectileHitEvent e) {
        Projectile projectile = e.getEntity();
        if (projectile.getType() == EntityType.ARROW) {
            projectile.remove();
        }
    }
}
