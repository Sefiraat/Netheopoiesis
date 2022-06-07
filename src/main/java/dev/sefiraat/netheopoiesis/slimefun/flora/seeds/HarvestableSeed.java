package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import dev.sefiraat.netheopoiesis.core.plant.GrowthType;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

/**
 * This plant can be harvested by right clicking with a {@link dev.sefiraat.netheopoiesis.slimefun.tools.HarvestingTool}
 * dropping the provided ItemStack into the world.
 * The plant then reverts to its first stage in its {@link GrowthType}
 */
public class HarvestableSeed extends NetherSeed {

    private final ItemStack harvestItemStack;

    public HarvestableSeed(@Nonnull ItemGroup itemGroup,
                           @Nonnull SlimefunItemStack item,
                           @Nonnull GrowthType growthType,
                           @Nonnull Set<String> placement,
                           @Nonnull ItemStack harvestItemStack,
                           double growthRate,
                           int purificationValue
    ) {
        super(itemGroup, item, growthType, placement, growthRate, purificationValue);
        this.harvestItemStack = harvestItemStack;
    }

    @Nullable
    @Override
    public ItemStack getHarvestingResult() {
        return this.harvestItemStack;
    }


}
