package dev.sefiraat.netheopoiesis.utils;

import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataHolder;

import javax.annotation.ParametersAreNonnullByDefault;

public final class Cooldowns {

    private Cooldowns() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Adds a time to the provided {@link ItemStack} that can be checked
     * to see if the item is on cooldown.
     * see {@link Cooldowns#isOnCooldown(NamespacedKey, ItemStack)}
     *
     * @param key               A {@link NamespacedKey} for this cooldown
     * @param itemStack         The {@link ItemStack} to put on cooldown
     * @param durationInSeconds The duration in seconds to put the stack on cooldown for
     */
    @ParametersAreNonnullByDefault
    public static void addCooldown(NamespacedKey key, ItemStack itemStack, int durationInSeconds) {
        addCooldown(key, itemStack, durationInSeconds * 1000L);
    }

    /**
     * Adds a time to the provided {@link ItemStack} that can be checked
     * to see if the item is on cooldown.
     * see {@link Cooldowns#isOnCooldown(NamespacedKey, ItemStack)}
     *
     * @param key          A {@link NamespacedKey} for this cooldown
     * @param itemStack    The {@link ItemStack} to put on cooldown
     * @param durationInMs The duration in milliseconds to put the stack on cooldown for
     */
    @ParametersAreNonnullByDefault
    public static void addCooldown(NamespacedKey key, ItemStack itemStack, long durationInMs) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        addCooldown(key, itemMeta, durationInMs);
    }

    /**
     * Adds a time to the provided {@link ItemStack} that can be checked
     * to see if the item is on cooldown.
     * see {@link Cooldowns#isOnCooldown(NamespacedKey, ItemStack)}
     *
     * @param key               A {@link NamespacedKey} for this cooldown
     * @param holder            The {@link PersistentDataHolder} to put on cooldown
     * @param durationInSeconds The duration in seconds to put the stack on cooldown for
     */
    @ParametersAreNonnullByDefault
    public static void addCooldown(NamespacedKey key, PersistentDataHolder holder, int durationInSeconds) {
        addCooldown(key, holder, durationInSeconds * 1000L);
    }

    /**
     * Adds a time to the provided {@link ItemStack} that can be checked
     * to see if the item is on cooldown.
     * see {@link Cooldowns#isOnCooldown(NamespacedKey, ItemStack)}
     *
     * @param key          A {@link NamespacedKey} for this cooldown
     * @param holder       The {@link PersistentDataHolder} to put on cooldown
     * @param durationInMs The duration in milliseconds to put the stack on cooldown for
     */
    @ParametersAreNonnullByDefault
    public static void addCooldown(NamespacedKey key, PersistentDataHolder holder, long durationInMs) {
        PersistentDataAPI.setLong(holder, key, System.currentTimeMillis() + durationInMs);
    }

    /**
     * Checks if the item has a cooldown time and returns if it has not yet expired
     * or false if it doesn't have a matching cooldown
     *
     * @param key       A {@link NamespacedKey} matching the cooldown to retrieve
     * @param itemStack The {@link ItemStack} to put on cooldown
     * @return True if the item is still on cooldown. False if expired or has no cooldown
     */
    @ParametersAreNonnullByDefault
    public static boolean isOnCooldown(NamespacedKey key, ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        return isOnCooldown(key, itemMeta);
    }

    /**
     * Checks if the item has a cooldown time and returns if it has not yet expired
     * or false if it doesn't have a matching cooldown
     *
     * @param key    A {@link NamespacedKey} matching the cooldown to retrieve
     * @param holder The {@link PersistentDataHolder} to put on cooldown
     * @return True if the item is still on cooldown. False if expired or has no cooldown
     */
    @ParametersAreNonnullByDefault
    public static boolean isOnCooldown(NamespacedKey key, PersistentDataHolder holder) {
        long cooldownUntil = PersistentDataAPI.getLong(holder, key, 0);
        return System.currentTimeMillis() < cooldownUntil;
    }
}
