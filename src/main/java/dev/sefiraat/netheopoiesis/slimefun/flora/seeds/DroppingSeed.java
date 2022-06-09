package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import dev.sefiraat.netheopoiesis.core.plant.GrowthDescription;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A plant that will drop a specific itemstack in-world when fully mature.
 * Should only be used for Redstone, String and Cobblestone as these are
 * mandatory for the EnhancedCraftingTable before you can make a tool
 */
public class DroppingSeed extends NetherSeed {

    private final ItemStack[] stacksToDrop;
    private final double chance;

    @ParametersAreNonnullByDefault
    public DroppingSeed(SlimefunItemStack item, ItemStack drop, double chance, GrowthDescription description) {
        this(item, new ItemStack[]{drop}, chance, description);
    }

    @ParametersAreNonnullByDefault
    public DroppingSeed(SlimefunItemStack item, ItemStack[] drops, double chance, GrowthDescription description) {
        super(item, description);
        this.stacksToDrop = drops;
        this.chance = chance;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onTickFullyGrown(Location location, NetherSeed seed, Config data) {
        double randomChance = ThreadLocalRandom.current().nextDouble();
        if (randomChance <= chance) {
            final int random = ThreadLocalRandom.current().nextInt(this.stacksToDrop.length);
            location.getWorld().dropItem(location, this.stacksToDrop[random]);
        }
    }

    public ItemStack[] getStacksToDrop() {
        return stacksToDrop;
    }
}
