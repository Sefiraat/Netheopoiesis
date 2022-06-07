package dev.sefiraat.netheopoiesis.core.plant;

import dev.sefiraat.netheopoiesis.slimefun.NpsStacks;

import java.util.Collections;
import java.util.Set;

public final class Placements {

    private Placements() {
        throw new IllegalStateException("Utility class");
    }

    public static final Set<String> ALL = Set.of(
        NpsStacks.BASIC_PURIFIED_NETHERRACK.getItemId(),
        NpsStacks.PURIFIED_NETHERRACK.getItemId(),
        NpsStacks.VORACIOUS_DIRT.getItemId(),
        NpsStacks.NETHER_DIRT.getItemId(),
        NpsStacks.NETHER_GRASS.getItemId()
    );

    public static final Set<String> PURIFIED_AND_UP = Set.of(
        NpsStacks.PURIFIED_NETHERRACK.getItemId(),
        NpsStacks.VORACIOUS_DIRT.getItemId(),
        NpsStacks.NETHER_DIRT.getItemId(),
        NpsStacks.NETHER_GRASS.getItemId()
    );

    public static final Set<String> VORACIOUS_AND_UP = Set.of(
        NpsStacks.VORACIOUS_DIRT.getItemId(),
        NpsStacks.NETHER_DIRT.getItemId(),
        NpsStacks.NETHER_GRASS.getItemId()
    );

    public static final Set<String> NETHER_DIRT_AND_UP = Set.of(
        NpsStacks.NETHER_DIRT.getItemId(),
        NpsStacks.NETHER_GRASS.getItemId()
    );


    public static final Set<String> NETHER_GRASS_AND_UP = Set.of(
        NpsStacks.NETHER_GRASS.getItemId()
    );

    public static final Set<String> NULL = Collections.emptySet();
}
