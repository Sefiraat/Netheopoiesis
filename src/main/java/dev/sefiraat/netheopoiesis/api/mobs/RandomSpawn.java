package dev.sefiraat.netheopoiesis.api.mobs;

import com.google.common.base.Preconditions;
import dev.sefiraat.netheopoiesis.Purification;
import dev.sefiraat.netheopoiesis.managers.MobManager;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * This class is used to describe a mob spawn with it's EntityType, chance and purification level
 *
 * @see dev.sefiraat.netheopoiesis.listeners.MobSpawnListener
 */
public class RandomSpawn {

    @Nonnull
    private final EntityType type;
    @Nonnull
    private final MobCapType mobCapType;
    private final int requiredPurification;
    private final double chance;
    @Nonnull
    private final Predicate<Location> predicate;
    private final boolean randomize;
    @Nullable
    private final Consumer<LivingEntity> callback;

    /**
     * Creates a new RandomSpawn
     *
     * @param type          The {@link EntityType} that will spawn
     * @param requiredValue The required Purification level in the chunk for the spawn to be attempted
     * @param chance        The chance for the mob to spawn successfully
     */
    public RandomSpawn(@Nonnull EntityType type, @Nonnull MobCapType mobCapType, int requiredValue, double chance) {
        this(type, mobCapType, requiredValue, chance, true, location -> true);
    }

    /**
     * Creates a new RandomSpawn
     *
     * @param type          The {@link EntityType} that will spawn
     * @param requiredValue The required Purification level in the chunk for the spawn to be attempted
     * @param chance        The chance for the mob to spawn successfully
     * @param randomize     Defines if the mob's data should be randomized (default = true)
     */
    public RandomSpawn(@Nonnull EntityType type,
                       @Nonnull MobCapType mobCapType,
                       int requiredValue,
                       double chance,
                       boolean randomize
    ) {
        this(type, mobCapType, requiredValue, chance, randomize, location -> true);
    }

    /**
     * Creates a new RandomSpawn
     *
     * @param type          The {@link EntityType} that will spawn
     * @param requiredValue The required Purification level in the chunk for the spawn to be attempted
     * @param chance        The chance for the mob to spawn successfully
     * @param predicate     This predicate is used to determine if the mob can spawn. Use for additional spawn
     *                      requirements, e.g. Location being in water
     */
    public RandomSpawn(@Nonnull EntityType type,
                       @Nonnull MobCapType mobCapType,
                       int requiredValue,
                       double chance,
                       Predicate<Location> predicate
    ) {
        this(type, mobCapType, requiredValue, chance, true, predicate);
    }

    /**
     * Creates a new RandomSpawn
     *
     * @param type          The {@link EntityType} that will spawn
     * @param requiredValue The required Purification level in the chunk for the spawn to be attempted
     * @param chance        The chance for the mob to spawn successfully
     * @param randomize     Defines if the mob's data should be randomized (default = true)
     * @param predicate     This predicate is used to determine if the mob can spawn. Use for additional spawn
     *                      requirements, e.g. Location being in water
     */
    public RandomSpawn(@Nonnull EntityType type,
                       @Nonnull MobCapType mobCapType,
                       int requiredValue,
                       double chance,
                       boolean randomize,
                       Predicate<Location> predicate
    ) {
        this(type, mobCapType, requiredValue, chance, randomize, predicate, null);
    }

    /**
     * Creates a new RandomSpawn
     *
     * @param type          The {@link EntityType} that will spawn
     * @param requiredValue The required Purification level in the chunk for the spawn to be attempted
     * @param chance        The chance for the mob to spawn successfully
     * @param randomize     Defines if the mob's data should be randomized (default = true)
     * @param predicate     This predicate is used to determine if the mob can spawn. Use for additional spawn
     *                      requirements, e.g. Location being in water
     * @param callback      The {@link Consumer} that will be accepted after the mob has been spawned
     */
    public RandomSpawn(@Nonnull EntityType type,
                       @Nonnull MobCapType mobCapType,
                       int requiredValue,
                       double chance,
                       boolean randomize,
                       Predicate<Location> predicate,
                       @Nullable Consumer<LivingEntity> callback
    ) {
        Preconditions.checkArgument(type.isAlive(), "Only LivingEntities can be RandomSpawns");
        Preconditions.checkArgument(type.isSpawnable(), "Specified type is not spawnable");
        this.type = type;
        this.mobCapType = mobCapType;
        this.requiredPurification = requiredValue;
        this.chance = chance;
        this.randomize = randomize;
        this.predicate = predicate;
        this.callback = callback;
    }

    /**
     * The {@link EntityType} that will be spawned
     *
     * @return The {@link EntityType} that will be spawned
     */
    public EntityType getType() {
        return type;
    }

    /**
     * The required level of purification for a spawn attempt
     *
     * @return The required level of purification
     */
    public int getRequiredPurification() {
        return requiredPurification;
    }

    /**
     * The chance of a spawn occurring (0-1)
     *
     * @return The chance of a spawn occurring (0-1)
     */
    public double getChance() {
        return chance;
    }

    @Nonnull
    public MobCapType getMobCapType() {
        return mobCapType;
    }

    @Nonnull
    public Predicate<Location> getPredicate() {
        return predicate;
    }

    public boolean isRandomize() {
        return randomize;
    }

    @Nullable
    public Consumer<LivingEntity> getCallback() {
        return callback;
    }

    /**
     * Attempts to spawn the {@link EntityType} defined.
     *
     * @param location The location to spawn at (and to get purificationValue from)
     * @return true if the spawn was successful
     */
    public boolean trySpawn(@Nonnull Location location) {
        int purificationLevel = Purification.getValue(location.getChunk());
        return trySpawn(location, purificationLevel);
    }

    /**
     * Attempts to spawn the {@link EntityType} defined. Allows you to provide the location's
     * purification's level, if it's already known.
     *
     * @param location          The location to spawn at
     * @param purificationLevel The location's purification level (if already accessed and known).
     *                          Use other constructor otherwise
     * @return true if the spawn was successful
     */
    public boolean trySpawn(@Nonnull Location location, int purificationLevel) {
        if (purificationLevel >= this.requiredPurification) {
            final double random = ThreadLocalRandom.current().nextDouble();
            if (random <= this.chance && this.predicate.test(location) && hasEnoughSpace(location)) {
                final LivingEntity livingEntity = MobManager.getInstance().spawnMob(
                    mobCapType,
                    type,
                    location,
                    randomize
                );

                if (livingEntity != null) {
                    if (callback != null) {
                        callback.accept(livingEntity);
                    }
                    livingEntity.setRemoveWhenFarAway(true);
                    livingEntity.setNoDamageTicks(20);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasEnoughSpace(@Nonnull Location location) {
        final World world = location.getWorld();

        // Don't want too many mobs in one specific location
        return world.getNearbyEntities(location, 7, 7, 7).size() <= 5;
    }
}
