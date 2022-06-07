package dev.sefiraat.netheopoiesis.slimefun;

import dev.sefiraat.netheopoiesis.utils.ItemStackUtils;
import dev.sefiraat.netheopoiesis.utils.Skulls;
import dev.sefiraat.netheopoiesis.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import org.bukkit.Color;
import org.bukkit.Material;

import javax.annotation.Nonnull;
import java.util.stream.Stream;

public class NpsStacks {

    private NpsStacks() {
        throw new IllegalStateException("Utility class");
    }

    // region Crafting

    public static final SlimefunItemStack ADDON_BERRY = Theme.themedSlimefunItemStack(
        "NPS_ADDON_BERRY",
        Material.SWEET_BERRIES,
        Theme.CRAFTING,
        "Addon Berry",
        "A deliciously sweet berry that",
        "can be made into jam."
    );
    public static final SlimefunItemStack ADDON_JAM = Theme.themedSlimefunItemStack(
        "NPS_ADDON_JAM",
        ItemStackUtils.potion(Color.RED),
        Theme.CRAFTING,
        "Addon Jam",
        "A very sweet jam.",
        "For some reason, it feels rushed",
        "and incomplete."
    );

    // endregion

    // region Tools

    public static final SlimefunItemStack CRUDE_HARVESTING_TOOL = Theme.themedSlimefunItemStack(
        "NPS_CRUDE_HARVESTING_TOOL",
        Material.PRISMARINE_SHARD,
        Theme.TOOL,
        "Crude Harvesting Tool",
        "This tool will harvest plants",
        "where possible.",
        "This one is very shoddy and won't",
        "last long",
        "",
        LoreBuilder.usesLeft(25)
    );
    public static final SlimefunItemStack HARVESTING_TOOL = Theme.themedSlimefunItemStack(
        "NPS_HARVESTING_TOOL",
        Material.PRISMARINE_SHARD,
        Theme.TOOL,
        "Harvesting Tool",
        "This tool will harvest plants",
        "where possible.",
        "This one is sturdier but still won't",
        "last long",
        "",
        LoreBuilder.usesLeft(150)
    );
    public static final SlimefunItemStack PURIFICATION_BAROMETER = Theme.themedSlimefunItemStack(
        "NPS_PURIFICATION_BAROMETER",
        Material.CLOCK,
        Theme.TOOL,
        "Purification Barometer",
        "When used, this tool will tell you",
        "how purified the current chunk is."
    );
    public static final SlimefunItemStack PURIFICATION_SCANNER = Theme.themedSlimefunItemStack(
        "NPS_PURIFICATION_SCANNER",
        Material.BRICK,
        Theme.TOOL,
        "Purification Scanner",
        "When used on a relevant block or plant,",
        "this tool will tell you what its",
        "purifying effect on the world is."
    );
    public static final SlimefunItemStack SEED_ANALYSER = Theme.themedSlimefunItemStack(
        "NPS_SEED_ANALYSER",
        Material.COMPASS,
        Theme.TOOL,
        "Seed Analyser",
        "When used on a Nether Plant, this",
        "tool will provide you information",
        "about it's type, growth and",
        "purification values."
    );
    public static final SlimefunItemStack ENDER_CAKE = Theme.themedSlimefunItemStack(
        "NPS_ENDER_CAKE",
        Material.CAKE,
        Theme.TOOL,
        "Ender Cake",
        "This tasty cake has the power",
        "to break through dimensions",
        "when eaten."
    );

    // endregion

    // region Crux'

    public static final SlimefunItemStack BASIC_PURIFIED_NETHERRACK = Theme.themedSlimefunItemStack(
        "NPS_BASIC_PURIFIED_NETHERRACK",
        Material.MYCELIUM,
        Theme.CRUX,
        "Basic Purified Netherrack",
        "This Netherrack has been purified",
        "to allow more complex seeds to",
        "be grown on it."
    );
    public static final SlimefunItemStack PURIFIED_NETHERRACK = Theme.themedSlimefunItemStack(
        "NPS_PURIFIED_NETHERRACK",
        Material.PODZOL,
        Theme.CRUX,
        "Purified Netherrack",
        "This Netherrack has been purified",
        "to allow more complex seeds to",
        "be grown on it."
    );
    public static final SlimefunItemStack VORACIOUS_DIRT = Theme.themedSlimefunItemStack(
        "NPS_VORACIOUS_DIRT",
        Material.ROOTED_DIRT,
        Theme.CRUX,
        "Voracious Dirt",
        "The Netherrack has been purified to",
        "a point in which it is a viable soil."
    );
    public static final SlimefunItemStack NETHER_DIRT = Theme.themedSlimefunItemStack(
        "NPS_NETHER_DIRT",
        Material.DIRT,
        Theme.CRUX,
        "Nether Dirt",
        "The Netherrack has been purified to",
        "a point in which it is a good soil."
    );
    public static final SlimefunItemStack NETHER_GRASS = Theme.themedSlimefunItemStack(
        "NPS_NETHER_GRASS",
        Material.GRASS_BLOCK,
        Theme.CRUX,
        "Nether Grass",
        "This grass is positively healthy."
    );

    // endregion

    // region Seeds

    //  region Spreaders

    public static final SlimefunItemStack PURIFICATION_SEED = Theme.themedSeed(
        "NPS_PURIFICATION_SEED",
        Skulls.SEED_BLUE.getPlayerHead(),
        Theme.SEED,
        "Purification Seed",
        new String[]{"This seed, when fully grown,", "will slowly purify surrounding", "blocks."},
        getCanBePlacedOnLore("Netherrack", "Warped Nylium", "Crimson Nylium")
    );

    public static final SlimefunItemStack SOUL_SEED = Theme.themedSeed(
        "NPS_SOUL_SEED",
        Skulls.SEED_BLUE.getPlayerHead(),
        Theme.SEED,
        "Soul Seed",
        new String[]{"This seed, when fully grown,", "will further push the purification", "process of nearby blocks."},
        getCanBePlacedOnLore("Basic Purified Netherrack (or better)")
    );

    public static final SlimefunItemStack SPIRIT_SEED = Theme.themedSeed(
        "NPS_SPIRIT_SEED",
        Skulls.SEED_BLUE.getPlayerHead(),
        Theme.SEED,
        "Spirit Seed",
        new String[]{"This seed, when fully grown,", "will allow an even deeper purification", "for nearby blocks."},
        getCanBePlacedOnLore("Purified Netherrack (or better)")
    );

    public static final SlimefunItemStack SAINTLY_SEED = Theme.themedSeed(
        "NPS_SAINTLY_SEED",
        Skulls.SEED_BLUE.getPlayerHead(),
        Theme.SEED,
        "Saintly Seed",
        new String[]{"This seed, when fully grown,", "will remove nearly all of the", "Nether's taint for nearby blocks."},
        getCanBePlacedOnLore("Voracious Dirt (or better)")
    );

    //        EDEN_SEED = Theme.themedSeed(
//            "NPS_EDEN_SEED",
//            Skulls.SEED_BLUE.getPlayerHead(),
//            Theme.SEED,
//            "Eden Seed",
//            new String[]{"This seed, when fully grown,", "will create and eden in the Nether."},
//            getCanBePlacedOnLore("Nether Dirt (or better)")
//        );

    // endregion

    // region Purification -> Soul

    public static final SlimefunItemStack SPINDLE_SEED = Theme.themedSeed(
        "NPS_SPINDLE_SEED",
        Skulls.SEED_ORANGE.getPlayerHead(),
        Theme.SEED,
        "Spindle Seed",
        new String[]{"This seed, when fully grown,", "will grow it's roots out which", "can be harvested."},
        getCanBePlacedOnLore("Basic Purified Netherrack (or better)")
    );

    public static final SlimefunItemStack GRAINY_SEED = Theme.themedSeed(
        "NPS_GRAINY_SEED",
        Skulls.SEED_RED.getPlayerHead(),
        Theme.SEED,
        "Grainy Seed",
        new String[]{"This seed, when fully grown,", "will slowly drop powder nearby", "which can be picked up."},
        getCanBePlacedOnLore("Basic Purified Netherrack (or better)")
    );

    public static final SlimefunItemStack STRINGY_SEED = Theme.themedSeed(
        "NPS_STRINGY_SEED",
        Skulls.SEED_GREEN.getPlayerHead(),
        Theme.SEED,
        "Stringy Seed",
        new String[]{"This seed, when fully grown,", "will slowly envelop the nearby", "area in a stringy substance."},
        getCanBePlacedOnLore("Basic Purified Netherrack (or better)")
    );

    public static final SlimefunItemStack STONEY_SEED = Theme.themedSeed(
        "NPS_STONEY_SEED",
        Skulls.SEED_PURPLE.getPlayerHead(),
        Theme.SEED,
        "Stoney Seed",
        new String[]{"This seed, when fully grown,", "will slowly produce small amounts of", "cobblestone."},
        getCanBePlacedOnLore("Basic Purified Netherrack (or better)")
    );

    public static final SlimefunItemStack DUSTY_SEED = Theme.themedSeed(
        "NPS_DUSTY_SEED",
        Skulls.SEED_CYAN.getPlayerHead(),
        Theme.SEED,
        "Dusty Seed",
        new String[]{"This seed, when fully grown,", "can be harvested with a tool for", "gravel."},
        getCanBePlacedOnLore("Basic Purified Netherrack (or better)")
    );

    public static final SlimefunItemStack SEASIDE_SEED = Theme.themedSeed(
        "NPS_SEASIDE_SEED",
        Skulls.SEED_YELLOW.getPlayerHead(),
        Theme.SEED,
        "Seaside Seed",
        new String[]{"This seed, when fully grown,", "can be harvested with a tool for", "sand."},
        getCanBePlacedOnLore("Basic Purified Netherrack (or better)")
    );

    public static final SlimefunItemStack MOLDABLE_SEED = Theme.themedSeed(
        "NPS_MOLDABLE_SEED",
        Skulls.SEED_PURPLE.getPlayerHead(),
        Theme.SEED,
        "Moldable Seed",
        new String[]{"This seed, when fully grown,", "can be harvested with a tool for", "clay balls."},
        getCanBePlacedOnLore("Basic Purified Netherrack (or better)")
    );

    public static final SlimefunItemStack WET_SEED = Theme.themedSeed(
        "NPS_WET_SEED",
        Skulls.SEED_BLUE.getPlayerHead(),
        Theme.SEED,
        "Wet Seed",
        new String[]{"This seed, when fully grown,", "will fill up a held, empty,", "bucket."},
        getCanBePlacedOnLore("Basic Purified Netherrack (or better)")
    );

    public static final SlimefunItemStack SPLINTERED_SEED = Theme.themedSeed(
        "NPS_SPLINTERED_SEED",
        Skulls.SEED_CYAN.getPlayerHead(),
        Theme.SEED,
        "Splintered Seed",
        new String[]{"This seed, when fully grown,", "will sometimes cause skeletons to", "spawn in the surrounding area."},
        getCanBePlacedOnLore("Basic Purified Netherrack (or better)")
    );

    public static final SlimefunItemStack ROTTEN_SEED = Theme.themedSeed(
        "NPS_ROTTEN_SEED",
        Skulls.SEED_GREEN.getPlayerHead(),
        Theme.SEED,
        "Rotten Seed",
        new String[]{"This seed, when fully grown,", "will sometimes cause zombies to", "spawn in the surrounding area."},
        getCanBePlacedOnLore("Basic Purified Netherrack (or better)")
    );

    // endregion

    // region Soul -> Spirit

    public static final SlimefunItemStack METALLIC_SEED = Theme.themedSeed(
        "NPS_METALLIC_SEED",
        Skulls.SEED_RED.getPlayerHead(),
        Theme.SEED,
        "Metallic Seed",
        new String[]{"This seed, when fully grown,", "can be harvested with a tool for", "iron nuggets."},
        getCanBePlacedOnLore("Purified Netherrack (or better)")
    );

    public static final SlimefunItemStack SHINY_SEED = Theme.themedSeed(
        "NPS_SHINY_SEED",
        Skulls.SEED_YELLOW.getPlayerHead(),
        Theme.SEED,
        "Shiny Seed",
        new String[]{"This seed, when fully grown,", "can be harvested with a tool for", "gold nuggets."},
        getCanBePlacedOnLore("Purified Netherrack (or better)")
    );

    public static final SlimefunItemStack SMOOTH_SEED = Theme.themedSeed(
        "NPS_SMOOTH_SEED",
        Skulls.SEED_CYAN.getPlayerHead(),
        Theme.SEED,
        "Smooth Seed",
        new String[]{"This seed, when fully grown,", "can be harvested with a tool for", "amethyst shards."},
        getCanBePlacedOnLore("Purified Netherrack (or better)")
    );

    public static final SlimefunItemStack ENCHANTED_SEED = Theme.themedSeed(
        "NPS_ENCHANTED_SEED",
        Skulls.SEED_BLUE.getPlayerHead(),
        Theme.SEED,
        "Enchanted Seed",
        new String[]{"This seed, when fully grown,", "can be harvested with a tool for", "lapis lazuli."},
        getCanBePlacedOnLore("Purified Netherrack (or better)")
    );

    public static final SlimefunItemStack COMBUSTIBLE_SEED = Theme.themedSeed(
        "NPS_COMBUSTABLE_SEED",
        Skulls.SEED_RED.getPlayerHead(),
        Theme.SEED,
        "Combustable Seed",
        new String[]{"This seed, when fully grown,", "can be harvested with a tool for", "coal."},
        getCanBePlacedOnLore("Purified Netherrack (or better)")
    );

    public static final SlimefunItemStack PROTECTIVE_SEED = Theme.themedSeed(
        "NPS_PROTECTIVE_SEED",
        Skulls.SEED_PURPLE.getPlayerHead(),
        Theme.SEED,
        "Protective Seed",
        new String[]{"This seed, when fully grown,", "will spawn protective iron golems."},
        getCanBePlacedOnLore("Purified Netherrack (or better)")
    );

    public static final SlimefunItemStack PORKY_SEED = Theme.themedSeed(
        "NPS_PORKY_SEED",
        Skulls.SEED_RED.getPlayerHead(),
        Theme.SEED,
        "Porky Seed",
        new String[]{"This seed, when fully grown,", "will spawn piggies!"},
        getCanBePlacedOnLore("Purified Netherrack (or better)")
    );

    public static final SlimefunItemStack VALUABLE_SEED = Theme.themedSeed(
        "NPS_VALUABLE_SEED",
        Skulls.SEED_GREEN.getPlayerHead(),
        Theme.SEED,
        "Valuable Seed",
        new String[]{"This seed, when fully grown,", "can be harvested with a tool for", "emeralds."},
        getCanBePlacedOnLore("Purified Netherrack (or better)")
    );

    public static final SlimefunItemStack PERFECTION_SEED = Theme.themedSeed(
        "NPS_PERFECTION_SEED",
        Skulls.SEED_BLUE.getPlayerHead(),
        Theme.SEED,
        "Perfection Seed",
        new String[]{"This seed, when fully grown,", "can be harvested with a tool for", "diamonds."},
        getCanBePlacedOnLore("Purified Netherrack (or better)")
    );

    // endregion

    // region Spirit -> Saintly

    public static final SlimefunItemStack RAINBOW_SEED = Theme.themedSeed(
        "NPS_RAINBOW_SEED",
        Skulls.SEED_CYAN.getPlayerHead(),
        Theme.SEED,
        "Rainbow Seed",
        new String[]{"This seed, when fully grown,", "will periodically drop a random dye"},
        getCanBePlacedOnLore("Voracious Dirt (or better)")
    );

    public static final SlimefunItemStack GLOWING_SEED = Theme.themedSeed(
        "NPS_GLOWING_SEED",
        Skulls.SEED_RED.getPlayerHead(),
        Theme.SEED,
        "Glowing Seed",
        new String[]{"This seed, when fully grown,", "will periodically drop glowing items"},
        getCanBePlacedOnLore("Voracious Dirt (or better)")
    );

    public static final SlimefunItemStack ETHEREAL_SEED = Theme.themedSeed(
        "NPS_ETHEREAL_SEED",
        Skulls.SEED_GREEN.getPlayerHead(),
        Theme.SEED,
        "Ethereal Seed",
        new String[]{"This seed, when fully grown,", "will cause Endermen to appear nearby"},
        getCanBePlacedOnLore("Voracious Dirt (or better)")
    );

    public static final SlimefunItemStack IGNITED_SEED = Theme.themedSeed(
        "NPS_IGNITED_SEED",
        Skulls.SEED_RED.getPlayerHead(),
        Theme.SEED,
        "Ignited Seed",
        new String[]{"This seed, when fully grown,", "will cause Blaze to appear nearby"},
        getCanBePlacedOnLore("Voracious Dirt (or better)")
    );

    public static final SlimefunItemStack BARTERED_SEED = Theme.themedSeed(
        "NPS_BARTERED_SEED",
        Skulls.SEED_CYAN.getPlayerHead(),
        Theme.SEED,
        "Bartered Seed",
        new String[]{"This seed, when fully grown,", "will cause Piglins to pop", "by for a visit"},
        getCanBePlacedOnLore("Voracious Dirt (or better)")
    );

    public static final SlimefunItemStack PRISMATIC_SEED = Theme.themedSeed(
        "NPS_PRISMATIC_SEED",
        Skulls.SEED_GREEN.getPlayerHead(),
        Theme.SEED,
        "Prismatic Seed",
        new String[]{"This seed, when fully grown,", "will cause prismatic items to drop."},
        getCanBePlacedOnLore("Voracious Dirt (or better)")
    );

    public static final SlimefunItemStack POROUS_SEED = Theme.themedSeed(
        "NPS_POROUS_SEED",
        Skulls.SEED_YELLOW.getPlayerHead(),
        Theme.SEED,
        "Prismatic Seed",
        new String[]{"This seed, when fully grown,", "can be harvested with a tool", "for sponge"},
        getCanBePlacedOnLore("Voracious Dirt (or better)")
    );

    public static final SlimefunItemStack LEARNED_SEED = Theme.themedSeed(
        "NPS_LEARNED_SEED",
        Skulls.SEED_ORANGE.getPlayerHead(),
        Theme.SEED,
        "Learned Seed",
        new String[]{"This seed, when fully grown,", "can be harvested for a wealth", "of knowledge."},
        getCanBePlacedOnLore("Voracious Dirt (or better)")
    );

    public static final SlimefunItemStack OAKENDRAN_SEED = Theme.themedSeed(
        "NPS_OAKENDRAN_SEED",
        Skulls.SEED_ORANGE.getPlayerHead(),
        Theme.SEED,
        "Oakendran Seed",
        new String[]{"This seed, when fully grown,", "may cause trees to spontaneously", "grow."},
        getCanBePlacedOnLore("Voracious Dirt (or better)")
    );

    // endregion

    // region Saintly -> Eden

    public static final SlimefunItemStack ADDON_BERRY_SEED = Theme.themedSeed(
        "NPS_ADDON_BERRY_SEED",
        Skulls.SEED_BLUE.getPlayerHead(),
        Theme.SEED,
        "Addon-berry Seed",
        new String[]{"This seed, when fully grown,", "can be harvested for an", "addon berry."},
        getCanBePlacedOnLore("Nether Dirt (or better)")
    );

    //        CUTE_SEED = Theme.themedSeed(
//            "NPS_CUTE_SEED",
//            Skulls.SEED_CYAN.getPlayerHead(),
//            Theme.SEED,
//            "Cute Seed",
//            new String[]{"This seed, when fully grown,", "will sometimes cause an Axolotl", "to appear."},
//            getCanBePlacedOnLore("Nether Dirt (or better)")
//        );
//
//        BUZZING_SEED = Theme.themedSeed(
//            "NPS_BUZZING_SEED",
//            Skulls.SEED_YELLOW.getPlayerHead(),
//            Theme.SEED,
//            "Buzzing Seed",
//            new String[]{"This seed, when fully grown,", "can be harvested for honeycomb."},
//            getCanBePlacedOnLore("Nether Dirt (or better)")
//        );
//
//        TERRIFYING_SEED = Theme.themedSeed(
//            "NPS_TERRIFYING_SEED",
//            Skulls.SEED_BLUE.getPlayerHead(),
//            Theme.SEED,
//            "Terrifying Seed",
//            new String[]{"This seed, when fully grown,", "will sometimes cause a Wither Skeleton", "to appear."},
//            getCanBePlacedOnLore("Nether Dirt (or better)")
//        );
//
//        HATE_FILLED_SEED = Theme.themedSeed(
//            "NPS_HATE_FILLED_SEED",
//            Skulls.SEED_YELLOW.getPlayerHead(),
//            Theme.SEED,
//            "Hate-Filled Seed",
//            new String[]{"This seed, when fully grown,", "will sometimes cause a Wither", "to appear. Be sure to use", "wisely!"},
//            getCanBePlacedOnLore("Nether Dirt (or better)")
//        );
//
//        PULSING_SEED = Theme.themedSeed(
//            "NPS_PULSING_SEED",
//            Skulls.SEED_YELLOW.getPlayerHead(),
//            Theme.SEED,
//            "Pulsing Seed",
//            new String[]{"This seed, when fully grown,", "will pulse out powerful but", "random (positive) effects"},
//            getCanBePlacedOnLore("Nether Dirt (or better)")
//        );
//
//        GATEWAY_SEED = Theme.themedSeed(
//            "NPS_GATEWAY_SEED",
//            Skulls.SEED_GREEN.getPlayerHead(),
//            Theme.SEED,
//            "Gateway Seed",
//            new String[]{"This seed, when fully grown,", "will allow villagers to cross", "over into your new nirvana."},
//            getCanBePlacedOnLore("Nether Dirt (or better)")
//        );

    // endregion

    // endregion

    @Nonnull
    public static String[] getCanBePlacedOnLore(@Nonnull String... validPlacements) {
        String[] strings = new String[]{
            "",
            Theme.CRUX + "Can be grown on:"
        };
        return Stream.of(strings, validPlacements).flatMap(Stream::of).toArray(String[]::new);
    }
}
