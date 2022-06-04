package dev.sefiraat.netheopoiesis.slimefun;

import dev.sefiraat.netheopoiesis.utils.Skulls;
import dev.sefiraat.netheopoiesis.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.Material;

import javax.annotation.Nonnull;
import java.util.stream.Stream;

public class NpsSlimefunItemStacks {

    private NpsSlimefunItemStacks() {
        throw new IllegalStateException("Utility class");
    }

    // Seeds
    public static final SlimefunItemStack PURIFICATION_SEED;

    // Crux'
    public static final SlimefunItemStack BASIC_PURIFIED_NETHERRACK;

    static {

        // region Seeds

        PURIFICATION_SEED = Theme.themedSeed(
            "NPS_PURIFICATION_SEED",
            Skulls.SEED_BLUE.getPlayerHead(),
            Theme.CRAFTING,
            "Purification Seed",
            new String[]{"This seed, when fully grown,", "will slowly purify surrounding", "blocks."},
            getCanBePlacedOnLore("Netherrack", "Warped Nylium", "Crimson Nylium")
        );

        // endregion

        // region Crux'

        BASIC_PURIFIED_NETHERRACK = Theme.themedSlimefunItemStack(
            "NPS_BASIC_PURIFIED_NETHERRACK",
            Material.MYCELIUM,
            Theme.CRAFTING,
            "Basic Purified Netherrack",
            "This Netherrack has been purified",
            "to allow more complex seeds to",
            "be grown on it."
        );

        // endregion
    }

    @Nonnull
    public static String[] getCanBePlacedOnLore(@Nonnull String... validPlacements) {
        String[] strings = new String[]{
            "",
            Theme.CRUX + "Can be grown on:"
        };
        return Stream.of(strings, validPlacements).flatMap(Stream::of).toArray(String[]::new);
    }
}
