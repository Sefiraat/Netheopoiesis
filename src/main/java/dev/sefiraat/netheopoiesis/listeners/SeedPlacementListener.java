package dev.sefiraat.netheopoiesis.listeners;

import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.NetherSeed;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import javax.annotation.Nonnull;

public class SeedPlacementListener implements Listener {

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onSeedPlaced(@Nonnull BlockPlaceEvent event) {
        final SlimefunItem slimefunItem = SlimefunItem.getByItem(event.getItemInHand());
        if (slimefunItem instanceof NetherSeed netherSeed) {
            netherSeed.whenPlaced(event);
        }
    }
}
