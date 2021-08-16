package nl.djorr.duels.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class SettingsManager {

    private SettingsManager() { }

    private static SettingsManager instance = new SettingsManager();

    public static SettingsManager getInstance() {
        return instance;
    }

    private Plugin p;

    private FileConfiguration server;
    private File sfile;

    private FileConfiguration config;
    private File cfile;

    private FileConfiguration gamelocations;
    private File glfile;

    public void setup(Plugin p) {
        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdir();
        }

        cfile = new File(p.getDataFolder(), "config.yml");
        config = p.getConfig();
        config.options().copyDefaults(true);
        saveConfig();

        glfile = new File(p.getDataFolder(), "gamelocations.yml");

        if (!glfile.exists()) {
            try {
                glfile.createNewFile();
            }
            catch (IOException e) {
                Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create gamelocations.yml!");
            }
        }

        gamelocations = YamlConfiguration.loadConfiguration(glfile);

        sfile = new File(p.getDataFolder(), "server.yml");

        if (!sfile.exists()) {
            try {
                sfile.createNewFile();
            }
            catch (IOException e) {
                Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create server.yml!");
            }
        }

        server = YamlConfiguration.loadConfiguration(sfile);
    }

    public FileConfiguration getServer() {
        return server;
    }

    public void saveServer() {
        try {
            server.save(sfile);
        }
        catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save server.yml!");
        }
    }

    public void reloadServer() {
        server = YamlConfiguration.loadConfiguration(sfile);
    }

    public FileConfiguration getGameLocations() {
        return gamelocations;
    }

    public void saveGameLocations() {
        try {
            gamelocations.save(glfile);
        }
        catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save gamelocations.yml!");
        }
    }

    public void reloadGameLocations() {
        gamelocations = YamlConfiguration.loadConfiguration(glfile);
    }


    public FileConfiguration getConfig() {
        return config;
    }

    public void saveConfig() {
        try {
            config.save(cfile);
        }
        catch (IOException e) {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save config.yml!");
        }
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(cfile);
    }

    public PluginDescriptionFile getDesc() {
        return p.getDescription();
    }
}