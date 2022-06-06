package dev.sefiraat.netheopoiesis.core.plant;

import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherSeedCrux;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;

import javax.annotation.Nonnull;
import java.util.Set;

public class Placement {

    @Nonnull
    private final Set<String> validPlacements;

    /**
     * This class defines what possible Crux' id a seed can be placed on
     *
     * @param placements The {@link Set} of {@link String} ids that the seed can be placed on
     */
    public Placement(@Nonnull Set<String> placements) {
        this.validPlacements = placements;
    }

    @Nonnull
    public Set<String> getValidPlacements() {
        return validPlacements;
    }

    /**
     * Checks if the given Crux is contained within this placement
     *
     * @param cruxId The {@link String} to check
     * @return true if the crux' id is present
     */
    public boolean contains(@Nonnull String cruxId) {
        return this.validPlacements.contains(cruxId);
    }
}
