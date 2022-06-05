package dev.sefiraat.netheopoiesis.slimefun.flora.seeds.implementation.first;

import dev.sefiraat.netheopoiesis.slimefun.NpsSlimefunItems;
import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherSeedCrux;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.NetherSeed;
import dev.sefiraat.netheopoiesis.utils.Skulls;
import dev.sefiraat.netheopoiesis.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class StoneySeed extends NetherSeed {

    private final LinkedList<Skulls> growthPhases = new LinkedList<>();

    public StoneySeed(@Nonnull ItemGroup itemGroup,
                      @Nonnull SlimefunItemStack item,
                      @Nonnull RecipeType recipeType,
                      @Nonnull ItemStack[] recipe
    ) {
        super(itemGroup, item, recipeType, recipe);
        growthPhases.add(Skulls.SEED_VIOLET);
        growthPhases.add(Skulls.PLANT_HARDY_GROWTH_1);
        growthPhases.add(Skulls.PLANT_HARDY_GROWTH_2);
        growthPhases.add(Skulls.PLANT_HARDY_GROWTH_3);
        growthPhases.add(Skulls.PLANT_HARDY_GROWTH_4);
        growthPhases.add(Skulls.PLANT_HARDY_GROWTH_5);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onTickFullyGrown(Location location, NetherSeed seed, Config data) {
        double randomChance = ThreadLocalRandom.current().nextDouble();
        if (randomChance <= 0.005) {
            location.getWorld().dropItem(location, new ItemStack(Material.COBBLESTONE));
        }
    }

    @Nonnull
    @Override
    public Theme getTheme() {
        return Theme.SEED_VIOLET;
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
