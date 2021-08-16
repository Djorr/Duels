package nl.djorr.duels.utils.teams;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static nl.djorr.duels.utils.Prefix.prefix;

public class Teams {

    private static List<UUID> team1 = new ArrayList<>();
    private static List<UUID> team2 = new ArrayList<>();
    private static List<UUID> spectator = new ArrayList<>();

    public static void addToTeam(TeamType type, Player player) {
        switch (type) {
            case team1:
                team1.add(player.getUniqueId());
                player.sendMessage(prefix + "Je zit nu in team1!");
                break;
            case team2:
                team2.add(player.getUniqueId());
                player.sendMessage(prefix + "Je zit nu in team2!");
                break;
            case spectator:
                spectator().add(player.getUniqueId());
                player.sendMessage(prefix + "Je zit nu in spectator modus!");
                break;
        }
    }

    public static boolean isInteam(Player player) {
        return team1.contains(player.getUniqueId())
                || team2.contains(player.getUniqueId());
    }

    public static boolean isPlayerInTeam1(Player player) {

        return team1.contains(player.getUniqueId());
    }

    public static boolean killedTeam1(Player player) {

        return team1.contains(player.getUniqueId());
    }

    public static void clearTeams() {
        team1.clear();
        team2.clear();
        spectator.clear();
    }

    public static List<UUID> getTeam1() {
        return team1;
    }

    public static List<UUID> getTeam2() {
        return team2;
    }

    public static List<UUID> spectator() {
        return spectator;
    }

    public static List<UUID> getAllPlayersInTeams() {
        List<UUID> combinedTeams = new ArrayList<>();
        combinedTeams.addAll(team1);
        combinedTeams.addAll(team2);
        combinedTeams.addAll(spectator);
        return combinedTeams;
    }

    public static TeamType getTeamType(Player player) {
        if (!isInteam(player))
            return null;
        return (team1.contains(player.getUniqueId()) ? TeamType.team1 : TeamType.team2);
    }
}
