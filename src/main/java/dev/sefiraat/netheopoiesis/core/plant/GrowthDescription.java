package dev.sefiraat.netheopoiesis.core.plant;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;

/**
 * This class collates the various classes used to describe the way a plant grows
 * and/or interacts with the world.
 */
public class GrowthDescription {

    private final GrowthStages stages;
    private final Set<String> placements;
    private final int purificationValue;
    private final double growthRate;

    @ParametersAreNonnullByDefault
    public GrowthDescription(GrowthStages stages, Set<String> places, int purificationValue, double growthRate) {
        this.stages = stages;
        this.placements = places;
        this.purificationValue = purificationValue;
        this.growthRate = growthRate;
    }

    public GrowthStages getStages() {
        return stages;
    }

    public Set<String> getPlacements() {
        return placements;
    }

    public int getPurificationValue() {
        return purificationValue;
    }

    public double getGrowthRate() {
        return growthRate;
    }
}
