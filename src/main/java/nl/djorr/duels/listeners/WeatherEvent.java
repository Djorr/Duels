package nl.djorr.duels.listeners;

import nl.djorr.duels.Duels;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherEvent implements Listener {

    private Duels plugin;

    public WeatherEvent(Duels Duels) {
        plugin = Duels;
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {

        event.setCancelled(true);
    }
}

