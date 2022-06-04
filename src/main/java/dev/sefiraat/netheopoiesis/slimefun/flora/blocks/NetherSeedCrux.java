package dev.sefiraat.netheopoiesis.slimefun.flora.blocks;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

public class NetherSeedCrux extends SlimefunItem {

    public NetherSeedCrux(ItemGroup itemGroup,
                          SlimefunItemStack item,
                          RecipeType recipeType,
                          ItemStack[] recipe
    ) {
        super(itemGroup, item, recipeType, recipe);
    }

    public NetherSeedCrux(ItemGroup itemGroup,
                          SlimefunItemStack item,
                          RecipeType recipeType,
                          ItemStack[] recipe,
                          @Nullable ItemStack recipeOutput
    ) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
    }
}
