package dev.sefiraat.netheopoiesis.listeners;

import dev.sefiraat.netheopoiesis.api.items.NetherCrux;
import dev.sefiraat.netheopoiesis.api.items.NetherSeed;
import dev.sefiraat.netheopoiesis.utils.WorldUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

import javax.annotation.Nonnull;

/**
 * The purpose of this listener is to stop abnormal removal of our Crux blocks.
 * Currently, Endermen can pick them up and place them (as Vanilla blocks) elsewhere.
 * This is confusing to players who may not know that the block is NOT a crux.
 * This listener isn't limited to just Endermen, we don't want ANY entities to
 * effect our seeds/crux'
 */
public class BlockProtectionListener implements Listener {

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onBlockChange(@Nonnull EntityChangeBlockEvent event) {
        if (WorldUtils.inNether(event.getEntity())) {
            final SlimefunItem slimefunItem = BlockStorage.check(event.getBlock());
            if (slimefunItem instanceof NetherSeed || slimefunItem instanceof NetherCrux) {
                event.setCancelled(true);
            }
        }
    }
}
