package dev.sefiraat.netheopoiesis.core.purification;

import dev.sefiraat.netheopoiesis.PurificationMemory;
import org.bukkit.block.Block;

import javax.annotation.Nonnull;

/**
 * This interface is used to describe an object that adds a purification value to the Nether
 */
public interface PurifyingObject {
    /**
     * The amount that this object in the world will
     *
     * @return The value that this item will increment the purification amount
     */
    int getPurificationValue();

    /**
     * Adds the purification value to the {@link PurificationMemory} instance.
     */
    default void registerPurificationValue(@Nonnull Block block) {
        PurificationMemory.getInstance().addPurifyingValue(block, getPurificationValue());
    }

    /**
     * Removes the purification value to the {@link PurificationMemory} instance.
     */
    default void removePurificationRegistry(@Nonnull Block block) {
        PurificationMemory.getInstance().removePurifyingValue(block);
    }
}
