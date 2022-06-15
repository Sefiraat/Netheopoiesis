package dev.sefiraat.netheopoiesis.api.mobs;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MobCap {

    public static final MobCap WATER_AMBIENT = new MobCap(MobCapType.WATER_AMBIENT, 15);
    public static final MobCap WATER_ANIMAL = new MobCap(MobCapType.WATER_ANIMAL, 5);
    public static final MobCap WATER_HOSTILE = new MobCap(MobCapType.WATER_HOSTILE, 10);
    public static final MobCap LAND_AMBIENT = new MobCap(MobCapType.WATER_AMBIENT, 5);
    public static final MobCap LAND_ANIMAL = new MobCap(MobCapType.LAND_ANIMAL, 10);
    public static final MobCap LAND_HOSTILE = new MobCap(MobCapType.LAND_HOSTILE, 10);
    public static final MobCap VILLAGER = new MobCap(MobCapType.VILLAGER, 5);
    public static final MobCap PIGLIN_TRADER = new MobCap(MobCapType.PIGLIN_TRADER, 1);
    public static final MobCap WANDERING_TRADER = new MobCap(MobCapType.WANDERING_TRADER, 1);

    private final int perPlayerAmount;
    @Nonnull
    private final MobCapType type;
    @Nonnull
    private final List<UUID> mobs = new ArrayList<>();

    public MobCap(@Nonnull MobCapType type, int maxPerPlayer) {
        this.perPlayerAmount = maxPerPlayer;
        this.type = type;
    }

    public int count() {
        return mobs.size();
    }

    public boolean hasSpace() {
        return mobs.size() < perPlayerAmount * Bukkit.getOnlinePlayers().size();
    }

    public boolean contains(@Nonnull UUID mobUuid) {
        return mobs.contains(mobUuid);
    }

    public void addMob(@Nonnull UUID mobUuid) {
        mobs.add(mobUuid);
    }

    public void removeMob(@Nonnull UUID mobUuid) {
        mobs.remove(mobUuid);
    }

    public void killMob(@Nonnull UUID mobUuid) {
        mobs.remove(mobUuid);
        final Entity entity = Bukkit.getEntity(mobUuid);
        if (entity != null) {
            entity.remove();
        }
    }

    public void killAllMobs() {
        for (UUID mob : mobs) {
            final Entity entity = Bukkit.getEntity(mob);
            if (entity != null) {
                entity.remove();
            }
        }
        mobs.clear();
    }

    public int getPerPlayerAmount() {
        return perPlayerAmount;
    }

    @Nonnull
    public MobCapType getType() {
        return type;
    }

    @Nonnull
    public List<UUID> getMobs() {
        return mobs;
    }

}
