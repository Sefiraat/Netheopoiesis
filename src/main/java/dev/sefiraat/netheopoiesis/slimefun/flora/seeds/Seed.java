package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Location;
import org.bukkit.Material;

import javax.annotation.Nonnull;
import java.util.Set;

public interface Seed {

    default void onTickAlways(@Nonnull Location location, @Nonnull Config data) {

    }

    default void onTickFullyGrown(@Nonnull Location location, @Nonnull Config data) {

    }

    default void onGrowthStep(@Nonnull Location location, @Nonnull Config data) {

    }

    default void onFullyMatures(@Nonnull Location location, @Nonnull Config data) {

    }

    @Nonnull
    Set<Material> getValidPlaces();

    double getGrowthRate();
}
