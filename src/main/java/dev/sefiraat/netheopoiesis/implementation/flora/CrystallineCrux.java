package dev.sefiraat.netheopoiesis.implementation.flora;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.Purification;
import dev.sefiraat.netheopoiesis.api.items.NetherCrux;
import dev.sefiraat.netheopoiesis.implementation.tasks.UpdateCruxTask;
import dev.sefiraat.netheopoiesis.implementation.Stacks;
import dev.sefiraat.netheopoiesis.utils.Keys;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The CrystallineCrux starts from a plant spreading on to lava. After which is slowly spreads out
 * over nearby lava. When there is no lava adjacent, then it will turn to water
 */
public class CrystallineCrux extends NetherCrux {

    public static final int STEPS = 15;

    private static final Set<Material> VALID_MATERIALS = Set.of(
        Material.LAVA,
        Material.COBBLESTONE,
        Material.OBSIDIAN,
        Material.STONE
    );

    private static final Set<BlockFace> VALID_FACES = Set.of(
        BlockFace.DOWN,
        BlockFace.NORTH,
        BlockFace.SOUTH,
        BlockFace.EAST,
        BlockFace.WEST
    );

    public CrystallineCrux(@Nonnull ItemGroup itemGroup, @Nonnull SlimefunItemStack item, int purificationValue) {
        super(itemGroup, item, purificationValue);
    }

    @Override
    public void preRegister() {
        addItemHandler(
            new BlockBreakHandler(false, true) {
                @Override
                @ParametersAreNonnullByDefault
                public void onPlayerBreak(BlockBreakEvent event, ItemStack item, List<ItemStack> drops) {
                    // We do not want crux' to be able to drop and placed elsewhere thus gaming the system
                    final Block block = event.getBlock();
                    final ItemStack heldItem = event.getPlayer().getInventory().getItemInMainHand();
                    if (SlimefunItem.getByItem(heldItem).getId().equals(Stacks.CRUX_GATHERER.getItemId())) {
                        event.setCancelled(true);
                        block.setType(Material.AIR);
                    }
                    BlockStorage.clearBlockInfo(block);
                    removePurificationRegistry(block);
                }
            },
            new BlockTicker() {
                @Override
                public boolean isSynchronized() {
                    return true;
                }

                @Override
                public void tick(Block block, SlimefunItem item, Config data) {
                    onTick(block);
                }
            }
        );
    }

    // Todo - Work on performance here
    private void onTick(@Nonnull Block block) {
        final int stepsLeft = getRemainingSteps(block);
        // If no steps left, kill off the block to stop ticking
        if (stepsLeft == 0) {
            block.setType(Material.WATER, true);
            BlockStorage.clearBlockInfo(block);
            Purification.removeValue(block);
            return;
        }

        boolean canTransform = true;
        for (BlockFace validFace : VALID_FACES) {
            final Block testBlock = block.getRelative(validFace);
            if (VALID_MATERIALS.contains(testBlock.getType())) {
                final double chance = ThreadLocalRandom.current().nextDouble();
                final double required = 0.05 * Netheopoiesis.CRYSTALLINE_SPREAD_MULTIPLIER;

                canTransform = false;
                if (chance <= required) {
                    BlockStorage.clearBlockInfo(testBlock);
                    Purification.removeValue(testBlock);
                    // Schedule a task to ensure the new block storage happens only AFTER deletion
                    final UpdateCruxTask task = new UpdateCruxTask(
                        testBlock,
                        Stacks.CRYSTALLINE_CRUX,
                        stepsLeft - 1
                    );
                    task.runTaskTimer(Netheopoiesis.getInstance(), 1, 20);
                }
            }
        }
        // We can only transform when there is no lava around, to stop turning to cobble
        if (canTransform) {
            block.setType(Material.WATER, true);
            BlockStorage.clearBlockInfo(block);
            Purification.removeValue(block);
        }
    }

    public void setRemainingSteps(@Nonnull Block block, int steps) {
        BlockStorage.addBlockInfo(block, Keys.CRYSTALLINE_STEPS_REMAINING, String.valueOf(steps));
    }

    public int getRemainingSteps(@Nonnull Block block) {
        final String steps = BlockStorage.getLocationInfo(block.getLocation(), Keys.CRYSTALLINE_STEPS_REMAINING);
        return steps == null ? 0 : Integer.parseInt(steps);
    }
}
