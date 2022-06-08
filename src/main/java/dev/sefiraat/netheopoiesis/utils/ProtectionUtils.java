package dev.sefiraat.netheopoiesis.utils;

import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.UUID;

public final class ProtectionUtils {

    private ProtectionUtils() {
        throw new IllegalStateException("Utility class");
    }

    @ParametersAreNonnullByDefault
    public static boolean hasPermission(Player player, Block block, Interaction interaction) {
        return hasPermission(player.getUniqueId(), block.getLocation(), interaction);
    }

    @ParametersAreNonnullByDefault
    public static boolean hasPermission(Player player, Location location, Interaction interaction) {
        return hasPermission(player.getUniqueId(), location, interaction);
    }

    @ParametersAreNonnullByDefault
    public static boolean hasPermission(UUID player, Block block, Interaction interaction) {
        return hasPermission(player, block.getLocation(), interaction);
    }

    @ParametersAreNonnullByDefault
    public static boolean hasPermission(UUID player, Location location, Interaction interaction) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player);
        return Slimefun.getProtectionManager().hasPermission(offlinePlayer, location, interaction);
    }
}
