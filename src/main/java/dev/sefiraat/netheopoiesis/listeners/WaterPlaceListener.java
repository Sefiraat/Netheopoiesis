package dev.sefiraat.netheopoiesis.listeners;

import dev.sefiraat.netheopoiesis.Purification;
import dev.sefiraat.netheopoiesis.utils.WorldUtils;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import javax.annotation.Nonnull;

/**
 * The purpose of this listener is to allow players to place water buckets in the Nether
 * when above a purification threshold.
 * Todo - Remove particles if/when possible
 */
public class WaterPlaceListener implements Listener {

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onWaterPlace(@Nonnull PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final Block clickedBlock = event.getClickedBlock();
        if (event.getClickedBlock() != null
            && event.getItem() != null
            && clickedBlock != null
            && WorldUtils.inNether(player.getWorld())
            && player.getInventory().getItemInMainHand().getType() == Material.WATER_BUCKET
            && Purification.getValue(clickedBlock.getChunk()) >= Purification.PLACE_WATER
        ) {
            event.setCancelled(true);

            if (clickedBlock.getBlockData() instanceof Waterlogged block
                && !block.isWaterlogged()
                && !player.isSneaking()
            ) {
                block.setWaterlogged(true);
            } else if (clickedBlock.getRelative(event.getBlockFace()).isEmpty()) {
                clickedBlock.getRelative(event.getBlockFace()).setType(Material.WATER);
            }

            if (player.getGameMode() != GameMode.CREATIVE) {
                event.getItem().setType(Material.BUCKET);
            }
        }
    }
}
