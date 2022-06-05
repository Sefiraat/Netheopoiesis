package dev.sefiraat.netheopoiesis.slimefun.flora.seeds.implementation;

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
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class OakendranSeed extends NetherSeed {

    private final LinkedList<Skulls> growthPhases = new LinkedList<>();

    public OakendranSeed(@Nonnull ItemGroup itemGroup,
                         @Nonnull SlimefunItemStack item,
                         @Nonnull RecipeType recipeType,
                         @Nonnull ItemStack[] recipe
    ) {
        super(itemGroup, item, recipeType, recipe);
        growthPhases.add(Skulls.SEED_ORANGE);
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
        if (randomChance <= 0.5) {
            final double randomX = ThreadLocalRandom.current().nextInt(-3, 4);
            final double randomY = ThreadLocalRandom.current().nextInt(-2, 3);
            final double randomZ = ThreadLocalRandom.current().nextInt(-3, 4);
            final Block block = location.clone().add(randomX, randomY, randomZ).getBlock();

            // the first block we spawn on needs to be AIR
            if (block.getType() != Material.AIR) {
                return;
            }

            final Block blockBelow = block.getRelative(BlockFace.DOWN);
            final SlimefunItem possibleCrux = BlockStorage.check(blockBelow);

            // And the block below must be a valid crux
            if (possibleCrux instanceof NetherSeedCrux crux && getValidPlaces().contains(crux)) {
                block.getWorld().generateTree(block.getLocation(), TreeType.TREE);
            }
        }
    }

    @Nonnull
    @Override
    public Theme getTheme() {
        return Theme.SEED_ORANGE;
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
