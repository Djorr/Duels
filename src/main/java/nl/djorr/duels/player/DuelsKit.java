package nl.djorr.duels.player;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static nl.djorr.duels.utils.Prefix.prefix;

public class DuelsKit {

    public void addKit(Player player) {
        player.getInventory().clear();
        player.sendMessage(prefix + "Jouw kit is ingeladen..");

        /** Zwaard met enchantments */
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 5);
        sword.setDurability((short) 0);

        /** Boog met enchantments */
        ItemStack bow = new ItemStack(Material.BOW);
        bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        bow.addEnchantment(Enchantment.ARROW_DAMAGE, 5);
        bow.setDurability((short) 0);

        /** Boog met enchantments */
        ItemStack arrow = new ItemStack(Material.ARROW, 1);

        /** Gouden appel */
        ItemStack gapple = new ItemStack(Material.GOLDEN_APPLE, 16);

        /** Helmet met enchantments */
        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        helmet.addEnchantment(Enchantment.WATER_WORKER, 1);
        helmet.setDurability((short) 0);

        /** Chestplate met enchantments */
        ItemStack chest = new ItemStack(Material.DIAMOND_CHESTPLATE);
        chest.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        chest.setDurability((short) 0);

        /** Leggings met enchantments */
        ItemStack legs = new ItemStack(Material.DIAMOND_LEGGINGS);
        legs.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        legs.setDurability((short) 0);

        /** Boots met enchantments */
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        boots.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        boots.addEnchantment(Enchantment.DEPTH_STRIDER, 3);
        boots.setDurability((short) 0);

        player.getInventory().clear();
        player.getInventory().addItem(sword);
        player.getInventory().addItem(bow);
        player.getInventory().addItem(arrow);
        player.getInventory().addItem(gapple);
        player.getInventory().setHelmet(helmet);
        player.getInventory().setChestplate(chest);
        player.getInventory().setLeggings(legs);
        player.getInventory().setBoots(boots);
    }
}
