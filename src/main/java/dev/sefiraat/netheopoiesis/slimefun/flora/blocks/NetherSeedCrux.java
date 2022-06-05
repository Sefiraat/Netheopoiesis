package dev.sefiraat.netheopoiesis.slimefun.flora.blocks;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

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

    @Override
    public void preRegister() {
        addItemHandler(
            new BlockBreakHandler(false, true) {
                @Override
                public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                    // We do not want crux' to be able to drop and placed elsewhere thus gaming the system
                    e.setCancelled(true);
                    e.getBlock().setType(Material.AIR);
                    BlockStorage.clearBlockInfo(e.getBlock());
                }
            }
        );
    }
}
