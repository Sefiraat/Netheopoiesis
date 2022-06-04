package dev.sefiraat.netheopoiesis.slimefun;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.listeners.VanillaDropListener;
import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherSeedCrux;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.implementation.PurificationSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.implementation.OakendranSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.implementation.SpindleSeed;
import dev.sefiraat.netheopoiesis.slimefun.groups.NpsItemGroups;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class NpsSlimefunItems {

    private NpsSlimefunItems() {
        throw new IllegalStateException("Utility class");
    }

    // Seeds
    public static final PurificationSeed PURIFICATION_SEED;
    public static final SpindleSeed SPINDLE_SEED;




    public static final OakendranSeed OAKENDRAN_SEED
        ;

    // Crux'
    public static final NetherSeedCrux BASIC_PURIFIED_NETHERRACK;
    public static final NetherSeedCrux PURIFIED_NETHERRACK;
    public static final NetherSeedCrux VORACIOUS_DIRT;
    public static final NetherSeedCrux NETHER_DIRT;
    public static final NetherSeedCrux NETHER_GRASS;

    static {

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

        SPINDLE_SEED = new SpindleSeed(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.SPINDLE_SEED,
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

        // Seeds
        PURIFICATION_SEED.register(plugin);
        SPINDLE_SEED.register(plugin);




        OAKENDRAN_SEED.register(plugin);

        // Crux'
        BASIC_PURIFIED_NETHERRACK.register(plugin);
        PURIFIED_NETHERRACK.register(plugin);
        VORACIOUS_DIRT.register(plugin);
        NETHER_DIRT.register(plugin);
        NETHER_GRASS.register(plugin);
    }
}
