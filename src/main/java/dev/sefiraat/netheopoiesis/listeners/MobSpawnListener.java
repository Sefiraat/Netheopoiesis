package dev.sefiraat.netheopoiesis.listeners;

import dev.sefiraat.netheopoiesis.PurificationMemory;
import dev.sefiraat.netheopoiesis.utils.TimePeriod;
import io.github.bakedlibs.dough.collections.RandomizedSet;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
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
        HOSTILE_MOBS.add(EntityType.CREEPER, 1);
        HOSTILE_MOBS.add(EntityType.SKELETON, 1);
        HOSTILE_MOBS.add(EntityType.SLIME, 1);
        HOSTILE_MOBS.add(EntityType.WITCH, 1);
        HOSTILE_MOBS.add(EntityType.ZOMBIE, 1);
        HOSTILE_MOBS.add(EntityType.ZOMBIE_VILLAGER, 1);
        HOSTILE_MOBS.add(EntityType.CAVE_SPIDER, 1);
        HOSTILE_MOBS.add(EntityType.EVOKER, 1);
        HOSTILE_MOBS.add(EntityType.PILLAGER, 1);
        HOSTILE_MOBS.add(EntityType.VINDICATOR, 1);
        HOSTILE_MOBS.add(EntityType.RAVAGER, 1);

        // Passive Mobs
        PASSIVE_MOBS.add(EntityType.CHICKEN, 1);
        PASSIVE_MOBS.add(EntityType.COW, 1);
        PASSIVE_MOBS.add(EntityType.DONKEY, 1);
        PASSIVE_MOBS.add(EntityType.HORSE, 1);
        PASSIVE_MOBS.add(EntityType.MULE, 1);
        PASSIVE_MOBS.add(EntityType.SHEEP, 1);

        // Required purification values
        MAP.put(EntityType.MAGMA_CUBE, 500);
        MAP.put(EntityType.PIGLIN, 500);
        MAP.put(EntityType.BLAZE, 1_000);
        MAP.put(EntityType.ZOMBIFIED_PIGLIN, 1_000);
        MAP.put(EntityType.HOGLIN, 1_000);
        MAP.put(EntityType.PIGLIN_BRUTE, 2_000);
        MAP.put(EntityType.WITHER_SKELETON, 2_000);
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onMobSpawn(@Nonnull EntitySpawnEvent event) {
        final Entity entity = event.getEntity();
        final World world = entity.getWorld();
        // Check if the spawn is a monster and we're in the Nether
        if (entity instanceof Monster
            && world.getEnvironment() == World.Environment.NETHER) {
            final int requiredValue = MAP.getOrDefault(entity.getType(), -1);
            final Location location = entity.getLocation();
            final int value = PurificationMemory.getInstance().getPurificationValue(location.getChunk());
            if (requiredValue == -1 || value < requiredValue) {
                // Either the mob cannot be replaced or the chunk is not purified enough
                return;
            }
            event.setCancelled(true);

            final boolean isDay = TimePeriod.isDay(world);
            if (entity.getType() == EntityType.GHAST) {
                // Special case for ghasts to replace only with flying mobs
                world.spawnEntity(location, isDay ? EntityType.BAT : EntityType.PHANTOM);
            } else {
                // Replace the spawn with a relevant type
                world.spawnEntity(location, isDay ? PASSIVE_MOBS.getRandom() : HOSTILE_MOBS.getRandom());
            }
        }
    }
}
