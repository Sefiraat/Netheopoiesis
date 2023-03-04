package dev.sefiraat.netheopoiesis.implementation.netheos;

import dev.sefiraat.netheopoiesis.api.plant.netheos.NetheoBalls;
import dev.sefiraat.netheopoiesis.api.plant.netheos.TradePool;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class NetheoBall extends SlimefunItem {

    @Nonnull
    private final NetheoBalls parent;
    @Nonnull
    private final TradePool tradePool;

    @ParametersAreNonnullByDefault
    public NetheoBall(ItemGroup itemGroup,
                      SlimefunItemStack item,
                      RecipeType recipeType,
                      ItemStack[] recipe,
                      NetheoBalls parent,
                      TradePool tradePool
    ) {
        super(itemGroup, item, recipeType, recipe);
        this.parent = parent;
        this.tradePool = tradePool;
    }
    
    @Override
    public void preRegister() {
        addItemHandler((ItemUseHandler) PlayerRightClickEvent::cancel);
    }

    @Nonnull
    public NetheoBalls getParent() {
        return parent;
    }

    @Nonnull
    public TradePool getTradePool() {
        return tradePool;
    }
}
