package nl.djorr.duels.player.profiles;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;


public class CustomPlayer {

    private Player player;
    private String name;
    private UUID uuid;
    private String ipAddress;
    private boolean loaded = false;

    private int wins = 0;
    private int winstreak = 0;
    private int kills = 0;



    public CustomPlayer(UUID uuid) {
        this.player = Bukkit.getPlayer(uuid);
        this.name = player.getName();
        this.uuid = uuid;
        this.ipAddress = player.getAddress().getHostString();
    }

    /** --- Wins --- */

    public void insertWins(int wins) {
        this.wins = wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getWins() {
        return this.wins;
    }

    public void addWins(int wins) {
        setWins(this.wins + wins);
    }

    public void removeWins(int wins) {
        setWins(this.wins - wins);
    }

    /** --- Winstreak --- */

    public void insertWinstreak(int winstreak) {
        this.winstreak = winstreak;
    }

    public void setWinstreak(int winstreak) {
        this.winstreak = winstreak;
    }

    public int getWinstreak() {
        return this.winstreak;
    }

    public void addWinstreak(int winstreak) {
        setWinstreak(this.winstreak + winstreak);
    }

    public void removeWinstreak(int winstreak) {
        setWinstreak(this.winstreak - winstreak);
    }

    /** --- Lobby --- */

    public void teleportServer(String server) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);

        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
        } catch (IOException e) {
            // ERROR
        }

    }

    public void setLoaded(boolean isloaded) {
        this.loaded = isloaded;
    }

    public boolean isLoaded() {
        return this.loaded;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public String getName() {
        return this.name;
    }

    public Location getLocation() {
        return this.player.getLocation();
    }

    public void sendMessage(String msg) {
        this.player.sendMessage(msg);
    }
}
