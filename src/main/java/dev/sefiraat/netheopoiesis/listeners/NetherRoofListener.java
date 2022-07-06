package dev.sefiraat.netheopoiesis.listeners;

import dev.sefiraat.netheopoiesis.utils.Keys;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

import javax.annotation.Nonnull;

/**
 * The NetherBeacon will 'break' blocks and drop them as falling blocks for the visual. As we do not want the
 * blocks to remain after the visual cue, we cancel the block change event here.
 *
 * @see dev.sefiraat.netheopoiesis.implementation.blocks.NetherBeacon
 */
public class NetherRoofListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onBlockLands(@Nonnull EntityChangeBlockEvent event) {
        if (event.getEntity() instanceof FallingBlock fallingBlock
            && PersistentDataAPI.getBoolean(fallingBlock, Keys.MANAGED_FALLING_BLOCK)
        ) {
            event.setCancelled(true);
        }
    }
}
