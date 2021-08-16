package nl.djorr.duels.events;

import nl.djorr.duels.Duels;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class Build implements Listener {

    private Duels plugin;

    public Build(Duels Duels) {
        plugin = Duels;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        Block block = event.getBlock();

        if (player.getGameMode() != GameMode.CREATIVE ) {
            event.setCancelled(true);
        } else if (player.getGameMode() != GameMode.ADVENTURE){
            event.setCancelled(true);
        }

        if (block.getType() == Material.STONE || block.getType() == Material.STEP ||
                block.getType() == Material.STONE || block.getType() == Material.IRON_TRAPDOOR ||
                block.getType() == Material.COBBLESTONE_STAIRS || block.getType() == Material.BARRIER ||
                block.getType() == Material.COBBLE_WALL || block.getType() == Material.SMOOTH_STAIRS ||
                block.getType() == Material.SMOOTH_BRICK || block.getType() == Material.DEAD_BUSH ||
                block.getType() == Material.GRASS || block.getType() == Material.LONG_GRASS ||
                block.getType() == Material.DOUBLE_STEP || block.getType() == Material.STONE ||
                block.getType() == Material.DIRT || block.getType() == Material.LEAVES || block.getType() == Material.LEAVES_2) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        Block block = event.getBlock();

        if (player.getGameMode() != GameMode.CREATIVE ) {
            event.setCancelled(true);
        } else if (player.getGameMode() != GameMode.ADVENTURE){
            event.setCancelled(true);
        }

        if (block.getType() == Material.STONE || block.getType() == Material.STEP ||
                block.getType() == Material.STONE || block.getType() == Material.IRON_TRAPDOOR ||
                block.getType() == Material.COBBLESTONE_STAIRS || block.getType() == Material.BARRIER ||
                block.getType() == Material.COBBLE_WALL || block.getType() == Material.SMOOTH_STAIRS ||
                block.getType() == Material.SMOOTH_BRICK || block.getType() == Material.DEAD_BUSH ||
                block.getType() == Material.GRASS || block.getType() == Material.LONG_GRASS ||
                block.getType() == Material.DOUBLE_STEP || block.getType() == Material.STONE ||
                block.getType() == Material.DIRT || block.getType() == Material.LEAVES || block.getType() == Material.LEAVES_2) {
            event.setCancelled(true);
        }
    }
}
