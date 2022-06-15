package dev.sefiraat.netheopoiesis.managers;

import com.google.common.base.Preconditions;
import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.api.mobs.MobCap;
import dev.sefiraat.netheopoiesis.api.mobs.MobCapType;
import dev.sefiraat.netheopoiesis.implementation.tasks.MobRemovalTask;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.Unmodifiable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * The MomManager is used to store all the {@link MobCap}s used by this addon with basic methods for
 * finding, adding, spawning and removing mobs.
 */
public class MobManager {

    private static MobManager instance;

    private final Map<MobCapType, MobCap> mobCaps = new EnumMap<>(MobCapType.class);

    public MobManager() {
        Preconditions.checkArgument(instance == null, "Cannot create a new instance of the MobManager");
        instance = this;

        mobCaps.put(MobCapType.WATER_AMBIENT, MobCap.WATER_AMBIENT);
        mobCaps.put(MobCapType.WATER_ANIMAL, MobCap.WATER_ANIMAL);
        mobCaps.put(MobCapType.WATER_HOSTILE, MobCap.WATER_HOSTILE);
        mobCaps.put(MobCapType.LAND_AMBIENT, MobCap.LAND_AMBIENT);
        mobCaps.put(MobCapType.LAND_ANIMAL, MobCap.LAND_ANIMAL);
        mobCaps.put(MobCapType.LAND_HOSTILE, MobCap.LAND_HOSTILE);
        mobCaps.put(MobCapType.VILLAGER, MobCap.VILLAGER);
        mobCaps.put(MobCapType.PIGLIN_TRADER, MobCap.PIGLIN_TRADER);
        mobCaps.put(MobCapType.WANDERING_TRADER, MobCap.WANDERING_TRADER);

        new MobRemovalTask().runTaskTimer(Netheopoiesis.getInstance(), 1200, 1200);
    }

    /**
     * Get the specified mob cap.
     *
     * @param type The MobCap of matching {@link MobCapType} you want to retrieve
     * @return The {@link MobCap} with the matching type
     */
    @Nonnull
    public MobCap getMobCap(@Nonnull MobCapType type) {
        return mobCaps.get(type);
    }

    /**
     * Gets the {@link MobCap} that the specified mob is within, if any
     *
     * @param livingEntity The {@link LivingEntity} to check for
     * @return The {@link MobCap} the entity is in. Returned null if not in any
     */
    @Nullable
    public MobCap containedWithin(@Nonnull LivingEntity livingEntity) {
        return containedWithin(livingEntity.getUniqueId());
    }

    /**
     * Gets the {@link MobCap} that the specified mob is within, if any
     *
     * @param mobUuid The {@link UUID} of the entity to check for
     * @return The {@link MobCap} the entity is in. Returned null if not in any
     */
    @Nullable
    public MobCap containedWithin(@Nonnull UUID mobUuid) {
        for (MobCap boundMobList : mobCaps.values()) {
            if (boundMobList.contains(mobUuid)) {
                return boundMobList;
            }
        }
        return null;
    }

    /**
     * Gets an immutable list of all mobs currently contained within all caps
     *
     * @return The {@link List} of all mobs
     */
    @Nonnull
    @Unmodifiable
    public List<UUID> getAllMobs() {
        List<UUID> list = new ArrayList<>();
        for (MobCap mobCap : mobCaps.values()) {
            list.addAll(mobCap.getMobs());
        }
        return Collections.unmodifiableList(list);
    }

    /**
     * Gets a count of all mobs from all caps
     *
     * @return The count of all mobs
     */
    public int getMobCount() {
        int count = 0;
        for (MobCap mobCap : mobCaps.values()) {
            count += mobCap.count();
        }
        return count;
    }

    /**
     * Gets the count of all mobs within a specific cap
     *
     * @param type The {@link MobCapType} to check
     * @return The count of all mobs within that cap
     */
    public int getMobCountByMobCapType(@Nonnull MobCapType type) {
        return mobCaps.get(type).count();
    }

    /**
     * Checks if there is space to spawn a mob in the given cap
     *
     * @param type The {@link MobCapType} to check
     * @return True is space is available
     */
    public boolean mobCapHasSpace(@Nonnull MobCapType type) {
        return mobCaps.get(type).hasSpace();
    }

    /**
     * Checks all {@link MobCap}s to see if the specified mob is being managed
     *
     * @param livingEntity The {@link LivingEntity} to find
     * @return True if its within any cap
     */
    public boolean isMobManaged(@Nonnull LivingEntity livingEntity) {
        return isMobManaged(livingEntity.getUniqueId());
    }

    /**
     * Checks all {@link MobCap}s to see if the specified mob is being managed
     *
     * @param mobUuid The {@link UUID} of the mob to find
     * @return True if its within any cap
     */
    public boolean isMobManaged(@Nonnull UUID mobUuid) {
        for (MobCap mobCap : mobCaps.values()) {
            if (mobCap.contains(mobUuid)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a specific mob manually to the specified mob cap. Should be used only if spawnMob() isn't suitable
     *
     * @param type         The {@link MobCapType} to add the mob to
     * @param livingEntity The {@link LivingEntity} being added
     */
    public void addMob(@Nonnull MobCapType type, @Nonnull LivingEntity livingEntity) {
        addMob(type, livingEntity.getUniqueId());
    }

    /**
     * Adds a specific mob manually to the specified mob cap. Should be used only if spawnMob() isn't suitable
     *
     * @param type    The {@link MobCapType} to add the mob to
     * @param mobUuid The {@link UUID} of the mob being added
     */
    public void addMob(@Nonnull MobCapType type, @Nonnull UUID mobUuid) {
        if (mobCapHasSpace(type)) {
            final MobCap mobCap = mobCaps.get(type);
            mobCap.addMob(mobUuid);
            mobCaps.put(type, mobCap);
        }
    }

    /**
     * Checks all caps and removes the mob from any and all
     *
     * @param livingEntity The {@link LivingEntity} to remove
     * @param kill         Whether the mob should be killed (in game) also
     */
    public void removeMob(@Nonnull LivingEntity livingEntity, boolean kill) {
        removeMob(livingEntity.getUniqueId(), kill);
    }

    /**
     * Checks all caps and removes the mob from any and all
     *
     * @param mobUuid The {@link UUID} of the mob to remove
     * @param kill    Whether the mob should be killed (in game) also
     */
    public void removeMob(@Nonnull UUID mobUuid, boolean kill) {
        final MobCap mobCap = containedWithin(mobUuid);
        if (mobCap != null) {
            mobCap.removeMob(mobUuid);
        }
        if (kill) {
            final Entity entity = Bukkit.getEntity(mobUuid);
            if (entity != null && entity.isValid()) {
                entity.remove();
            }
        }
    }

    /**
     * Spawns a mob and adds it to the specified {@link MobCap}
     *
     * @param capType    The {@link MobCapType} to add the mob to
     * @param entityType The {@link EntityType} to spawn (must be a LivingEntity)
     * @param spawnLoc   The {@link Location} to spawn the new mob at
     * @param rand       if the mob should have its data randomized
     * @return The newly spawned {@link LivingEntity}. Returns null if the spawn failed, for example if the cap is full
     */
    @Nullable
    @ParametersAreNonnullByDefault
    public LivingEntity spawnMob(MobCapType capType, EntityType entityType, Location spawnLoc, boolean rand) {
        if (entityType.isAlive() && entityType.isSpawnable()) {
            final MobCap cap = getMobCap(capType);
            if (cap.hasSpace()) {
                // Unchecked casting fine due to .isAlive()
                LivingEntity livingEntity = (LivingEntity) spawnLoc.getWorld().spawnEntity(spawnLoc, entityType, rand);
                cap.addMob(livingEntity.getUniqueId());
                return livingEntity;
            }
        }
        return null;
    }

    /**
     * Shuts down the mob manager and kills all mobs within all mob caps
     */
    public void shutdown() {
        for (MobCap cap : mobCaps.values()) {
            cap.killAllMobs();
        }
    }

    public static MobManager getInstance() {
        return instance;
    }
}
