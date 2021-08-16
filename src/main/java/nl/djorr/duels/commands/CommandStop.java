package nl.djorr.duels.commands;

import nl.djorr.duels.Duels;
import nl.djorr.duels.utils.GameState;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandStop implements CommandExecutor {

    private Duels plugin;

    public CommandStop(Duels Duels) {
        this.plugin = Duels;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                if (label.equalsIgnoreCase("forcestop")) {
                    if (Duels.GameState == GameState.BUSY) {
                        if (player.isOp()) {
                            Duels.GameState = GameState.STOPPING;
                            //TODO FORCE STOP
                        }
                    }
                }
            }
        }
        return false;
    }

    private void teleportPlayersToLobby() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (Bukkit.getOnlinePlayers().size() > 0) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        plugin.teleportServer(p, plugin.lobbyServerName);
                        p.sendMessage("Teleporting to lobby...");
                    }
                } else {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "stop");
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 100L);
    }
}
