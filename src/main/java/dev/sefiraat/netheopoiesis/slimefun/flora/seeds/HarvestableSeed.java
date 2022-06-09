package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import dev.sefiraat.netheopoiesis.core.plant.GrowthDescription;
import dev.sefiraat.netheopoiesis.core.plant.GrowthStages;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This plant can be harvested by right-clicking with a {@link dev.sefiraat.netheopoiesis.slimefun.tools.HarvestingTool}
 * dropping the provided ItemStack into the world.
 * The plant then reverts to its first stage in its {@link GrowthStages}
 */
public class HarvestableSeed extends NetherSeed {

    private final ItemStack harvestItemStack;

    @ParametersAreNonnullByDefault
    public HarvestableSeed(SlimefunItemStack item, ItemStack harvest, GrowthDescription description) {
        super(item, description);
        this.harvestItemStack = harvest;
    }

    @Nullable
    @Override
    public ItemStack getHarvestingResult() {
        return this.harvestItemStack;
    }


}
