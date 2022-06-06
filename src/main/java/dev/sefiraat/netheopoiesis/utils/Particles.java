package dev.sefiraat.netheopoiesis.utils;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.ThreadLocalRandom;

public final class Particles {

    private Particles() {
        throw new IllegalStateException("Utility class");
    }

    @ParametersAreNonnullByDefault
    public static void displayParticleRandomly(Entity entity, Particle particle, double rangeRadius) {
        displayParticleRandomly(entity.getLocation(), particle, rangeRadius, 5);
    }

    @ParametersAreNonnullByDefault
    public static void displayParticleRandomly(Location location,
                                               Particle particle,
                                               double rangeRadius,
                                               int numberOfParticles
    ) {
        for (int i = 0; i < numberOfParticles; i++) {
            double x = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            double y = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            double z = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            location.getWorld().spawnParticle(particle, location.clone().add(x, y, z), 1);
        }
    }

    @ParametersAreNonnullByDefault
    public static void displayParticleRandomly(Entity entity,
                                               Particle particle,
                                               double rangeRadius,
                                               int numberOfParticles
    ) {
        displayParticleRandomly(entity.getLocation().clone().add(0, 1, 0), particle, rangeRadius, numberOfParticles);
    }

    @ParametersAreNonnullByDefault
    public static void displayParticleRandomly(Location location, Particle particle, double rangeRadius) {
        displayParticleRandomly(location, particle, rangeRadius, 5);
    }

    @ParametersAreNonnullByDefault
    public static void displayParticleRandomly(Entity entity,
                                               double rangeRadius,
                                               int numberOfParticles,
                                               Particle.DustOptions dustOptions
    ) {
        displayParticleRandomly(entity.getLocation(), rangeRadius, numberOfParticles, dustOptions);
    }

    @ParametersAreNonnullByDefault
    public static void displayParticleRandomly(Location location,
                                               double rangeRadius,
                                               int numberOfParticles,
                                               Particle.DustOptions dustOptions
    ) {
        for (int i = 0; i < numberOfParticles; i++) {
            double x = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            double y = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            double z = ThreadLocalRandom.current().nextDouble(-rangeRadius, rangeRadius + 0.1);
            location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(x, y, z), 1, dustOptions);
        }
    }

    @ParametersAreNonnullByDefault
    public static void displayParticleRandomly(Entity entity, double rangeRadius, Particle.DustOptions dustOptions) {
        displayParticleRandomly(entity.getLocation(), rangeRadius, 5, dustOptions);
    }
}
