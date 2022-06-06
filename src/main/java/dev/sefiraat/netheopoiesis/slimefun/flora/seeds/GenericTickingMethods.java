package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherSeedCrux;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Holds the methods for the {@link GenericTickingPlant}'s consumer
 */
public final class GenericTickingMethods {

    private GenericTickingMethods() {
        throw new IllegalStateException("Utility class");
    }

    @ParametersAreNonnullByDefault
    public static void onTickSpindleSeed(GenericTickingPlant.TickParameters parameters) {
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
            if (possibleCrux instanceof NetherSeedCrux crux
                && parameters.getSeed().getPlacement().contains(crux.getId())
            ) {
                block.setType(Material.OAK_LOG);
            }
        }
    }

    @ParametersAreNonnullByDefault
    public static void onTickOakendranSeed(GenericTickingPlant.TickParameters parameters) {
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
            if (possibleCrux instanceof NetherSeedCrux crux
                && parameters.getSeed().getPlacement().contains(crux.getId())
            ) {
                block.getWorld().generateTree(block.getLocation(), TreeType.TREE);
            }
        }
    }

}
