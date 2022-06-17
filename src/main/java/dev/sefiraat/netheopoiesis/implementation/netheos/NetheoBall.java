package dev.sefiraat.netheopoiesis.implementation.netheos;

import dev.sefiraat.netheopoiesis.api.plant.netheos.NetheoBalls;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

public class NetheoBall extends SlimefunItem {

    private final NetheoBalls parent;

    @ParametersAreNonnullByDefault
    public NetheoBall(ItemGroup itemGroup,
                      SlimefunItemStack item,
                      RecipeType recipeType,
                      ItemStack[] recipe,
                      NetheoBalls parent
    ) {
        super(itemGroup, item, recipeType, recipe);
        this.parent = parent;
    }

    public NetheoBalls getParent() {
        return parent;
    }
}
