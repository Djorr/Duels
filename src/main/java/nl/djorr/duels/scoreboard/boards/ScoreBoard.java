package nl.djorr.duels.scoreboard.boards;

import nl.djorr.duels.Duels;
import nl.djorr.duels.utils.GameState;
import nl.djorr.duels.utils.Prefix;
import nl.djorr.duels.utils.database.PlayerStatsSQL;
import nl.djorr.duels.utils.teams.Teams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreBoard {

    private Duels plugin;

    private String blank = "";

    public ScoreBoard(Duels Duels)
    {
        this.plugin = Duels;
    }

    public void setupScoreboard() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            createScoreboard(player);
        }

    }

    public void setupGameScoreboard() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            gameScoreboard(player);
        }

    }

    public void setupEndScoreboard() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            endScoreboard(player);
        }
    }

    private void createScoreboard(Player player) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        if (Duels.GameState == GameState.OPEN) {

            Objective objective = board.registerNewObjective("scoreboard", "dummy");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);

            String name = " " + ChatColor.BLUE + ChatColor.BOLD + "DUELS";
            objective.setDisplayName(name);

            addSpace(board, 8);

            Team status = board.registerNewTeam("status");
            status.addEntry(ChatColor.BLUE.toString());
            status.setPrefix(ChatColor.WHITE + "Status: ");
            status.setSuffix(ChatColor.GREEN + "Wachten..");
            objective.getScore(ChatColor.BLUE.toString()).setScore(7);

            addSpace(board, 6);

            Team spelers = board.registerNewTeam("spelers");
            spelers.addEntry(ChatColor.GREEN.toString());
            spelers.setPrefix(ChatColor.WHITE + "Spelers: ");
            spelers.setSuffix("" + ChatColor.YELLOW + Bukkit.getOnlinePlayers().size() + "/2");
            objective.getScore(ChatColor.GREEN.toString()).setScore(5);

            addSpace(board, 4);

            Team server = board.registerNewTeam("server");
            server.addEntry(ChatColor.RED.toString());
            server.setPrefix(ChatColor.WHITE + "Server: ");
            server.setSuffix(ChatColor.YELLOW + "D-1");
            objective.getScore(ChatColor.RED.toString()).setScore(3);

            addSpace(board, 2);

            Team serverIp = board.registerNewTeam("serverIp");
            serverIp.addEntry(ChatColor.YELLOW.toString());
            serverIp.setPrefix(ChatColor.BLUE + "play.zout");
            serverIp.setSuffix(ChatColor.BLUE +"epopcorn.net");
            objective.getScore(ChatColor.YELLOW.toString()).setScore(1);

        }

        player.setScoreboard(board);
    }

    private void gameScoreboard(Player player) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective objective = board.registerNewObjective("gameboard", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Objective objective1 = board.registerNewObjective("showhealth", "health");
        objective1.setDisplaySlot(DisplaySlot.BELOW_NAME);
        objective1.setDisplayName(ChatColor.GOLD + "❤");

        for (Player online : Bukkit.getOnlinePlayers()) {
            if (online.getHealth() > 0) {
                online.setHealth(online.getHealth() - 0.0001);
            }
            online.setScoreboard(board);
        }

        String name = " " + ChatColor.BLUE + ChatColor.BOLD + "DUELS";
        objective.setDisplayName(name);

        addSpace(board, 11);

        Team time = board.registerNewTeam("time");
        time.addEntry(ChatColor.DARK_PURPLE.toString());
        time.setPrefix(ChatColor.WHITE + "Tijd over: ");
        time.setSuffix(ChatColor.YELLOW + "8:00");
        objective.getScore(ChatColor.DARK_PURPLE.toString()).setScore(10);

        addSpace(board, 9);

        Team speler1 = board.registerNewTeam("speler1");
        speler1.addEntry(ChatColor.LIGHT_PURPLE.toString());
        speler1.setPrefix(ChatColor.GREEN + player.getName() + " ");
        speler1.setSuffix("" + ChatColor.WHITE + 20 + ChatColor.GOLD + "❤");
        objective.getScore(ChatColor.LIGHT_PURPLE.toString()).setScore(8);

        Player player2;
        if (Teams.isPlayerInTeam1(player)) {
            player2 = Bukkit.getPlayer(Teams.getTeam2().get(0));
        } else {
            player2 = Bukkit.getPlayer(Teams.getTeam2().get(0));
        }


        Team speler2 = board.registerNewTeam("speler2");
        speler2.addEntry(ChatColor.RED.toString());
        speler2.setPrefix(ChatColor.RED + player2.getName() + " ");
        speler2.setSuffix("" + ChatColor.WHITE + 20 + ChatColor.GOLD + "❤");
        objective.getScore(ChatColor.RED.toString()).setScore(7);

        addSpace(board, 6);

        Team divisie = board.registerNewTeam("divisie");
        divisie.addEntry(ChatColor.GREEN.toString());
        divisie.setPrefix(ChatColor.WHITE + "Divisie: ");
        divisie.setSuffix(Prefix.getDivisie(player));
        objective.getScore(ChatColor.GREEN.toString()).setScore(5);

        Team wins = board.registerNewTeam("wins");
        wins.addEntry(ChatColor.BLUE.toString());
        wins.setPrefix(ChatColor.WHITE + "Wins: ");
        wins.setSuffix("" + ChatColor.YELLOW + String.valueOf(PlayerStatsSQL.getWins(player)));
        objective.getScore(ChatColor.BLUE.toString()).setScore(4);

        Team hwins = board.registerNewTeam("hwins");
        hwins.addEntry(ChatColor.GREEN.toString());
        hwins.setPrefix(ChatColor.WHITE + "Best wins: ");
        hwins.setSuffix(ChatColor.YELLOW + "0");
        objective.getScore(ChatColor.GREEN.toString()).setScore(3);

        addSpace(board, 2);

        Team serverIp = board.registerNewTeam("serverIp");
        serverIp.addEntry(ChatColor.YELLOW.toString());
        serverIp.setPrefix(ChatColor.BLUE + "play.zoute");
        serverIp.setSuffix(ChatColor.BLUE + "popcorn.net");
        objective.getScore(ChatColor.YELLOW.toString()).setScore(1);

        player.setScoreboard(board);
    }


    private void endScoreboard(Player player) {
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective objective = board.registerNewObjective("gameboard", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        String name = " " + ChatColor.BLUE + ChatColor.BOLD + "DUELS";
        objective.setDisplayName(name);

        addSpace(board, 2);

        Team serverIp = board.registerNewTeam("serverIp");
        serverIp.addEntry(ChatColor.YELLOW.toString());
        serverIp.setPrefix(ChatColor.BLUE + "play.zoute");
        serverIp.setSuffix(ChatColor.BLUE + "popcorn.net");
        objective.getScore(ChatColor.YELLOW.toString()).setScore(1);

        player.setScoreboard(board);
    }

    private void addSpace(Scoreboard board1, int i) {
        this.blank += " ";
        Score score = board1.getObjective(DisplaySlot.SIDEBAR).getScore(this.blank);
        score.setScore(i);
    }

    private String time(int seconds) {
        int second =  Math.round(seconds);
        int minute = Math.round(second/60);
        int hour =  Math.round(minute/60);

        int s = second - minute * 60;
        int m = minute - hour * 60;

        int length = (int)(Math.log10(s)+ 1);

        if (length == 1) {
            String time = "" + m + ":0" + s;
            return time;
        } else if (s == 0) {
            String time = "" + m + ":00";
            return time;
        }

        String time = "" + m + ":" + s;
        return time;
    }
}
