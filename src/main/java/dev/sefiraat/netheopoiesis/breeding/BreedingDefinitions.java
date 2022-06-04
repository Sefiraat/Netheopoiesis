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

    private static final BreedingPair SPINDLE_PAIR = new BreedingPair(
        NpsSlimefunItems.SPINDLE_SEED,
        NpsSlimefunItems.PURIFICATION_SEED,
        NpsSlimefunItems.PURIFICATION_SEED,
        0.1,
        0.2
    );

    private static final Set<BreedingPair> BREEDING_PAIRS = Set.of(
        SPINDLE_PAIR
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
