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
        Validate.isTrue(instance == null, "Cannot create a new instance of the PurificationMemory");
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

    public void addPurifyingValue(@Nonnull Block block, int value) {
        final BlockPosition blockPosition = new BlockPosition(block);
        this.purifyingObjectValues.put(blockPosition, value);
    }

    public int getPurificationValue(@Nonnull Block block) {
        return purifyingObjectValues.getOrDefault(new BlockPosition(block), 0);
    }

    public int getPurificationValue(@Nonnull Chunk chunk) {
        return chunkValues.getOrDefault(new ChunkPosition(chunk), 0);
    }

    public static PurificationMemory getInstance() {
        return instance;
    }
}
