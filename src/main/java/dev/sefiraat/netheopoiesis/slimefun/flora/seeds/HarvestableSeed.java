package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import dev.sefiraat.netheopoiesis.core.plants.GrowthDescription;
import dev.sefiraat.netheopoiesis.core.plants.Placement;
import dev.sefiraat.netheopoiesis.slimefun.NpsRecipeTypes;
import dev.sefiraat.netheopoiesis.slimefun.NpsSlimefunItems;
import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherSeedCrux;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

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
