package dev.sefiraat.netheopoiesis.listeners;

import dev.sefiraat.netheopoiesis.Purification;
import dev.sefiraat.netheopoiesis.utils.WorldUtils;
import org.bukkit.GameMode;
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
        if (event.getClickedBlock() != null
            && event.getItem() != null
            && WorldUtils.inNether(player.getWorld())
            && player.getInventory().getItemInMainHand().getType() == Material.WATER_BUCKET
            && Purification.getValue(event.getClickedBlock().getChunk()) >= Purification.PLACE_WATER
        ) {
            event.setCancelled(true);
            event.getClickedBlock().getRelative(event.getBlockFace()).setType(Material.WATER);
            if (player.getGameMode() != GameMode.CREATIVE) {
                event.getItem().setType(Material.BUCKET);
            }
        }
    }
}
