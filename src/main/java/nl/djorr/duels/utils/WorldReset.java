package nl.djorr.duels.utils;

import nl.djorr.duels.Duels;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.IOException;

public class WorldReset implements Listener {

    private Duels plugin;
    public WorldReset(Duels Duels) {
        plugin = Duels;
    }

    public String worldname = "DuelsM1";
    private String freshworld = "world";
    private String backupworld = "DuelsM1_BackUp";

    public void resetWorld() {
        File wFile = new File(worldname);
        if (wFile.exists()) {
            try {
                FileUtils.deleteDirectory(new File(worldname));

                FileUtils.copyDirectory(new File(backupworld), new File(worldname));
                File file = new File(worldname, "uid.dat");
                file.delete();
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                console.sendMessage(ChatColor.GREEN + "De wereld: " + worldname + " is succesvol verwijderd!");
            } catch (IOException e) {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                console.sendMessage(ChatColor.RED + "Kon de wereld: " + worldname + " niet verwijderen!");
            }
        } else {
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            console.sendMessage(ChatColor.RED + "De GameMap is nog niet aangemaakt, gebruik: /setup map create!");
        }
    }

    public void saveWorld(Player player) {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        try {
            FileUtils.deleteDirectory(new File(backupworld));

            FileUtils.copyDirectory(new File(worldname), new File(backupworld));
            console.sendMessage(ChatColor.GREEN + "De back-up: " + backupworld + " is opgeslagen!");
            player.sendMessage(ChatColor.GREEN + "De back-up: " + backupworld + " is opgeslagen!");
        } catch (IOException e) {
            console.sendMessage(ChatColor.RED + "De back-up: " + backupworld + " is niet opgeslagen!");
            player.sendMessage(ChatColor.RED + "De back-up: " + backupworld + " is niet opgeslagen!");
        }
    }

    public void makeBackUp(Player player) {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        try {
            File file = new File(backupworld);
            if (file.exists()) {
                FileUtils.deleteDirectory(file);
            }
            FileUtils.copyDirectory(new File(freshworld), new File(backupworld));
            console.sendMessage(ChatColor.GREEN + "De back-up: " + backupworld + " is opgeslagen!");
            player.sendMessage(ChatColor.GREEN + "De back-up: " + backupworld + " is opgeslagen!");
        } catch (IOException e) {
            console.sendMessage(ChatColor.RED + "De back-up: " + backupworld + " is niet opgeslagen!");
            player.sendMessage(ChatColor.RED + "De back-up: " + backupworld + " is niet opgeslagen!");
        }
    }
}
