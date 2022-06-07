package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import dev.sefiraat.netheopoiesis.core.plant.GrowthDescription;
import dev.sefiraat.netheopoiesis.core.plant.GrowthStages;
import dev.sefiraat.netheopoiesis.utils.WorldUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This plant will spawn the provided entity when fully matured
 */
public class EntitySpawningSeed extends NetherSeed {

    private final EntityType entityType;

    @ParametersAreNonnullByDefault
    public EntitySpawningSeed(ItemGroup group, SlimefunItemStack item, EntityType type, GrowthDescription description) {
        super(group, item, description);
        this.entityType = type;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onTickFullyGrown(Location location, NetherSeed seed, Config data) {
        double randomChance = ThreadLocalRandom.current().nextDouble();
        if (randomChance <= 0.05) {
            final Block block = WorldUtils.randomLocation(location, 4).getBlock();

            // The first block we spawn on needs to be AIR
            if (block.getType() != Material.AIR) {
                return;
            }

            final Block blockBelow = block.getRelative(BlockFace.DOWN);

            // And we need a solid floor and finally not too many nearby mobs
            if (blockBelow.getType() == Material.AIR
                && block.getWorld().getNearbyEntities(block.getLocation(), 10, 10, 10).size() < 6
            ) {
                return;
            }

            // Clear to spawn
            blockBelow.getWorld().spawnEntity(block.getLocation(), this.entityType);
        }
    }

    public EntityType getEntityType() {
        return entityType;
    }
}
