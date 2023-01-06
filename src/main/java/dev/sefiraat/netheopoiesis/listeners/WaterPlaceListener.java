package dev.sefiraat.netheopoiesis.listeners;

import dev.sefiraat.netheopoiesis.Purification;
import dev.sefiraat.netheopoiesis.utils.WorldUtils;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import javax.annotation.Nonnull;

/**
 * The purpose of this listener is to allow players to place water buckets in the Nether
 * when above a purification threshold.
 * Todo - Remove particles if/when possible
 */
public class WaterPlaceListener implements Listener {

    public boolean isValidClick(@Nonnull PlayerInteractEvent event) {
        return event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR;
    }

    public boolean isAllowedToPlaceWater(@Nonnull PlayerInteractEvent event) {
        return WorldUtils.inNether(event.getPlayer().getWorld())
            && Purification.getValue(event.getClickedBlock().getChunk()) >= Purification.PLACE_WATER;
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onWaterPlace(@Nonnull PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final Block clickedBlock = event.getClickedBlock();
        if (clickedBlock != null
            && event.getItem() != null
            && player.getInventory().getItemInMainHand().getType() == Material.WATER_BUCKET
            && isAllowedToPlaceWater(event)
            && isValidClick(event)
        ) {
            event.setCancelled(true);
            final BlockData blockData = clickedBlock.getBlockData();
            if (blockData instanceof Waterlogged block
                && !block.isWaterlogged()
                && !player.isSneaking()
            ) {
                block.setWaterlogged(true);
            } else {
                clickedBlock.getRelative(event.getBlockFace()).setType(Material.WATER);
            }

            clickedBlock.setBlockData(blockData, true);

            if (player.getGameMode() != GameMode.CREATIVE) {
                event.getItem().setType(Material.BUCKET);
            }
        }
    }
}
