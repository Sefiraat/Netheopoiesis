package dev.sefiraat.netheopoiesis.core.plants.breeding;

import javax.annotation.Nonnull;

public class BreedResult {

    private final BreedingPair matchedPair;
    private final BreedResultType resultType;

    public BreedResult(@Nonnull BreedingPair matchedPair, @Nonnull BreedResultType resultType) {
        this.matchedPair = matchedPair;
        this.resultType = resultType;
    }

    public BreedingPair getMatchedPair() {
        return matchedPair;
    }

    public BreedResultType getResultType() {
        return resultType;
    }

    public enum BreedResultType {
        /**
         * Breed has failed, no changes
         */
        BREED_FAIL,
        /**
         * Breed triggered a spread of the original plant
         */
        BREED_SPREAD,
        /**
         * Breed successful resulting in child plant
         */
        BREED_SUCCESS;
    }
}
