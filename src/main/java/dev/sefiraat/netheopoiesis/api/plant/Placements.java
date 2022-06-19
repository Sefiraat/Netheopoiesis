package dev.sefiraat.netheopoiesis.api.plant;

import dev.sefiraat.netheopoiesis.api.items.NetherSeed;
import dev.sefiraat.netheopoiesis.api.plant.Growth;
import dev.sefiraat.netheopoiesis.implementation.Stacks;

import java.util.Collections;
import java.util.Set;

/**
 * This class is used to contain the most common combinations of possible placements for plants
 *
 * @see Growth
 * @see NetherSeed
 * TODO Replace with a crux tier system (how to deal with fringes?)
 */
public final class Placements {

    private Placements() {
        throw new IllegalStateException("Utility class");
    }

    public static final Set<String> ALL = Set.of(
        Stacks.BASIC_PURIFIED_NETHERRACK.getItemId(),
        Stacks.PURIFIED_NETHERRACK.getItemId(),
        Stacks.VORACIOUS_DIRT.getItemId(),
        Stacks.NETHER_DIRT.getItemId(),
        Stacks.NETHER_GRASS.getItemId(),
        Stacks.JUNGLE_CRUX.getItemId(),
        Stacks.BEACH_CRUX.getItemId(),
        Stacks.DESERT_CRUX.getItemId(),
        Stacks.SNOW_CRUX.getItemId(),
        Stacks.STONEY_CRUX.getItemId(),
        Stacks.SWAMP_CRUX.getItemId()
    );

    public static final Set<String> PURIFIED_AND_UP = Set.of(
        Stacks.PURIFIED_NETHERRACK.getItemId(),
        Stacks.VORACIOUS_DIRT.getItemId(),
        Stacks.NETHER_DIRT.getItemId(),
        Stacks.NETHER_GRASS.getItemId(),
        Stacks.JUNGLE_CRUX.getItemId(),
        Stacks.BEACH_CRUX.getItemId(),
        Stacks.DESERT_CRUX.getItemId(),
        Stacks.SNOW_CRUX.getItemId(),
        Stacks.STONEY_CRUX.getItemId(),
        Stacks.SWAMP_CRUX.getItemId()
    );

    public static final Set<String> VORACIOUS_AND_UP = Set.of(
        Stacks.VORACIOUS_DIRT.getItemId(),
        Stacks.NETHER_DIRT.getItemId(),
        Stacks.NETHER_GRASS.getItemId(),
        Stacks.JUNGLE_CRUX.getItemId(),
        Stacks.BEACH_CRUX.getItemId(),
        Stacks.DESERT_CRUX.getItemId(),
        Stacks.SNOW_CRUX.getItemId(),
        Stacks.STONEY_CRUX.getItemId(),
        Stacks.SWAMP_CRUX.getItemId()
    );

    public static final Set<String> NETHER_DIRT_AND_UP = Set.of(
        Stacks.NETHER_DIRT.getItemId(),
        Stacks.NETHER_GRASS.getItemId(),
        Stacks.JUNGLE_CRUX.getItemId(),
        Stacks.BEACH_CRUX.getItemId(),
        Stacks.DESERT_CRUX.getItemId(),
        Stacks.SNOW_CRUX.getItemId(),
        Stacks.STONEY_CRUX.getItemId(),
        Stacks.SWAMP_CRUX.getItemId()
    );

    public static final Set<String> NETHER_GRASS_AND_UP = Set.of(
        Stacks.NETHER_GRASS.getItemId(),
        Stacks.JUNGLE_CRUX.getItemId(),
        Stacks.BEACH_CRUX.getItemId(),
        Stacks.DESERT_CRUX.getItemId(),
        Stacks.SNOW_CRUX.getItemId(),
        Stacks.STONEY_CRUX.getItemId(),
        Stacks.SWAMP_CRUX.getItemId()
    );

    public static final Set<String> JUNGLE_FRINGE = Set.of(
        Stacks.NETHER_GRASS.getItemId(),
        Stacks.JUNGLE_CRUX.getItemId()
    );

    public static final Set<String> JUNGLE_BIOME = Set.of(
        Stacks.JUNGLE_CRUX.getItemId()
    );

    public static final Set<String> BEACH_FRINGE = Set.of(
        Stacks.NETHER_GRASS.getItemId(),
        Stacks.BEACH_CRUX.getItemId()
    );

    public static final Set<String> BEACH_BIOME = Set.of(
        Stacks.BEACH_CRUX.getItemId()
    );

    public static final Set<String> DESERT_FRINGE = Set.of(
        Stacks.NETHER_GRASS.getItemId(),
        Stacks.DESERT_CRUX.getItemId()
    );

    public static final Set<String> DESERT_BIOME = Set.of(
        Stacks.DESERT_CRUX.getItemId()
    );

    public static final Set<String> SNOW_FRINGE = Set.of(
        Stacks.NETHER_GRASS.getItemId(),
        Stacks.SNOW_CRUX.getItemId()
    );

    public static final Set<String> SNOW_BIOME = Set.of(
        Stacks.SNOW_CRUX.getItemId()
    );

    public static final Set<String> STONEY_FRINGE = Set.of(
        Stacks.NETHER_GRASS.getItemId(),
        Stacks.STONEY_CRUX.getItemId()
    );

    public static final Set<String> STONEY_BIOME = Set.of(
        Stacks.STONEY_CRUX.getItemId()
    );

    public static final Set<String> SWAMP_FRINGE = Set.of(
        Stacks.NETHER_GRASS.getItemId(),
        Stacks.SWAMP_CRUX.getItemId()
    );

    public static final Set<String> SWAMP_BIOME = Set.of(
        Stacks.SWAMP_CRUX.getItemId()
    );

    public static final Set<String> NULL = Collections.emptySet();
}
