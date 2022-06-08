package dev.sefiraat.netheopoiesis.utils;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import org.bukkit.entity.Player;

import javax.annotation.ParametersAreNonnullByDefault;
import java.text.MessageFormat;
import java.util.UUID;

public final class StatisticUtils {

    private StatisticUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final MessageFormat BREED_UNLOCK = new MessageFormat("{0}.BREEDING.{1}.UNLOCKED");

    @ParametersAreNonnullByDefault
    public static void unlockDiscovery(Player player, String itemId) {
        unlockDiscovery(player.getUniqueId(), itemId);
    }

    @ParametersAreNonnullByDefault
    public static void unlockDiscovery(UUID player, String itemId) {
        Netheopoiesis.getConfigManager().getDiscoveries().set(
            BREED_UNLOCK.format(new Object[]{player, itemId}), true);
    }

    @ParametersAreNonnullByDefault
    public static boolean isDiscovered(Player player, String itemId) {
        return isDiscovered(player.getUniqueId(), itemId);
    }

    @ParametersAreNonnullByDefault
    public static boolean isDiscovered(UUID player, String itemId) {
        return Netheopoiesis.getConfigManager().getDiscoveries().getBoolean(
            BREED_UNLOCK.format(new Object[]{player, itemId})
        );
    }
}

