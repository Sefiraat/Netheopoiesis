package dev.sefiraat.netheopoiesis.core.plant;

import dev.sefiraat.netheopoiesis.slimefun.NpsStacks;

import java.util.Collections;
import java.util.Set;

/**
 * This class is used to contain the most common combinations of possible placements for plants
 *
 * @see GrowthDescription
 * @see dev.sefiraat.netheopoiesis.slimefun.flora.seeds.NetherSeed
 * TODO Replace with a crux tier system (how to deal with fringes?)
 */
public final class Placements {

    private Placements() {
        throw new IllegalStateException("Utility class");
    }

    public static final Set<String> ALL = Set.of(
        NpsStacks.BASIC_PURIFIED_NETHERRACK.getItemId(),
        NpsStacks.PURIFIED_NETHERRACK.getItemId(),
        NpsStacks.VORACIOUS_DIRT.getItemId(),
        NpsStacks.NETHER_DIRT.getItemId(),
        NpsStacks.NETHER_GRASS.getItemId(),
        NpsStacks.JUNGLE_CRUX.getItemId(),
        NpsStacks.BEACH_CRUX.getItemId(),
        NpsStacks.DESERT_CRUX.getItemId(),
        NpsStacks.SNOW_CRUX.getItemId(),
        NpsStacks.STONEY_CRUX.getItemId(),
        NpsStacks.SWAMP_CRUX.getItemId()
    );

    public static final Set<String> PURIFIED_AND_UP = Set.of(
        NpsStacks.PURIFIED_NETHERRACK.getItemId(),
        NpsStacks.VORACIOUS_DIRT.getItemId(),
        NpsStacks.NETHER_DIRT.getItemId(),
        NpsStacks.NETHER_GRASS.getItemId(),
        NpsStacks.JUNGLE_CRUX.getItemId(),
        NpsStacks.BEACH_CRUX.getItemId(),
        NpsStacks.DESERT_CRUX.getItemId(),
        NpsStacks.SNOW_CRUX.getItemId(),
        NpsStacks.STONEY_CRUX.getItemId(),
        NpsStacks.SWAMP_CRUX.getItemId()
    );

    public static final Set<String> VORACIOUS_AND_UP = Set.of(
        NpsStacks.VORACIOUS_DIRT.getItemId(),
        NpsStacks.NETHER_DIRT.getItemId(),
        NpsStacks.NETHER_GRASS.getItemId(),
        NpsStacks.JUNGLE_CRUX.getItemId(),
        NpsStacks.BEACH_CRUX.getItemId(),
        NpsStacks.DESERT_CRUX.getItemId(),
        NpsStacks.SNOW_CRUX.getItemId(),
        NpsStacks.STONEY_CRUX.getItemId(),
        NpsStacks.SWAMP_CRUX.getItemId()
    );

    public static final Set<String> NETHER_DIRT_AND_UP = Set.of(
        NpsStacks.NETHER_DIRT.getItemId(),
        NpsStacks.NETHER_GRASS.getItemId(),
        NpsStacks.JUNGLE_CRUX.getItemId(),
        NpsStacks.BEACH_CRUX.getItemId(),
        NpsStacks.DESERT_CRUX.getItemId(),
        NpsStacks.SNOW_CRUX.getItemId(),
        NpsStacks.STONEY_CRUX.getItemId(),
        NpsStacks.SWAMP_CRUX.getItemId()
    );

    public static final Set<String> NETHER_GRASS_AND_UP = Set.of(
        NpsStacks.NETHER_GRASS.getItemId(),
        NpsStacks.JUNGLE_CRUX.getItemId(),
        NpsStacks.BEACH_CRUX.getItemId(),
        NpsStacks.DESERT_CRUX.getItemId(),
        NpsStacks.SNOW_CRUX.getItemId(),
        NpsStacks.STONEY_CRUX.getItemId(),
        NpsStacks.SWAMP_CRUX.getItemId()
    );

    public static final Set<String> JUNGLE_FRINGE = Set.of(
        NpsStacks.NETHER_GRASS.getItemId(),
        NpsStacks.JUNGLE_CRUX.getItemId()
    );

    public static final Set<String> JUNGLE_BIOME = Set.of(
        NpsStacks.JUNGLE_CRUX.getItemId()
    );

    public static final Set<String> BEACH_FRINGE = Set.of(
        NpsStacks.NETHER_GRASS.getItemId(),
        NpsStacks.BEACH_CRUX.getItemId()
    );

    public static final Set<String> BEACH_BIOME = Set.of(
        NpsStacks.BEACH_CRUX.getItemId()
    );

    public static final Set<String> DESERT_FRINGE = Set.of(
        NpsStacks.NETHER_GRASS.getItemId(),
        NpsStacks.DESERT_CRUX.getItemId()
    );

    public static final Set<String> DESERT_BIOME = Set.of(
        NpsStacks.DESERT_CRUX.getItemId()
    );

    public static final Set<String> SNOW_FRINGE = Set.of(
        NpsStacks.NETHER_GRASS.getItemId(),
        NpsStacks.SNOW_CRUX.getItemId()
    );

    public static final Set<String> SNOW_BIOME = Set.of(
        NpsStacks.SNOW_CRUX.getItemId()
    );

    public static final Set<String> STONEY_FRINGE = Set.of(
        NpsStacks.NETHER_GRASS.getItemId(),
        NpsStacks.STONEY_CRUX.getItemId()
    );

    public static final Set<String> STONEY_BIOME = Set.of(
        NpsStacks.STONEY_CRUX.getItemId()
    );

    public static final Set<String> SWAMP_FRINGE = Set.of(
        NpsStacks.NETHER_GRASS.getItemId(),
        NpsStacks.SWAMP_CRUX.getItemId()
    );

    public static final Set<String> SWAMP_BIOME = Set.of(
        NpsStacks.SWAMP_CRUX.getItemId()
    );

    public static final Set<String> NULL = Collections.emptySet();
}
