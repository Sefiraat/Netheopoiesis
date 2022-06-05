package dev.sefiraat.netheopoiesis.slimefun.flora.seeds.implementation.second;

import dev.sefiraat.netheopoiesis.slimefun.NpsSlimefunItems;
import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherSeedCrux;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.NetherSeed;
import dev.sefiraat.netheopoiesis.utils.Skulls;
import dev.sefiraat.netheopoiesis.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ValuableSeed extends NetherSeed {

    private final List<Skulls> growthPhases = new ArrayList<>();

    public ValuableSeed(@Nonnull ItemGroup itemGroup,
                        @Nonnull SlimefunItemStack item,
                        @Nonnull RecipeType recipeType,
                        @Nonnull ItemStack[] recipe
    ) {
        super(itemGroup, item, recipeType, recipe);
        growthPhases.add(Skulls.SEED_GREEN);
        growthPhases.add(Skulls.PLANT_DELICATE_GROWTH_1);
        growthPhases.add(Skulls.PLANT_DELICATE_GROWTH_2);
        growthPhases.add(Skulls.PLANT_DELICATE_GROWTH_3);
        growthPhases.add(Skulls.PLANT_DELICATE_GROWTH_4);
        growthPhases.add(Skulls.PLANT_DELICATE_GROWTH_5);
    }

    @Nonnull
    @Override
    public Theme getTheme() {
        return Theme.SEED_GREEN;
    }

    @Nonnull
    @Override
    public Set<NetherSeedCrux> getValidPlaces() {
        return Set.of(
            NpsSlimefunItems.PURIFIED_NETHERRACK,
            NpsSlimefunItems.VORACIOUS_DIRT,
            NpsSlimefunItems.NETHER_DIRT,
            NpsSlimefunItems.NETHER_GRASS
        );
    }

    @Nullable
    @Override
    public ItemStack getHarvestingResult() {
        return new ItemStack(Material.EMERALD);
    }

    @Override
    public double getGrowthRate() {
        return 0.07;
    }

    @Override
    public List<Skulls> getGrowthPhases() {
        return this.growthPhases;
    }

    @Override
    public int purificationValue() {
        return 3;
    }
}
