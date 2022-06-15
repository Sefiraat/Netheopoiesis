package dev.sefiraat.netheopoiesis.api.items;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import io.github.bakedlibs.dough.collections.RandomizedSet;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A plant that will drop a specific itemstack in-world when fully mature.
 * Should only be used for Redstone, String and Cobblestone as these are
 * mandatory for the EnhancedCraftingTable before you can make a tool
 */
public class DroppingSeed extends NetherSeed {

    private final RandomizedSet<ItemStack> stacksToDrop = new RandomizedSet<>();
    private double chance = 0.1;

    public DroppingSeed(@Nonnull SlimefunItemStack item) {
        super(item);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onTickFullyGrown(Location location, NetherSeed seed, Config data) {
        double randomChance = ThreadLocalRandom.current().nextDouble();
        if (randomChance <= chance) {
            location.getWorld().dropItem(location, this.stacksToDrop.getRandom());
        }
    }

    @Nonnull
    public DroppingSeed setTriggerChance(double chance) {
        this.chance = chance;
        return this;
    }

    @Nonnull
    public DroppingSeed addDrop(@Nonnull ItemStack stack, int weight) {
        this.stacksToDrop.add(stack, weight);
        return this;
    }

    @Nonnull
    public RandomizedSet<ItemStack> getPossibleDrops() {
        return stacksToDrop;
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    protected boolean validateSeed() {
        if (this.stacksToDrop.isEmpty()) {
            Netheopoiesis.logWarning(this.getId() + " has no drops, item will not be registered.");
            return false;
        }
        return true;
    }
}
