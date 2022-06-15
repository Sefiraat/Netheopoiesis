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

    @Nonnull
    public MobCap getMobCap(@Nonnull MobCapType type) {
        return mobCaps.get(type);
    }

    @Nullable
    public MobCap containedWithin(@Nonnull UUID mobUuid) {
        for (MobCap boundMobList : mobCaps.values()) {
            if (boundMobList.contains(mobUuid)) {
                return boundMobList;
            }
        }
        return null;
    }

    @Nonnull
    @Unmodifiable
    public List<UUID> getAllMobs() {
        List<UUID> list = new ArrayList<>();
        for (MobCap mobCap : mobCaps.values()) {
            list.addAll(mobCap.getMobs());
        }
        return Collections.unmodifiableList(list);
    }

    public int getMobCount() {
        int count = 0;
        for (MobCap mobCap : mobCaps.values()) {
            count += mobCap.count();
        }
        return count;
    }

    public int getMobCountByMobCapType(@Nonnull MobCapType type) {
        return mobCaps.get(type).count();
    }

    public boolean mobCapHasSpace(@Nonnull MobCapType type) {
        return mobCaps.get(type).hasSpace();
    }

    public boolean isMobManaged(@Nonnull LivingEntity livingEntity) {
        return isMobManaged(livingEntity.getUniqueId());
    }

    public boolean isMobManaged(@Nonnull UUID mobUuid) {
        for (MobCap mobCap : mobCaps.values()) {
            if (mobCap.contains(mobUuid)) {
                return true;
            }
        }
        return false;
    }

    public void addMob(@Nonnull MobCapType type, @Nonnull UUID mobUuid) {
        if (mobCapHasSpace(type)) {
            final MobCap mobCap = mobCaps.get(type);
            mobCap.addMob(mobUuid);
            mobCaps.put(type, mobCap);
        }
    }

    public void removeMob(@Nonnull LivingEntity livingEntity, boolean kill) {
        removeMob(livingEntity.getUniqueId(), kill);
    }

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

    public void shutdown() {
        for (MobCap cap : mobCaps.values()) {
            cap.killAllMobs();
        }
    }

    public static MobManager getInstance() {
        return instance;
    }
}
