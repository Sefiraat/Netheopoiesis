package dev.sefiraat.netheopoiesis.core.plant;

import dev.sefiraat.netheopoiesis.slimefun.NpsStacks;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.Set;

public class Placement {

    public static final Placement ALL = new Placement(
        Set.of(
            NpsStacks.BASIC_PURIFIED_NETHERRACK.getItemId(),
            NpsStacks.PURIFIED_NETHERRACK.getItemId(),
            NpsStacks.VORACIOUS_DIRT.getItemId(),
            NpsStacks.NETHER_DIRT.getItemId(),
            NpsStacks.NETHER_GRASS.getItemId()
        )
    );

    public static final Placement PURIFIED_AND_UP = new Placement(
        Set.of(
            NpsStacks.PURIFIED_NETHERRACK.getItemId(),
            NpsStacks.VORACIOUS_DIRT.getItemId(),
            NpsStacks.NETHER_DIRT.getItemId(),
            NpsStacks.NETHER_GRASS.getItemId()
        )
    );

    public static final Placement VORACIOUS_AND_UP = new Placement(
        Set.of(
            NpsStacks.VORACIOUS_DIRT.getItemId(),
            NpsStacks.NETHER_DIRT.getItemId(),
            NpsStacks.NETHER_GRASS.getItemId()
        )
    );

    public static final Placement NETHER_DIRT_AND_UP = new Placement(
        Set.of(
            NpsStacks.NETHER_DIRT.getItemId(),
            NpsStacks.NETHER_GRASS.getItemId()
        )
    );

    public static final Placement NETHER_GRASS_AND_UP = new Placement(
        Set.of(
            NpsStacks.NETHER_GRASS.getItemId()
        )
    );

    public static final Placement NULL = new Placement(
        Collections.emptySet()
    );

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
