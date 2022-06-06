package dev.sefiraat.netheopoiesis.core.plant.breeding;

import dev.sefiraat.netheopoiesis.slimefun.NpsItems;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.NetherSeed;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * This Enum holds the possible breeding possibilities available in the addon
 */
public enum BreedingDefinitions {

    SPINDLE(new BreedingPair(
        NpsItems.SPINDLE_SEED,
        NpsItems.PURIFICATION_SEED,
        NpsItems.PURIFICATION_SEED,
        0.1,
        0.2
    )),

    GRAINY(new BreedingPair(
        NpsItems.GRAINY_SEED,
        NpsItems.PURIFICATION_SEED,
        NpsItems.PURIFICATION_SEED,
        0.1,
        0.2
    )),

    STRINGY(new BreedingPair(
        NpsItems.STRINGY_SEED,
        NpsItems.PURIFICATION_SEED,
        NpsItems.PURIFICATION_SEED,
        0.1,
        0.2
    )),

    STONEY(new BreedingPair(
        NpsItems.STONEY_SEED,
        NpsItems.SPINDLE_SEED,
        NpsItems.GRAINY_SEED,
        0.1,
        0.2
    )),

    DUSTY(new BreedingPair(
        NpsItems.DUSTY_SEED,
        NpsItems.STONEY_SEED,
        NpsItems.GRAINY_SEED,
        0.1,
        0.2
    )),

    SEASIDE(new BreedingPair(
        NpsItems.SEASIDE_SEED,
        NpsItems.SPINDLE_SEED,
        NpsItems.DUSTY_SEED,
        0.05,
        0.2
    )),

    MOLDABLE(new BreedingPair(
        NpsItems.MOLDABLE_SEED,
        NpsItems.SEASIDE_SEED,
        NpsItems.STONEY_SEED,
        0.05,
        0.2
    )),

    SPLINTERED(new BreedingPair(
        NpsItems.SPLINTERED_SEED,
        NpsItems.SPINDLE_SEED,
        NpsItems.STRINGY_SEED,
        0.05,
        0.2
    )),

    ROTTEN(new BreedingPair(
        NpsItems.ROTTEN_SEED,
        NpsItems.SPLINTERED_SEED,
        NpsItems.DUSTY_SEED,
        0.05,
        0.2
    )),

    SOUL(new BreedingPair(
        NpsItems.SOUL_SEED,
        NpsItems.ROTTEN_SEED,
        NpsItems.PURIFICATION_SEED,
        0.05,
        0.2
    )),

    METALLIC(new BreedingPair(
        NpsItems.METALLIC_SEED,
        NpsItems.SOUL_SEED,
        NpsItems.SOUL_SEED,
        0.05,
        0.2
    )),

    SHINY(new BreedingPair(
        NpsItems.SHINY_SEED,
        NpsItems.SOUL_SEED,
        NpsItems.SOUL_SEED,
        0.05,
        0.2
    )),

    SMOOTH(new BreedingPair(
        NpsItems.SMOOTH_SEED,
        NpsItems.SOUL_SEED,
        NpsItems.SOUL_SEED,
        0.05,
        0.2
    )),

    ENCHANTED(new BreedingPair(
        NpsItems.ENCHANTED_SEED,
        NpsItems.SHINY_SEED,
        NpsItems.SOUL_SEED,
        0.05,
        0.2
    )),

    COMBUSTIBLE(new BreedingPair(
        NpsItems.COMBUSTIBLE_SEED,
        NpsItems.SMOOTH_SEED,
        NpsItems.SPLINTERED_SEED,
        0.05,
        0.2
    )),

    PROTECTIVE(new BreedingPair(
        NpsItems.PROTECTIVE_SEED,
        NpsItems.METALLIC_SEED,
        NpsItems.SOUL_SEED,
        0.02,
        0.1
    )),

    VALUABLE(new BreedingPair(
        NpsItems.VALUABLE_SEED,
        NpsItems.SHINY_SEED,
        NpsItems.ENCHANTED_SEED,
        0.05,
        0.2
    )),

    PERFECTION(new BreedingPair(
        NpsItems.PERFECTION_SEED,
        NpsItems.SHINY_SEED,
        NpsItems.VALUABLE_SEED,
        0.05,
        0.2
    ));

    @Nonnull
    private static final BreedingDefinitions[] CACHED_VALUES = values();
    @Nonnull
    private final BreedingPair breedingPair;

    BreedingDefinitions(@Nonnull BreedingPair breedingPair) {
        this.breedingPair = breedingPair;
    }

    @Nonnull
    public BreedingPair getBreedingPair() {
        return breedingPair;
    }

    @Nullable
    public static BreedResult getBreedResult(@Nonnull NetherSeed seed1, @Nonnull NetherSeed seed2) {
        for (BreedingDefinitions definition : CACHED_VALUES) {
            final BreedResult.BreedResultType result = definition.getBreedingPair().testBreed(seed1, seed2);
            if (result != BreedResult.BreedResultType.BREED_FAIL) {
                return new BreedResult(definition.getBreedingPair(), result);
            }
        }
        return null;
    }
}
