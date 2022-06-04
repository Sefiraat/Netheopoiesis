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
    public static final SlimefunItemStack SPINDLE_SEED;




    public static final SlimefunItemStack OAKENDRAN_SEED;

    // Crux'
    public static final SlimefunItemStack BASIC_PURIFIED_NETHERRACK;
    public static final SlimefunItemStack PURIFIED_NETHERRACK;
    public static final SlimefunItemStack VORACIOUS_DIRT;
    public static final SlimefunItemStack NETHER_DIRT;
    public static final SlimefunItemStack NETHER_GRASS;

    static {

        // region Seeds

        PURIFICATION_SEED = Theme.themedSeed(
            "NPS_PURIFICATION_SEED",
            Skulls.SEED_BLUE.getPlayerHead(),
            Theme.SEED,
            "Purification Seed",
            new String[]{"This seed, when fully grown,", "will slowly purify surrounding", "blocks."},
            getCanBePlacedOnLore("Netherrack", "Warped Nylium", "Crimson Nylium")
        );

        SPINDLE_SEED = Theme.themedSeed(
            "NPS_SPINDLE_SEED",
            Skulls.SEED_RED.getPlayerHead(),
            Theme.SEED,
            "Spindle Seed",
            new String[]{"This seed, when fully grown,", "will grow it's roots out which", "can be harvested."},
            getCanBePlacedOnLore("Basic Purified Netherrack (or better)")
        );






        OAKENDRAN_SEED = Theme.themedSeed(
            "NPS_OAKENDRAN_SEED",
            Skulls.SEED_ORANGE.getPlayerHead(),
            Theme.SEED,
            "Stiff Seed",
            new String[]{"This seed, when fully grown,", "may cause trees to spontaneously", "grow."},
            getCanBePlacedOnLore("Purified Netherrack (or better)")
        );

        // endregion

        // region Crux'

        BASIC_PURIFIED_NETHERRACK = Theme.themedSlimefunItemStack(
            "NPS_BASIC_PURIFIED_NETHERRACK",
            Material.MYCELIUM,
            Theme.CRUX,
            "Basic Purified Netherrack",
            "This Netherrack has been purified",
            "to allow more complex seeds to",
            "be grown on it."
        );

        PURIFIED_NETHERRACK = Theme.themedSlimefunItemStack(
            "NPS_PURIFIED_NETHERRACK",
            Material.PODZOL,
            Theme.CRUX,
            "Purified Netherrack",
            "This Netherrack has been purified",
            "to allow more complex seeds to",
            "be grown on it."
        );

        VORACIOUS_DIRT = Theme.themedSlimefunItemStack(
            "NPS_VORACIOUS_DIRT",
            Material.ROOTED_DIRT,
            Theme.CRUX,
            "Voracious Dirt",
            "The Netherrack has been purified to",
            "a point in which it is a viable soil."
        );

        NETHER_DIRT = Theme.themedSlimefunItemStack(
            "NPS_NETHER_DIRT",
            Material.DIRT,
            Theme.CRUX,
            "Nether Dirt",
            "The Netherrack has been purified to",
            "a point in which it is a good soil."
        );

        NETHER_GRASS = Theme.themedSlimefunItemStack(
            "NPS_NETHER_GRASS",
            Material.GRASS_BLOCK,
            Theme.CRUX,
            "Nether Grass",
            "This grass is positively healthy."
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
