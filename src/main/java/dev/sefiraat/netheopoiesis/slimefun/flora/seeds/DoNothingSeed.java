package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import dev.sefiraat.netheopoiesis.core.plant.GrowthDescription;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This is a seed that does nothing except grow. Can be used for breeding steps or Mechanics outside
 * the plants themselves.
 */
public class DoNothingSeed extends NetherSeed {

    @ParametersAreNonnullByDefault
    public DoNothingSeed(SlimefunItemStack item, GrowthDescription description) {
        super(item, description);
    }
}
