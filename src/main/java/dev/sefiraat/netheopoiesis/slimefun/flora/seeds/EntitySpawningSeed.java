package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import dev.sefiraat.netheopoiesis.core.plant.GrowthDescription;
import dev.sefiraat.netheopoiesis.slimefun.NpsRecipeTypes;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This plant will spawn the provided entity when fully matured
 */
public class EntitySpawningSeed extends NetherSeed {

    private final EntityType entityType;
    private final double growthRate;
    private final int purificationValue;

    public EntitySpawningSeed(@Nonnull ItemGroup itemGroup,
                              @Nonnull SlimefunItemStack item,
                              @Nonnull GrowthDescription growthDescription,
                              @Nonnull Set<String> placement,
                              @Nonnull EntityType entityType,
                              double growthRate,
                              int purificationValue
    ) {
        super(itemGroup, item, NpsRecipeTypes.PLANT_BREEDING, new ItemStack[0], growthDescription, placement);
        this.entityType = entityType;
        this.growthRate = growthRate;
        this.purificationValue = purificationValue;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onTickFullyGrown(Location location, NetherSeed seed, Config data) {
        double randomChance = ThreadLocalRandom.current().nextDouble();
        if (randomChance <= 0.05) {
            final double randomX = ThreadLocalRandom.current().nextInt(-3, 4);
            final double randomY = ThreadLocalRandom.current().nextInt(-2, 3);
            final double randomZ = ThreadLocalRandom.current().nextInt(-3, 4);
            final Block block = location.clone().add(randomX, randomY, randomZ).getBlock();

            // the first block we spawn on needs to be AIR
            if (block.getType() != Material.AIR) {
                return;
            }

            final Block blockBelow = block.getRelative(BlockFace.DOWN);

            // And we need a solid floor
            if (blockBelow.getType() == Material.AIR) {
                return;
            }

            blockBelow.getWorld().spawnEntity(block.getLocation(), this.entityType);
        }
    }

    public EntityType getEntityType() {
        return entityType;
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
