package dev.sefiraat.netheopoiesis.utils;

import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public final class ItemStackUtils {

    private ItemStackUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * @param color
     * @return
     */
    @Nonnull
    public static ItemStack potion(@Nonnull Color color) {
        ItemStack itemStack = new ItemStack(Material.POTION);
        PotionMeta potionMeta = (PotionMeta) itemStack.getItemMeta();
        potionMeta.setBasePotionData(new PotionData(PotionType.WATER));
        potionMeta.setColor(color);
        potionMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        itemStack.setItemMeta(potionMeta);
        return itemStack;
    }

    /**
     * @param material
     * @return
     */
    @Nonnull
    public static ItemStack enchantedItemStack(@Nonnull Material material) {
        return enchantedItemStack(material, true, new Pair<>(Enchantment.LURE, 1));
    }

    /**
     * @param material
     * @param enchantments
     * @return
     */
    @Nonnull
    @SafeVarargs
    public static ItemStack enchantedItemStack(@Nonnull Material material,
                                               @Nonnull Pair<Enchantment, Integer>... enchantments
    ) {
        return enchantedItemStack(material, true, enchantments);
    }

    /**
     * @param material
     * @param hide
     * @param enchantments
     * @return
     */
    @Nonnull
    @SafeVarargs
    public static ItemStack enchantedItemStack(@Nonnull Material material,
                                               boolean hide,
                                               @Nonnull Pair<Enchantment, Integer>... enchantments
    ) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        for (Pair<Enchantment, Integer> pair : enchantments) {
            itemMeta.addEnchant(pair.getFirstValue(), pair.getSecondValue(), true);
        }
        if (hide) {
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    /**
     * Adds a time to the provided {@link ItemStack} that can be checked
     * to see if the item is on cooldown.
     * see {@link ItemStackUtils#isOnCooldown(ItemStack)}
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
     * see {@link ItemStackUtils#isOnCooldown(ItemStack)}
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
     * see {@link ItemStackUtils#isOnCooldown(ItemStack)}
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
     * see {@link ItemStackUtils#isOnCooldown(ItemStack)}
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
