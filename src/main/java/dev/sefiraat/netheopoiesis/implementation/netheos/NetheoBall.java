package dev.sefiraat.netheopoiesis.implementation.netheos;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

public class NetheoBall extends SlimefunItem {

    @ParametersAreNonnullByDefault
    public NetheoBall(ItemGroup itemGroup,
                      SlimefunItemStack item,
                      RecipeType recipeType,
                      ItemStack[] recipe
    ) {
        super(itemGroup, item, recipeType, recipe);
    }
}
