package nl.djorr.duels.utils.database;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.*;

public class MySQL {

    private static String host = "51.89.153.129";
    private static String port = "3306";
    private static String username = "u28_KjWzGBiKFT";
    private static String password = "fkj5CITlCrB4FBxM6kJhGGbi";
    private static String database = "s28_os";

    private static Connection connect;


    private static boolean isConnectedToDatabase(){
        return connect != null;
    }

    public static void connectToDatabase() {
        if(!isConnectedToDatabase()){
            try {
                connect = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", username, password);
                Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + " »" + ChatColor.GRAY + "De verbinding met de database is vastgesteld.");
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + " »" + ChatColor.GRAY + "De verbinding met de database kan niet worden vastgesteld.");
            }
        }
    }

    public static void disconnectFromDatabase(){
        try {
            connect.close();
            Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + " »" + ChatColor.GRAY + "De verbinding met de database is gesloten.");
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + " »" + ChatColor.GRAY + "De verbinding met de dataabse kan niet worden gesloten.");
        }
    }

    public static PreparedStatement getStatement(String sql){
        if(isConnectedToDatabase()){
            PreparedStatement ps;
            try {
                ps = connect.prepareStatement(sql);
                return ps;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static ResultSet getResultOfDatabase(String sql){
        if(isConnectedToDatabase()){
            PreparedStatement ps;
            ResultSet rs;
            try {
                ps = getStatement(sql);
                rs = ps.executeQuery();
                return rs;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}

