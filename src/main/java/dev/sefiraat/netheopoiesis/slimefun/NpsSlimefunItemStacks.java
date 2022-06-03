package dev.sefiraat.netheopoiesis.slimefun;

import dev.sefiraat.netheopoiesis.utils.ItemStackUtils;
import dev.sefiraat.netheopoiesis.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class NpsSlimefunItemStacks {

    private NpsSlimefunItemStacks() {
        throw new IllegalStateException("Utility class");
    }

    // Vanilla
    public static final ItemStack VANILLA_COAL = new ItemStack(Material.COAL);

    // Materials
    public static final SlimefunItemStack UNORTHODOX_COAL;

    static {

        // region Crafting Materials

        UNORTHODOX_COAL = Theme.themedSlimefunItemStack(
            "ETC_UNORTHODOX_COAL",
            ItemStackUtils.enchantedItemStack(Material.COAL, true, new Pair<>(Enchantment.LURE, 1)),
            Theme.CRAFTING,
            "Unorthodox Coal",
            "The molecules in this coal have been",
            "rearranged. Seemingly with little effect."
        );

        // endregion
    }
}
