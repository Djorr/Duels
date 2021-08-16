package nl.djorr.duels.utils.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlayerStatsSQL
{
    public static void createTable() {
        try {
            PreparedStatement ps = MySQL.getStatement("CREATE TABLE IF NOT EXISTS Duel_Stats(UUID VARCHAR(4000), Name VARCHAR(4000), Wins INT(255), Winstreak INT(255), Karma INT(255))");
            ps.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static int getWins(OfflinePlayer p)
    {
        try
        {
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM Duel_Stats WHERE UUID = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            int returning = 0;
            if (rs.next())
            {
                returning = rs.getInt("Wins");
                rs.close();
                ps.close();
                return returning;
            }
            ps.close();
            rs.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return 0;
    }

    public static String[] getTopWins(String type, int amount) {
        String[] array = new String[amount];
        try {
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM Duel_Stats ORDER BY CONVERT(" + type.toLowerCase() + ", UNSIGNED INTEGER) DESC LIMIT " + amount);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                array[amount - 1] = rs.getInt("Wins") + "." + rs.getInt(type.toLowerCase());

            }
        }catch(SQLException e) { }
        return array;
    }

    public static int getWinsStreak(OfflinePlayer p)
    {
        try
        {
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM Duel_Stats WHERE UUID = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            int returning = 0;
            if (rs.next())
            {
                returning = rs.getInt("Deaths");
                rs.close();
                ps.close();
                return returning;
            }
            ps.close();
            rs.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return 0;
    }

    public static void setWins(OfflinePlayer p, Integer wins)
    {
        try
        {
            PreparedStatement ps = MySQL.getStatement("UPDATE Duel_Stats SET Wins = ? WHERE UUID = ?");
            ps.setInt(1, wins.intValue());
            ps.setString(2, p.getUniqueId().toString());
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public static void setWinsStreak(OfflinePlayer p, Integer winstreak)
    {
        try
        {
            PreparedStatement ps = MySQL.getStatement("UPDATE Duel_Stats SET Winsstreak = ? WHERE UUID = ?");
            ps.setInt(1, winstreak.intValue());
            ps.setString(2, p.getUniqueId().toString());
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public static void setKarma(OfflinePlayer p, Integer karma)
    {
        try
        {
            PreparedStatement ps = MySQL.getStatement("UPDATE Duel_Stats SET Karma = ? WHERE UUID = ?");
            ps.setInt(1, karma.intValue());
            ps.setString(2, p.getUniqueId().toString());
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public static int getKarma(OfflinePlayer p)
    {
        try
        {
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM Duel_Stats WHERE UUID = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            int returning = 0;
            if (rs.next())
            {
                returning = rs.getInt("Karma");
                rs.close();
                ps.close();
                return returning;
            }
            ps.close();
            rs.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return 0;
    }

    public static HashMap<Integer, UUID> getLeaderboard()
    {
        HashMap<Integer, UUID> returning = new HashMap();
        try
        {
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM Duel_Stats ORDER BY Wins DESC LIMIT 3");
            ResultSet rs = ps.executeQuery();
            int x = 1;
            while (rs.next())
            {
                returning.put(Integer.valueOf(x), UUID.fromString(rs.getString("UUID")));
                x++;
            }
            ps.close();
            rs.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return returning;
    }

    public static void resetScores(Player p)
    {
        try
        {
            PreparedStatement ps = MySQL.getStatement("DELETE FROM Duel_Stats WHERE UUID = ?");
            ps.setString(1, p.getUniqueId().toString());
            ps.executeUpdate();
            ps.close();
            register(p);
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public static boolean isRegistered(Player p)
    {
        try
        {
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM Duel_Stats WHERE UUID = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                rs.close();
                return true;
            }
            rs.close();
            return false;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    public static void register(Player p)
    {
        if (isRegistered(p)) {
            return;
        }
        try
        {
            PreparedStatement ps = MySQL.getStatement("INSERT INTO Duel_Stats(UUID, Name, Wins, Winsstreak, Karma) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, p.getUniqueId().toString());
            ps.setString(2, p.getName().toString());
            ps.setInt(3, 0);
            ps.setInt(4, 0);
            ps.setInt(5, 0);
            ps.executeUpdate();
            ps.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}