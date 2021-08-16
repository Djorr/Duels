package nl.djorr.duels.utils.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameSQL {

    public static void createTable() {
        try {
            PreparedStatement ps = MySQL.getStatement("CREATE TABLE IF NOT EXISTS Duels_Servers(Server VARCHAR(4000), Players INT(225), State INT(225))");
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static List<String> getAllServersInfo() {

        ArrayList<String> list = new ArrayList<String>();
        try {
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM Duels_Servers");
            ResultSet rs = ps.executeQuery();
            try {
                while (rs.next() ) {
                    int numColumns = rs.getMetaData().getColumnCount();

                    String server = null;
                    int players = 0;
                    int state = 0;

                    for (int i = 1 ; i <= numColumns ; i++ ) {

                        switch (i) {
                            case 1:
                                server = "" + rs.getObject(i);
                                break;
                            case 2:
                                players = Integer.valueOf(rs.getObject(i).toString());
                                break;
                            case 3:
                                state = Integer.valueOf(rs.getObject(i).toString());
                                break;
                        }
                    }
                    list.add(server + ":" + players + ":" + state);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public static void setState(String server, int state) {
        // 0 = gesloten, 2 = open.
        try {
            PreparedStatement ps = MySQL.getStatement("UPDATE Duels_Servers SET State = ? WHERE Server = ?");
            ps.setInt(1, state);
            ps.setString(2, server);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static int getState(String server) {
        // 0 = gesloten, 1 = open.
        try {
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM Duels_Servers WHERE Server = ?");
            ps.setString(1, server);
            ResultSet rs = ps.executeQuery();
            int returning = 0;
            while (rs.next()) {
                returning = rs.getInt("State");
                rs.close();
                ps.close();
                return returning;
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public static void setPlayers(String server, int players) {
        try {
            PreparedStatement ps = MySQL.getStatement("UPDATE Duels_Servers SET Players = ? WHERE Server = ?");
            ps.setInt(1, players);
            ps.setString(2, server);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static int getPlayers(String server) {
        try {
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM Duels_Servers WHERE Server = ?");
            ps.setString(1, server);
            ResultSet rs = ps.executeQuery();
            int returning = 0;
            while (rs.next()) {
                returning = rs.getInt("Players");
                rs.close();
                ps.close();
                return returning;
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public static void register(String serverName) {
        try {
            PreparedStatement ps = MySQL.getStatement("INSERT INTO Duels_Servers(Server, Players, State) VALUES (?, ?, ?)");
            ps.setString(1, serverName);
            ps.setInt(2, 0);
            ps.setInt(3, 0);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void unregister(String serverName) {
        try {
            PreparedStatement ps = MySQL.getStatement("DELETE FROM Duels_Servers WHERE Server = ?");
            ps.setString(1, serverName);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean isRegistered(String serverName) {
        try {
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM Duels_Servers WHERE Server = ?");
            ps.setString(1, serverName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rs.close();
                return true;
            } else {
                rs.close();
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean getState(String server, int state) {
        // 0 = gesloten, 2 = open.
        try {
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM Duels_Servers = ? WHERE Server = ?");
            ps.setInt(1, state);
            ps.setString(2, server);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
