package dev.sefiraat.netheopoiesis.slimefun.flora.seeds.second;

import dev.sefiraat.netheopoiesis.slimefun.NpsSlimefunItemStacks;
import dev.sefiraat.netheopoiesis.slimefun.NpsSlimefunItems;
import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherSeedCrux;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.NetherSeed;
import dev.sefiraat.netheopoiesis.utils.Skulls;
import dev.sefiraat.netheopoiesis.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class SoulSeed extends NetherSeed {

    private final List<Skulls> growthPhases = new ArrayList<>();

    public SoulSeed(@Nonnull ItemGroup itemGroup,
                    @Nonnull SlimefunItemStack item,
                    @Nonnull RecipeType recipeType,
                    @Nonnull ItemStack[] recipe
    ) {
        super(itemGroup, item, recipeType, recipe);
        growthPhases.add(Skulls.SEED_BLUE);
        growthPhases.add(Skulls.PLANT_VINES_GROWTH_1);
        growthPhases.add(Skulls.PLANT_VINES_GROWTH_2);
        growthPhases.add(Skulls.PLANT_VINES_GROWTH_3);
        growthPhases.add(Skulls.PLANT_VINES_GROWTH_4);
        growthPhases.add(Skulls.PLANT_VINES_GROWTH_5);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onTickFullyGrown(Location location, NetherSeed seed, Config data) {
        double randomChance = ThreadLocalRandom.current().nextDouble();
        if (randomChance <= 0.1) {
            final double randomX = ThreadLocalRandom.current().nextInt(-3, 4);
            final double randomZ = ThreadLocalRandom.current().nextInt(-3, 4);
            // For loop to make sure the purification can creep up and down.
            for (int i = -1; i < 2; i++) {
                final Block block = location.clone().add(randomX, i, randomZ).getBlock();
                final SlimefunItem possibleCrux = BlockStorage.check(block);
                if (possibleCrux instanceof NetherSeedCrux crux && getValidPlaces().contains(crux)) {
                    block.setType(NpsSlimefunItemStacks.PURIFIED_NETHERRACK.getType());
                    BlockStorage.store(block, NpsSlimefunItemStacks.PURIFIED_NETHERRACK.getItemId());
                    // Return so we only effect the one block per valid tick
                    return;
                }
            }
        }
    }

    @Nonnull
    @Override
    public Theme getTheme() {
        return Theme.SEED_BLUE;
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
        return 0.7;
    }

    @Override
    public List<Skulls> getGrowthPhases() {
        return this.growthPhases;
    }

    @Override
    public int purificationValue() {
        return 2;
    }
}
