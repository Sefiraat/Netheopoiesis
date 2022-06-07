package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import dev.sefiraat.netheopoiesis.core.plant.GrowthType;
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

    private final ItemStack[] stacksToDrop;

    public DroppingSeed(@Nonnull ItemGroup itemGroup,
                        @Nonnull SlimefunItemStack item,
                        @Nonnull GrowthType growthType,
                        @Nonnull Set<String> placement,
                        @Nonnull ItemStack drop,
                        double growthRate,
                        int purificationValue
    ) {
        this(itemGroup, item, growthType, placement, new ItemStack[]{drop}, growthRate, purificationValue);
    }

    public DroppingSeed(@Nonnull ItemGroup itemGroup,
                        @Nonnull SlimefunItemStack item,
                        @Nonnull GrowthType growthType,
                        @Nonnull Set<String> placement,
                        @Nonnull ItemStack[] drops,
                        double growthRate,
                        int purificationValue
    ) {
        super(itemGroup, item, growthType, placement, growthRate, purificationValue);
        this.stacksToDrop = drops;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onTickFullyGrown(Location location, NetherSeed seed, Config data) {
        double randomChance = ThreadLocalRandom.current().nextDouble();
        if (randomChance <= 0.005) {
            final int random = ThreadLocalRandom.current().nextInt(this.stacksToDrop.length);
            location.getWorld().dropItem(location, this.stacksToDrop[random]);
        }
    }

    public ItemStack[] getStacksToDrop() {
        return stacksToDrop;
    }
}
