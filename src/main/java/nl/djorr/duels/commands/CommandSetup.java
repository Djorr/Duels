package nl.djorr.duels.commands;

import nl.djorr.duels.Duels;
import nl.djorr.duels.utils.Prefix;
import nl.djorr.duels.utils.SettingsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetup implements CommandExecutor {

    private Duels plugin;


    public CommandSetup(Duels Duels) {
        this.plugin = Duels;
    }
    private SettingsManager settings = SettingsManager.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                if (label.equalsIgnoreCase("setup")) {
                    if (player.isOp()) {
                        player.sendMessage(ChatColor.GRAY + "/setup lobby");
                        player.sendMessage(ChatColor.GRAY + "/setup spawn (team1/team2)");
                        player.sendMessage(ChatColor.GRAY + "/setup map (create/save/tp)");
                    }
                }
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("lobby")) {
                    if (player.isOp()) {
                        settings.getGameLocations().set("Lobby." + "x", player.getLocation().getBlockX() + 0.5);
                        settings.getGameLocations().set("Lobby." + "y", player.getLocation().getBlockY());
                        settings.getGameLocations().set("Lobby." + "z", player.getLocation().getBlockZ() + 0.5);
                        settings.getGameLocations().set("Lobby." + "world", player.getWorld().getName());
                        settings.getGameLocations().set("Lobby." + "dir", player.getLocation().getDirection());
                        settings.saveGameLocations();

                        player.sendMessage(Prefix.prefix + "Lobby spawn is geplaatst!");
                    }
                } else if (args[0].equalsIgnoreCase("spectator")) {
                    if (player.isOp()) {
                        settings.getGameLocations().set("Spectator." + "x", player.getLocation().getBlockX() + 0.5);
                        settings.getGameLocations().set("Spectator." + "y", player.getLocation().getBlockY());
                        settings.getGameLocations().set("Spectator." + "z", player.getLocation().getBlockZ() + 0.5);
                        settings.getGameLocations().set("Spectator." + "world", player.getWorld().getName());
                        settings.getGameLocations().set("Spectator." + "dir", player.getLocation().getDirection());
                        settings.saveGameLocations();

                        player.sendMessage(Prefix.prefix + "Spectator spawn is geplaatst!");
                    }
                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("map")) {
                    if (player.isOp()) {
                        if (args[1].equalsIgnoreCase("create")) {
                            plugin.WorldReset.makeBackUp(player);
                        } else if (args[1].equalsIgnoreCase("save")) {
                            Bukkit.getWorld(plugin.WorldReset.worldname).save();
                            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                                @Override
                                public void run() {
                                    plugin.WorldReset.saveWorld(player);
                                }
                            }, 40);
                        } else if (args[1].equalsIgnoreCase("tp")) {
                            player.teleport(Bukkit.getWorld(plugin.WorldReset.worldname).getSpawnLocation().add(0, 100, 0));
                        }
                    }
                } else if (args[0].equalsIgnoreCase("spawn")) {
                    if (player.isOp()) {
                        if (args[1].equalsIgnoreCase("team1")) {
                            setTeam1Spawn(player);
                        } else if (args[1].equalsIgnoreCase("team2")) {
                            setTeam2Spawn(player);
                        }
                    }
                }
            }
        }
        return false;
    }

    private void setTeam1Spawn(Player player) {
        settings.getGameLocations().set("Spawn." + "Team1" + ".x", player.getLocation().getBlockX()+0.5);
        settings.getGameLocations().set("Spawn." + "Team1" + ".y", player.getLocation().getBlockY());
        settings.getGameLocations().set("Spawn." + "Team1" + ".z", player.getLocation().getBlockZ()+0.5);
        settings.getGameLocations().set("Spawn." + "Team1" + ".world", player.getWorld().getName());
        settings.getGameLocations().set("Spawn." + "Team1" + ".dir", player.getLocation().getDirection());
        settings.saveGameLocations();

        player.sendMessage(Prefix.prefix + "Je hebt de spawn van Team1 geplaatst!");
    }

    private void setTeam2Spawn(Player player) {
        settings.getGameLocations().set("Spawn." + "Team2" + ".x", player.getLocation().getBlockX()+0.5);
        settings.getGameLocations().set("Spawn." + "Team2" + ".y", player.getLocation().getBlockY());
        settings.getGameLocations().set("Spawn." + "Team2" + ".z", player.getLocation().getBlockZ()+0.5);
        settings.getGameLocations().set("Spawn." + "Team2" + ".world", player.getWorld().getName());
        settings.getGameLocations().set("Spawn." + "Team2" + ".dir", player.getLocation().getDirection());
        settings.saveGameLocations();

        player.sendMessage(Prefix.prefix + "Je hebt de spawn van Team2 geplaatst!");
    }
}

