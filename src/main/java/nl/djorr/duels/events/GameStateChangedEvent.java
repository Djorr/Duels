package nl.djorr.duels.events;

import nl.djorr.duels.Duels;
import nl.djorr.duels.utils.GameState;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class GameStateChangedEvent extends Event {

    private Duels plugin;

    public GameStateChangedEvent(Duels Duels) {
        plugin = Duels;
    }

    private GameState gameState;

    private static final HandlerList HANDLERS = new HandlerList();

    public GameStateChangedEvent(GameState starting) {
        Bukkit.getPluginManager().callEvent(new GameStateChangedEvent(GameState.OPEN));
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}
