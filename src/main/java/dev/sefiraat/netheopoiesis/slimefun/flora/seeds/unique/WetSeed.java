package dev.sefiraat.netheopoiesis.slimefun.flora.seeds.unique;

import dev.sefiraat.netheopoiesis.core.plant.GrowthDescription;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.NetherSeed;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

public class WetSeed extends NetherSeed {

    @ParametersAreNonnullByDefault
    public WetSeed(@Nonnull ItemGroup itemGroup,
                   @Nonnull SlimefunItemStack item,
                   GrowthDescription description
    ) {
        super(itemGroup, item, description);
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
            updateGrowthStage(block, 0);
            heldStack.setType(Material.WATER_BUCKET);
        }
    }
}
