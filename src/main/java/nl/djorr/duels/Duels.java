package nl.djorr.duels;

import net.md_5.bungee.api.ChatColor;
import nl.djorr.duels.chat.Chat;
import net.zoutepopcorn.commands.*;
import nl.djorr.duels.commands.CommandSetup;
import nl.djorr.duels.events.Build;
import nl.djorr.duels.handlers.GameHandler;
import nl.djorr.duels.handlers.ItemDrops;
import net.zoutepopcorn.listeners.*;
import nl.djorr.duels.listeners.*;
import nl.djorr.duels.scoreboard.boards.ScoreBoard;
import nl.djorr.duels.tasks.GameTimer;
import nl.djorr.duels.utils.GameUtils;
import nl.djorr.duels.utils.SettingsManager;
import nl.djorr.duels.utils.WorldReset;
import nl.djorr.duels.utils.database.GameSQL;
import nl.djorr.duels.utils.database.MySQL;
import nl.djorr.duels.utils.database.PlayerStatsSQL;
import nl.djorr.duels.utils.teams.Teams;
import nl.djorr.duels.commands.CommandStart;
import nl.djorr.duels.commands.CommandStop;
import org.bukkit.Bukkit;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Duels extends JavaPlugin {

    private static Duels instance = null;
    private static Plugin p;

    @Nullable
    public Duels() throws IllegalStateException {
        if (instance != null)
            throw new IllegalStateException("Only one instance can run");
        instance = this;
    }

    public static Duels getInstance() {
        if (instance == null)
            throw new IllegalStateException("Cannot get instance: instance is null");
        return instance;
    }

    /** Chat */
    public nl.djorr.duels.chat.Chat Chat = new Chat(this);

    /** Listeners */
    private nl.djorr.duels.listeners.JoinListener JoinListener = new JoinListener(this);
    private ItemDrops ItemDrops = new ItemDrops(this);
    private nl.djorr.duels.listeners.DamageEvent DamageEvent = new DamageEvent(this);
    public nl.djorr.duels.listeners.DeathEvent DeathEvent = new DeathEvent(this);
    public nl.djorr.duels.listeners.ArrowEvent ArrowEvent = new ArrowEvent(this);
    public nl.djorr.duels.listeners.WeatherEvent WeatherEvent = new WeatherEvent(this);
    public JoinFull JoinFull = new JoinFull(this);
    public nl.djorr.duels.events.Build Build = new Build(this);

    /** Handlers */
    public GameHandler GameHandler = new GameHandler(this);

    /** Players */

    /** Utils */
    public static nl.djorr.duels.utils.GameState GameState =  nl.djorr.duels.utils.GameState.OPEN;
    public ScoreBoard ScoreBoard = new ScoreBoard(this);
    public nl.djorr.duels.tasks.GameTimer GameTimer = new GameTimer(this);
    public nl.djorr.duels.utils.GameUtils GameUtils = new GameUtils(this);
    public nl.djorr.duels.utils.WorldReset WorldReset = new WorldReset(this);

    private SettingsManager settings = SettingsManager.getInstance();
    public String lobbyServerName = "Lobby-1";
    public Map<UUID, Bat> titleMap = new HashMap<UUID, Bat>();

    @Override
    public void onEnable() {
        MySQL.connectToDatabase();
        PlayerStatsSQL.createTable();
        GameSQL.createTable();

        Teams.clearTeams();

        Duels.GameState = GameState.OPEN;

        /** Server */
        settings.setup(this);
        if (settings.getServer().getString("Server") == null) {
            settings.getServer().set("Server", "D-");
            settings.saveServer();
            Bukkit.getConsoleSender().sendMessage(org.bukkit.ChatColor.YELLOW + "[Setup] Plaats de goede naam in de server.yml van je server!");
        } else {
            GameSQL.setState(settings.getServer().getString("Server"), 1);
        }

        /** Wereld */
        WorldReset.resetWorld();

        /** Pluginmanager */
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(JoinListener, this);
        pm.registerEvents(ItemDrops, this);
        pm.registerEvents(DamageEvent, this);
        pm.registerEvents(GameTimer, this);
        pm.registerEvents(DeathEvent, this);
        pm.registerEvents(ArrowEvent, this);
        pm.registerEvents(WeatherEvent, this);
        pm.registerEvents(Chat, this);
        pm.registerEvents(JoinFull, this);
        pm.registerEvents(Build, this);

        /** Commands */
        getCommand("forcestart").setExecutor(new CommandStart(this));
        getCommand("forcestop").setExecutor(new CommandStop(this));
        getCommand("setup").setExecutor(new CommandSetup(this));

    }

    @Override
    public void onDisable() {
        Teams.clearTeams();

        MySQL.disconnectFromDatabase();

    }

    public void teleportServer(Player p, String server) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        p.sendMessage(ChatColor.GREEN + "Terug naar Lobby...");
        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
        } catch (IOException eee) {
            // ERROR
        }

        p.sendPluginMessage(this, "BungeeCord", b.toByteArray());
    }



    //TODO FIX WANNEER SPELER LEAVED HET WEER OPEN STAAT EN ALS SPELER WEER JOINT HIJ WEER BEGINT
    //TODO LAAD DATA IN VAN SPELER EN ALS HIJ QUIT MOET DE DATA BEWAARD WORDEN
    //TODO https://bukkit.org/threads/nameplates-with-no-visible-mob-underneath-holograms.235898/
    //TODO https://bukkit.org/threads/holographic-displays-v2-an-easy-way-to-manage-holograms-power-ups.269886/



    //TODO https://bukkit.org/threads/checking-if-a-string-has-a-specific-word.171312/
    //TODO https://www.spigotmc.org/resources/gadgetsmenu-1-8-1-12.10885/
    //TODO https://bukkit.org/threads/checking-if-a-players-name-is-in-a-chat-message.354733/
}
