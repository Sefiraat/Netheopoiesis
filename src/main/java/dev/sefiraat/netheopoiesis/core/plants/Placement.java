package dev.sefiraat.netheopoiesis.core.plants;

import dev.sefiraat.netheopoiesis.slimefun.NpsSlimefunItems;
import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherSeedCrux;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.Set;

public class Placement {

    public static Placement ALL = new Placement(
        Set.of(
            NpsSlimefunItems.BASIC_PURIFIED_NETHERRACK,
            NpsSlimefunItems.PURIFIED_NETHERRACK,
            NpsSlimefunItems.VORACIOUS_DIRT,
            NpsSlimefunItems.NETHER_DIRT,
            NpsSlimefunItems.NETHER_GRASS
        )
    );

    public static Placement PURIFIED_AND_UP = new Placement(
        Set.of(
            NpsSlimefunItems.PURIFIED_NETHERRACK,
            NpsSlimefunItems.VORACIOUS_DIRT,
            NpsSlimefunItems.NETHER_DIRT,
            NpsSlimefunItems.NETHER_GRASS
        )
    );

    public static Placement VORACIOUS_AND_UP = new Placement(
        Set.of(
            NpsSlimefunItems.VORACIOUS_DIRT,
            NpsSlimefunItems.NETHER_DIRT,
            NpsSlimefunItems.NETHER_GRASS
        )
    );

    public static Placement NETHER_DIRT_AND_UP = new Placement(
        Set.of(
            NpsSlimefunItems.NETHER_DIRT,
            NpsSlimefunItems.NETHER_GRASS
        )
    );

    public static Placement NETHER_GRASS_AND_UP = new Placement(
        Set.of(
            NpsSlimefunItems.NETHER_GRASS
        )
    );

    public static Placement NULL = new Placement(
        Collections.emptySet()
    );

    private final Set<NetherSeedCrux> validPlacements;

    public Placement(@Nonnull Set<NetherSeedCrux> placements) {
        this.validPlacements = placements;
    }

    public Set<NetherSeedCrux> getValidPlacements() {
        return validPlacements;
    }

    public boolean contains(@Nonnull NetherSeedCrux crux) {
        return this.validPlacements.contains(crux);
    }
}
