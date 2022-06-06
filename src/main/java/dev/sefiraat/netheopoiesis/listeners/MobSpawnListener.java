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

public class MobSpawnListener implements Listener {

    private static final RandomizedSet<EntityType> HOSTILE_MOBS = new RandomizedSet<>();
    private static final RandomizedSet<EntityType> PASSIVE_MOBS = new RandomizedSet<>();
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
        if (entity instanceof Monster
            && world.getEnvironment() == World.Environment.NETHER) {
            final int requiredValue = MAP.getOrDefault(entity.getType(), -1);
            final Location location = entity.getLocation();
            final int value = PurificationMemory.getInstance().getPurificationValue(location.getChunk());
            if (requiredValue == -1 || value < requiredValue) {
                return;
            }
            event.setCancelled(true);

            final boolean isDay = TimePeriod.isDay(world);
            if (entity.getType() == EntityType.GHAST) {
                world.spawnEntity(location, isDay ? EntityType.BAT : EntityType.PHANTOM);
            } else {
                world.spawnEntity(location, isDay ? PASSIVE_MOBS.getRandom() : HOSTILE_MOBS.getRandom());
            }
        }
    }
}
