package dev.sefiraat.netheopoiesis.core.plants;

import dev.sefiraat.netheopoiesis.slimefun.NpsSlimefunItems;
import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherSeedCrux;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.Set;

public class Placement {

    public static final Placement ALL = new Placement(
        Set.of(
            NpsSlimefunItems.BASIC_PURIFIED_NETHERRACK,
            NpsSlimefunItems.PURIFIED_NETHERRACK,
            NpsSlimefunItems.VORACIOUS_DIRT,
            NpsSlimefunItems.NETHER_DIRT,
            NpsSlimefunItems.NETHER_GRASS
        )
    );

    public static final Placement PURIFIED_AND_UP = new Placement(
        Set.of(
            NpsSlimefunItems.PURIFIED_NETHERRACK,
            NpsSlimefunItems.VORACIOUS_DIRT,
            NpsSlimefunItems.NETHER_DIRT,
            NpsSlimefunItems.NETHER_GRASS
        )
    );

    public static final Placement VORACIOUS_AND_UP = new Placement(
        Set.of(
            NpsSlimefunItems.VORACIOUS_DIRT,
            NpsSlimefunItems.NETHER_DIRT,
            NpsSlimefunItems.NETHER_GRASS
        )
    );

    public static final Placement NETHER_DIRT_AND_UP = new Placement(
        Set.of(
            NpsSlimefunItems.NETHER_DIRT,
            NpsSlimefunItems.NETHER_GRASS
        )
    );

    public static final Placement NETHER_GRASS_AND_UP = new Placement(
        Set.of(
            NpsSlimefunItems.NETHER_GRASS
        )
    );

    public static final Placement NULL = new Placement(
        Collections.emptySet()
    );

    @Nonnull
    private final Set<NetherSeedCrux> validPlacements;

    /**
     * This class defines what possible Crux' a seed can be placed on
     *
     * @param placements The {@link Set} of {@link NetherSeedCrux} that the seed can be placed on
     */
    public Placement(@Nonnull Set<NetherSeedCrux> placements) {
        this.validPlacements = placements;
    }

    @Nonnull
    public Set<NetherSeedCrux> getValidPlacements() {
        return validPlacements;
    }

    /**
     * Checks if the given Crux is contained within this placement
     *
     * @param crux The {@link NetherSeedCrux} to check
     * @return true if the crux is present
     */
    public boolean contains(@Nonnull NetherSeedCrux crux) {
        return this.validPlacements.contains(crux);
    }
}
