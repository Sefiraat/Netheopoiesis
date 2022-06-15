package dev.sefiraat.netheopoiesis.listeners;

import dev.sefiraat.netheopoiesis.api.items.NetherSeed;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import javax.annotation.Nonnull;

/**
 * The purpose of this listener is to allow us to cancel the block placement if not on the
 * correct crux/material. If done within the onBlocPlace handler, the BlockStorage is retained
 * leading to dupes.
 * TODO PR to slimefun to either do blockstorage after checking the event is cancelled or to remove
 */
public class SeedPlacementListener implements Listener {

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onSeedPlaced(@Nonnull BlockPlaceEvent event) {
        final SlimefunItem slimefunItem = SlimefunItem.getByItem(event.getItemInHand());
        if (slimefunItem instanceof NetherSeed netherSeed) {
            netherSeed.whenPlaced(event);
        }
    }
}
