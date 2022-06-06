package dev.sefiraat.netheopoiesis;

import io.github.bakedlibs.dough.blocks.BlockPosition;
import io.github.bakedlibs.dough.blocks.ChunkPosition;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.block.Block;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class PurificationMemory {

    private static PurificationMemory instance;

    private final Map<BlockPosition, Integer> purifyingObjectValues = new HashMap<>();
    private final Map<ChunkPosition, Integer> chunkValues = new HashMap<>();

    public PurificationMemory() {
        Validate.isTrue(instance == null, "Cannot create a new instance of PurificationMemory");
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
        return instance.getChunkValues().getOrDefault(new ChunkPosition(chunk), 0);
    }

    public static PurificationMemory getInstance() {
        return instance;
    }
}
