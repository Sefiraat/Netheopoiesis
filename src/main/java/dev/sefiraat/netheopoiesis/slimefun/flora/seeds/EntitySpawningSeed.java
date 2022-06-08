package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import dev.sefiraat.netheopoiesis.core.plant.GrowthDescription;
import dev.sefiraat.netheopoiesis.utils.WorldUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

/**
 * This plant will spawn the provided entity when fully matured
 */
public class EntitySpawningSeed extends NetherSeed {

    private final EntityType entityType;
    private final Consumer<Entity> callback;

    @ParametersAreNonnullByDefault
    public EntitySpawningSeed(ItemGroup group, SlimefunItemStack item, EntityType type, GrowthDescription description) {
        this(group, item, type, description, null);
    }

    @ParametersAreNonnullByDefault
    public EntitySpawningSeed(ItemGroup group,
                              SlimefunItemStack item,
                              EntityType type,
                              GrowthDescription description,
                              @Nullable Consumer<Entity> callback
    ) {
        super(group, item, description);
        this.entityType = type;
        this.callback = callback;
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
            final Entity entity = blockBelow.getWorld().spawnEntity(block.getLocation(), this.entityType);

            // Accept call back if preset
            if (this.callback != null) {
                this.callback.accept(entity);
            }
        }
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public Consumer<Entity> getCallback() {
        return callback;
    }
}
