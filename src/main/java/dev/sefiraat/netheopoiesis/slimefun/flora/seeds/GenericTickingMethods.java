package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherCrux;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.TreeType;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

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

    public static void onTickSpindleSeed(@Nonnull TickParameters parameters) {
        double randomChance = ThreadLocalRandom.current().nextDouble();
        if (randomChance <= 0.05) {
            final double randomX = ThreadLocalRandom.current().nextInt(-3, 4);
            final double randomY = ThreadLocalRandom.current().nextInt(-2, 3);
            final double randomZ = ThreadLocalRandom.current().nextInt(-3, 4);
            final Block block = parameters.getLocation().add(randomX, randomY, randomZ).getBlock();

            // the first block we spawn on needs to be AIR
            if (block.getType() != Material.AIR) {
                return;
            }

            final Block blockBelow = block.getRelative(BlockFace.DOWN);
            final SlimefunItem possibleCrux = BlockStorage.check(blockBelow);

            // And the block below must be a valid crux
            if (possibleCrux instanceof NetherCrux crux
                && parameters.getSeed().getPlacements().contains(crux.getId())
            ) {
                block.setType(Material.OAK_LOG);
            }
        }
    }

    public static void onTickSpineySeed(@Nonnull TickParameters parameters) {
        double randomChance = ThreadLocalRandom.current().nextDouble();
        if (randomChance <= 0.1) {
            final double randomX = ThreadLocalRandom.current().nextInt(-3, 4);
            final double randomY = ThreadLocalRandom.current().nextInt(-2, 3);
            final double randomZ = ThreadLocalRandom.current().nextInt(-3, 4);
            final Block block = parameters.getLocation().add(randomX, randomY, randomZ).getBlock();

            // the first block we spawn on needs to be AIR and Biome DESERT
            if (block.getType() != Material.AIR && block.getBiome() == Biome.DESERT) {
                return;
            }

            final Block blockBelow = block.getRelative(BlockFace.DOWN);
            final SlimefunItem possibleCrux = BlockStorage.check(blockBelow);

            // And the block below must be a valid crux
            if (possibleCrux instanceof NetherCrux crux
                && parameters.getSeed().getPlacements().contains(crux.getId())
            ) {
                block.setType(Material.CACTUS);
            }
        }
    }

    public static void onTickOakendranSeed(@Nonnull TickParameters parameters) {
        double randomChance = ThreadLocalRandom.current().nextDouble();
        if (randomChance <= 0.5) {
            final double randomX = ThreadLocalRandom.current().nextInt(-3, 4);
            final double randomY = ThreadLocalRandom.current().nextInt(-2, 3);
            final double randomZ = ThreadLocalRandom.current().nextInt(-3, 4);
            final Block block = parameters.getLocation().add(randomX, randomY, randomZ).getBlock();

            // the first block we spawn on needs to be AIR
            if (block.getType() != Material.AIR) {
                return;
            }

            final Block blockBelow = block.getRelative(BlockFace.DOWN);
            final SlimefunItem possibleCrux = BlockStorage.check(blockBelow);

            // And the block below must be a valid crux
            if (possibleCrux instanceof NetherCrux crux
                && parameters.getSeed().getPlacements().contains(crux.getId())
            ) {
                block.getWorld().generateTree(block.getLocation(), TreeType.TREE);
            }
        }
    }

    public static void onTickHateFilledSeed(@Nonnull TickParameters parameters) {
        double randomChance = ThreadLocalRandom.current().nextDouble();
        if (randomChance <= 0.05) {
            final double randomX = ThreadLocalRandom.current().nextInt(-3, 4);
            final double randomY = ThreadLocalRandom.current().nextInt(-3, 3);
            final double randomZ = ThreadLocalRandom.current().nextInt(-3, 4);
            final Location location = parameters.getLocation().clone().add(randomX, randomY, randomZ);
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
