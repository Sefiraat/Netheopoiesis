package dev.sefiraat.netheopoiesis.api.items;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This is a seed that does nothing except grow. Can be used for breeding steps or Mechanics outside
 * the plants themselves.
 */
public class DoNothingSeed extends NetherSeed {

    @ParametersAreNonnullByDefault
    public DoNothingSeed(SlimefunItemStack item) {
        super(item);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    protected boolean validateSeed() {
        return true;
    }
}
