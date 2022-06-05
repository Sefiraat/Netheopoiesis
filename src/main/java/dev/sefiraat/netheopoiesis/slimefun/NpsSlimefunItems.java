package dev.sefiraat.netheopoiesis.slimefun;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.listeners.VanillaDropListener;
import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherSeedCrux;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.implementation.DustySeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.implementation.GrainySeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.implementation.OakendranSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.implementation.PurificationSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.implementation.RottenSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.implementation.SeasideSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.implementation.SoulSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.implementation.SpindleSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.implementation.SplinteredSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.implementation.StoneySeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.implementation.StringySeed;
import dev.sefiraat.netheopoiesis.slimefun.groups.NpsItemGroups;
import dev.sefiraat.netheopoiesis.slimefun.tools.HarvestingTool;
import dev.sefiraat.netheopoiesis.slimefun.tools.PurificationBarometer;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class NpsSlimefunItems {

    private NpsSlimefunItems() {
        throw new IllegalStateException("Utility class");
    }

    // Vanilla Materials
    private static final ItemStack OAK_PLANK = new ItemStack(Material.OAK_PLANKS);
    private static final ItemStack GLASS = new ItemStack(Material.GLASS);
    private static final ItemStack REDSTONE = new ItemStack(Material.REDSTONE);

    // Crafting

    // Tools
    public static final HarvestingTool CRUDE_HARVESTING_TOOL;
    public static final PurificationBarometer PURIFICATION_BAROMETER;

    // Seeds
    public static final PurificationSeed PURIFICATION_SEED;
    public static final SoulSeed SOUL_SEED;

    public static final SpindleSeed SPINDLE_SEED;
    public static final GrainySeed GRAINY_SEED;
    public static final StringySeed STRINGY_SEED;
    public static final StoneySeed STONEY_SEED;
    public static final DustySeed DUSTY_SEED;
    public static final SeasideSeed SEASIDE_SEED;
    public static final SplinteredSeed SPLINTERED_SEED;
    public static final RottenSeed ROTTEN_SEED;


    public static final OakendranSeed OAKENDRAN_SEED;

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
            )
        );

        SOUL_SEED = new SoulSeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.SOUL_SEED,
            NpsRecipeTypes.PLANT_BREEDING,
            new ItemStack[0]
        );

        SPINDLE_SEED = new SpindleSeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.SPINDLE_SEED,
            NpsRecipeTypes.PLANT_BREEDING,
            new ItemStack[0]
        );

        GRAINY_SEED = new GrainySeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.GRAINY_SEED,
            NpsRecipeTypes.PLANT_BREEDING,
            new ItemStack[0]
        );

        STRINGY_SEED = new StringySeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.STRINGY_SEED,
            NpsRecipeTypes.PLANT_BREEDING,
            new ItemStack[0]
        );

        STONEY_SEED = new StoneySeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.STONEY_SEED,
            NpsRecipeTypes.PLANT_BREEDING,
            new ItemStack[0]
        );

        DUSTY_SEED = new DustySeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.DUSTY_SEED,
            NpsRecipeTypes.PLANT_BREEDING,
            new ItemStack[0]
        );

        SEASIDE_SEED = new SeasideSeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.SEASIDE_SEED,
            NpsRecipeTypes.PLANT_BREEDING,
            new ItemStack[0]
        );

        SPLINTERED_SEED = new SplinteredSeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.SPLINTERED_SEED,
            NpsRecipeTypes.PLANT_BREEDING,
            new ItemStack[0]
        );

        ROTTEN_SEED = new RottenSeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.ROTTEN_SEED,
            NpsRecipeTypes.PLANT_BREEDING,
            new ItemStack[0]
        );

        OAKENDRAN_SEED = new OakendranSeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.OAKENDRAN_SEED,
            NpsRecipeTypes.PLANT_BREEDING,
            new ItemStack[0]
        );

        // endregion

        // region Crux'

        BASIC_PURIFIED_NETHERRACK = new NetherSeedCrux(
            NpsItemGroups.CRUX,
            NpsSlimefunItemStacks.BASIC_PURIFIED_NETHERRACK,
            NpsRecipeTypes.NETHER_PURIFICATION,
            new ItemStack[0]
        );

        PURIFIED_NETHERRACK = new NetherSeedCrux(
            NpsItemGroups.CRUX,
            NpsSlimefunItemStacks.PURIFIED_NETHERRACK,
            NpsRecipeTypes.NETHER_PURIFICATION,
            new ItemStack[0]
        );

        VORACIOUS_DIRT = new NetherSeedCrux(
            NpsItemGroups.CRUX,
            NpsSlimefunItemStacks.VORACIOUS_DIRT,
            NpsRecipeTypes.NETHER_PURIFICATION,
            new ItemStack[0]
        );

        NETHER_DIRT = new NetherSeedCrux(
            NpsItemGroups.CRUX,
            NpsSlimefunItemStacks.NETHER_DIRT,
            NpsRecipeTypes.NETHER_PURIFICATION,
            new ItemStack[0]
        );

        NETHER_GRASS = new NetherSeedCrux(
            NpsItemGroups.CRUX,
            NpsSlimefunItemStacks.NETHER_GRASS,
            NpsRecipeTypes.NETHER_PURIFICATION,
            new ItemStack[0]
        );

        // endregion
    }

    public static void setup() {
        final Netheopoiesis plugin = Netheopoiesis.getInstance();

        // Crafting

        // Tools
        CRUDE_HARVESTING_TOOL.register(plugin);
        PURIFICATION_BAROMETER.register(plugin);

        // Seeds
        PURIFICATION_SEED.register(plugin);
        SOUL_SEED.register(plugin);

        SPINDLE_SEED.register(plugin);
        GRAINY_SEED.register(plugin);
        STRINGY_SEED.register(plugin);
        STONEY_SEED.register(plugin);
        DUSTY_SEED.register(plugin);
        SEASIDE_SEED.register(plugin);
        SPLINTERED_SEED.register(plugin);
        ROTTEN_SEED.register(plugin);

        OAKENDRAN_SEED.register(plugin);

        // Crux'
        BASIC_PURIFIED_NETHERRACK.register(plugin);
        PURIFIED_NETHERRACK.register(plugin);
        VORACIOUS_DIRT.register(plugin);
        NETHER_DIRT.register(plugin);
        NETHER_GRASS.register(plugin);
    }
}
