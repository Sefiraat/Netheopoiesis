package dev.sefiraat.netheopoiesis.utils;

import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.UUID;

public final class Protection {

    private Protection() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean hasPermission(@Nonnull Player player, @Nonnull Block block, @Nonnull Interaction interaction) {
        return hasPermission(player.getUniqueId(), block.getLocation(), interaction);
    }

    public static boolean hasPermission(@Nonnull Player player, @Nonnull Location location, @Nonnull Interaction interaction) {
        return hasPermission(player.getUniqueId(), location, interaction);
    }

    public static boolean hasPermission(@Nonnull UUID player, @Nonnull Block block, @Nonnull Interaction interaction) {
        return hasPermission(player, block.getLocation(), interaction);
    }

    public static boolean hasPermission(@Nonnull UUID player, @Nonnull Location location, @Nonnull Interaction interaction) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player);
        return Slimefun.getProtectionManager().hasPermission(offlinePlayer, location, interaction);
    }
}
