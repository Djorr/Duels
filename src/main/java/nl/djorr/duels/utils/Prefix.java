package nl.djorr.duels.utils;

import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.Contexts;
import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.User;
import me.lucko.luckperms.api.caching.MetaData;
import me.lucko.luckperms.api.caching.UserData;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Prefix {

    public static String prefix =  ChatColor.BLUE + " »" + ChatColor.GRAY + " ";

    public static String getRank(Player player) {
        String rank = null;

        if (getRank(player) == null)
            return ChatColor.GRAY + "";

        switch (getRank(player)) {
            case "normal":
                rank = "" + ChatColor.GRAY + " ";
                break;

            case "vip":
                rank = "" + ChatColor.GOLD + "[VIP] ";
                break;

            case "elite":
                rank = "" + ChatColor.AQUA + "[ELITE] ";
                break;

            case "popcorn":
                rank = "" + ChatColor.RED + "[POPCORN] ";
                break;

            case "eindbaas":
                rank = "" + ChatColor.LIGHT_PURPLE + "[EINDBAAS] ";
                break;

            case "custom":
                rank = "" + ChatColor.GOLD + "[CUSTOM] ";
                break;

            case "twitch":
                rank = "" + ChatColor.DARK_PURPLE + "[Twitch] ";
                break;

            case "youtube":
                rank = "" + ChatColor.DARK_GRAY + "[" + ChatColor.WHITE + "Y" + ChatColor.RED + "T" + ChatColor.DARK_GRAY + "] ";
                break;

            case "helper":
                rank = "" + ChatColor.YELLOW + "[Helper] ";
                break;

            case "jrdev":
                rank = "" + ChatColor.DARK_PURPLE + "[Jr.Dev] ";
                break;

            case "moderator":
                rank = "" + ChatColor.BLUE + "[Mod] ";
                break;

            case "admin":
                rank = "" + ChatColor.RED + "[Admin] ";
                break;

            case "developer":
                rank = "" + ChatColor.DARK_PURPLE + "[Dev] ";
                break;

            case "beheerder":
                rank = "" + ChatColor.DARK_RED + "[Beheerder] ";
                break;

            case "eigenaar":
                rank = "" + ChatColor.DARK_RED + "[Eigenaar] ";
                break;
        }

        return rank;
    }

    public static String getDivisie(Player player) {
        String divisie = null;

        if (getDivisie(player) == null)
            return ChatColor.GRAY + "Unranked";

        switch (getDivisie(player)) {
            case "0":
                divisie = "" + ChatColor.DARK_PURPLE + "[Wood-V] ";
                break;

            case "1":
                divisie = "" + ChatColor.DARK_PURPLE + "[Wood-IV] ";
                break;

            case "2":
                divisie = "" + ChatColor.DARK_PURPLE + "[Wood-III] ";
                break;

            case "3":
                divisie = "" + ChatColor.DARK_PURPLE + "[Wood-II] ";
                break;

            case "4":
                divisie = "" + ChatColor.DARK_PURPLE + "[Wood-I] ";
                break;

            case "5":
                divisie = "" + ChatColor.DARK_GRAY + "[Stone-V] ";
                break;

            case "6":
                divisie = "" + ChatColor.DARK_GRAY + "[Stone-IV] ";
                break;

            case "7":
                divisie = "" + ChatColor.DARK_GRAY + "[Stone-III] ";
                break;

            case "8":
                divisie = "" + ChatColor.DARK_GRAY + "[Stone-II] ";
                break;

            case "9":
                prefix = "" + ChatColor.DARK_GRAY + "[Stone-I] ";
                break;

            case "10":
                divisie = "" + ChatColor.GOLD + "[Bronze-V] ";
                break;

            case "11":
                divisie = "" + ChatColor.GOLD + "[Bronze-IV] ";
                break;

            case "12":
                divisie = "" + ChatColor.GOLD + "[Bronze-III] ";
                break;

            case "13":
                divisie = "" + ChatColor.GOLD + "[Bronze-II] ";
                break;

            case "14":
                divisie = "" + ChatColor.GOLD + "[Bronze-I] ";
                break;

            case "15":
                divisie = "" + ChatColor.WHITE + "[Silver-V] ";
                break;

            case "16":
                divisie = "" + ChatColor.WHITE + "[Silver-IV] ";
                break;

            case "17":
                divisie = "" + ChatColor.WHITE + "[Silver-III] ";
                break;

            case "18":
                divisie = "" + ChatColor.WHITE + "[Silver-II] ";
                break;

            case "19":
                divisie = "" + ChatColor.WHITE + "[Silver-I] ";
                break;

            case "20":
                divisie = "" + ChatColor.AQUA + "[Diamond-V] ";
                break;

            case "21":
                divisie = "" + ChatColor.AQUA + "[Diamond-IV] ";
                break;

            case "22":
                divisie = "" + ChatColor.AQUA + "[Diamond-III] ";
                break;

            case "23":
                divisie = "" + ChatColor.AQUA + "[Diamond-II] ";
                break;

            case "24":
                divisie = "" + ChatColor.AQUA + "[Diamond-I] ";
                break;

            case "25":
                divisie = "" + ChatColor.YELLOW + "[Platinum-V] ";
                break;

            case "26":
                divisie = "" + ChatColor.YELLOW + "[Platinum-IV] ";
                break;

            case "27":
                divisie = "" + ChatColor.YELLOW + "[Platinum-III] ";
                break;

            case "28":
                divisie = "" + ChatColor.YELLOW + "[Platinum-II] ";
                break;

            case "29":
                divisie = "" + ChatColor.YELLOW + "[Platinum-I] ";
                break;
        }

        return divisie;
    }
}

