package dev.sefiraat.netheopoiesis.utils;

import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataHolder;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public final class Cooldowns {

    private Cooldowns() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Adds a time to the provided {@link ItemStack} that can be checked
     * to see if the item is on cooldown.
     * see {@link Cooldowns#isOnCooldown(ItemStack)}
     *
     * @param itemStack         The {@link ItemStack} to put on cooldown
     * @param durationInSeconds The duration in seconds to put the stack on cooldown for
     */
    public static void addCooldown(@Nonnull ItemStack itemStack, int durationInSeconds) {
        addCooldown(itemStack, durationInSeconds * 1000L);
    }

    /**
     * Adds a time to the provided {@link ItemStack} that can be checked
     * to see if the item is on cooldown.
     * see {@link Cooldowns#isOnCooldown(ItemStack)}
     *
     * @param itemStack    The {@link ItemStack} to put on cooldown
     * @param durationInMs The duration in milliseconds to put the stack on cooldown for
     */
    public static void addCooldown(@Nonnull ItemStack itemStack, long durationInMs) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        addCooldown(itemMeta, durationInMs);
        itemStack.setItemMeta(itemMeta);
    }

    /**
     * Adds a time to the provided {@link ItemStack} that can be checked
     * to see if the item is on cooldown.
     * see {@link Cooldowns#isOnCooldown(ItemStack)}
     *
     * @param holder            The {@link PersistentDataHolder} to put on cooldown
     * @param durationInSeconds The duration in seconds to put the stack on cooldown for
     */
    public static void addCooldown(@Nonnull PersistentDataHolder holder, int durationInSeconds) {
        addCooldown(holder, durationInSeconds * 1000L);
    }

    /**
     * Adds a time to the provided {@link ItemStack} that can be checked
     * to see if the item is on cooldown.
     * see {@link Cooldowns#isOnCooldown(ItemStack)}
     *
     * @param holder       The {@link PersistentDataHolder} to put on cooldown
     * @param durationInMs The duration in milliseconds to put the stack on cooldown for
     */
    public static void addCooldown(@Nonnull PersistentDataHolder holder, long durationInMs) {
        PersistentDataAPI.setLong(holder, Keys.COOLDOWN, System.currentTimeMillis() + durationInMs);
    }

    /**
     * Checks if the item has a cooldown time and returns if it has not yet expired
     * or false if it doesn't have a matching cooldown
     *
     * @param itemStack The {@link ItemStack} to put on cooldown
     * @return True if the item is still on cooldown. False if expired or has no cooldown
     */
    public static boolean isOnCooldown(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        return isOnCooldown(itemMeta);
    }

    /**
     * Checks if the item has a cooldown time and returns if it has not yet expired
     * or false if it doesn't have a matching cooldown
     *
     * @param holder The {@link PersistentDataHolder} to put on cooldown
     * @return True if the item is still on cooldown. False if expired or has no cooldown
     */
    @ParametersAreNonnullByDefault
    public static boolean isOnCooldown(PersistentDataHolder holder) {
        long cooldownUntil = PersistentDataAPI.getLong(holder, Keys.COOLDOWN, 0);
        return System.currentTimeMillis() < cooldownUntil;
    }
}
