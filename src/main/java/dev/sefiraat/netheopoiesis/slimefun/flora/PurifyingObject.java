package dev.sefiraat.netheopoiesis.slimefun.flora;

/**
 * This interface is used to describe an object that adds a purification value to the Nether
 */
public interface PurifyingObject {
    /**
     * The amount that this object in the world will
     * @return The value that this item will increment the purification amount
     */
    int purificationValue();
}
