package dev.sefiraat.netheopoiesis.slimefun.flora.seeds.unique;

import dev.sefiraat.netheopoiesis.core.plant.GrowthDescription;
import dev.sefiraat.netheopoiesis.slimefun.NpsRecipeTypes;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.NetherSeed;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.Set;

public class WetSeed extends NetherSeed {

    private final double growthRate;
    private final int purificationValue;

    public WetSeed(@Nonnull ItemGroup itemGroup,
                   @Nonnull SlimefunItemStack item,
                   @Nonnull GrowthDescription growthDescription,
                   @Nonnull Set<String> placement,
                   double growthRate,
                   int purificationValue
    ) {
        super(itemGroup, item, NpsRecipeTypes.PLANT_BREEDING, new ItemStack[0], growthDescription, placement);
        this.growthRate = growthRate;
        this.purificationValue = purificationValue;
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

    @Override
    public double getGrowthRate() {
        return growthRate;
    }

    @Override
    public int getPurificationValue() {
        return purificationValue;
    }
}
