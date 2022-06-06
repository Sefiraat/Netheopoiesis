package dev.sefiraat.netheopoiesis.utils;

import org.bukkit.World;

import javax.annotation.Nonnull;

public final class WorldUtils {

    private WorldUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Checks if the world is provided is NORMAL
     *
     * @param world The {@link World} to check the environment of
     * @return true if the {@link World.Environment} is NORMAL
     */
    public static boolean inOverworld(@Nonnull World world) {
        return world.getEnvironment() == World.Environment.NORMAL;
    }

    /**
     * Checks if the world is provided is NETHER
     *
     * @param world The {@link World} to check the environment of
     * @return true if the {@link World.Environment} is NETHER
     */
    public static boolean inNether(@Nonnull World world) {
        return world.getEnvironment() == World.Environment.NETHER;
    }

    /**
     * Checks if the world is provided is END
     *
     * @param world The {@link World} to check the environment of
     * @return true if the {@link World.Environment} is END
     */
    public static boolean inEnd(@Nonnull World world) {
        return world.getEnvironment() == World.Environment.THE_END;
    }
}
