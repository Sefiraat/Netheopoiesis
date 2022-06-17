package dev.sefiraat.netheopoiesis;

import com.google.common.base.Preconditions;
import dev.sefiraat.netheopoiesis.utils.WorldUtils;
import io.github.bakedlibs.dough.blocks.BlockPosition;
import io.github.bakedlibs.dough.blocks.ChunkPosition;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.block.Block;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class Purification {

    // Purification Requirement Base Values
    public static final int SLEEP_IN_BED = 250;
    public static final int PLACE_WATER = 500;
    public static final int FRIENDLY_PIGLINS = 750;
    public static final int FRIENDLY_HOGLINS = 900;
    public static final int ENDER_CAKE = 1500;

    // Purification Requirement Mob Swapping Values
    public static final int SWAP_MAGMA_CUBE = 500;
    public static final int SWAP_PIGLIN = 500;
    public static final int SWAP_BLAZE = 1_000;
    public static final int SWAP_ZOMBIFIED_PIGLIN = 1_000;
    public static final int SWAP_HOGLIN = 1_500;
    public static final int SWAP_PIGLIN_BRUTE = 2_000;
    public static final int SWAP_GHAST = 2_000;
    public static final int SWAP_WITHER_SKELETON = 2_000;

    // Random Mob Spawning
    public static final int SPAWN_SQUID = 600;
    public static final int SPAWN_SALMON = 600;
    public static final int SPAWN_COD = 600;
    public static final int SPAWN_PUFFER_FISH = 700;
    public static final int SPAWN_TROPICAL_FISH = 1000;
    public static final int SPAWN_AXOLOTL = 1000;
    public static final int WANDERING_TRADER = 1500;
    public static final int WANDERING_PIGLIN = 1500;

    // Regeneration
    public static final int REGEN_1 = 500;
    public static final int REGEN_2 = 1500;
    public static final int REGEN_3 = 2500;

    // Particles
    public static final int PARTICLES_1 = 1000;
    public static final int PARTICLES_2 = 2000;
    public static final int PARTICLES_3 = 3000;

    private static Purification instance;

    private final Map<BlockPosition, Integer> purifyingObjectValues = new HashMap<>();
    private final Map<ChunkPosition, Integer> chunkValues = new HashMap<>();

    public Purification() {
        Preconditions.checkArgument(instance == null, "Cannot create a new instance of Purification");
        instance = this;
        Bukkit.getScheduler().runTaskTimer(Netheopoiesis.getInstance(), this::collateChunkValues, 1, 100);
    }

    private void collateChunkValues() {
        chunkValues.clear();
        for (Map.Entry<BlockPosition, Integer> entry : purifyingObjectValues.entrySet()) {
            final BlockPosition blockPosition = entry.getKey();
            final ChunkPosition chunkPosition = new ChunkPosition(blockPosition.getChunk());
            final int currentValue = chunkValues.getOrDefault(chunkPosition, 0);
            final int newValue = currentValue + entry.getValue();
            chunkValues.put(chunkPosition, newValue);
        }
    }

    public Map<BlockPosition, Integer> getPurifyingObjectValues() {
        return purifyingObjectValues;
    }

    public Map<ChunkPosition, Integer> getChunkValues() {
        return chunkValues;
    }

    public static void addValue(@Nonnull Block block, int value) {
        final BlockPosition blockPosition = new BlockPosition(block);
        instance.getPurifyingObjectValues().put(blockPosition, value);
    }

    public static void removeValue(@Nonnull Block block) {
        final BlockPosition blockPosition = new BlockPosition(block);
        instance.getPurifyingObjectValues().remove(blockPosition);
    }

    public static int getValue(@Nonnull Block block) {
        return instance.getPurifyingObjectValues().getOrDefault(new BlockPosition(block), 0);
    }

    public static int getValue(@Nonnull Chunk chunk) {
        if (WorldUtils.inNether(chunk.getWorld())) {
            return instance.getChunkValues().getOrDefault(new ChunkPosition(chunk), 0);
        }
        return 0;
    }

    public static Purification getInstance() {
        return instance;
    }
}
