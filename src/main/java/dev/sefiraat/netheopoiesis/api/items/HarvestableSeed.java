package dev.sefiraat.netheopoiesis.api.items;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.api.plant.GrowthStages;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

/**
 * This plant can be harvested by right-clicking with a {@link dev.sefiraat.netheopoiesis.implementation.tools.HarvestingTool}
 * dropping the provided ItemStack into the world.
 * The plant then reverts to its first stage in its {@link GrowthStages}
 */
public class HarvestableSeed extends NetherSeed {

    @Nullable
    private ItemStack harvestItemStack;

    public HarvestableSeed(@Nonnull SlimefunItemStack item) {
        super(item);
    }

    @Nonnull
    public HarvestableSeed setHarvestingResult(@Nonnull ItemStack harvestStack) {
        this.harvestItemStack = harvestStack;
        return this;
    }

    @Nullable
    @Override
    public ItemStack getHarvestingResult() {
        return this.harvestItemStack;
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    protected boolean validateSeed() {
        if (this.harvestItemStack == null) {
            Netheopoiesis.logWarning(this.getId() + " has no ItemStack, it will not be registered.");
            return false;
        }
        return true;
    }
}
