package nl.djorr.duels.handlers;

import nl.djorr.duels.Duels;
import nl.djorr.duels.utils.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

public class GameHandler {

    private Duels plugin;
    public GameHandler(Duels Duels) {
        plugin = Duels;
    }
    private SettingsManager settings = SettingsManager.getInstance();

    private void greenSpawnPlayers() {
        for (String player : plugin.GameUtils.team1) {
            Bukkit.getPlayer(player).teleport(team1Spawn());
        }
    }

    private void orangeSpawnPlayers() {
        for (String player : plugin.GameUtils.team2) {
            Bukkit.getPlayer(player).teleport(team2Spawn());
        }
    }

    public Location team1Spawn() {
        double x = settings.getGameLocations().getDouble("Spawn." + "Team1" + ".x");
        double y = settings.getGameLocations().getDouble("Spawn." + "Team1" + ".y");
        double z = settings.getGameLocations().getDouble("Spawn." + "Team1" + ".z");
        World world = Bukkit.getWorld(settings.getGameLocations().getString("Spawn." + "Team1" + ".world"));
        Vector dir = settings.getGameLocations().getVector("Spawn." + "Team1" + ".dir");

        Location loc = new Location(world, x, y+1, z);

        loc.setDirection(dir);

        return loc;
    }

    public Location team2Spawn() {
        double x = settings.getGameLocations().getDouble("Spawn." + "Team2" + ".x");
        double y = settings.getGameLocations().getDouble("Spawn." + "Team2" + ".y");
        double z = settings.getGameLocations().getDouble("Spawn." + "Team2" + ".z");
        World world = Bukkit.getWorld(settings.getGameLocations().getString("Spawn." + "Team2" + ".world"));
        Vector dir = settings.getGameLocations().getVector("Spawn." + "Team2" + ".dir");

        Location loc = new Location(world, x, y+1, z);

        loc.setDirection(dir);

        return loc;
    }


}
