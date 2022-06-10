package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherCrux;
import dev.sefiraat.netheopoiesis.utils.WorldUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Holds the methods for the {@link GenericTickingSeed}'s consumer
 */
public final class GenericTickingMethods {

    private GenericTickingMethods() {
        throw new IllegalStateException("Utility class");
    }

    public static void onTickSpindleSeed(@Nonnull TickParameters params) {
        double randomChance = ThreadLocalRandom.current().nextDouble();
        if (randomChance <= 0.05) {
            final Block block = WorldUtils.randomLocation(params.getLocation(), 3, 2, 2).getBlock();

            // the first block we spawn on needs to be AIR
            if (block.getType() != Material.AIR) {
                return;
            }

            final Block blockBelow = block.getRelative(BlockFace.DOWN);
            final SlimefunItem possibleCrux = BlockStorage.check(blockBelow);

            // And the block below must be a valid crux
            if (possibleCrux instanceof NetherCrux crux
                && params.getSeed().getPlacements().contains(crux.getId())
            ) {
                block.setType(Material.OAK_LOG);
            }
        }
    }

    public static void onTickSpineySeed(@Nonnull TickParameters params) {
        double randomChance = ThreadLocalRandom.current().nextDouble();
        if (randomChance <= 0.1) {
            final Block block = WorldUtils.randomLocation(params.getLocation(), 3, 2, 2).getBlock();

            // the first block we spawn on needs to be AIR and Biome DESERT
            if (block.getType() != Material.AIR && block.getBiome() == Biome.DESERT) {
                return;
            }

            final Block blockBelow = block.getRelative(BlockFace.DOWN);
            final SlimefunItem possibleCrux = BlockStorage.check(blockBelow);

            // And the block below must be a valid crux
            if (possibleCrux instanceof NetherCrux crux
                && params.getSeed().getPlacements().contains(crux.getId())
            ) {
                block.setType(Material.CACTUS);
            }
        }
    }

    public static void onTickOakendranSeed(@Nonnull TickParameters params) {
        double randomChance = ThreadLocalRandom.current().nextDouble();
        if (randomChance <= 0.5) {
            final Block block = WorldUtils.randomLocation(params.getLocation(), 3, 2, 2).getBlock();

            // the first block we spawn on needs to be AIR
            if (block.getType() != Material.AIR) {
                return;
            }

            final Block blockBelow = block.getRelative(BlockFace.DOWN);
            final SlimefunItem possibleCrux = BlockStorage.check(blockBelow);

            // And the block below must be a valid crux
            if (possibleCrux instanceof NetherCrux crux
                && params.getSeed().getPlacements().contains(crux.getId())
            ) {
                block.getWorld().generateTree(block.getLocation(), TreeType.TREE);
            }
        }
    }

    public static void onTickHateFilledSeed(@Nonnull TickParameters params) {
        double randomChance = ThreadLocalRandom.current().nextDouble();
        if (randomChance <= 0.05) {
            final Location location = WorldUtils.randomLocation(params.getLocation(), 3, 2, 2);
            location.getWorld().createExplosion(location, 2F, true, false);
        }
    }

    public static void onTickPulsingSeed(@Nonnull TickParameters parameters) {
        // Todo set up beacon effects
    }

    public static class TickParameters {
        private final Location location;
        private final NetherSeed seed;
        private final Config data;

        @ParametersAreNonnullByDefault
        public TickParameters(Location location, NetherSeed seed, Config data) {
            this.location = location;
            this.seed = seed;
            this.data = data;
        }

        public Location getLocation() {
            return location.clone();
        }

        public NetherSeed getSeed() {
            return seed;
        }

        public Config getData() {
            return data;
        }
    }
}
