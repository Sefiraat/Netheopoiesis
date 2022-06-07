package dev.sefiraat.netheopoiesis.slimefun.flora.blocks;

import dev.sefiraat.netheopoiesis.core.purification.PurifyingObject;
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

import java.util.List;
import java.util.Set;

/**
 * A NetherSeedCrux is a block that allows NetherPlants to grow on top.
 * Each plant has a {@link Set<String>} of id's to specify which Crux's they can be placed on
 * @see {@link dev.sefiraat.netheopoiesis.core.plant.Placements}
 */
public class NetherSeedCrux extends SlimefunItem implements PurifyingObject {

    private final int purificationValue;
    private int tick = 0;

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
                public void onPlayerBreak(BlockBreakEvent event, ItemStack item, List<ItemStack> drops) {
                    // We do not want crux' to be able to drop and placed elsewhere thus gaming the system
                    final Block block = event.getBlock();
                    event.setCancelled(true);
                    block.setType(Material.AIR);
                    BlockStorage.clearBlockInfo(block);
                    removePurificationRegistry(block);
                }
            },
            new BlockTicker() {
                @Override
                public boolean isSynchronized() {
                    return false;
                }

                @Override
                public void tick(Block block, SlimefunItem item, Config data) {
                    if (NetherSeedCrux.this.tick >= 5) {
                        registerPurificationValue(block);
                    }
                }

                @Override
                public void uniqueTick() {
                    tick = tick >= 5 ? 0 : tick + 1;
                }
            }
        );
    }

    @Override
    public int getPurificationValue() {
        return purificationValue;
    }
}
