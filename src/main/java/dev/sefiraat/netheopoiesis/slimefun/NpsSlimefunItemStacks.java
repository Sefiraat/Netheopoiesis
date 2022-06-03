package dev.sefiraat.netheopoiesis.slimefun;

import dev.sefiraat.netheopoiesis.utils.ItemStackUtils;
import dev.sefiraat.netheopoiesis.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public class NpsSlimefunItemStacks {

    private NpsSlimefunItemStacks() {
        throw new IllegalStateException("Utility class");
    }

    // Seeds
    public static final SlimefunItemStack PURIFICATION_SEED;

    static {

        // region Seeds

        PURIFICATION_SEED = Theme.themedSlimefunItemStack(
            "NPS_PURIFICATION_SEED",
            ItemStackUtils.enchantedItemStack(Material.COAL, true, new Pair<>(Enchantment.LURE, 1)),
            Theme.CRAFTING,
            "Unorthodox Coal",
            "The molecules in this coal have been",
            "rearranged. Seemingly with little effect."
        );

        // endregion
    }
}
