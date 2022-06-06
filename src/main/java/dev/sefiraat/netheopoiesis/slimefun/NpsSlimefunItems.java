package dev.sefiraat.netheopoiesis.slimefun;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.core.plant.GrowthDescription;
import dev.sefiraat.netheopoiesis.core.plant.GrowthDescriptions;
import dev.sefiraat.netheopoiesis.core.plant.Placements;
import dev.sefiraat.netheopoiesis.listeners.VanillaDropListener;
import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherSeedCrux;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.DroppingSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.EntitySpawningPlant;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.GenericTickingMethods;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.GenericTickingPlant;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.HarvestableSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.progression.PurificationSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.progression.SoulSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.progression.SpiritSeed;
import dev.sefiraat.netheopoiesis.slimefun.groups.NpsItemGroups;
import dev.sefiraat.netheopoiesis.slimefun.tools.Analyser;
import dev.sefiraat.netheopoiesis.slimefun.tools.HarvestingTool;
import dev.sefiraat.netheopoiesis.slimefun.tools.PurificationBarometer;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class NpsSlimefunItems {

    private NpsSlimefunItems() {
        throw new IllegalStateException("Utility class");
    }

    // Vanilla Materials
    private static final ItemStack OAK_PLANK = new ItemStack(Material.OAK_PLANKS);
    private static final ItemStack IRON_INGOT = new ItemStack(Material.IRON_INGOT);
    private static final ItemStack GLASS = new ItemStack(Material.GLASS);
    private static final ItemStack REDSTONE = new ItemStack(Material.REDSTONE);

    // Crafting

    // Tools
    public static final HarvestingTool CRUDE_HARVESTING_TOOL;
    public static final HarvestingTool HARVESTING_TOOL;
    public static final PurificationBarometer PURIFICATION_BAROMETER;
    public static final Analyser SEED_ANALYSER;

    // Seeds
    public static final PurificationSeed PURIFICATION_SEED;
    public static final SoulSeed SOUL_SEED;
    public static final SpiritSeed SPIRIT_SEED;

    // First Stage
    public static final GenericTickingPlant SPINDLE_SEED;
    public static final DroppingSeed GRAINY_SEED;
    public static final DroppingSeed STRINGY_SEED;
    public static final DroppingSeed STONEY_SEED;
    public static final HarvestableSeed DUSTY_SEED;
    public static final HarvestableSeed SEASIDE_SEED;
    public static final HarvestableSeed MOLDABLE_SEED;
    public static final EntitySpawningPlant SPLINTERED_SEED;
    public static final EntitySpawningPlant ROTTEN_SEED;

    // Second Stage
    public static final HarvestableSeed METALLIC_SEED;
    public static final HarvestableSeed SHINY_SEED;
    public static final HarvestableSeed SMOOTH_SEED;
    public static final HarvestableSeed ENCHANTED_SEED;
    public static final HarvestableSeed COMBUSTIBLE_SEED;
    public static final EntitySpawningPlant PROTECTIVE_SEED;
    public static final HarvestableSeed VALUABLE_SEED;
    public static final HarvestableSeed PERFECTION_SEED;

    // Third Stage
    public static final GenericTickingPlant OAKENDRAN_SEED;

    // Crux'
    public static final NetherSeedCrux BASIC_PURIFIED_NETHERRACK;
    public static final NetherSeedCrux PURIFIED_NETHERRACK;
    public static final NetherSeedCrux VORACIOUS_DIRT;
    public static final NetherSeedCrux NETHER_DIRT;
    public static final NetherSeedCrux NETHER_GRASS;

    static {

        // region Crafting

        // endregion

        // region Tools

        CRUDE_HARVESTING_TOOL = new HarvestingTool(
            NpsItemGroups.TOOLS,
            NpsSlimefunItemStacks.CRUDE_HARVESTING_TOOL,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                null, null, OAK_PLANK,
                OAK_PLANK, OAK_PLANK, null,
                OAK_PLANK, OAK_PLANK, null,
            },
            25
        );

        HARVESTING_TOOL = new HarvestingTool(
            NpsItemGroups.TOOLS,
            NpsSlimefunItemStacks.HARVESTING_TOOL,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                null, null, IRON_INGOT,
                IRON_INGOT, IRON_INGOT, null,
                IRON_INGOT, IRON_INGOT, null,
            },
            150
        );

        PURIFICATION_BAROMETER = new PurificationBarometer(
            NpsItemGroups.TOOLS,
            NpsSlimefunItemStacks.PURIFICATION_BAROMETER,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                SlimefunItems.ZINC_INGOT, GLASS, SlimefunItems.ZINC_INGOT,
                GLASS, REDSTONE, GLASS,
                SlimefunItems.ZINC_INGOT, GLASS, SlimefunItems.ZINC_INGOT,
            }
        );

        SEED_ANALYSER = new Analyser(
            NpsItemGroups.TOOLS,
            NpsSlimefunItemStacks.SEED_ANALYSER,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                SlimefunItems.DAMASCUS_STEEL_INGOT, GLASS, SlimefunItems.DAMASCUS_STEEL_INGOT,
                SlimefunItems.BRASS_INGOT, GLASS, SlimefunItems.BRASS_INGOT,
                SlimefunItems.DAMASCUS_STEEL_INGOT, REDSTONE, SlimefunItems.DAMASCUS_STEEL_INGOT,
            }
        );

        // endregion

        // region Seeds

        PURIFICATION_SEED = new PurificationSeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.PURIFICATION_SEED,
            NpsRecipeTypes.VANILLA_BLOCK_DROP,
            VanillaDropListener.createRecipeWorldDrop(
                NpsSlimefunItemStacks.PURIFICATION_SEED,
                new ItemStack(Material.SOUL_SOIL),
                0.05
            ),
            GrowthDescriptions.HARDY_BLUE,
            Placements.NULL
        );

        SOUL_SEED = new SoulSeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.SOUL_SEED,
            GrowthDescriptions.HARDY_BLUE,
            Placements.ALL
        );

        SPIRIT_SEED = new SpiritSeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.SPIRIT_SEED,
            GrowthDescriptions.HARDY_BLUE,
            Placements.PURIFIED_AND_UP
        );

        SPINDLE_SEED = new GenericTickingPlant(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.SPINDLE_SEED,
            GrowthDescriptions.HARDY_ORANGE,
            Placements.ALL,
            GenericTickingMethods::onTickSpindleSeed,
            0.09,
            1
        );

        GRAINY_SEED = new DroppingSeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.GRAINY_SEED,
            GrowthDescriptions.HARDY_RED,
            Placements.ALL,
            new ItemStack(Material.REDSTONE),
            0.09,
            1
        );

        STRINGY_SEED = new DroppingSeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.STRINGY_SEED,
            GrowthDescriptions.HARDY_GREEN,
            Placements.ALL,
            new ItemStack(Material.STRING),
            0.09,
            1
        );

        STONEY_SEED = new DroppingSeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.STONEY_SEED,
            GrowthDescriptions.HARDY_INDIGO,
            Placements.ALL,
            new ItemStack(Material.COBBLESTONE),
            0.09,
            1
        );

        DUSTY_SEED = new HarvestableSeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.DUSTY_SEED,
            GrowthDescriptions.HARDY_INDIGO,
            Placements.ALL,
            new ItemStack(Material.GRAVEL),
            0.09,
            1
        );

        SEASIDE_SEED = new HarvestableSeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.SEASIDE_SEED,
            GrowthDescriptions.HARDY_YELLOW,
            Placements.ALL,
            new ItemStack(Material.SAND),
            0.09,
            1
        );

        MOLDABLE_SEED = new HarvestableSeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.MOLDABLE_SEED,
            GrowthDescriptions.HARDY_INDIGO,
            Placements.ALL,
            new ItemStack(Material.CLAY_BALL),
            0.09,
            1
        );

        SPLINTERED_SEED = new EntitySpawningPlant(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.SPLINTERED_SEED,
            GrowthDescriptions.HARDY_ORANGE,
            Placements.ALL,
            EntityType.SKELETON,
            0.08,
            2
        );

        ROTTEN_SEED = new EntitySpawningPlant(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.ROTTEN_SEED,
            GrowthDescriptions.HARDY_GREEN,
            Placements.ALL,
            EntityType.ZOMBIE,
            0.08,
            2
        );

        METALLIC_SEED = new HarvestableSeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.METALLIC_SEED,
            GrowthDescriptions.HARDY_RED,
            Placements.PURIFIED_AND_UP,
            new ItemStack(Material.IRON_NUGGET),
            0.08,
            2
        );

        SHINY_SEED = new HarvestableSeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.SHINY_SEED,
            GrowthDescriptions.HARDY_YELLOW,
            Placements.PURIFIED_AND_UP,
            new ItemStack(Material.GOLD_NUGGET),
            0.08,
            2
        );

        SMOOTH_SEED = new HarvestableSeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.SMOOTH_SEED,
            GrowthDescriptions.HARDY_VIOLET,
            Placements.PURIFIED_AND_UP,
            new ItemStack(Material.AMETHYST_SHARD),
            0.08,
            2
        );

        ENCHANTED_SEED = new HarvestableSeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.ENCHANTED_SEED,
            GrowthDescriptions.HARDY_BLUE,
            Placements.PURIFIED_AND_UP,
            new ItemStack(Material.LAPIS_LAZULI),
            0.07,
            3
        );

        COMBUSTIBLE_SEED = new HarvestableSeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.COMBUSTIBLE_SEED,
            GrowthDescriptions.HARDY_VIOLET,
            Placements.PURIFIED_AND_UP,
            new ItemStack(Material.COAL),
            0.07,
            3
        );

        PROTECTIVE_SEED = new EntitySpawningPlant(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.PROTECTIVE_SEED,
            GrowthDescriptions.HARDY_YELLOW,
            Placements.ALL,
            EntityType.IRON_GOLEM,
            0.03,
            5
        );

        VALUABLE_SEED = new HarvestableSeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.VALUABLE_SEED,
            GrowthDescriptions.HARDY_VIOLET,
            Placements.PURIFIED_AND_UP,
            new ItemStack(Material.EMERALD),
            0.07,
            3
        );

        PERFECTION_SEED = new HarvestableSeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.PERFECTION_SEED,
            GrowthDescriptions.HARDY_BLUE,
            Placements.PURIFIED_AND_UP,
            new ItemStack(Material.DIAMOND),
            0.07,
            5
        );

        OAKENDRAN_SEED = new GenericTickingPlant(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.OAKENDRAN_SEED,
            GrowthDescriptions.HARDY_RED,
            Placements.VORACIOUS_AND_UP,
            GenericTickingMethods::onTickOakendranSeed,
            0.04,
            12
        );

        // endregion

        // region Crux'

        BASIC_PURIFIED_NETHERRACK = new NetherSeedCrux(
            NpsItemGroups.CRUX,
            NpsSlimefunItemStacks.BASIC_PURIFIED_NETHERRACK,
            NpsRecipeTypes.NETHER_PURIFICATION,
            new ItemStack[0],
            1
        );

        PURIFIED_NETHERRACK = new NetherSeedCrux(
            NpsItemGroups.CRUX,
            NpsSlimefunItemStacks.PURIFIED_NETHERRACK,
            NpsRecipeTypes.NETHER_PURIFICATION,
            new ItemStack[0],
            2
        );

        VORACIOUS_DIRT = new NetherSeedCrux(
            NpsItemGroups.CRUX,
            NpsSlimefunItemStacks.VORACIOUS_DIRT,
            NpsRecipeTypes.NETHER_PURIFICATION,
            new ItemStack[0],
            4
        );

        NETHER_DIRT = new NetherSeedCrux(
            NpsItemGroups.CRUX,
            NpsSlimefunItemStacks.NETHER_DIRT,
            NpsRecipeTypes.NETHER_PURIFICATION,
            new ItemStack[0],
            8
        );

        NETHER_GRASS = new NetherSeedCrux(
            NpsItemGroups.CRUX,
            NpsSlimefunItemStacks.NETHER_GRASS,
            NpsRecipeTypes.NETHER_PURIFICATION,
            new ItemStack[0],
            16
        );

        // endregion
    }

    public static void setup() {
        final Netheopoiesis plugin = Netheopoiesis.getInstance();

        // Crafting

        // Tools
        CRUDE_HARVESTING_TOOL.register(plugin);
        HARVESTING_TOOL.register(plugin);
        PURIFICATION_BAROMETER.register(plugin);
        SEED_ANALYSER.register(plugin);

        // Seeds
        PURIFICATION_SEED.register(plugin);
        SOUL_SEED.register(plugin);
        SPIRIT_SEED.register(plugin);

        SPINDLE_SEED.register(plugin);
        GRAINY_SEED.register(plugin);
        STRINGY_SEED.register(plugin);
        STONEY_SEED.register(plugin);
        DUSTY_SEED.register(plugin);
        SEASIDE_SEED.register(plugin);
        MOLDABLE_SEED.register(plugin);
        SPLINTERED_SEED.register(plugin);
        ROTTEN_SEED.register(plugin);

        METALLIC_SEED.register(plugin);
        SHINY_SEED.register(plugin);
        SMOOTH_SEED.register(plugin);
        ENCHANTED_SEED.register(plugin);
        COMBUSTIBLE_SEED.register(plugin);
        PROTECTIVE_SEED.register(plugin);
        VALUABLE_SEED.register(plugin);
        PERFECTION_SEED.register(plugin);

        OAKENDRAN_SEED.register(plugin);

        // Crux'
        BASIC_PURIFIED_NETHERRACK.register(plugin);
        PURIFIED_NETHERRACK.register(plugin);
        VORACIOUS_DIRT.register(plugin);
        NETHER_DIRT.register(plugin);
        NETHER_GRASS.register(plugin);
    }
}
