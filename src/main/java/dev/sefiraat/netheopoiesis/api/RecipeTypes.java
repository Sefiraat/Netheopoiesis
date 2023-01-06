package dev.sefiraat.netheopoiesis.api;

import com.google.common.base.Preconditions;
import dev.sefiraat.netheopoiesis.api.interfaces.WorldCrushable;
import dev.sefiraat.netheopoiesis.api.plant.netheos.NetheoBalls;
import dev.sefiraat.netheopoiesis.listeners.DropListener;
import dev.sefiraat.netheopoiesis.utils.Keys;
import dev.sefiraat.netheopoiesis.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * Final class used to store and initialise the {@link RecipeType}s used in the addon
 */
public final class RecipeTypes {

    private RecipeTypes() {
        throw new IllegalStateException("Utility class");
    }

    @Nonnull
    public static final RecipeType NETHER_PURIFICATION = new RecipeType(
        Keys.newKey("nether_purification"),
        Theme.themedItemStack(
            Material.WITHER_ROSE,
            Theme.RECIPE_TYPE,
            "Nether Purification",
            "These items are made by purifying the Nether.",
            "This is done by planting Purifying Seeds."
        )
    );

    @Nonnull
    public static final RecipeType PLANT_HARVEST = new RecipeType(
        Keys.newKey("plant_harvest"),
        Theme.themedItemStack(
            Material.PRISMARINE_SHARD,
            Theme.RECIPE_TYPE,
            "Plant Harvesting",
            "This item can be harvested from a plant."
        )
    );

    @Nonnull
    public static final RecipeType PLANT_BREEDING = new RecipeType(
        Keys.newKey("plant_breeding"),
        Theme.themedItemStack(
            Material.FLOWER_POT,
            Theme.RECIPE_TYPE,
            "Plant Breeding",
            "This seed is found via breeding.",
            "You breed plants by placing",
            "two plants down with an air block",
            "between them.",
            "",
            "The breeding dictionary can be used",
            "to track your discoveries.",
            "",
            "Hint: Black particles mean the",
            "two plants CANNOT breed."
        )
    );

    @Nonnull
    public static final RecipeType VANILLA_DROP = new RecipeType(
        Keys.newKey("vanilla_block_drop"),
        Theme.themedItemStack(
            Material.BROWN_DYE,
            Theme.RECIPE_TYPE,
            "World Drop",
            "Drops from blocks when broken in the world."
        )
    );

    @Nonnull
    public static final RecipeType CRUSHING = new RecipeType(
        Keys.newKey("crushing"),
        Theme.themedItemStack(
            Material.ANVIL,
            Theme.RECIPE_TYPE,
            "Tough Crushing",
            "This item is made by crushing it.",
            "To crush items, from an Anvil on it from",
            "a height."
        )
    );

    @Nonnull
    public static final RecipeType NETHEO_MIXING = new RecipeType(
        Keys.newKey("netheo-mixing"),
        Theme.themedItemStack(
            Material.QUARTZ,
            Theme.RECIPE_TYPE,
            "Netheo Mixing",
            "This item is made by mixing three",
            "Netheo pastes together.",
            "Throw the three pastes onto the ground",
            "and then right click with a Mixing Quartz."
        )
    );

    @Nonnull
    public static final RecipeType WANDERING_PIGLIN_TRADE = new RecipeType(
        Keys.newKey("piglin-trade"),
        Theme.themedItemStack(
            Material.PIGLIN_SPAWN_EGG,
            Theme.RECIPE_TYPE,
            "Wandering Piglin Trade",
            "This item is a chance trade from",
            "a Wandering Pigling (NOT A NORMAL PIGLIN)",
            "Wandering Piglins spawn with two",
            "Striders and offer special trades.",
            "More information on spawning can",
            "be found in the purification section",
            "of the guide."
        )
    );

    /**
     * This method both registers the drop and returns an ItemStack array that can be used
     * for Slimefun's recipe system. {@link RecipeTypes#VANILLA_DROP}
     *
     * @param stackToDrop The {@link ItemStack} to drop in the world
     * @param dropFrom    The {@link ItemStack} to drop from (#getType() is used) and the stack is used in the recipe.
     * @param dropChance  The chance (0-1) for the drop to occur
     * @return A {@link ItemStack[]} used for Slimefun's Recipe registration with the dropFrom item in the middle.
     */
    @Nonnull
    public static ItemStack[] createWorldDropRecipe(@Nonnull ItemStack stackToDrop,
                                                    @Nonnull ItemStack dropFrom,
                                                    double dropChance
    ) {
        final Material material = dropFrom.getType();
        DropListener.getDropMap().put(material, new DropListener.BlockDrop(stackToDrop, material, dropChance));
        return new ItemStack[]{
            null, null, null,
            null, dropFrom, null,
            null, null, null
        };
    }

    /**
     * This method returns an ItemStack array that can be used for Slimefun's recipe system.
     *
     * @param dropFrom The {@link SlimefunItem} (must implement WorldCrushable) to drop from.
     * @return A {@link ItemStack[]} used for Slimefun's Recipe registration with the dropFrom item in the middle.
     * @see RecipeTypes#CRUSHING
     */
    @Nonnull
    public static ItemStack[] createCrushingRecipe(@Nonnull SlimefunItem dropFrom) {
        Preconditions.checkArgument(
            dropFrom instanceof WorldCrushable,
            "A crushing recipe item must implement WorldCrushable"
        );
        return new ItemStack[]{
            null, null, null,
            null, dropFrom.getItem(), null,
            null, null, null
        };
    }

    /**
     * This method returns an ItemStack array that can be used for Slimefun's Recipe system
     *
     * @param ball The Netheoball type required for the trade
     * @param minFlavour The minimum flavour required
     * @return A {@link ItemStack[]} used for Slimefun's Recipe registration with the recipe item in the middle.
     */
    @Nonnull
    public static ItemStack[] createTradingRecipe(@Nonnull ItemStack itemStack, @Nonnull NetheoBalls ball, int minFlavour) {
        final ItemStack flavourStack = new CustomItemStack(
            Material.MELON_SEEDS,
            Theme.MAIN.apply("Required Flavour"),
            Theme.CLICK_INFO.asTitle("Netheoball Type", ball.getSlimefunItemStack().getDisplayName()),
            Theme.CLICK_INFO.asTitle("Flavour Amount", minFlavour)
        );
        ball.getTradePool().addTrade(itemStack, minFlavour);
        return new ItemStack[]{
            null, null, null,
            null, flavourStack, null,
            null, null, null
        };
    }
}
