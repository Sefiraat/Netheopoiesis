package dev.sefiraat.netheopoiesis.utils;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.ThreadLocalRandom;

public final class ParticleUtils {

    private ParticleUtils() {
        throw new IllegalStateException("Utility class");
    }

    @ParametersAreNonnullByDefault
    public static void randomSpread(Entity entity, Particle particle, double rangeRadius) {
        randomSpread(entity.getLocation(), particle, rangeRadius, 5);
    }

    @ParametersAreNonnullByDefault
    public static void randomSpread(Location location,
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
    public static void randomSpread(Entity entity,
                                    Particle particle,
                                    double rangeRadius,
                                    int numberOfParticles
    ) {
        randomSpread(entity.getLocation().clone().add(0, 1, 0), particle, rangeRadius, numberOfParticles);
    }

    @ParametersAreNonnullByDefault
    public static void randomSpread(Location location, Particle particle, double rangeRadius) {
        randomSpread(location, particle, rangeRadius, 5);
    }

    @ParametersAreNonnullByDefault
    public static void randomSpread(Entity entity,
                                    double rangeRadius,
                                    int numberOfParticles,
                                    Particle.DustOptions dustOptions
    ) {
        randomSpread(entity.getLocation(), rangeRadius, numberOfParticles, dustOptions);
    }

    @ParametersAreNonnullByDefault
    public static void randomSpread(Location location,
                                    double radius,
                                    int number,
                                    Particle.DustOptions options
    ) {
        for (int i = 0; i < number; i++) {
            double x = ThreadLocalRandom.current().nextDouble(-radius, radius + 0.1);
            double y = ThreadLocalRandom.current().nextDouble(-radius, radius + 0.1);
            double z = ThreadLocalRandom.current().nextDouble(-radius, radius + 0.1);
            location.getWorld().spawnParticle(Particle.REDSTONE, location.clone().add(x, y, z), 1, options);
        }
    }

    @ParametersAreNonnullByDefault
    public static void randomSpread(Entity entity, double rangeRadius, Particle.DustOptions dustOptions) {
        randomSpread(entity.getLocation(), rangeRadius, 5, dustOptions);
    }

    @ParametersAreNonnullByDefault
    public static void drawLine(Particle particle, Location start, Location end, double space) {
        drawLine(particle, start, end, space, null);
    }

    @ParametersAreNonnullByDefault
    public static void drawLine(Location start, Location end, double space, @Nonnull Particle.DustOptions dustOptions) {
        drawLine(Particle.REDSTONE, start, end, space, dustOptions);
    }

    @ParametersAreNonnullByDefault
    public static void drawLine(Particle particle, Location start, Location end, double space, @Nullable Particle.DustOptions dustOptions) {
        final double distance = start.distance(end);
        final Vector startVector = start.toVector();
        final Vector endVector = end.toVector();
        final Vector vector = endVector.clone().subtract(startVector).normalize().multiply(space);

        double currentPoint = 0;

        while (currentPoint < distance) {
            if (dustOptions != null) {
                start.getWorld().spawnParticle(
                    particle,
                    startVector.getX(),
                    startVector.getY(),
                    startVector.getZ(),
                    1,
                    dustOptions
                );
            } else {
                start.getWorld().spawnParticle(
                    particle,
                    startVector.getX(),
                    startVector.getY(),
                    startVector.getZ(),
                    1
                );
            }
            currentPoint += space;
            startVector.add(vector);
        }
    }
}
