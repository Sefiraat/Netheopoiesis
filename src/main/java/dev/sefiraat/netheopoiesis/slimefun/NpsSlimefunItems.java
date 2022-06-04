package dev.sefiraat.netheopoiesis.slimefun;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.listeners.VanillaDropListener;
import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherSeedCrux;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.PurificationSeed;
import dev.sefiraat.netheopoiesis.slimefun.groups.NpsItemGroups;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class NpsSlimefunItems {

    private NpsSlimefunItems() {
        throw new IllegalStateException("Utility class");
    }

    // Seeds
    public static final PurificationSeed PURIFICATION_SEED;

    // Crux'
    public static final NetherSeedCrux BASIC_PURIFIED_NETHERRACK;

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

        // endregion

        // region Seeds

        BASIC_PURIFIED_NETHERRACK = new NetherSeedCrux(
            NpsItemGroups.SEEDS,
            NpsSlimefunItemStacks.BASIC_PURIFIED_NETHERRACK,
            RecipeType.NULL,
            new ItemStack[0]
        );

        // endregion
    }

    public static void setup() {
        final Netheopoiesis plugin = Netheopoiesis.getInstance();

        // Seeds
        PURIFICATION_SEED.register(plugin);

        // Crux'
        BASIC_PURIFIED_NETHERRACK.register(plugin);
    }
}
