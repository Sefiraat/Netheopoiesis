package dev.sefiraat.netheopoiesis.slimefun;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.core.plant.GrowthDescription;
import dev.sefiraat.netheopoiesis.core.plant.Placements;
import dev.sefiraat.netheopoiesis.listeners.VanillaDropListener;
import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherSeedCrux;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.DroppingSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.EntitySpawningSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.GenericTickingMethods;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.GenericTickingSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.HarvestableSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.progression.PurificationSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.progression.SaintlySeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.progression.SoulSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.progression.SpiritSeed;
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

    // Crafting
    public static final SlimefunItem ADDON_BERRY;
    public static final SlimefunItem ADDON_JAM;

    // Tools
    public static final HarvestingTool CRUDE_HARVESTING_TOOL;
    public static final HarvestingTool HARVESTING_TOOL;
    public static final PurificationBarometer PURIFICATION_BAROMETER;
    public static final PurificationScanner PURIFICATION_SCANNER;
    public static final Analyser SEED_ANALYSER;
    public static final EnderCake ENDER_CAKE;

    // Seeds
    public static final PurificationSeed PURIFICATION_SEED;
    public static final SoulSeed SOUL_SEED;
    public static final SpiritSeed SPIRIT_SEED;
    public static final SaintlySeed SAINTLY_SEED;

    // First Stage
    public static final GenericTickingSeed SPINDLE_SEED;
    public static final DroppingSeed GRAINY_SEED;
    public static final DroppingSeed STRINGY_SEED;
    public static final DroppingSeed STONEY_SEED;
    public static final HarvestableSeed DUSTY_SEED;
    public static final HarvestableSeed SEASIDE_SEED;
    public static final HarvestableSeed MOLDABLE_SEED;
    public static final WetSeed WET_SEED;
    public static final EntitySpawningSeed SPLINTERED_SEED;
    public static final EntitySpawningSeed ROTTEN_SEED;

    // Second Stage
    public static final HarvestableSeed METALLIC_SEED;
    public static final HarvestableSeed SHINY_SEED;
    public static final HarvestableSeed SMOOTH_SEED;
    public static final HarvestableSeed ENCHANTED_SEED;
    public static final HarvestableSeed COMBUSTIBLE_SEED;
    public static final EntitySpawningSeed PROTECTIVE_SEED;
    public static final EntitySpawningSeed PORKY_SEED;
    public static final HarvestableSeed VALUABLE_SEED;
    public static final HarvestableSeed PERFECTION_SEED;

    // Third Stage
    public static final DroppingSeed RAINBOW_SEED;
    public static final DroppingSeed GLOWING_SEED;
    public static final EntitySpawningSeed ETHEREAL_SEED;
    public static final EntitySpawningSeed IGNITED_SEED;
    public static final EntitySpawningSeed BARTERED_SEED;
    public static final DroppingSeed PRISMATIC_SEED;
    public static final HarvestableSeed POROUS_SEED;
    public static final HarvestableSeed LEARNED_SEED;
    public static final GenericTickingSeed OAKENDRAN_SEED;

    // Fourth Stage
    public static final HarvestableSeed ADDON_BERRY_SEED;

    // Crux'
    public static final NetherSeedCrux BASIC_PURIFIED_NETHERRACK;
    public static final NetherSeedCrux PURIFIED_NETHERRACK;
    public static final NetherSeedCrux VORACIOUS_DIRT;
    public static final NetherSeedCrux NETHER_DIRT;
    public static final NetherSeedCrux NETHER_GRASS;

    static {

        // region Crafting

        ADDON_BERRY = new SlimefunItem(
            NpsGroups.CRAFTING,
            NpsStacks.ADDON_BERRY,
            NpsRecipeTypes.PLANT_HARVEST,
            new ItemStack[0]
        );

        ADDON_JAM = new SlimefunItem(
            NpsGroups.CRAFTING,
            NpsStacks.ADDON_JAM,
            RecipeType.ORE_CRUSHER,
            new ItemStack[]{
                NpsStacks.ADDON_BERRY
            }
        );

        // endregion

        // region Tools

        CRUDE_HARVESTING_TOOL = new HarvestingTool(
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

        HARVESTING_TOOL = new HarvestingTool(
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

        PURIFICATION_BAROMETER = new PurificationBarometer(
            NpsGroups.TOOLS,
            NpsStacks.PURIFICATION_BAROMETER,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                SlimefunItems.ZINC_INGOT, GLASS, SlimefunItems.ZINC_INGOT,
                GLASS, REDSTONE, GLASS,
                SlimefunItems.ZINC_INGOT, GLASS, SlimefunItems.ZINC_INGOT,
            }
        );

        PURIFICATION_SCANNER = new PurificationScanner(
            NpsGroups.TOOLS,
            NpsStacks.PURIFICATION_SCANNER,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                SlimefunItems.BILLON_INGOT, GLASS, SlimefunItems.BILLON_INGOT,
                GLASS, NpsStacks.PURIFICATION_BAROMETER, GLASS,
                SlimefunItems.BILLON_INGOT, GLASS, SlimefunItems.BILLON_INGOT,
            }
        );

        SEED_ANALYSER = new Analyser(
            NpsGroups.TOOLS,
            NpsStacks.SEED_ANALYSER,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                SlimefunItems.DAMASCUS_STEEL_INGOT, GLASS, SlimefunItems.DAMASCUS_STEEL_INGOT,
                SlimefunItems.BRASS_INGOT, GLASS, SlimefunItems.BRASS_INGOT,
                SlimefunItems.DAMASCUS_STEEL_INGOT, REDSTONE, SlimefunItems.DAMASCUS_STEEL_INGOT,
            }
        );

        // todo create AddonJam,
        ENDER_CAKE = new EnderCake(
            NpsGroups.TOOLS,
            NpsStacks.ENDER_CAKE,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                null, null, null,
                null, null, null,
                null, null, null,
            }
        );

        // endregion

        // region Seeds

        PURIFICATION_SEED = new PurificationSeed(
            NpsGroups.SEEDS,
            NpsStacks.PURIFICATION_SEED,
            NpsRecipeTypes.VANILLA_BLOCK_DROP,
            VanillaDropListener.createRecipeWorldDrop(
                NpsStacks.PURIFICATION_SEED,
                new ItemStack(Material.SOUL_SOIL),
                0.05
            ),
            GrowthDescription.VINEY_BLUE,
            Placements.NULL
        );

        SOUL_SEED = new SoulSeed(
            NpsGroups.SEEDS,
            NpsStacks.SOUL_SEED,
            GrowthDescription.VINEY_BLUE,
            Placements.ALL
        );

        SPIRIT_SEED = new SpiritSeed(
            NpsGroups.SEEDS,
            NpsStacks.SPIRIT_SEED,
            GrowthDescription.VINEY_BLUE,
            Placements.PURIFIED_AND_UP
        );

        SAINTLY_SEED = new SaintlySeed(
            NpsGroups.SEEDS,
            NpsStacks.SAINTLY_SEED,
            GrowthDescription.VINEY_BLUE,
            Placements.VORACIOUS_AND_UP
        );

        SPINDLE_SEED = new GenericTickingSeed(
            NpsGroups.SEEDS,
            NpsStacks.SPINDLE_SEED,
            GrowthDescription.VINEY_ORANGE,
            Placements.ALL,
            GenericTickingMethods::onTickSpindleSeed,
            0.09,
            1
        );

        GRAINY_SEED = new DroppingSeed(
            NpsGroups.SEEDS,
            NpsStacks.GRAINY_SEED,
            GrowthDescription.VINEY_RED,
            Placements.ALL,
            new ItemStack(Material.REDSTONE),
            0.09,
            1
        );

        STRINGY_SEED = new DroppingSeed(
            NpsGroups.SEEDS,
            NpsStacks.STRINGY_SEED,
            GrowthDescription.VINEY_GREEN,
            Placements.ALL,
            new ItemStack(Material.STRING),
            0.09,
            1
        );

        STONEY_SEED = new DroppingSeed(
            NpsGroups.SEEDS,
            NpsStacks.STONEY_SEED,
            GrowthDescription.VINEY_PURPLE,
            Placements.ALL,
            new ItemStack(Material.COBBLESTONE),
            0.09,
            1
        );

        DUSTY_SEED = new HarvestableSeed(
            NpsGroups.SEEDS,
            NpsStacks.DUSTY_SEED,
            GrowthDescription.VINEY_CYAN,
            Placements.ALL,
            new ItemStack(Material.GRAVEL),
            0.09,
            1
        );

        SEASIDE_SEED = new HarvestableSeed(
            NpsGroups.SEEDS,
            NpsStacks.SEASIDE_SEED,
            GrowthDescription.VINEY_YELLOW,
            Placements.ALL,
            new ItemStack(Material.SAND),
            0.09,
            1
        );

        MOLDABLE_SEED = new HarvestableSeed(
            NpsGroups.SEEDS,
            NpsStacks.MOLDABLE_SEED,
            GrowthDescription.VINEY_PURPLE,
            Placements.ALL,
            new ItemStack(Material.CLAY_BALL),
            0.09,
            1
        );

        WET_SEED = new WetSeed(
            NpsGroups.SEEDS,
            NpsStacks.WET_SEED,
            GrowthDescription.VINEY_BLUE,
            Placements.ALL,
            0.11,
            2
        );

        SPLINTERED_SEED = new EntitySpawningSeed(
            NpsGroups.SEEDS,
            NpsStacks.SPLINTERED_SEED,
            GrowthDescription.VINEY_CYAN,
            Placements.ALL,
            EntityType.SKELETON,
            0.08,
            2
        );

        ROTTEN_SEED = new EntitySpawningSeed(
            NpsGroups.SEEDS,
            NpsStacks.ROTTEN_SEED,
            GrowthDescription.VINEY_GREEN,
            Placements.ALL,
            EntityType.ZOMBIE,
            0.08,
            2
        );

        METALLIC_SEED = new HarvestableSeed(
            NpsGroups.SEEDS,
            NpsStacks.METALLIC_SEED,
            GrowthDescription.VINEY_RED,
            Placements.PURIFIED_AND_UP,
            new ItemStack(Material.IRON_NUGGET),
            0.08,
            2
        );

        SHINY_SEED = new HarvestableSeed(
            NpsGroups.SEEDS,
            NpsStacks.SHINY_SEED,
            GrowthDescription.VINEY_YELLOW,
            Placements.PURIFIED_AND_UP,
            new ItemStack(Material.GOLD_NUGGET),
            0.08,
            2
        );

        SMOOTH_SEED = new HarvestableSeed(
            NpsGroups.SEEDS,
            NpsStacks.SMOOTH_SEED,
            GrowthDescription.VINEY_CYAN,
            Placements.PURIFIED_AND_UP,
            new ItemStack(Material.AMETHYST_SHARD),
            0.08,
            2
        );

        ENCHANTED_SEED = new HarvestableSeed(
            NpsGroups.SEEDS,
            NpsStacks.ENCHANTED_SEED,
            GrowthDescription.VINEY_BLUE,
            Placements.PURIFIED_AND_UP,
            new ItemStack(Material.LAPIS_LAZULI),
            0.07,
            3
        );

        COMBUSTIBLE_SEED = new HarvestableSeed(
            NpsGroups.SEEDS,
            NpsStacks.COMBUSTIBLE_SEED,
            GrowthDescription.VINEY_RED,
            Placements.PURIFIED_AND_UP,
            new ItemStack(Material.COAL),
            0.07,
            3
        );

        PROTECTIVE_SEED = new EntitySpawningSeed(
            NpsGroups.SEEDS,
            NpsStacks.PROTECTIVE_SEED,
            GrowthDescription.VINEY_PURPLE,
            Placements.ALL,
            EntityType.IRON_GOLEM,
            0.03,
            5
        );

        PORKY_SEED = new EntitySpawningSeed(
            NpsGroups.SEEDS,
            NpsStacks.PORKY_SEED,
            GrowthDescription.VINEY_RED,
            Placements.ALL,
            EntityType.PIG,
            0.08,
            3
        );

        VALUABLE_SEED = new HarvestableSeed(
            NpsGroups.SEEDS,
            NpsStacks.VALUABLE_SEED,
            GrowthDescription.VINEY_GREEN,
            Placements.PURIFIED_AND_UP,
            new ItemStack(Material.EMERALD),
            0.07,
            3
        );

        PERFECTION_SEED = new HarvestableSeed(
            NpsGroups.SEEDS,
            NpsStacks.PERFECTION_SEED,
            GrowthDescription.VINEY_BLUE,
            Placements.PURIFIED_AND_UP,
            new ItemStack(Material.DIAMOND),
            0.07,
            5
        );

        RAINBOW_SEED = new DroppingSeed(
            NpsGroups.SEEDS,
            NpsStacks.RAINBOW_SEED,
            GrowthDescription.VINEY_CYAN,
            Placements.VORACIOUS_AND_UP,
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
            0.06,
            8
        );

        GLOWING_SEED = new DroppingSeed(
            NpsGroups.SEEDS,
            NpsStacks.GLOWING_SEED,
            GrowthDescription.VINEY_RED,
            Placements.VORACIOUS_AND_UP,
            new ItemStack[]{
                new ItemStack(Material.GLOW_LICHEN),
                new ItemStack(Material.GLOW_BERRIES),
                new ItemStack(Material.GLOW_INK_SAC)
            },
            0.06,
            8
        );

        ETHEREAL_SEED = new EntitySpawningSeed(
            NpsGroups.SEEDS,
            NpsStacks.ETHEREAL_SEED,
            GrowthDescription.VINEY_GREEN,
            Placements.VORACIOUS_AND_UP,
            EntityType.ENDERMAN,
            0.06,
            6
        );

        IGNITED_SEED = new EntitySpawningSeed(
            NpsGroups.SEEDS,
            NpsStacks.IGNITED_SEED,
            GrowthDescription.VINEY_RED,
            Placements.VORACIOUS_AND_UP,
            EntityType.BLAZE,
            0.07,
            8
        );

        BARTERED_SEED = new EntitySpawningSeed(
            NpsGroups.SEEDS,
            NpsStacks.BARTERED_SEED,
            GrowthDescription.VINEY_CYAN,
            Placements.VORACIOUS_AND_UP,
            EntityType.PIGLIN,
            0.06,
            8
        );

        PRISMATIC_SEED = new DroppingSeed(
            NpsGroups.SEEDS,
            NpsStacks.PRISMATIC_SEED,
            GrowthDescription.VINEY_GREEN,
            Placements.VORACIOUS_AND_UP,
            new ItemStack[]{
                new ItemStack(Material.PRISMARINE_SHARD),
                new ItemStack(Material.PRISMARINE_CRYSTALS)
            },
            0.06,
            9
        );

        POROUS_SEED = new HarvestableSeed(
            NpsGroups.SEEDS,
            NpsStacks.POROUS_SEED,
            GrowthDescription.VINEY_YELLOW,
            Placements.VORACIOUS_AND_UP,
            new ItemStack(Material.SPONGE),
            0.06,
            9
        );

        LEARNED_SEED = new HarvestableSeed(
            NpsGroups.SEEDS,
            NpsStacks.LEARNED_SEED,
            GrowthDescription.VINEY_ORANGE,
            Placements.VORACIOUS_AND_UP,
            new ItemStack(Material.EXPERIENCE_BOTTLE),
            0.06,
            9
        );

        OAKENDRAN_SEED = new GenericTickingSeed(
            NpsGroups.SEEDS,
            NpsStacks.OAKENDRAN_SEED,
            GrowthDescription.VINEY_ORANGE,
            Placements.VORACIOUS_AND_UP,
            GenericTickingMethods::onTickOakendranSeed,
            0.04,
            12
        );

        ADDON_BERRY_SEED = new HarvestableSeed(
            NpsGroups.SEEDS,
            NpsStacks.ADDON_BERRY_SEED,
            GrowthDescription.VINEY_RED,
            Placements.NETHER_DIRT_AND_UP,
            NpsStacks.ADDON_BERRY,
            0.2,
            10
        );

        // endregion

        // region Crux'

        BASIC_PURIFIED_NETHERRACK = new NetherSeedCrux(
            NpsGroups.CRUX,
            NpsStacks.BASIC_PURIFIED_NETHERRACK,
            NpsRecipeTypes.NETHER_PURIFICATION,
            new ItemStack[0],
            1
        );

        PURIFIED_NETHERRACK = new NetherSeedCrux(
            NpsGroups.CRUX,
            NpsStacks.PURIFIED_NETHERRACK,
            NpsRecipeTypes.NETHER_PURIFICATION,
            new ItemStack[0],
            2
        );

        VORACIOUS_DIRT = new NetherSeedCrux(
            NpsGroups.CRUX,
            NpsStacks.VORACIOUS_DIRT,
            NpsRecipeTypes.NETHER_PURIFICATION,
            new ItemStack[0],
            4
        );

        NETHER_DIRT = new NetherSeedCrux(
            NpsGroups.CRUX,
            NpsStacks.NETHER_DIRT,
            NpsRecipeTypes.NETHER_PURIFICATION,
            new ItemStack[0],
            8
        );

        NETHER_GRASS = new NetherSeedCrux(
            NpsGroups.CRUX,
            NpsStacks.NETHER_GRASS,
            NpsRecipeTypes.NETHER_PURIFICATION,
            new ItemStack[0],
            16
        );

        // endregion
    }

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

        // Crux'
        BASIC_PURIFIED_NETHERRACK.register(plugin);
        PURIFIED_NETHERRACK.register(plugin);
        VORACIOUS_DIRT.register(plugin);
        NETHER_DIRT.register(plugin);
        NETHER_GRASS.register(plugin);
    }
}
