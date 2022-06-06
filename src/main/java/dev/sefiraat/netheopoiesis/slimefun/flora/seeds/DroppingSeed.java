package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import dev.sefiraat.netheopoiesis.core.plant.GrowthDescription;
import dev.sefiraat.netheopoiesis.slimefun.NpsRecipeTypes;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A plant that will drop a specific itemstack in-world when fully mature.
 * Should only be used for Redstone, String and Cobblestone as these are
 * mandatory for the EnhancedCraftingTable before you can make a tool
 */
public class DroppingSeed extends NetherSeed {

    private final ItemStack stackToDrop;
    private final double growthRate;
    private final int purificationValue;

    public DroppingSeed(@Nonnull ItemGroup itemGroup,
                        @Nonnull SlimefunItemStack item,
                        @Nonnull GrowthDescription growthDescription,
                        @Nonnull Set<String> placement,
                        @Nonnull ItemStack stackToDrop,
                        double growthRate,
                        int purificationValue
    ) {
        super(itemGroup, item, NpsRecipeTypes.PLANT_BREEDING, new ItemStack[0], growthDescription, placement);
        this.stackToDrop = stackToDrop;
        this.growthRate = growthRate;
        this.purificationValue = purificationValue;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onTickFullyGrown(Location location, NetherSeed seed, Config data) {
        double randomChance = ThreadLocalRandom.current().nextDouble();
        if (randomChance <= 0.005) {
            location.getWorld().dropItem(location, this.stackToDrop);
        }
    }

    public ItemStack getStackToDrop() {
        return stackToDrop;
    }

    @Override
    public double getGrowthRate() {
        return growthRate;
    }

    @Override
    public int getPurificationValue() {
        return purificationValue;
    }
}
