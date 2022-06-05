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
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class RottenSeed extends NetherSeed {

    private final List<Skulls> growthPhases = new ArrayList<>();

    public RottenSeed(@Nonnull ItemGroup itemGroup,
                      @Nonnull SlimefunItemStack item,
                      @Nonnull RecipeType recipeType,
                      @Nonnull ItemStack[] recipe
    ) {
        super(itemGroup, item, recipeType, recipe);
        growthPhases.add(Skulls.SEED_GREEN);
        growthPhases.add(Skulls.PLANT_GROSS_GROWTH_1);
        growthPhases.add(Skulls.PLANT_GROSS_GROWTH_2);
        growthPhases.add(Skulls.PLANT_GROSS_GROWTH_3);
        growthPhases.add(Skulls.PLANT_GROSS_GROWTH_4);
        growthPhases.add(Skulls.PLANT_GROSS_GROWTH_5);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onTickFullyGrown(Location location, NetherSeed seed, Config data) {
        double randomChance = ThreadLocalRandom.current().nextDouble();
        if (randomChance <= 0.05) {
            final double randomX = ThreadLocalRandom.current().nextInt(-3, 4);
            final double randomY = ThreadLocalRandom.current().nextInt(-2, 3);
            final double randomZ = ThreadLocalRandom.current().nextInt(-3, 4);
            final Block block = location.clone().add(randomX, randomY, randomZ).getBlock();

            // the first block we spawn on needs to be AIR
            if (block.getType() != Material.AIR) {
                return;
            }

            final Block blockBelow = block.getRelative(BlockFace.DOWN);

            // And we need a solid floor
            if (blockBelow.getType() == Material.AIR) {
                return;
            }

            blockBelow.getWorld().spawnEntity(block.getLocation(), EntityType.ZOMBIE);
        }
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
