package dev.sefiraat.netheopoiesis.slimefun;

import dev.sefiraat.netheopoiesis.utils.Keys;
import dev.sefiraat.netheopoiesis.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;

import javax.annotation.Nonnull;

public final class NpsRecipeTypes {

    private NpsRecipeTypes() {
        throw new IllegalStateException("Utility class");
    }

    @Nonnull
    public static final RecipeType NETHER_PURIFICATION = new RecipeType(
        Keys.newKey("nether_purification"),
        Theme.themedItemStack(
            Material.WITHER_ROSE,
            Theme.RECIPE_TYPE,
            "Nether Purification",
            "These items are made by purifying the Nether",
            "Planting certain Nether Plants may help."
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
            "The breeding dictionary can be used",
            "to track your discoveries."
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
}
