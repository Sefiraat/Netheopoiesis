package dev.sefiraat.netheopoiesis.utils;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.text.MessageFormat;
import java.util.UUID;

public final class StatisticUtils {

    private StatisticUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final MessageFormat BREED_UNLOCK = new MessageFormat("{0}.BREEDING.{1}.UNLOCKED");
    private static final MessageFormat TRADE_UNLOCK = new MessageFormat("{0}.TRADING.{1}.UNLOCKED");

    public static void unlockDiscovery(@Nonnull Player player, @Nonnull String itemId) {
        unlockDiscovery(player.getUniqueId(), itemId);
    }

    public static void unlockDiscovery(@Nonnull UUID player, @Nonnull String itemId) {
        Netheopoiesis.getConfigManager().getDiscoveries().set(BREED_UNLOCK.format(new Object[]{player, itemId}), true);
    }

    public static boolean isDiscovered(@Nonnull Player player, @Nonnull String itemId) {
        return isDiscovered(player.getUniqueId(), itemId);
    }

    public static boolean isDiscovered(@Nonnull UUID player, @Nonnull String itemId) {
        return Netheopoiesis.getConfigManager().getDiscoveries().getBoolean(
            BREED_UNLOCK.format(new Object[]{player, itemId})
        );
    }

    public static void unlockTrade(@Nonnull Player player, @Nonnull String itemId) {
        unlockTrade(player.getUniqueId(), itemId);
    }

    public static void unlockTrade(@Nonnull UUID player, @Nonnull String itemId) {
        Netheopoiesis.getConfigManager().getDiscoveries().set(TRADE_UNLOCK.format(new Object[]{player, itemId}), true);
    }

    public static boolean isTradeFound(@Nonnull Player player, @Nonnull String tradeId) {
        return isTradeFound(player.getUniqueId(), tradeId);
    }

    public static boolean isTradeFound(@Nonnull UUID player, @Nonnull String tradeId) {
        return Netheopoiesis.getConfigManager().getDiscoveries().getBoolean(
            TRADE_UNLOCK.format(new Object[]{player, tradeId})
        );
    }
}

