package dev.sefiraat.netheopoiesis.core.plant.breeding;

import javax.annotation.Nonnull;

public class BreedResult {

    private final BreedingPairs matchedPair;
    private final BreedResultType resultType;

    /**
     * This class represents the result of a breeding attempt including the matched {@link BreedingPairs}
     *
     * @param matchedPair The matching {@link BreedingPairs} found when testing the possible breeds
     * @param resultType  The {@link BreedResultType} denoting the success type and/or failure
     */
    public BreedResult(@Nonnull BreedingPairs matchedPair, @Nonnull BreedResultType resultType) {
        this.matchedPair = matchedPair;
        this.resultType = resultType;
    }

    public BreedingPairs getMatchedPair() {
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
