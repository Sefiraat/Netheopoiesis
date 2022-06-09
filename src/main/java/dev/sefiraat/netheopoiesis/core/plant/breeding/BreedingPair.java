package dev.sefiraat.netheopoiesis.core.plant.breeding;

import dev.sefiraat.netheopoiesis.slimefun.NpsItems;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.NetherSeed;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This Enum holds the possible breeding possibilities available in the addon
 */
public enum BreedingPair {

    SPINDLE(
        NpsItems.SPINDLE_SEED,
        NpsItems.PURIFICATION_SEED,
        NpsItems.PURIFICATION_SEED,
        0.1,
        0.2
    ),

    GRAINY(
        NpsItems.GRAINY_SEED,
        NpsItems.PURIFICATION_SEED,
        NpsItems.PURIFICATION_SEED,
        0.1,
        0.2
    ),

    STRINGY(
        NpsItems.STRINGY_SEED,
        NpsItems.PURIFICATION_SEED,
        NpsItems.PURIFICATION_SEED,
        0.1,
        0.2
    ),

    COBBLED(
        NpsItems.COBBLED_SEED,
        NpsItems.SPINDLE_SEED,
        NpsItems.GRAINY_SEED,
        0.1,
        0.2
    ),

    DUSTY(
        NpsItems.DUSTY_SEED,
        NpsItems.COBBLED_SEED,
        NpsItems.GRAINY_SEED,
        0.1,
        0.2
    ),

    SEASIDE(
        NpsItems.SEASIDE_SEED,
        NpsItems.SPINDLE_SEED,
        NpsItems.DUSTY_SEED,
        0.05,
        0.2
    ),

    MOLDABLE(
        NpsItems.MOLDABLE_SEED,
        NpsItems.SEASIDE_SEED,
        NpsItems.COBBLED_SEED,
        0.05,
        0.2
    ),

    WET(
        NpsItems.WET_SEED,
        NpsItems.SEASIDE_SEED,
        NpsItems.MOLDABLE_SEED,
        0.1,
        0.1
    ),

    SPLINTERED(
        NpsItems.SPLINTERED_SEED,
        NpsItems.SPINDLE_SEED,
        NpsItems.STRINGY_SEED,
        0.05,
        0.2
    ),

    ROTTEN(
        NpsItems.ROTTEN_SEED,
        NpsItems.SPLINTERED_SEED,
        NpsItems.DUSTY_SEED,
        0.05,
        0.2
    ),

    SOUL(
        NpsItems.SOUL_SEED,
        NpsItems.ROTTEN_SEED,
        NpsItems.PURIFICATION_SEED,
        0.05,
        0.2
    ),

    METALLIC(
        NpsItems.METALLIC_SEED,
        NpsItems.SOUL_SEED,
        NpsItems.SOUL_SEED,
        0.05,
        0.2
    ),

    SHINY(
        NpsItems.SHINY_SEED,
        NpsItems.SOUL_SEED,
        NpsItems.SOUL_SEED,
        0.05,
        0.2
    ),

    SMOOTH(
        NpsItems.SMOOTH_SEED,
        NpsItems.SOUL_SEED,
        NpsItems.SOUL_SEED,
        0.05,
        0.2
    ),

    ENCHANTED(
        NpsItems.ENCHANTED_SEED,
        NpsItems.SHINY_SEED,
        NpsItems.SOUL_SEED,
        0.05,
        0.2
    ),

    COMBUSTIBLE(
        NpsItems.COMBUSTIBLE_SEED,
        NpsItems.SMOOTH_SEED,
        NpsItems.SPLINTERED_SEED,
        0.05,
        0.2
    ),

    PROTECTIVE(
        NpsItems.PROTECTIVE_SEED,
        NpsItems.METALLIC_SEED,
        NpsItems.SOUL_SEED,
        0.02,
        0.1
    ),

    PORKY(
        NpsItems.PORKY_SEED,
        NpsItems.SPINDLE_SEED,
        NpsItems.SOUL_SEED,
        0.07,
        0.1
    ),

    VALUABLE(
        NpsItems.VALUABLE_SEED,
        NpsItems.SHINY_SEED,
        NpsItems.ENCHANTED_SEED,
        0.05,
        0.2
    ),

    PERFECTION(
        NpsItems.PERFECTION_SEED,
        NpsItems.SHINY_SEED,
        NpsItems.VALUABLE_SEED,
        0.05,
        0.2
    ),

    RAINBOW(
        NpsItems.RAINBOW_SEED,
        NpsItems.SPIRIT_SEED,
        NpsItems.SPIRIT_SEED,
        0.05,
        0.2
    ),

    GLOWING(
        NpsItems.GLOWING_SEED,
        NpsItems.SPIRIT_SEED,
        NpsItems.SPIRIT_SEED,
        0.05,
        0.2
    ),

    ETHEREAL(
        NpsItems.ETHEREAL_SEED,
        NpsItems.SPIRIT_SEED,
        NpsItems.SPIRIT_SEED,
        0.1,
        0.1
    ),

    IGNITED(
        NpsItems.IGNITED_SEED,
        NpsItems.ETHEREAL_SEED,
        NpsItems.COMBUSTIBLE_SEED,
        0.2,
        0.25
    ),

    BARTERED(
        NpsItems.BARTERED_SEED,
        NpsItems.IGNITED_SEED,
        NpsItems.PORKY_SEED,
        0.2,
        0.25
    ),

    PRISMATIC(
        NpsItems.PRISMATIC_SEED,
        NpsItems.RAINBOW_SEED,
        NpsItems.SPLINTERED_SEED,
        0.1,
        0.15
    ),

    POROUS(
        NpsItems.POROUS_SEED,
        NpsItems.PRISMATIC_SEED,
        NpsItems.SEASIDE_SEED,
        0.05,
        0.05
    ),

    LEARNED(
        NpsItems.LEARNED_SEED,
        NpsItems.ETHEREAL_SEED,
        NpsItems.ENCHANTED_SEED,
        0.1,
        0.15
    ),

    OAKENDRAN(
        NpsItems.OAKENDRAN_SEED,
        NpsItems.ETHEREAL_SEED,
        NpsItems.SPINDLE_SEED,
        0.1,
        0.15
    ),

    SAINTLY(
        NpsItems.SAINTLY_SEED,
        NpsItems.OAKENDRAN_SEED,
        NpsItems.SPIRIT_SEED,
        0.1,
        0.15
    ),

    ADDON_BERRY(
        NpsItems.ADDON_BERRY_SEED,
        NpsItems.SAINTLY_SEED,
        NpsItems.SAINTLY_SEED,
        0.1,
        0.15
    ),

    CUTE(
        NpsItems.CUTE_SEED,
        NpsItems.SAINTLY_SEED,
        NpsItems.SAINTLY_SEED,
        0.1,
        0.15
    ),

    BUZZING(
        NpsItems.BUZZING_SEED,
        NpsItems.CUTE_SEED,
        NpsItems.SPINDLE_SEED,
        0.15,
        0.2
    ),

    TERRIFYING(
        NpsItems.TERRIFYING_SEED,
        NpsItems.SAINTLY_SEED,
        NpsItems.SAINTLY_SEED,
        0.10,
        0.3
    ),

    HATE_FILLED(
        NpsItems.HATE_FILLED_SEED,
        NpsItems.TERRIFYING_SEED,
        NpsItems.PROTECTIVE_SEED,
        0.2,
        0.05
    ),

    PULSING(
        NpsItems.PULSING_SEED,
        NpsItems.HATE_FILLED_SEED,
        NpsItems.GLOWING_SEED,
        0.15,
        0.2
    ),

    GATEWAY(
        NpsItems.GATEWAY_SEED,
        NpsItems.PULSING_SEED,
        NpsItems.BARTERED_SEED,
        0.15,
        0.2
    ),

    EDEN(
        NpsItems.EDEN_SEED,
        NpsItems.GATEWAY_SEED,
        NpsItems.PERFECTION_SEED,
        0.15,
        0.2
    ),

    JUNGLE(
        NpsItems.JUNGLE_SEED,
        NpsItems.EDEN_SEED,
        NpsItems.EDEN_SEED,
        0.05,
        0.05
    ),

    BEACH(
        NpsItems.BEACH_SEED,
        NpsItems.EDEN_SEED,
        NpsItems.EDEN_SEED,
        0.05,
        0.05
    ),

    DESERT(
        NpsItems.DESERT_SEED,
        NpsItems.EDEN_SEED,
        NpsItems.EDEN_SEED,
        0.05,
        0.05
    ),

    SNOW(
        NpsItems.SNOW_SEED,
        NpsItems.EDEN_SEED,
        NpsItems.EDEN_SEED,
        0.05,
        0.05
    ),

    STONE(
        NpsItems.STONEY_SEED,
        NpsItems.EDEN_SEED,
        NpsItems.EDEN_SEED,
        0.05,
        0.05
    ),

    SWAMP(
        NpsItems.SWAMP_SEED,
        NpsItems.EDEN_SEED,
        NpsItems.EDEN_SEED,
        0.05,
        0.05
    ),

    BLACK_AND_WHITE(
        NpsItems.BLACK_AND_WHITE_SEED,
        NpsItems.JUNGLE_SEED,
        NpsItems.PORKY_SEED,
        0.01,
        0.05
    ),

    PARROT(
        NpsItems.PARROT_SEED,
        NpsItems.JUNGLE_SEED,
        NpsItems.RAINBOW_SEED,
        0.09,
        0.15
    ),

    WILD(
        NpsItems.WILD_SEED,
        NpsItems.JUNGLE_SEED,
        NpsItems.CUTE_SEED,
        0.05,
        0.15
    ),

    SHELLED(
        NpsItems.SHELLED_SEED,
        NpsItems.BEACH_SEED,
        NpsItems.PROTECTIVE_SEED,
        0.05,
        0.15
    ),

    TREASURED(
        NpsItems.TREASURED_SEED,
        NpsItems.BEACH_SEED,
        NpsItems.SHINY_SEED,
        0.10,
        0.5
    );

    @Nonnull
    private static final BreedingPair[] CACHED_VALUES = values();

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
    BreedingPair(NetherSeed childPlant,
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
     * @return The {@link BreedResultType} of the breed attampt
     */
    public BreedResultType testBreed(@Nonnull NetherSeed seed1, @Nonnull NetherSeed seed2) {
        if (isBreedPossible(seed1, seed2)) {
            final double chance = ThreadLocalRandom.current().nextDouble();
            if (chance <= getBreedChance()) {
                return BreedResultType.SUCCESS;
            } else if (chance <= getSpreadBreedChance()) {
                return BreedResultType.SPREAD;
            }
        } else {
            return BreedResultType.NOT_PAIR;
        }
        return BreedResultType.FAIL;
    }

    public double getBreedChance() {
        return breedChance;
    }

    public double getSpreadBreedChance() {
        return spreadChance;
    }

    @Nonnull
    public static BreedResult getBreedResult(@Nonnull NetherSeed seed1, @Nonnull NetherSeed seed2) {
        int matches = 0;
        for (BreedingPair pair : CACHED_VALUES) {
            final BreedResultType result = pair.testBreed(seed1, seed2);
            if (result != BreedResultType.NOT_PAIR) {
                if (result != BreedResultType.FAIL) {
                    return new BreedResult(pair, result);
                } else {
                    matches++;
                }
            }
        }
        return new BreedResult(CACHED_VALUES[0], matches == 0 ? BreedResultType.NO_PAIRS : BreedResultType.FAIL);
    }

    @Nonnull
    public static BreedingPair[] getCachedValues() {
        return CACHED_VALUES;
    }
}
