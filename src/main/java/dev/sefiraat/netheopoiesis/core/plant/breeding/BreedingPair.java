package dev.sefiraat.netheopoiesis.core.plant.breeding;

import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.NetherSeed;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.ThreadLocalRandom;

public class BreedingPair {

    private final NetherSeed childPlant;
    private final NetherSeed motherSeed;
    private final NetherSeed fatherSeed;
    private final double breedChance;
    private final double spreadChance;

    /**
     * This class defines a possible breeding pair, the chance of success or spread
     *
     * @param childPlant   The {@link NetherSeed} that will grow as a result of a successful breed
     * @param motherSeed   The {@link NetherSeed} representing one of the parents (the one initiating the breed)
     * @param fatherSeed   The {@link NetherSeed} representing the other parent
     * @param breedChance  The chance for the breed to be successful (spawning a child)
     * @param spreadChance The chance that, should a true breed fail, a spread can occur (spawning a copy of the mother)
     */
    @ParametersAreNonnullByDefault
    public BreedingPair(NetherSeed childPlant,
                        NetherSeed motherSeed,
                        NetherSeed fatherSeed,
                        double breedChance,
                        double spreadChance
    ) {
        this.childPlant = childPlant;
        this.motherSeed = motherSeed;
        this.fatherSeed = fatherSeed;
        this.breedChance = breedChance;
        this.spreadChance = spreadChance;
    }

    public NetherSeed getChildPlant() {
        return childPlant;
    }

    public NetherSeed getMotherSeed() {
        return motherSeed;
    }

    public NetherSeed getFatherSeed() {
        return fatherSeed;
    }

    /**
     * Checks if the two given seeds can breed regardless of chance.
     * No need to call this if you will also be rolling
     *
     * @param seed1 The first {@link NetherSeed} to check for breeding
     * @param seed2 The partner {@link NetherSeed} to check against the first.
     * @return True if the plants can breed
     */
    public boolean isBreedPossible(@Nonnull NetherSeed seed1, @Nonnull NetherSeed seed2) {
        final String id1 = seed1.getId();
        final String id2 = seed2.getId();

        if (id1.equals(motherSeed.getId())) {
            return id2.equals(fatherSeed.getId());
        } else if (id1.equals(fatherSeed.getId())) {
            return id2.equals(motherSeed.getId());
        }
        return false;
    }

    /**
     * Checks if the two given seeds can breed and, if so, tests against the
     * chances to get the breed result
     *
     * @param seed1 The first {@link NetherSeed} to check for breeding
     * @param seed2 The partner {@link NetherSeed} to check against the first.
     * @return The {@link BreedResult.BreedResultType} of the breed attampt
     */
    public BreedResult.BreedResultType testBreed(@Nonnull NetherSeed seed1, @Nonnull NetherSeed seed2) {
        if (isBreedPossible(seed1, seed2)) {
            final double chance = ThreadLocalRandom.current().nextDouble();
            if (chance <= getBreedChance()) {
                return BreedResult.BreedResultType.BREED_SUCCESS;
            } else if (chance <= getSpreadBreedChance()) {
                return BreedResult.BreedResultType.BREED_SPREAD;
            }
        }
        return BreedResult.BreedResultType.BREED_FAIL;
    }

    public double getBreedChance() {
        return breedChance;
    }

    public double getSpreadBreedChance() {
        return spreadChance;
    }
}
