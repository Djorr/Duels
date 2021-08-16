package nl.djorr.duels.events;

import nl.djorr.duels.Duels;
import nl.djorr.duels.utils.database.GameSQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SendPlayerToServer implements Listener {

    private Duels plugin;
    public SendPlayerToServer(Duels Duels) {
        this.plugin = Duels;
    }

    public void sendPlayerToServer() {
        teleportPlayersToLobby();
    }


    private void teleportPlayersToLobby() {
        if (Bukkit.getOnlinePlayers().size() > 0) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (GameSQL.getState("D-1", 2)) {
                    teleportServer(p, "D-1");
                    p.sendMessage("Je word verzonden naar de volgende game..");
                } else if (GameSQL.getState("D-2", 2)) {
                    teleportServer(p, "D-2");
                    p.sendMessage("Je word verzonden naar de volgende game..");
                } else if (GameSQL.getState("D-3", 2)) {
                    teleportServer(p, "D-3");
                    p.sendMessage("Je word verzonden naar de volgende game..");
                } else if (GameSQL.getState("D-4", 2)) {
                    teleportServer(p, "D-4");
                    p.sendMessage("Je word verzonden naar de volgende game..");
                } else if (GameSQL.getState("D-5", 2)) {
                    teleportServer(p, "D-5");
                    p.sendMessage("Je word verzonden naar de volgende game..");
                } else if (GameSQL.getState("D-6", 2)) {
                    teleportServer(p, "D-6");
                    p.sendMessage("Je word verzonden naar de volgende game..");
                } else if (GameSQL.getState("D-7", 2)) {
                    teleportServer(p, "D-7");
                    p.sendMessage("Je word verzonden naar de volgende game..");
                } else if (GameSQL.getState("D-8", 2)) {
                    teleportServer(p, "D-8");
                    p.sendMessage("Je word verzonden naar de volgende game..");
                } else if (GameSQL.getState("D-9", 2)) {
                    teleportServer(p, "D-9");
                    p.sendMessage("Je word verzonden naar de volgende game..");
                } else if (GameSQL.getState("D-10", 2)) {
                    teleportServer(p, "D-10");
                    p.sendMessage("Je word verzonden naar de volgende game..");
                }
            }
        } else {
            for (Player p : Bukkit.getOnlinePlayers()) {
                plugin.teleportServer(p, plugin.lobbyServerName);
                p.sendMessage("Oeps.. Alle games zijn vol! Je word verzonden naar de lobby..");
            }

            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "stop");
        }
    }



    private void teleportServer(Player p, String server) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
        } catch (IOException eee) {
            // ERROR
        }

        p.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
    }
}
