package dev.sefiraat.netheopoiesis.breeding;

import dev.sefiraat.netheopoiesis.slimefun.NpsSlimefunItems;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.NetherSeed;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

public final class BreedingDefinitions {

    private BreedingDefinitions() {
        throw new IllegalStateException("Utility class");
    }

    private static final BreedingPair SPINDLE = new BreedingPair(
        NpsSlimefunItems.SPINDLE_SEED,
        NpsSlimefunItems.PURIFICATION_SEED,
        NpsSlimefunItems.PURIFICATION_SEED,
        0.1,
        0.2
    );

    private static final BreedingPair GRAINY = new BreedingPair(
        NpsSlimefunItems.GRAINY_SEED,
        NpsSlimefunItems.PURIFICATION_SEED,
        NpsSlimefunItems.PURIFICATION_SEED,
        0.1,
        0.2
    );

    private static final BreedingPair STRINGY = new BreedingPair(
        NpsSlimefunItems.STRINGY_SEED,
        NpsSlimefunItems.PURIFICATION_SEED,
        NpsSlimefunItems.PURIFICATION_SEED,
        0.1,
        0.2
    );

    private static final BreedingPair STONEY = new BreedingPair(
        NpsSlimefunItems.STONEY_SEED,
        NpsSlimefunItems.SPINDLE_SEED,
        NpsSlimefunItems.GRAINY_SEED,
        0.1,
        0.2
    );

    private static final BreedingPair DUSTY = new BreedingPair(
        NpsSlimefunItems.DUSTY_SEED,
        NpsSlimefunItems.STONEY_SEED,
        NpsSlimefunItems.GRAINY_SEED,
        0.1,
        0.2
    );

    private static final BreedingPair SEASIDE = new BreedingPair(
        NpsSlimefunItems.SEASIDE_SEED,
        NpsSlimefunItems.SPINDLE_SEED,
        NpsSlimefunItems.DUSTY_SEED,
        0.05,
        0.2
    );

    private static final BreedingPair SPLINTERED = new BreedingPair(
        NpsSlimefunItems.SPLINTERED_SEED,
        NpsSlimefunItems.SPINDLE_SEED,
        NpsSlimefunItems.STRINGY_SEED,
        0.05,
        0.2
    );

    private static final BreedingPair ROTTEN = new BreedingPair(
        NpsSlimefunItems.ROTTEN_SEED,
        NpsSlimefunItems.SPLINTERED_SEED,
        NpsSlimefunItems.DUSTY_SEED,
        0.05,
        0.2
    );

    private static final BreedingPair SOUL = new BreedingPair(
        NpsSlimefunItems.SOUL_SEED,
        NpsSlimefunItems.ROTTEN_SEED,
        NpsSlimefunItems.PURIFICATION_SEED,
        0.05,
        0.2
    );

    private static final BreedingPair METALLIC = new BreedingPair(
        NpsSlimefunItems.METALLIC_SEED,
        NpsSlimefunItems.SOUL_SEED,
        NpsSlimefunItems.SOUL_SEED,
        0.05,
        0.2
    );

    private static final BreedingPair SHINY = new BreedingPair(
        NpsSlimefunItems.SHINY_SEED,
        NpsSlimefunItems.SOUL_SEED,
        NpsSlimefunItems.SOUL_SEED,
        0.05,
        0.2
    );

    private static final BreedingPair SMOOTH = new BreedingPair(
        NpsSlimefunItems.SMOOTH_SEED,
        NpsSlimefunItems.SOUL_SEED,
        NpsSlimefunItems.SOUL_SEED,
        0.05,
        0.2
    );

    private static final Set<BreedingPair> BREEDING_PAIRS = Set.of(
        SPINDLE,
        GRAINY,
        STRINGY,
        STONEY,
        DUSTY,
        SEASIDE,
        SPLINTERED,
        ROTTEN,
        SOUL,
        METALLIC,
        SHINY,
        SMOOTH
    );

    public static Set<BreedingPair> getBreedingPairs() {
        return BREEDING_PAIRS;
    }

    @Nullable
    public static BreedResult getBreedResult(@Nonnull NetherSeed seed1, @Nonnull NetherSeed seed2) {
        for (BreedingPair breedingPair : BREEDING_PAIRS) {
            final BreedResult.BreedResultType result = breedingPair.testBreed(seed1, seed2);
            if (result != BreedResult.BreedResultType.BREED_FAIL) {
                return new BreedResult(breedingPair, result);
            }
        }
        return null;
    }
}
