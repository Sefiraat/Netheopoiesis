package dev.sefiraat.netheopoiesis.slimefun.flora.blocks;

import dev.sefiraat.netheopoiesis.slimefun.flora.PurifyingObject;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class NetherSeedCrux extends SlimefunItem implements PurifyingObject {

    private final int purificationValue;

    public NetherSeedCrux(ItemGroup itemGroup,
                          SlimefunItemStack item,
                          RecipeType recipeType,
                          ItemStack[] recipe,
                          int purificationValue
    ) {
        super(itemGroup, item, recipeType, recipe);
        this.purificationValue = purificationValue;
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
            },
            new BlockTicker() {
                @Override
                public boolean isSynchronized() {
                    return false;
                }

                @Override
                public void tick(Block block, SlimefunItem slimefunItem, Config config) {
                    registerPurificationValue(block);
                }
            },
            new BlockBreakHandler(false, false) {
                @Override
                @ParametersAreNonnullByDefault
                public void onPlayerBreak(BlockBreakEvent blockBreakEvent, ItemStack itemStack, List<ItemStack> list) {
                    removePurificationRegistry(blockBreakEvent.getBlock());
                }
            }
        );
    }

    @Override
    public int purificationValue() {
        return purificationValue;
    }
}
