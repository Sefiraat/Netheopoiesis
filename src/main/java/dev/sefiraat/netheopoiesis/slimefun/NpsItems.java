package dev.sefiraat.netheopoiesis.slimefun;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.core.plant.GrowthDescription;
import dev.sefiraat.netheopoiesis.core.plant.GrowthStages;
import dev.sefiraat.netheopoiesis.core.plant.Placements;
import dev.sefiraat.netheopoiesis.listeners.VanillaDropListener;
import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherCrux;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.BiomeSpreadingSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.CruxSpreadingSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.DroppingSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.EntitySpawningSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.GenericTickingMethods;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.GenericTickingSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.HarvestableSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.unique.PurificationSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.unique.WetSeed;
import dev.sefiraat.netheopoiesis.slimefun.groups.NpsGroups;
import dev.sefiraat.netheopoiesis.slimefun.tools.Analyser;
import dev.sefiraat.netheopoiesis.slimefun.tools.EnderCake;
import dev.sefiraat.netheopoiesis.slimefun.tools.HarvestingTool;
import dev.sefiraat.netheopoiesis.slimefun.tools.PurificationBarometer;
import dev.sefiraat.netheopoiesis.slimefun.tools.PurificationScanner;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

/**
 * Final class used to store and initialise the {@link SlimefunItem}s used in the addon
 */
public final class NpsItems {

    private NpsItems() {
        throw new IllegalStateException("Utility class");
    }

    // Vanilla Materials
    private static final ItemStack OAK_PLANK = new ItemStack(Material.OAK_PLANKS);
    private static final ItemStack IRON_INGOT = new ItemStack(Material.IRON_INGOT);
    private static final ItemStack GLASS = new ItemStack(Material.GLASS);
    private static final ItemStack REDSTONE = new ItemStack(Material.REDSTONE);

    // region Crux'

    public static final NetherCrux BASIC_PURIFIED_NETHERRACK = new NetherCrux(
        NpsGroups.CRUX,
        NpsStacks.BASIC_PURIFIED_NETHERRACK,
        1
    );

    public static final NetherCrux PURIFIED_NETHERRACK = new NetherCrux(
        NpsGroups.CRUX,
        NpsStacks.PURIFIED_NETHERRACK,
        2
    );

    public static final NetherCrux VORACIOUS_DIRT = new NetherCrux(
        NpsGroups.CRUX,
        NpsStacks.VORACIOUS_DIRT,
        4
    );

    public static final NetherCrux NETHER_DIRT = new NetherCrux(
        NpsGroups.CRUX,
        NpsStacks.NETHER_DIRT,
        8
    );

    public static final NetherCrux NETHER_GRASS = new NetherCrux(
        NpsGroups.CRUX,
        NpsStacks.NETHER_GRASS,
        16
    );

    public static final NetherCrux JUNGLE_CRUX = new NetherCrux(
        NpsGroups.CRUX,
        NpsStacks.JUNGLE_CRUX,
        16
    );

    public static final NetherCrux BEACH_CRUX = new NetherCrux(
        NpsGroups.CRUX,
        NpsStacks.BEACH_CRUX,
        16
    );

    public static final NetherCrux DESERT_CRUX = new NetherCrux(
        NpsGroups.CRUX,
        NpsStacks.DESERT_CRUX,
        16
    );

    public static final NetherCrux SNOW_CRUX = new NetherCrux(
        NpsGroups.CRUX,
        NpsStacks.SNOW_CRUX,
        16
    );

    public static final NetherCrux STONEY_CRUX = new NetherCrux(
        NpsGroups.CRUX,
        NpsStacks.STONEY_CRUX,
        16
    );

    public static final NetherCrux SWAMP_CRUX = new NetherCrux(
        NpsGroups.CRUX,
        NpsStacks.SWAMP_CRUX,
        16
    );

    // endregion

    // region Crafting

    public static final SlimefunItem ADDON_BERRY = new SlimefunItem(
        NpsGroups.CRAFTING,
        NpsStacks.ADDON_BERRY,
        NpsRecipeTypes.PLANT_HARVEST,
        new ItemStack[0]
    );

    public static final SlimefunItem ADDON_JAM = new SlimefunItem(
        NpsGroups.CRAFTING,
        NpsStacks.ADDON_JAM,
        RecipeType.ORE_CRUSHER,
        new ItemStack[]{
            NpsStacks.ADDON_BERRY
        }
    );

    // endregion

    // region Tools

    public static final HarvestingTool CRUDE_HARVESTING_TOOL = new HarvestingTool(
        NpsGroups.TOOLS,
        NpsStacks.CRUDE_HARVESTING_TOOL,
        RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[]{
            null, null, OAK_PLANK,
            OAK_PLANK, OAK_PLANK, null,
            OAK_PLANK, OAK_PLANK, null,
        },
        25
    );

    public static final HarvestingTool HARVESTING_TOOL = new HarvestingTool(
        NpsGroups.TOOLS,
        NpsStacks.HARVESTING_TOOL,
        RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[]{
            null, null, IRON_INGOT,
            IRON_INGOT, IRON_INGOT, null,
            IRON_INGOT, IRON_INGOT, null,
        },
        150
    );

    public static final PurificationBarometer PURIFICATION_BAROMETER = new PurificationBarometer(
        NpsGroups.TOOLS,
        NpsStacks.PURIFICATION_BAROMETER,
        RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[]{
            SlimefunItems.ZINC_INGOT, GLASS, SlimefunItems.ZINC_INGOT,
            GLASS, REDSTONE, GLASS,
            SlimefunItems.ZINC_INGOT, GLASS, SlimefunItems.ZINC_INGOT,
        }
    );

    public static final PurificationScanner PURIFICATION_SCANNER = new PurificationScanner(
        NpsGroups.TOOLS,
        NpsStacks.PURIFICATION_SCANNER,
        RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[]{
            SlimefunItems.BILLON_INGOT, GLASS, SlimefunItems.BILLON_INGOT,
            GLASS, NpsStacks.PURIFICATION_BAROMETER, GLASS,
            SlimefunItems.BILLON_INGOT, GLASS, SlimefunItems.BILLON_INGOT,
        }
    );

    public static final Analyser CRUX_GATHERER = new Analyser(
        NpsGroups.TOOLS,
        NpsStacks.CRUX_GATHERER,
        RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[]{
            SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT,
            null, GLASS, null,
            null, GLASS, null,
        }
    );

    public static final Analyser SEED_ANALYSER = new Analyser(
        NpsGroups.TOOLS,
        NpsStacks.SEED_ANALYSER,
        RecipeType.ENHANCED_CRAFTING_TABLE,
        new ItemStack[]{
            SlimefunItems.DAMASCUS_STEEL_INGOT, GLASS, SlimefunItems.DAMASCUS_STEEL_INGOT,
            SlimefunItems.BRASS_INGOT, GLASS, SlimefunItems.BRASS_INGOT,
            SlimefunItems.DAMASCUS_STEEL_INGOT, REDSTONE, SlimefunItems.DAMASCUS_STEEL_INGOT,
        }
    );

    // todo finish recipe
    public static final EnderCake ENDER_CAKE = new EnderCake(
        NpsGroups.TOOLS,
        NpsStacks.ENDER_CAKE,
        RecipeType.ANCIENT_ALTAR,
        new ItemStack[]{
            null, null, null,
            null, NpsStacks.ADDON_JAM, null,
            null, null, null,
        }
    );

    // endregion

    // region Seeds

    public static final PurificationSeed PURIFICATION_SEED = new PurificationSeed(
        NpsStacks.PURIFICATION_SEED,
        VanillaDropListener.createRecipe(NpsStacks.PURIFICATION_SEED, new ItemStack(Material.SOUL_SOIL), 0.05),
        new GrowthDescription(GrowthStages.VINEY_BLUE, Placements.NULL, 1, 0.30)
    );

    public static final CruxSpreadingSeed SOUL_SEED = new CruxSpreadingSeed(
        NpsStacks.SOUL_SEED,
        0.25,
        PURIFIED_NETHERRACK,
        new GrowthDescription(GrowthStages.VINEY_BLUE, Placements.ALL, 2, 0.2)
    );

    public static final CruxSpreadingSeed SPIRIT_SEED = new CruxSpreadingSeed(
        NpsStacks.SPIRIT_SEED,
        0.2,
        VORACIOUS_DIRT,
        new GrowthDescription(GrowthStages.VINEY_BLUE, Placements.PURIFIED_AND_UP, 4, 0.15)
    );

    public static final CruxSpreadingSeed SAINTLY_SEED = new CruxSpreadingSeed(
        NpsStacks.SAINTLY_SEED,
        0.15,
        NETHER_DIRT,
        new GrowthDescription(GrowthStages.VINEY_BLUE, Placements.VORACIOUS_AND_UP, 8, 0.1)
    );

    public static final CruxSpreadingSeed EDEN_SEED = new CruxSpreadingSeed(
        NpsStacks.EDEN_SEED,
        0.1,
        NETHER_GRASS,
        new GrowthDescription(GrowthStages.VINEY_BLUE, Placements.NETHER_DIRT_AND_UP, 16, 0.1)
    );

    public static final CruxSpreadingSeed JUNGLE_SEED = new BiomeSpreadingSeed(
        NpsStacks.JUNGLE_SEED,
        0.1,
        JUNGLE_CRUX,
        Biome.JUNGLE,
        new GrowthDescription(GrowthStages.VINEY_GREEN, Placements.JUNGLE_FRINGE, 16, 0.1)
    );

    public static final CruxSpreadingSeed BEACH_SEED = new BiomeSpreadingSeed(
        NpsStacks.BEACH_SEED,
        0.1,
        BEACH_CRUX,
        Biome.BEACH,
        new GrowthDescription(GrowthStages.VINEY_YELLOW, Placements.BEACH_FRINGE, 16, 0.1)
    );

    public static final CruxSpreadingSeed DESERT_SEED = new BiomeSpreadingSeed(
        NpsStacks.DESERT_SEED,
        0.1,
        DESERT_CRUX,
        Biome.DESERT,
        new GrowthDescription(GrowthStages.VINEY_RED, Placements.DESERT_FRINGE, 16, 0.1)
    );

    public static final CruxSpreadingSeed SNOW_SEED = new BiomeSpreadingSeed(
        NpsStacks.SNOW_SEED,
        0.1,
        SNOW_CRUX,
        Biome.SNOWY_PLAINS,
        new GrowthDescription(GrowthStages.VINEY_CYAN, Placements.SNOW_FRINGE, 16, 0.1)
    );

    public static final CruxSpreadingSeed STONEY_SEED = new BiomeSpreadingSeed(
        NpsStacks.STONEY_SEED,
        0.1,
        STONEY_CRUX,
        Biome.STONY_SHORE,
        new GrowthDescription(GrowthStages.VINEY_PURPLE, Placements.STONEY_FRINGE, 16, 0.1)
    );

    public static final CruxSpreadingSeed SWAMP_SEED = new BiomeSpreadingSeed(
        NpsStacks.SWAMP_SEED,
        0.1,
        SWAMP_CRUX,
        Biome.SWAMP,
        new GrowthDescription(GrowthStages.VINEY_GREEN, Placements.SWAMP_FRINGE, 16, 0.1)
    );

    // First Stage
    public static final GenericTickingSeed SPINDLE_SEED = new GenericTickingSeed(
        NpsStacks.SPINDLE_SEED,
        GenericTickingMethods::onTickSpindleSeed,
        new GrowthDescription(GrowthStages.VINEY_ORANGE, Placements.ALL, 1, 0.09)
    );

    public static final DroppingSeed GRAINY_SEED = new DroppingSeed(
        NpsStacks.GRAINY_SEED,
        new ItemStack(Material.REDSTONE),
        new GrowthDescription(GrowthStages.VINEY_RED, Placements.ALL, 1, 0.09)
    );

    public static final DroppingSeed STRINGY_SEED = new DroppingSeed(
        NpsStacks.STRINGY_SEED,
        new ItemStack(Material.STRING),
        new GrowthDescription(GrowthStages.VINEY_GREEN, Placements.ALL, 1, 0.09)
    );

    public static final DroppingSeed COBBLED_SEED = new DroppingSeed(
        NpsStacks.COBBLED_SEED,
        new ItemStack(Material.COBBLESTONE),
        new GrowthDescription(GrowthStages.VINEY_PURPLE, Placements.ALL, 1, 0.09)
    );

    public static final HarvestableSeed DUSTY_SEED = new HarvestableSeed(
        NpsStacks.DUSTY_SEED,
        new ItemStack(Material.GRAVEL),
        new GrowthDescription(GrowthStages.VINEY_CYAN, Placements.ALL, 1, 0.09)
    );

    public static final HarvestableSeed SEASIDE_SEED = new HarvestableSeed(
        NpsStacks.SEASIDE_SEED,
        new ItemStack(Material.SAND),
        new GrowthDescription(GrowthStages.VINEY_YELLOW, Placements.ALL, 1, 0.09)
    );

    public static final HarvestableSeed MOLDABLE_SEED = new HarvestableSeed(
        NpsStacks.MOLDABLE_SEED,
        new ItemStack(Material.CLAY_BALL),
        new GrowthDescription(GrowthStages.VINEY_PURPLE, Placements.ALL, 1, 0.09)
    );

    public static final WetSeed WET_SEED = new WetSeed(
        NpsStacks.WET_SEED,
        new GrowthDescription(GrowthStages.VINEY_BLUE, Placements.ALL, 2, 0.11)
    );

    public static final EntitySpawningSeed SPLINTERED_SEED = new EntitySpawningSeed(
        NpsStacks.SPLINTERED_SEED,
        EntityType.SKELETON,
        new GrowthDescription(GrowthStages.VINEY_CYAN, Placements.ALL, 2, 0.08)
    );

    public static final EntitySpawningSeed ROTTEN_SEED = new EntitySpawningSeed(
        NpsStacks.ROTTEN_SEED,
        EntityType.ZOMBIE,
        new GrowthDescription(GrowthStages.VINEY_GREEN, Placements.ALL, 2, 0.08)
    );

    public static final HarvestableSeed METALLIC_SEED = new HarvestableSeed(
        NpsStacks.METALLIC_SEED,
        new ItemStack(Material.IRON_NUGGET),
        new GrowthDescription(GrowthStages.VINEY_RED, Placements.PURIFIED_AND_UP, 2, 0.08)
    );

    public static final HarvestableSeed SHINY_SEED = new HarvestableSeed(
        NpsStacks.SHINY_SEED,
        new ItemStack(Material.GOLD_NUGGET),
        new GrowthDescription(GrowthStages.VINEY_YELLOW, Placements.PURIFIED_AND_UP, 2, 0.08)
    );

    public static final HarvestableSeed SMOOTH_SEED = new HarvestableSeed(
        NpsStacks.SMOOTH_SEED,
        new ItemStack(Material.AMETHYST_SHARD),
        new GrowthDescription(GrowthStages.VINEY_PURPLE, Placements.PURIFIED_AND_UP, 2, 0.08)
    );

    public static final HarvestableSeed ENCHANTED_SEED = new HarvestableSeed(
        NpsStacks.ENCHANTED_SEED,
        new ItemStack(Material.LAPIS_LAZULI),
        new GrowthDescription(GrowthStages.VINEY_BLUE, Placements.PURIFIED_AND_UP, 3, 0.07)
    );

    public static final HarvestableSeed COMBUSTIBLE_SEED = new HarvestableSeed(
        NpsStacks.COMBUSTIBLE_SEED,
        new ItemStack(Material.COAL),
        new GrowthDescription(GrowthStages.VINEY_RED, Placements.PURIFIED_AND_UP, 3, 0.07)
    );

    public static final EntitySpawningSeed PROTECTIVE_SEED = new EntitySpawningSeed(
        NpsStacks.PROTECTIVE_SEED,
        EntityType.IRON_GOLEM,
        new GrowthDescription(GrowthStages.VINEY_PURPLE, Placements.PURIFIED_AND_UP, 5, 0.03)
    );

    public static final EntitySpawningSeed PORKY_SEED = new EntitySpawningSeed(
        NpsStacks.PORKY_SEED,
        EntityType.PIG,
        new GrowthDescription(GrowthStages.VINEY_RED, Placements.PURIFIED_AND_UP, 3, 0.08)
    );

    public static final HarvestableSeed VALUABLE_SEED = new HarvestableSeed(
        NpsStacks.VALUABLE_SEED,
        new ItemStack(Material.EMERALD),
        new GrowthDescription(GrowthStages.VINEY_GREEN, Placements.PURIFIED_AND_UP, 3, 0.07)
    );

    public static final HarvestableSeed PERFECTION_SEED = new HarvestableSeed(
        NpsStacks.PERFECTION_SEED,
        new ItemStack(Material.DIAMOND),
        new GrowthDescription(GrowthStages.VINEY_BLUE, Placements.PURIFIED_AND_UP, 5, 0.07)
    );

    public static final DroppingSeed RAINBOW_SEED = new DroppingSeed(
        NpsStacks.RAINBOW_SEED,
        new ItemStack[]{
            new ItemStack(Material.WHITE_DYE),
            new ItemStack(Material.ORANGE_DYE),
            new ItemStack(Material.MAGENTA_DYE),
            new ItemStack(Material.LIGHT_BLUE_DYE),
            new ItemStack(Material.YELLOW_DYE),
            new ItemStack(Material.LIME_DYE),
            new ItemStack(Material.PINK_DYE),
            new ItemStack(Material.GRAY_DYE),
            new ItemStack(Material.LIGHT_GRAY_DYE),
            new ItemStack(Material.CYAN_DYE),
            new ItemStack(Material.PURPLE_DYE),
            new ItemStack(Material.BLUE_DYE),
            new ItemStack(Material.BROWN_DYE),
            new ItemStack(Material.GREEN_DYE),
            new ItemStack(Material.RED_DYE),
            new ItemStack(Material.BLACK_DYE),
        },
        new GrowthDescription(GrowthStages.VINEY_CYAN, Placements.VORACIOUS_AND_UP, 8, 0.06)
    );

    public static final DroppingSeed GLOWING_SEED = new DroppingSeed(
        NpsStacks.GLOWING_SEED,
        new ItemStack[]{
            new ItemStack(Material.GLOW_LICHEN),
            new ItemStack(Material.GLOW_BERRIES),
            new ItemStack(Material.GLOW_INK_SAC)
        },
        new GrowthDescription(GrowthStages.VINEY_RED, Placements.VORACIOUS_AND_UP, 8, 0.06)
    );

    public static final EntitySpawningSeed ETHEREAL_SEED = new EntitySpawningSeed(
        NpsStacks.ETHEREAL_SEED,
        EntityType.ENDERMAN,
        new GrowthDescription(GrowthStages.VINEY_GREEN, Placements.VORACIOUS_AND_UP, 6, 0.06)
    );

    public static final EntitySpawningSeed IGNITED_SEED = new EntitySpawningSeed(
        NpsStacks.IGNITED_SEED,
        EntityType.BLAZE,
        new GrowthDescription(GrowthStages.VINEY_RED, Placements.VORACIOUS_AND_UP, 8, 0.07)
    );

    public static final EntitySpawningSeed BARTERED_SEED = new EntitySpawningSeed(
        NpsStacks.BARTERED_SEED,
        EntityType.PIGLIN,
        new GrowthDescription(GrowthStages.VINEY_CYAN, Placements.VORACIOUS_AND_UP, 8, 0.06)
    );

    public static final DroppingSeed PRISMATIC_SEED = new DroppingSeed(
        NpsStacks.PRISMATIC_SEED,
        new ItemStack[]{
            new ItemStack(Material.PRISMARINE_SHARD),
            new ItemStack(Material.PRISMARINE_CRYSTALS)
        },
        new GrowthDescription(GrowthStages.VINEY_GREEN, Placements.VORACIOUS_AND_UP, 9, 0.06)
    );

    public static final HarvestableSeed POROUS_SEED = new HarvestableSeed(
        NpsStacks.POROUS_SEED,
        new ItemStack(Material.SPONGE),
        new GrowthDescription(GrowthStages.VINEY_YELLOW, Placements.VORACIOUS_AND_UP, 9, 0.06)
    );

    public static final HarvestableSeed LEARNED_SEED = new HarvestableSeed(
        NpsStacks.LEARNED_SEED,
        new ItemStack(Material.EXPERIENCE_BOTTLE),
        new GrowthDescription(GrowthStages.VINEY_ORANGE, Placements.VORACIOUS_AND_UP, 9, 0.06)
    );

    public static final GenericTickingSeed OAKENDRAN_SEED = new GenericTickingSeed(
        NpsStacks.OAKENDRAN_SEED,
        GenericTickingMethods::onTickOakendranSeed,
        new GrowthDescription(GrowthStages.VINEY_ORANGE, Placements.VORACIOUS_AND_UP, 12, 0.04)
    );

    public static final HarvestableSeed ADDON_BERRY_SEED = new HarvestableSeed(
        NpsStacks.ADDON_BERRY_SEED,
        NpsStacks.ADDON_BERRY,
        new GrowthDescription(GrowthStages.VINEY_RED, Placements.NETHER_DIRT_AND_UP, 10, 0.2)
    );

    public static final EntitySpawningSeed CUTE_SEED = new EntitySpawningSeed(
        NpsStacks.CUTE_SEED,
        EntityType.AXOLOTL,
        new GrowthDescription(GrowthStages.VINEY_BLUE, Placements.NETHER_DIRT_AND_UP, 15, 0.15)
    );

    public static final HarvestableSeed BUZZING_SEED = new HarvestableSeed(
        NpsStacks.BUZZING_SEED,
        new ItemStack(Material.HONEYCOMB),
        new GrowthDescription(GrowthStages.VINEY_YELLOW, Placements.NETHER_DIRT_AND_UP, 10, 0.2)
    );

    public static final EntitySpawningSeed TERRIFYING_SEED = new EntitySpawningSeed(
        NpsStacks.TERRIFYING_SEED,
        EntityType.WITHER_SKELETON,
        new GrowthDescription(GrowthStages.VINEY_PURPLE, Placements.NETHER_DIRT_AND_UP, 15, 0.1)
    );

    public static final GenericTickingSeed HATE_FILLED_SEED = new GenericTickingSeed(
        NpsStacks.HATE_FILLED_SEED,
        GenericTickingMethods::onTickHateFilledSeed,
        new GrowthDescription(GrowthStages.VINEY_YELLOW, Placements.NETHER_DIRT_AND_UP, 0, 0.2)
    );

    public static final GenericTickingSeed PULSING_SEED = new GenericTickingSeed(
        NpsStacks.PULSING_SEED,
        GenericTickingMethods::onTickHateFilledSeed,
        new GrowthDescription(GrowthStages.VINEY_GREEN, Placements.NETHER_DIRT_AND_UP, 20, 0.08)
    );

    public static final EntitySpawningSeed GATEWAY_SEED = new EntitySpawningSeed(
        NpsStacks.GATEWAY_SEED,
        EntityType.VILLAGER,
        new GrowthDescription(GrowthStages.VINEY_PURPLE, Placements.NETHER_DIRT_AND_UP, 20, 0.08)
    );

    public static void setup() {
        final Netheopoiesis plugin = Netheopoiesis.getInstance();

        // Crafting
        ADDON_BERRY.register(plugin);
        ADDON_JAM.register(plugin);

        // Tools
        CRUDE_HARVESTING_TOOL.register(plugin);
        HARVESTING_TOOL.register(plugin);
        PURIFICATION_BAROMETER.register(plugin);
        PURIFICATION_SCANNER.register(plugin);
        SEED_ANALYSER.register(plugin);
        CRUX_GATHERER.register(plugin);
        ENDER_CAKE.register(plugin);

        // Crux'
        BASIC_PURIFIED_NETHERRACK.register(plugin);
        PURIFIED_NETHERRACK.register(plugin);
        VORACIOUS_DIRT.register(plugin);
        NETHER_DIRT.register(plugin);
        NETHER_GRASS.register(plugin);
        JUNGLE_CRUX.register(plugin);
        BEACH_CRUX.register(plugin);
        DESERT_CRUX.register(plugin);
        SNOW_CRUX.register(plugin);
        STONEY_CRUX.register(plugin);
        SWAMP_CRUX.register(plugin);

        // Seeds
        PURIFICATION_SEED.register(plugin);
        SOUL_SEED.register(plugin);
        SPIRIT_SEED.register(plugin);
        SAINTLY_SEED.register(plugin);
        EDEN_SEED.register(plugin);
        JUNGLE_SEED.register(plugin);
        BEACH_SEED.register(plugin);
        DESERT_SEED.register(plugin);
        SNOW_SEED.register(plugin);
        STONEY_SEED.register(plugin);
        SWAMP_SEED.register(plugin);

        SPINDLE_SEED.register(plugin);
        GRAINY_SEED.register(plugin);
        STRINGY_SEED.register(plugin);
        COBBLED_SEED.register(plugin);
        DUSTY_SEED.register(plugin);
        SEASIDE_SEED.register(plugin);
        WET_SEED.register(plugin);
        MOLDABLE_SEED.register(plugin);
        SPLINTERED_SEED.register(plugin);
        ROTTEN_SEED.register(plugin);

        METALLIC_SEED.register(plugin);
        SHINY_SEED.register(plugin);
        SMOOTH_SEED.register(plugin);
        ENCHANTED_SEED.register(plugin);
        COMBUSTIBLE_SEED.register(plugin);
        PROTECTIVE_SEED.register(plugin);
        PORKY_SEED.register(plugin);
        VALUABLE_SEED.register(plugin);
        PERFECTION_SEED.register(plugin);

        RAINBOW_SEED.register(plugin);
        GLOWING_SEED.register(plugin);
        ETHEREAL_SEED.register(plugin);
        IGNITED_SEED.register(plugin);
        BARTERED_SEED.register(plugin);
        PRISMATIC_SEED.register(plugin);
        POROUS_SEED.register(plugin);
        LEARNED_SEED.register(plugin);
        OAKENDRAN_SEED.register(plugin);

        ADDON_BERRY_SEED.register(plugin);
        CUTE_SEED.register(plugin);
        BUZZING_SEED.register(plugin);
        TERRIFYING_SEED.register(plugin);
        HATE_FILLED_SEED.register(plugin);
        PULSING_SEED.register(plugin);
        GATEWAY_SEED.register(plugin);
    }
}
