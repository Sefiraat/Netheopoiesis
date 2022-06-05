package dev.sefiraat.netheopoiesis.core.plants.breeding;

import dev.sefiraat.netheopoiesis.slimefun.NpsSlimefunItems;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.NetherSeed;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum BreedingDefinitions {


    SPINDLE(new BreedingPair(
        NpsSlimefunItems.SPINDLE_SEED,
        NpsSlimefunItems.PURIFICATION_SEED,
        NpsSlimefunItems.PURIFICATION_SEED,
        0.1,
        0.2
    )),

    GRAINY(new BreedingPair(
        NpsSlimefunItems.GRAINY_SEED,
        NpsSlimefunItems.PURIFICATION_SEED,
        NpsSlimefunItems.PURIFICATION_SEED,
        0.1,
        0.2
    )),

    STRINGY(new BreedingPair(
        NpsSlimefunItems.STRINGY_SEED,
        NpsSlimefunItems.PURIFICATION_SEED,
        NpsSlimefunItems.PURIFICATION_SEED,
        0.1,
        0.2
    )),

    STONEY(new BreedingPair(
        NpsSlimefunItems.STONEY_SEED,
        NpsSlimefunItems.SPINDLE_SEED,
        NpsSlimefunItems.GRAINY_SEED,
        0.1,
        0.2
    )),

    DUSTY(new BreedingPair(
        NpsSlimefunItems.DUSTY_SEED,
        NpsSlimefunItems.STONEY_SEED,
        NpsSlimefunItems.GRAINY_SEED,
        0.1,
        0.2
    )),

    SEASIDE(new BreedingPair(
        NpsSlimefunItems.SEASIDE_SEED,
        NpsSlimefunItems.SPINDLE_SEED,
        NpsSlimefunItems.DUSTY_SEED,
        0.05,
        0.2
    )),

    MOLDABLE(new BreedingPair(
        NpsSlimefunItems.MOLDABLE_SEED,
        NpsSlimefunItems.SEASIDE_SEED,
        NpsSlimefunItems.STONEY_SEED,
        0.05,
        0.2
    )),

    SPLINTERED(new BreedingPair(
        NpsSlimefunItems.SPLINTERED_SEED,
        NpsSlimefunItems.SPINDLE_SEED,
        NpsSlimefunItems.STRINGY_SEED,
        0.05,
        0.2
    )),

    ROTTEN(new BreedingPair(
        NpsSlimefunItems.ROTTEN_SEED,
        NpsSlimefunItems.SPLINTERED_SEED,
        NpsSlimefunItems.DUSTY_SEED,
        0.05,
        0.2
    )),

    SOUL(new BreedingPair(
        NpsSlimefunItems.SOUL_SEED,
        NpsSlimefunItems.ROTTEN_SEED,
        NpsSlimefunItems.PURIFICATION_SEED,
        0.05,
        0.2
    )),

    METALLIC(new BreedingPair(
        NpsSlimefunItems.METALLIC_SEED,
        NpsSlimefunItems.SOUL_SEED,
        NpsSlimefunItems.SOUL_SEED,
        0.05,
        0.2
    )),

    SHINY(new BreedingPair(
        NpsSlimefunItems.SHINY_SEED,
        NpsSlimefunItems.SOUL_SEED,
        NpsSlimefunItems.SOUL_SEED,
        0.05,
        0.2
    )),

    SMOOTH(new BreedingPair(
        NpsSlimefunItems.SMOOTH_SEED,
        NpsSlimefunItems.SOUL_SEED,
        NpsSlimefunItems.SOUL_SEED,
        0.05,
        0.2
    )),

    ENCHANTED(new BreedingPair(
        NpsSlimefunItems.ENCHANTED_SEED,
        NpsSlimefunItems.SHINY_SEED,
        NpsSlimefunItems.SOUL_SEED,
        0.05,
        0.2
    )),

    COMBUSTIBLE(new BreedingPair(
        NpsSlimefunItems.COMBUSTIBLE_SEED,
        NpsSlimefunItems.SMOOTH_SEED,
        NpsSlimefunItems.SPLINTERED_SEED,
        0.05,
        0.2
    )),

    PROTECTIVE(new BreedingPair(
        NpsSlimefunItems.PROTECTIVE_SEED,
        NpsSlimefunItems.METALLIC_SEED,
        NpsSlimefunItems.SOUL_SEED,
        0.02,
        0.1
    )),

    VALUABLE(new BreedingPair(
        NpsSlimefunItems.VALUABLE_SEED,
        NpsSlimefunItems.SHINY_SEED,
        NpsSlimefunItems.ENCHANTED_SEED,
        0.05,
        0.2
    )),

    PERFECTION(new BreedingPair(
        NpsSlimefunItems.PERFECTION_SEED,
        NpsSlimefunItems.SHINY_SEED,
        NpsSlimefunItems.VALUABLE_SEED,
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
