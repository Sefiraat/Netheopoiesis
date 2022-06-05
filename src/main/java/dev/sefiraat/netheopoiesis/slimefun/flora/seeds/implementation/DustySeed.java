package dev.sefiraat.netheopoiesis.slimefun.flora.seeds.implementation;

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
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DustySeed extends NetherSeed {

    private final LinkedList<Skulls> growthPhases = new LinkedList<>();

    public DustySeed(@Nonnull ItemGroup itemGroup,
                     @Nonnull SlimefunItemStack item,
                     @Nonnull RecipeType recipeType,
                     @Nonnull ItemStack[] recipe
    ) {
        super(itemGroup, item, recipeType, recipe);
        growthPhases.add(Skulls.SEED_INDIGO);
        growthPhases.add(Skulls.PLANT_HARDY_GROWTH_1);
        growthPhases.add(Skulls.PLANT_HARDY_GROWTH_2);
        growthPhases.add(Skulls.PLANT_HARDY_GROWTH_3);
        growthPhases.add(Skulls.PLANT_HARDY_GROWTH_4);
        growthPhases.add(Skulls.PLANT_HARDY_GROWTH_5);
    }

    @Nonnull
    @Override
    public Theme getTheme() {
        return Theme.SEED_INDIGO;
    }

    @Nonnull
    @Override
    public Set<NetherSeedCrux> getValidPlaces() {
        return Set.of(
            NpsSlimefunItems.BASIC_PURIFIED_NETHERRACK,
            NpsSlimefunItems.PURIFIED_NETHERRACK,
            NpsSlimefunItems.VORACIOUS_DIRT,
            NpsSlimefunItems.NETHER_DIRT,
            NpsSlimefunItems.NETHER_GRASS
        );
    }

    @Nullable
    @Override
    public ItemStack getHarvestingResult() {
        return new ItemStack(Material.GRAVEL);
    }

    @Override
    public double getGrowthRate() {
        return 0.09;
    }

    @Override
    public List<Skulls> getGrowthPhases() {
        return this.growthPhases;
    }

    @Override
    public int purificationValue() {
        return 1;
    }
}
