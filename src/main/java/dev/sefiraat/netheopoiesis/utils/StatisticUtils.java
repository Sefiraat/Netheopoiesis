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
    private static final MessageFormat TRADE_UNLOCK = new MessageFormat("{0}.TRADING.{1}.UNLOCKED");

    @ParametersAreNonnullByDefault
    public static void unlockDiscovery(Player player, String itemId) {
        unlockDiscovery(player.getUniqueId(), itemId);
    }

    @ParametersAreNonnullByDefault
    public static void unlockDiscovery(UUID player, String itemId) {
        Netheopoiesis.getConfigManager().getDiscoveries().set(BREED_UNLOCK.format(new Object[]{player, itemId}), true);
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

    @ParametersAreNonnullByDefault
    public static void unlockTrade(Player player, String itemId) {
        unlockTrade(player.getUniqueId(), itemId);
    }

    @ParametersAreNonnullByDefault
    public static void unlockTrade(UUID player, String itemId) {
        Netheopoiesis.getConfigManager().getDiscoveries().set(TRADE_UNLOCK.format(new Object[]{player, itemId}), true);
    }

    @ParametersAreNonnullByDefault
    public static boolean isTradeFound(Player player, String tradeId) {
        return isTradeFound(player.getUniqueId(), tradeId);
    }

    @ParametersAreNonnullByDefault
    public static boolean isTradeFound(UUID player, String tradeId) {
        return Netheopoiesis.getConfigManager().getDiscoveries().getBoolean(
            TRADE_UNLOCK.format(new Object[]{player, tradeId})
        );
    }
}

