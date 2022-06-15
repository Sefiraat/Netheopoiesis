package dev.sefiraat.netheopoiesis.api.mobs;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.Unmodifiable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
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

    private final int amountPerPlayer;
    @Nonnull
    private final MobCapType type;
    @Nonnull
    private final List<UUID> mobs = new ArrayList<>();

    public MobCap(@Nonnull MobCapType type, int maxPerPlayer) {
        this.amountPerPlayer = maxPerPlayer;
        this.type = type;
    }

    /**
     * Gets the number of mobs currently held in this cap
     * @return The number of mobs currently held in this cap
     */
    public int count() {
        return mobs.size();
    }

    /**
     * Checks if there is space in this cap for another mob
     * @return true if there is space
     */
    public boolean hasSpace() {
        return mobs.size() < amountPerPlayer * Bukkit.getOnlinePlayers().size();
    }

    /**
     * Checks if a given {@link UUID} is contained in this cap
     * @param mobUuid The {@link UUID} for the mob being checked
     * @return true if contained in the cap
     */
    public boolean contains(@Nonnull UUID mobUuid) {
        return mobs.contains(mobUuid);
    }

    /**
     * Adds a new mob to this cap. Does not check for space and can be forced if required.
     * Check hasSpace() first!
     * @param mobUuid The {@link UUID} of the mob to add
     */
    public void addMob(@Nonnull UUID mobUuid) {
        mobs.add(mobUuid);
    }

    /**
     * Removes a mob from this cap if possible
     * @param mobUuid The {@link UUID} of the mob to remove
     */
    public void removeMob(@Nonnull UUID mobUuid) {
        mobs.remove(mobUuid);
    }

    /**
     * Kills a mob whilst also removing them from the cap
     * @param mobUuid The {@link UUID} of the mob to kill/remove
     */
    public void killMob(@Nonnull UUID mobUuid) {
        mobs.remove(mobUuid);
        final Entity entity = Bukkit.getEntity(mobUuid);
        if (entity != null) {
            entity.remove();
        }
    }

    /**
     * Kills all mobs in this cap and removes them from it
     */
    public void killAllMobs() {
        for (UUID mob : mobs) {
            final Entity entity = Bukkit.getEntity(mob);
            if (entity != null) {
                entity.remove();
            }
        }
        mobs.clear();
    }

    /**
     * Gets the maximum number of spawns, per player, for this cap.
     * @return The max number of mobs
     */
    public int getMaxAmountPerPlayer() {
        return amountPerPlayer;
    }

    /**
     * Gets the maximum number of spawns for this cap (includes the multiplier per-player)
     * @return The max number of mobs
     */
    public int getMaxAmount() {
        return amountPerPlayer;
    }

    /**
     * The {@link MobCapType} for this cap
     * @return The {@link MobCapType} for this cap
     */
    @Nonnull
    public MobCapType getType() {
        return type;
    }

    /**
     * Gets an immutable list of all mobs contained within this cap
     * @return An immutable list of all mobs contained within this cap
     */
    @Nonnull
    @Unmodifiable
    public List<UUID> getMobs() {
        return Collections.unmodifiableList(mobs);
    }
}
