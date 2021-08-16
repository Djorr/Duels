package nl.djorr.duels.commands;

import nl.djorr.duels.Duels;
import nl.djorr.duels.utils.GameState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandStart implements CommandExecutor{

    private Duels plugin;

    public CommandStart(Duels Duels) {
        this.plugin = Duels;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                if (label.equalsIgnoreCase("forcestart")) {
                    if (Duels.GameState == GameState.OPEN) {
                        if (player.isOp()) {
                            Duels.GameState = GameState.STARTING;
                            for (Player p1 : Bukkit.getOnlinePlayers()) {
                                p1.getScoreboard().getTeam("status").setSuffix(ChatColor.GOLD + "Starten..");
                            }
                            //TODO Lobby scoreboard
                        }

                    }
                }
            }
        }
        return false;
    }
}
