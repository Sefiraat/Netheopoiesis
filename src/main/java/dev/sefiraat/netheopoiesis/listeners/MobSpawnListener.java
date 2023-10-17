package dev.sefiraat.netheopoiesis.listeners;

import dev.sefiraat.netheopoiesis.Purification;
import dev.sefiraat.netheopoiesis.api.mobs.MobCapType;
import dev.sefiraat.netheopoiesis.managers.MobManager;
import dev.sefiraat.netheopoiesis.utils.TimePeriod;
import dev.sefiraat.netheopoiesis.utils.WorldUtils;
import io.github.bakedlibs.dough.collections.RandomizedSet;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import javax.annotation.Nonnull;
import java.util.EnumMap;
import java.util.Map;

/**
 * The purpose of this listener is to stop traditional Nether mob spawns when the location is above a specific
 * purification level required for that mob. Mobs are then replaced with an Overworld mob, the type of which
 * is determined by the time of day
 */
public class MobSpawnListener implements Listener {

    // Stores a set of possible hostile mobs that can replace a spawn during the night
    private static final RandomizedSet<EntityType> HOSTILE_MOBS = new RandomizedSet<>();
    // Stores a set of possible passive mobs that can replace a spawn during the day
    private static final RandomizedSet<EntityType> PASSIVE_MOBS = new RandomizedSet<>();
    // Stores replaceable mobs and the purification value required for them to be replaced
    private static final Map<EntityType, Integer> MAP = new EnumMap<>(EntityType.class);

    static {
        // Hostile mobs
        HOSTILE_MOBS.add(EntityType.CREEPER, 5);
        HOSTILE_MOBS.add(EntityType.SKELETON, 5);
        HOSTILE_MOBS.add(EntityType.SLIME, 3);
        HOSTILE_MOBS.add(EntityType.WITCH, 2);
        HOSTILE_MOBS.add(EntityType.ZOMBIE, 5);
        HOSTILE_MOBS.add(EntityType.ZOMBIE_VILLAGER, 1);
        HOSTILE_MOBS.add(EntityType.SPIDER, 3);
        HOSTILE_MOBS.add(EntityType.CAVE_SPIDER, 3);
        HOSTILE_MOBS.add(EntityType.EVOKER, 1);
        HOSTILE_MOBS.add(EntityType.PILLAGER, 2);
        HOSTILE_MOBS.add(EntityType.VINDICATOR, 2);
        HOSTILE_MOBS.add(EntityType.RAVAGER, 1);

        // Passive Mobs
        PASSIVE_MOBS.add(EntityType.COW, 5);
        PASSIVE_MOBS.add(EntityType.SHEEP, 5);
        PASSIVE_MOBS.add(EntityType.CHICKEN, 4);
        PASSIVE_MOBS.add(EntityType.HORSE, 2);
        PASSIVE_MOBS.add(EntityType.DONKEY, 1);
        PASSIVE_MOBS.add(EntityType.MULE, 1);

        // Required purification values
        MAP.put(EntityType.MAGMA_CUBE, Purification.SWAP_MAGMA_CUBE);
        MAP.put(EntityType.BLAZE, Purification.SWAP_BLAZE);
        MAP.put(EntityType.ZOMBIFIED_PIGLIN, Purification.SWAP_ZOMBIFIED_PIGLIN);
        MAP.put(EntityType.HOGLIN, Purification.SWAP_HOGLIN);
        MAP.put(EntityType.PIGLIN_BRUTE, Purification.SWAP_PIGLIN_BRUTE);
        MAP.put(EntityType.GHAST, Purification.SWAP_GHAST);
        MAP.put(EntityType.WITHER_SKELETON, Purification.SWAP_WITHER_SKELETON);
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onMobSpawn(@Nonnull EntitySpawnEvent event) {
        final Entity entity = event.getEntity();
        final World world = entity.getWorld();
        if (isValidMob(entity)) {
            final int requiredValue = MAP.getOrDefault(entity.getType(), -1);
            final Location location = entity.getLocation();
            final int value = Purification.getValue(location.getChunk());

            if (requiredValue == -1 || value < requiredValue) {
                // Either the mob cannot be replaced or the chunk is not purified enough
                return;
            }
            event.setCancelled(true);

            final MobManager manager = MobManager.getInstance();
            final boolean isDay = TimePeriod.isDay(world);

            if (entity.getType() == EntityType.GHAST) {
                // Special case for ghasts to replace only with flying mobs
                if (isDay) {
                    manager.spawnMob(MobCapType.LAND_AMBIENT, EntityType.BAT, location, false);
                } else {
                    manager.spawnMob(MobCapType.LAND_HOSTILE, EntityType.PHANTOM, location, false);
                }
            } else if (hasEnoughSpace(location)) {
                // Try to replace the spawn with a relevant type
                LivingEntity spawned = null;

                if (isDay) {
                    spawned = manager.spawnMob(MobCapType.LAND_ANIMAL, PASSIVE_MOBS.getRandom(), location, true);
                } else {
                    spawned = manager.spawnMob(MobCapType.LAND_HOSTILE, HOSTILE_MOBS.getRandom(), location, true);
                }

                if (spawned != null) {
                    spawned.setRemoveWhenFarAway(true);
                    spawned.setNoDamageTicks(20);
                }
            }
        }
    }

    private boolean isValidMob(@Nonnull Entity entity) {
        // Check if the spawn is a monster and we're in the Nether
        if (!WorldUtils.inNether(entity)) {
            return false;
        }
        // Magma Cube is not a monster, we need to include this as well
        return entity instanceof Monster || entity instanceof MagmaCube;
    }

    // Todo - Add a proper way to detect and manage mob-caps.
    private boolean hasEnoughSpace(@Nonnull Location location) {
        final World world = location.getWorld();

        // Don't want too many mobs in one specific location
        return world.getNearbyEntities(location, 7, 7, 7).size() <= 5;
    }
}
