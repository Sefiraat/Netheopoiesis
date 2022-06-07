package dev.sefiraat.netheopoiesis.core.purification;

import dev.sefiraat.netheopoiesis.Purification;
import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import javax.annotation.Nonnull;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

public class RandomSpawn {

    private final EntityType type;
    private final int requiredPurification;
    private final double chance;
    private final Predicate<Location> predicate;
    private final boolean randomize;

    public RandomSpawn(@Nonnull EntityType type, int requiredValue, double chance) {
        this(type, requiredValue, chance, true, location -> true);
    }

    public RandomSpawn(@Nonnull EntityType type, int requiredValue, double chance, boolean randomize) {
        this(type, requiredValue, chance, randomize, location -> true);
    }

    public RandomSpawn(@Nonnull EntityType type, int requiredValue, double chance, Predicate<Location> predicate) {
        this(type, requiredValue, chance, true, predicate);
    }

    public RandomSpawn(@Nonnull EntityType type,
                       int requiredValue,
                       double chance,
                       boolean randomize,
                       Predicate<Location> predicate
    ) {
        Validate.isTrue(type.isAlive(), "Only LivingEntities can be RandomSpawns");
        Validate.isTrue(type.isSpawnable(), "Specified type is not spawnable");
        this.type = type;
        this.requiredPurification = requiredValue;
        this.chance = chance;
        this.randomize = randomize;
        this.predicate = predicate;
    }

    public EntityType getType() {
        return type;
    }

    public int getRequiredPurification() {
        return requiredPurification;
    }

    public double getChance() {
        return chance;
    }

    public boolean trySpawn(@Nonnull Location location) {
        int purificationLevel = Purification.getValue(location.getChunk());
        return trySpawn(location, purificationLevel);
    }

    public boolean trySpawn(@Nonnull Location location, int purificationLevel) {
        if (purificationLevel >= this.requiredPurification) {
            final double random = ThreadLocalRandom.current().nextDouble();
            if (random <= this.chance && this.predicate.test(location) && hasEnoughSpace(location)) {


                LivingEntity livingEntity = (LivingEntity) location.getWorld().spawnEntity(
                    location,
                    this.type,
                    this.randomize
                );
                livingEntity.setRemoveWhenFarAway(true);
                livingEntity.setNoDamageTicks(20);
                return true;
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
