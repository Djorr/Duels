package nl.djorr.duels.utils;

import nl.djorr.duels.Duels;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GameUtils {

    public static nl.djorr.duels.utils.GameState GameState;

    private Duels plugin;

    public GameUtils(Duels Duels) {
        this.plugin = Duels;
    }


    public String waiting = "Open";
    public String starting = "Starten";
    public String busy = "Bezig";
    public String stopping = "Herstarten..";

    public ArrayList<String> lobby = new ArrayList<>();
    public ArrayList<String> game = new ArrayList<>();
    public ArrayList<String> team1 = new ArrayList<>();
    public ArrayList<String> team2 = new ArrayList<>();
    public ArrayList<String> team1Death = new ArrayList<>();
    public ArrayList<String> team2Death = new ArrayList<>();
    public ArrayList<String> spectator = new ArrayList<>();

    public void spectateMode(Player player) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                player.getInventory().clear();
                player.setGameMode(GameMode.SPECTATOR);
            }
        }, 0L);
    }

    public void defaultKit(Player player) {
        if (Duels.GameState == GameState.BUSY) {
            player.getInventory().clear();

            player.setFoodLevel(20);
            player.setHealth(20);

            player.setFireTicks(0);

            Bukkit.getOnlinePlayers();

            /** Inventory */
            ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
            ItemMeta swordMeta = sword.getItemMeta();

            sword.setItemMeta(swordMeta);

            sword.addEnchantment(Enchantment.DAMAGE_ALL, 5);
            sword.addEnchantment(Enchantment.DURABILITY, 3);

            ItemStack compass = new ItemStack(Material.COMPASS);
            ItemMeta compassMeta = compass.getItemMeta();

            compass.setItemMeta(compassMeta);

            ItemStack bow = new ItemStack(Material.BOW);
            ItemMeta bowMeta = bow.getItemMeta();

            bow.setItemMeta(bowMeta);

            bow.addEnchantment(Enchantment.ARROW_DAMAGE, 5);
            bow.addEnchantment(Enchantment.ARROW_FIRE, 1);
            bow.addEnchantment(Enchantment.DURABILITY, 3);
            bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);

            ItemStack water = new ItemStack(Material.WATER_BUCKET);
            ItemMeta waterMeta = water.getItemMeta();

            water.setItemMeta(waterMeta);

            ItemStack arrow = new ItemStack(Material.ARROW);
            ItemMeta arrowMeta = arrow.getItemMeta();

            arrow.setItemMeta(arrowMeta);

            ItemStack apple = new ItemStack(Material.GOLDEN_APPLE, 20);
            ItemMeta appleMeta = apple.getItemMeta();

            apple.setItemMeta(appleMeta);

            ItemStack pork = new ItemStack(Material.GRILLED_PORK, 64);
            ItemMeta porkMeta = pork.getItemMeta();

            pork.setItemMeta(porkMeta);

            ItemStack blocks = new ItemStack(Material.SMOOTH_BRICK, 64);
            ItemMeta blocksMeta = blocks.getItemMeta();

            blocks.setItemMeta(blocksMeta);

            ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
            ItemMeta pickaxeMeta = pickaxe.getItemMeta();

            pickaxe.setItemMeta(pickaxeMeta);

            pickaxe.addEnchantment(Enchantment.DIG_SPEED, 5);
            pickaxe.addEnchantment(Enchantment.DURABILITY, 3);
            pickaxe.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);

            ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
            ItemMeta axeMeta = axe.getItemMeta();

            axe.setItemMeta(axeMeta);

            axe.addEnchantment(Enchantment.DIG_SPEED, 5);
            axe.addEnchantment(Enchantment.DURABILITY, 3);
            axe.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);

            ItemStack lava = new ItemStack(Material.LAVA_BUCKET);
            ItemMeta lavaMeta = lava.getItemMeta();

            lava.setItemMeta(lavaMeta);

            ItemStack beef = new ItemStack(Material.COOKED_BEEF, 64);
            ItemMeta beefMeta = beef.getItemMeta();

            beef.setItemMeta(beefMeta);

            ItemStack tnt = new ItemStack(Material.TNT, 3);
            ItemMeta tntMeta = tnt.getItemMeta();

            tnt.setItemMeta(tntMeta);

            ItemStack fire = new ItemStack(Material.FLINT_AND_STEEL, 1);
            ItemMeta fireMeta = fire.getItemMeta();

            fire.setItemMeta(fireMeta);

            ItemStack fishingrod = new ItemStack(Material.FISHING_ROD, 1);
            ItemMeta fishingrodMeta = fishingrod.getItemMeta();

            fishingrod.setItemMeta(fishingrodMeta);

            ItemStack work = new ItemStack(Material.WORKBENCH, 3);
            ItemMeta workMeta = work.getItemMeta();

            work.setItemMeta(workMeta);

            ItemStack cobble = new ItemStack(Material.COBBLESTONE, 64);
            ItemMeta cobbleMeta = cobble.getItemMeta();

            cobble.setItemMeta(cobbleMeta);

            ItemStack wood = new ItemStack(Material.WOOD, 64);
            ItemMeta woodMeta = cobble.getItemMeta();

            wood.setItemMeta(woodMeta);

            ItemStack boat = new ItemStack(Material.BOAT, 3);
            ItemMeta boatMeta = boat.getItemMeta();

            boat.setItemMeta(boatMeta);

            player.getInventory().addItem(sword);
            player.getInventory().addItem(bow);
            player.getInventory().addItem(water);
            player.getInventory().addItem(apple);
            player.getInventory().addItem(pork);
            player.getInventory().addItem(arrow);


            /** Armor */
            ItemStack helm = new ItemStack(Material.DIAMOND_HELMET);
            ItemMeta helmMeta = helm.getItemMeta();

            helm.setItemMeta(helmMeta);

            helm.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            helm.addEnchantment(Enchantment.DURABILITY, 3);
            helm.addEnchantment(Enchantment.OXYGEN, 1);
            helm.addEnchantment(Enchantment.WATER_WORKER, 1);

            ItemStack harnas = new ItemStack(Material.DIAMOND_CHESTPLATE);
            ItemMeta harnasMeta = harnas.getItemMeta();

            harnas.setItemMeta(harnasMeta);

            harnas.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            harnas.addEnchantment(Enchantment.DURABILITY, 3);

            ItemStack broek = new ItemStack(Material.DIAMOND_LEGGINGS);
            ItemMeta broekMeta = broek.getItemMeta();

            broek.setItemMeta(broekMeta);

            broek.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            broek.addEnchantment(Enchantment.DURABILITY, 3);

            ItemStack laarzen = new ItemStack(Material.DIAMOND_BOOTS);
            ItemMeta laarzenMeta = laarzen.getItemMeta();

            laarzen.setItemMeta(laarzenMeta);

            laarzen.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
            laarzen.addEnchantment(Enchantment.DEPTH_STRIDER, 3);
            laarzen.addEnchantment(Enchantment.PROTECTION_FALL, 4);
            laarzen.addEnchantment(Enchantment.DURABILITY, 3);

            player.getInventory().setHelmet(helm);
            player.getInventory().setChestplate(harnas);
            player.getInventory().setLeggings(broek);
            player.getInventory().setBoots(laarzen);

            player.updateInventory();

            player.getInventory().setHelmet(helm);
            player.getInventory().setChestplate(harnas);
            player.getInventory().setLeggings(broek);
            player.getInventory().setBoots(laarzen);

        }
    }

    //TODO Teleport spelers naar lobby
}
