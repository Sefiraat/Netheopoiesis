package dev.sefiraat.netheopoiesis.implementation.flora;

import dev.sefiraat.netheopoiesis.api.items.NetherSeed;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 * This custom implementation of NetherSeed is used to allow a Right-click interaction to fill a bucket.
 * Todo - If we need more BlockUseHandlers, create a new extendable class with a PlayerRightClick consumer?
 */
public class WetSeed extends NetherSeed {

    public WetSeed(@Nonnull SlimefunItemStack item) {
        super(item);
    }

    @Override
    protected void onBlockUse(@Nonnull PlayerRightClickEvent event) {
        final ItemStack heldStack = event.getPlayer().getInventory().getItemInMainHand();

        if (heldStack.getType() != Material.BUCKET) {
            // We need an empty bucket
            return;
        }

        final Optional<Block> blockOptional = event.getClickedBlock();
        if (blockOptional.isEmpty()) {
            // No block, shouldn't be possible but can't process the event
            return;
        }

        final Block block = blockOptional.get();
        if (isMature(block)) {
            updateGrowthStage(block, 1);
            heldStack.setType(Material.WATER_BUCKET);
        }
    }

    @Override
    protected boolean validateSeed() {
        return true;
    }
}
