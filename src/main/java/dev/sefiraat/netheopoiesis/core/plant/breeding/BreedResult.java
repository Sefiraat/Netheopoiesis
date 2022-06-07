package dev.sefiraat.netheopoiesis.core.plant.breeding;

import javax.annotation.Nonnull;

public class BreedResult {

    @Nonnull
    private final BreedingPairs matchedPair;
    @Nonnull
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

    @Nonnull
    public BreedingPairs getMatchedPair() {
        return matchedPair;
    }

    @Nonnull
    public BreedResultType getResultType() {
        return resultType;
    }
}
