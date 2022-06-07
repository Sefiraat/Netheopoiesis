package dev.sefiraat.netheopoiesis.listeners;

import dev.sefiraat.netheopoiesis.Purification;
import dev.sefiraat.netheopoiesis.utils.WorldUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import javax.annotation.Nonnull;

public class WaterPlaceListener implements Listener {

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onWaterPlace(@Nonnull PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        if (WorldUtils.inNether(player.getWorld())
            && player.getInventory().getItemInMainHand().getType() == Material.WATER_BUCKET
            && Purification.getValue(player.getLocation().getChunk()) >= Purification.PLACE_WATER
            && event.getClickedBlock() != null
            && event.getItem() != null
        ) {

            event.setCancelled(true);
            event.getClickedBlock().getRelative(event.getBlockFace()).setType(Material.WATER);
            event.getItem().setType(Material.BUCKET);
        }
    }
}
