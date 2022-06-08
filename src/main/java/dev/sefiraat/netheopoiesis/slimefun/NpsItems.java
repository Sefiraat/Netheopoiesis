package dev.sefiraat.netheopoiesis.slimefun;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.core.plant.GrowthDescription;
import dev.sefiraat.netheopoiesis.core.plant.GrowthStages;
import dev.sefiraat.netheopoiesis.core.plant.Placements;
import dev.sefiraat.netheopoiesis.listeners.VanillaDropListener;
import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherCrux;
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
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class NpsItems {

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
        20
    );

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
        NpsGroups.SEEDS,
        NpsStacks.PURIFICATION_SEED,
        VanillaDropListener.createRecipe(NpsStacks.PURIFICATION_SEED, new ItemStack(Material.SOUL_SOIL), 0.05),
        new GrowthDescription(GrowthStages.VINEY_BLUE, Placements.NULL, 1, 0.25)
    );

    public static final CruxSpreadingSeed SOUL_SEED = new CruxSpreadingSeed(
        NpsGroups.SEEDS,
        NpsStacks.SOUL_SEED,
        0.2,
        PURIFIED_NETHERRACK,
        new GrowthDescription(GrowthStages.VINEY_BLUE, Placements.ALL, 2, 0.2)
    );

    public static final CruxSpreadingSeed SPIRIT_SEED = new CruxSpreadingSeed(
        NpsGroups.SEEDS,
        NpsStacks.SPIRIT_SEED,
        0.15,
        VORACIOUS_DIRT,
        new GrowthDescription(GrowthStages.VINEY_BLUE, Placements.PURIFIED_AND_UP, 4, 0.15)
    );

    public static final CruxSpreadingSeed SAINTLY_SEED = new CruxSpreadingSeed(
        NpsGroups.SEEDS,
        NpsStacks.SAINTLY_SEED,
        0.1,
        NETHER_DIRT,
        new GrowthDescription(GrowthStages.VINEY_BLUE, Placements.VORACIOUS_AND_UP, 8, 0.1)
    );

    // First Stage
    public static final GenericTickingSeed SPINDLE_SEED = new GenericTickingSeed(
        NpsGroups.SEEDS,
        NpsStacks.SPINDLE_SEED,
        GenericTickingMethods::onTickSpindleSeed,
        new GrowthDescription(GrowthStages.VINEY_ORANGE, Placements.ALL, 1, 0.09)
    );

    public static final DroppingSeed GRAINY_SEED = new DroppingSeed(
        NpsGroups.SEEDS,
        NpsStacks.GRAINY_SEED,
        new ItemStack(Material.REDSTONE),
        new GrowthDescription(GrowthStages.VINEY_RED, Placements.ALL, 1, 0.09)
    );

    public static final DroppingSeed STRINGY_SEED = new DroppingSeed(
        NpsGroups.SEEDS,
        NpsStacks.STRINGY_SEED,
        new ItemStack(Material.STRING),
        new GrowthDescription(GrowthStages.VINEY_GREEN, Placements.ALL, 1, 0.09)
    );

    public static final DroppingSeed STONEY_SEED = new DroppingSeed(
        NpsGroups.SEEDS,
        NpsStacks.STONEY_SEED,
        new ItemStack(Material.COBBLESTONE),
        new GrowthDescription(GrowthStages.VINEY_PURPLE, Placements.ALL, 1, 0.09)
    );

    public static final HarvestableSeed DUSTY_SEED = new HarvestableSeed(
        NpsGroups.SEEDS,
        NpsStacks.DUSTY_SEED,
        new ItemStack(Material.GRAVEL),
        new GrowthDescription(GrowthStages.VINEY_CYAN, Placements.ALL, 1, 0.09)
    );

    public static final HarvestableSeed SEASIDE_SEED = new HarvestableSeed(
        NpsGroups.SEEDS,
        NpsStacks.SEASIDE_SEED,
        new ItemStack(Material.SAND),
        new GrowthDescription(GrowthStages.VINEY_YELLOW, Placements.ALL, 1, 0.09)
    );

    public static final HarvestableSeed MOLDABLE_SEED = new HarvestableSeed(
        NpsGroups.SEEDS,
        NpsStacks.MOLDABLE_SEED,
        new ItemStack(Material.CLAY_BALL),
        new GrowthDescription(GrowthStages.VINEY_PURPLE, Placements.ALL, 1, 0.09)
    );

    public static final WetSeed WET_SEED = new WetSeed(
        NpsGroups.SEEDS,
        NpsStacks.WET_SEED,
        new GrowthDescription(GrowthStages.VINEY_BLUE, Placements.ALL, 2, 0.11)
    );

    public static final EntitySpawningSeed SPLINTERED_SEED = new EntitySpawningSeed(
        NpsGroups.SEEDS,
        NpsStacks.SPLINTERED_SEED,
        EntityType.SKELETON,
        new GrowthDescription(GrowthStages.VINEY_CYAN, Placements.ALL, 2, 0.08)
    );

    public static final EntitySpawningSeed ROTTEN_SEED = new EntitySpawningSeed(
        NpsGroups.SEEDS,
        NpsStacks.ROTTEN_SEED,
        EntityType.ZOMBIE,
        new GrowthDescription(GrowthStages.VINEY_GREEN, Placements.ALL, 2, 0.08)
    );

    public static final HarvestableSeed METALLIC_SEED = new HarvestableSeed(
        NpsGroups.SEEDS,
        NpsStacks.METALLIC_SEED,
        new ItemStack(Material.IRON_NUGGET),
        new GrowthDescription(GrowthStages.VINEY_RED, Placements.PURIFIED_AND_UP, 2, 0.08)
    );

    public static final HarvestableSeed SHINY_SEED = new HarvestableSeed(
        NpsGroups.SEEDS,
        NpsStacks.SHINY_SEED,
        new ItemStack(Material.GOLD_NUGGET),
        new GrowthDescription(GrowthStages.VINEY_YELLOW, Placements.PURIFIED_AND_UP, 2, 0.08)
    );

    public static final HarvestableSeed SMOOTH_SEED = new HarvestableSeed(
        NpsGroups.SEEDS,
        NpsStacks.SMOOTH_SEED,
        new ItemStack(Material.AMETHYST_SHARD),
        new GrowthDescription(GrowthStages.VINEY_PURPLE, Placements.PURIFIED_AND_UP, 2, 0.08)
    );

    public static final HarvestableSeed ENCHANTED_SEED = new HarvestableSeed(
        NpsGroups.SEEDS,
        NpsStacks.ENCHANTED_SEED,
        new ItemStack(Material.LAPIS_LAZULI),
        new GrowthDescription(GrowthStages.VINEY_BLUE, Placements.PURIFIED_AND_UP, 3, 0.07)
    );

    public static final HarvestableSeed COMBUSTIBLE_SEED = new HarvestableSeed(
        NpsGroups.SEEDS,
        NpsStacks.COMBUSTIBLE_SEED,
        new ItemStack(Material.COAL),
        new GrowthDescription(GrowthStages.VINEY_RED, Placements.PURIFIED_AND_UP, 3, 0.07)
    );

    public static final EntitySpawningSeed PROTECTIVE_SEED = new EntitySpawningSeed(
        NpsGroups.SEEDS,
        NpsStacks.PROTECTIVE_SEED,
        EntityType.IRON_GOLEM,
        new GrowthDescription(GrowthStages.VINEY_PURPLE, Placements.PURIFIED_AND_UP, 5, 0.03)
    );

    public static final EntitySpawningSeed PORKY_SEED = new EntitySpawningSeed(
        NpsGroups.SEEDS,
        NpsStacks.PORKY_SEED,
        EntityType.PIG,
        new GrowthDescription(GrowthStages.VINEY_RED, Placements.PURIFIED_AND_UP, 3, 0.08)
    );

    public static final HarvestableSeed VALUABLE_SEED = new HarvestableSeed(
        NpsGroups.SEEDS,
        NpsStacks.VALUABLE_SEED,
        new ItemStack(Material.EMERALD),
        new GrowthDescription(GrowthStages.VINEY_GREEN, Placements.PURIFIED_AND_UP, 3, 0.07)
    );

    public static final HarvestableSeed PERFECTION_SEED = new HarvestableSeed(
        NpsGroups.SEEDS,
        NpsStacks.PERFECTION_SEED,
        new ItemStack(Material.DIAMOND),
        new GrowthDescription(GrowthStages.VINEY_BLUE, Placements.PURIFIED_AND_UP, 5, 0.07)
    );

    public static final DroppingSeed RAINBOW_SEED = new DroppingSeed(
        NpsGroups.SEEDS,
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
        NpsGroups.SEEDS,
        NpsStacks.GLOWING_SEED,
        new ItemStack[]{
            new ItemStack(Material.GLOW_LICHEN),
            new ItemStack(Material.GLOW_BERRIES),
            new ItemStack(Material.GLOW_INK_SAC)
        },
        new GrowthDescription(GrowthStages.VINEY_RED, Placements.VORACIOUS_AND_UP, 8, 0.06)
    );

    public static final EntitySpawningSeed ETHEREAL_SEED = new EntitySpawningSeed(
        NpsGroups.SEEDS,
        NpsStacks.ETHEREAL_SEED,
        EntityType.ENDERMAN,
        new GrowthDescription(GrowthStages.VINEY_GREEN, Placements.VORACIOUS_AND_UP, 6, 0.06)
    );

    public static final EntitySpawningSeed IGNITED_SEED = new EntitySpawningSeed(
        NpsGroups.SEEDS,
        NpsStacks.IGNITED_SEED,
        EntityType.BLAZE,
        new GrowthDescription(GrowthStages.VINEY_RED, Placements.VORACIOUS_AND_UP, 8, 0.07)
    );

    public static final EntitySpawningSeed BARTERED_SEED = new EntitySpawningSeed(
        NpsGroups.SEEDS,
        NpsStacks.BARTERED_SEED,
        EntityType.PIGLIN,
        new GrowthDescription(GrowthStages.VINEY_CYAN, Placements.VORACIOUS_AND_UP, 8, 0.06)
    );

    public static final DroppingSeed PRISMATIC_SEED = new DroppingSeed(
        NpsGroups.SEEDS,
        NpsStacks.PRISMATIC_SEED,
        new ItemStack[]{
            new ItemStack(Material.PRISMARINE_SHARD),
            new ItemStack(Material.PRISMARINE_CRYSTALS)
        },
        new GrowthDescription(GrowthStages.VINEY_GREEN, Placements.VORACIOUS_AND_UP, 9, 0.06)
    );

    public static final HarvestableSeed POROUS_SEED = new HarvestableSeed(
        NpsGroups.SEEDS,
        NpsStacks.POROUS_SEED,
        new ItemStack(Material.SPONGE),
        new GrowthDescription(GrowthStages.VINEY_YELLOW, Placements.VORACIOUS_AND_UP, 9, 0.06)
    );

    public static final HarvestableSeed LEARNED_SEED = new HarvestableSeed(
        NpsGroups.SEEDS,
        NpsStacks.LEARNED_SEED,
        new ItemStack(Material.EXPERIENCE_BOTTLE),
        new GrowthDescription(GrowthStages.VINEY_ORANGE, Placements.VORACIOUS_AND_UP, 9, 0.06)
    );

    public static final GenericTickingSeed OAKENDRAN_SEED = new GenericTickingSeed(
        NpsGroups.SEEDS,
        NpsStacks.OAKENDRAN_SEED,
        GenericTickingMethods::onTickOakendranSeed,
        new GrowthDescription(GrowthStages.VINEY_ORANGE, Placements.VORACIOUS_AND_UP, 12, 0.04)
    );

    public static final HarvestableSeed ADDON_BERRY_SEED = new HarvestableSeed(
        NpsGroups.SEEDS,
        NpsStacks.ADDON_BERRY_SEED,
        NpsStacks.ADDON_BERRY,
        new GrowthDescription(GrowthStages.VINEY_RED, Placements.NETHER_DIRT_AND_UP, 10, 0.2)
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
        ENDER_CAKE.register(plugin);

        // Crux'
        BASIC_PURIFIED_NETHERRACK.register(plugin);
        PURIFIED_NETHERRACK.register(plugin);
        VORACIOUS_DIRT.register(plugin);
        NETHER_DIRT.register(plugin);
        NETHER_GRASS.register(plugin);
        JUNGLE_CRUX.register(plugin);

        // Seeds
        PURIFICATION_SEED.register(plugin);
        SOUL_SEED.register(plugin);
        SPIRIT_SEED.register(plugin);
        SAINTLY_SEED.register(plugin);

        SPINDLE_SEED.register(plugin);
        GRAINY_SEED.register(plugin);
        STRINGY_SEED.register(plugin);
        STONEY_SEED.register(plugin);
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
    }
}
