package dev.sefiraat.netheopoiesis.utils;

import org.bukkit.World;

import javax.annotation.Nonnull;

/**
 * This enum holds some common time periods and the start/end time represented in Minecraft's single long value
 * Also provides static methods to check common time-based events and intersecting periods
 */
public enum TimePeriod {
    SUNRISE(23000, 23999),
    DAY(24000, 11999),
    SUNSET(12000, 12999),
    NIGHT(13000, 22999),
    WAKE_UP(24000, 24000),
    BED_TIME_RAIN(12010, 12010),
    BED_TIME_CLEAR(12542, 12542),
    MOON_HIDE(167, 167),
    MOON_SHOW(11834, 11834),
    VILLAGER_WORK(2000, 8999),
    VILLAGER_SOCIALISE(9000, 11999),
    VILLAGER_BED_TIME(12000, 23999),
    SKY_LIGHT_WAX_CLEAR(22331, 23961),
    SKY_LIGHT_WAX_RAIN(22331, 23992),
    SKY_LIGHT_WANE_CLEAR(12040, 13670),
    SKY_LIGHT_WANE_RAIN(12010, 13670),
    MOB_SPAWN_CLEAR(13188, 22812),
    MOB_SPAWN_RAIN(12969, 23031);

    private final long start;
    private final long end;

    TimePeriod(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    public static boolean isDay(@Nonnull World world) {
        return isDay(world.getTime());
    }

    public static boolean isDay(long time) {
        return time < 13000 || time > 24000;
    }

    public static boolean isNight(@Nonnull World world) {
        return isNight(world.getTime());
    }

    public static boolean isNight(long time) {
        return !isDay(time);
    }

    /**
     * Returns if the given time period would be active within the given world
     *
     * @param world      The world to get the time from
     * @param timePeriod The TimePeriod to check is active
     * @return True if the time and the period intersect
     */
    public static boolean isActive(@Nonnull World world, @Nonnull TimePeriod timePeriod) {
        return isActive(world.getTime(), timePeriod);
    }

    /**
     * Returns if the given time period would be active during the given time
     *
     * @param time       The time to check against
     * @param timePeriod The TimePeriod to check is active
     * @return True if the time and the period intersect
     */
    public static boolean isActive(long time, @Nonnull TimePeriod timePeriod) {
        return time >= timePeriod.getStart() && time <= timePeriod.getEnd();
    }

    /**
     * Returns if villagers should be awake during the given time
     *
     * @param world the world to get the time from
     * @return True if they should be awake
     */
    public static boolean villagersAwake(@Nonnull World world) {
        return villagersAwake(world.getTime());
    }

    /**
     * Returns if villagers should be awake during the given time
     *
     * @param time The time to check against
     * @return True if they should be awake
     */
    public static boolean villagersAwake(long time) {
        return time >= WAKE_UP.getStart() && time <= VILLAGER_BED_TIME.getEnd();
    }

    /**
     * Returns if the moon is still visible in the Sky.
     *
     * @param world The world to check.
     * @return True if the moon is/would be out, false if not or wrong world type.
     */
    public static boolean moonOut(@Nonnull World world) {
        if (world.getEnvironment() == World.Environment.NORMAL) {
            return moonOut(world.getTime());
        }
        return false;
    }

    /**
     * Returns if the moon is still visible in the Sky during the specified time.
     * This method assumes the world is Overworld.
     *
     * @param time The time to check.
     * @return True if the moon is/would be out
     */
    public static boolean moonOut(long time) {
        return time >= MOON_SHOW.getStart() && time <= MOON_HIDE.getEnd();
    }

    public static boolean naturalMobsCanSpawn(@Nonnull World world) {
        long time = world.getTime();
        return world.isClearWeather()
               ? naturalMobsCanSpawn(time, false)
               : naturalMobsCanSpawn(time, true);
    }

    public static boolean naturalMobsCanSpawn(long time, boolean rain) {
        return rain
               ? time >= MOB_SPAWN_RAIN.getStart() && time <= MOB_SPAWN_RAIN.getEnd()
               : time >= MOB_SPAWN_CLEAR.getStart() && time <= MOB_SPAWN_CLEAR.getEnd();
    }

    /**
     * Returns if the given world is dark.
     *
     * @param world The world to check.
     * @return True if past sunset/before sunrise or in a different world.
     */
    public static boolean isDark(@Nonnull World world) {
        return !isLight(world);
    }

    /**
     * Returns if the given world is light.
     *
     * @param world The world to check.
     * @return True if past sunrise/before sunset or in a different world.
     */
    public static boolean isLight(@Nonnull World world) {
        if (world.getEnvironment() == World.Environment.NORMAL) {
            return isDay(world.getTime());
        }
        return false;
    }
}
