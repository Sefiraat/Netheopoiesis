package dev.sefiraat.netheopoiesis.core.plant;

import dev.sefiraat.netheopoiesis.slimefun.NpsSlimefunItemStacks;

import java.util.Collections;
import java.util.Set;

public final class Placements {

    private Placements() {
        throw new IllegalStateException("Utility class");
    }

    public static final Placement ALL = new Placement(
        Set.of(
            NpsSlimefunItemStacks.BASIC_PURIFIED_NETHERRACK.getItemId(),
            NpsSlimefunItemStacks.PURIFIED_NETHERRACK.getItemId(),
            NpsSlimefunItemStacks.VORACIOUS_DIRT.getItemId(),
            NpsSlimefunItemStacks.NETHER_DIRT.getItemId(),
            NpsSlimefunItemStacks.NETHER_GRASS.getItemId()
        )
    );

    public static final Placement PURIFIED_AND_UP = new Placement(
        Set.of(
            NpsSlimefunItemStacks.PURIFIED_NETHERRACK.getItemId(),
            NpsSlimefunItemStacks.VORACIOUS_DIRT.getItemId(),
            NpsSlimefunItemStacks.NETHER_DIRT.getItemId(),
            NpsSlimefunItemStacks.NETHER_GRASS.getItemId()
        )
    );

    public static final Placement VORACIOUS_AND_UP = new Placement(
        Set.of(
            NpsSlimefunItemStacks.VORACIOUS_DIRT.getItemId(),
            NpsSlimefunItemStacks.NETHER_DIRT.getItemId(),
            NpsSlimefunItemStacks.NETHER_GRASS.getItemId()
        )
    );

    public static final Placement NETHER_DIRT_AND_UP = new Placement(
        Set.of(
            NpsSlimefunItemStacks.NETHER_DIRT.getItemId(),
            NpsSlimefunItemStacks.NETHER_GRASS.getItemId()
        )
    );

    public static final Placement NETHER_GRASS_AND_UP = new Placement(
        Set.of(
            NpsSlimefunItemStacks.NETHER_GRASS.getItemId()
        )
    );

    public static final Placement NULL = new Placement(
        Collections.emptySet()
    );
}
