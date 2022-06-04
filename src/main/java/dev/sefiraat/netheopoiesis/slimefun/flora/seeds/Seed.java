package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import dev.sefiraat.netheopoiesis.utils.Skulls;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Location;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.Map;
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
    Set<SlimefunItem> getValidPlaces();

    double getGrowthRate();

    LinkedList<Skulls> getGrowthPhases();
}
