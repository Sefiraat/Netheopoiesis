package dev.sefiraat.netheopoiesis.listeners;

import dev.sefiraat.netheopoiesis.PurificationMemory;
import org.bukkit.Material;
import org.bukkit.World;
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
        if (player.getWorld().getEnvironment() == World.Environment.NETHER
            && player.getInventory().getItemInMainHand().getType() == Material.WATER_BUCKET
            && PurificationMemory.getInstance().getPurificationValue(player.getLocation().getChunk()) >= 500
            && event.getClickedBlock() != null
            && event.getItem() != null
        ) {

            event.setCancelled(true);
            event.getClickedBlock().getRelative(event.getBlockFace()).setType(Material.WATER);
            event.getItem().setType(Material.BUCKET);
        }
    }
}
