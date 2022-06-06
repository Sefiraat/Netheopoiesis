package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import dev.sefiraat.netheopoiesis.core.plant.GrowthDescription;
import dev.sefiraat.netheopoiesis.core.plant.Placement;
import dev.sefiraat.netheopoiesis.slimefun.NpsRecipeTypes;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class HarvestableSeed extends NetherSeed {

    private final ItemStack harvestItemStack;
    private final double growthRate;
    private final int purificationValue;

    public HarvestableSeed(@Nonnull ItemGroup itemGroup,
                           @Nonnull SlimefunItemStack item,
                           @Nonnull GrowthDescription growthDescription,
                           @Nonnull Placement placement,
                           @Nonnull ItemStack harvestItemStack,
                           double growthRate,
                           int purificationValue
    ) {
        super(itemGroup, item, NpsRecipeTypes.PLANT_BREEDING, new ItemStack[0], growthDescription, placement);
        this.harvestItemStack = harvestItemStack;
        this.growthRate = growthRate;
        this.purificationValue = purificationValue;
    }

    @Nullable
    @Override
    public ItemStack getHarvestingResult() {
        return this.harvestItemStack;
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
