package dev.sefiraat.netheopoiesis.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class VanillaDropListener implements Listener {

    private static final Map<Material, BlockDrop> DROP_MAP = new EnumMap<>(Material.class);

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onBlockBreak(@Nonnull BlockBreakEvent event) {
        final BlockDrop blockDrop = DROP_MAP.get(event.getBlock().getType());

        if (blockDrop == null) {
            return;
        }

        event.setCancelled(true);
        event.setExpToDrop(0);
        event.setDropItems(false);
        blockDrop.rollDrop(event);
    }

    @Nonnull
    public static ItemStack[] createRecipeWorldDrop(@Nonnull ItemStack stackToDrop,
                                                    @Nonnull ItemStack dropFrom,
                                                    double dropChance
    ) {
        final Material material = dropFrom.getType();
        DROP_MAP.put(material, new BlockDrop(stackToDrop, material, dropChance));
        return new ItemStack[]{
            null, null, null,
            stackToDrop, null, dropFrom,
            null, null, null
        };
    }

    public static class BlockDrop {
        private final ItemStack stackToDrop;
        private final Material dropFrom;
        private final double dropChance;

        public BlockDrop(@Nonnull ItemStack stackToDrop, @Nonnull Material dropFrom, double dropChance) {
            this.stackToDrop = stackToDrop;
            this.dropFrom = dropFrom;
            this.dropChance = dropChance;
        }

        @Nonnull
        public ItemStack getStackToDrop() {
            return stackToDrop;
        }

        @Nonnull
        public Material getDropFrom() {
            return dropFrom;
        }

        public double getDropChance() {
            return dropChance;
        }

        public void rollDrop(@Nonnull BlockBreakEvent event) {
            final double roll = ThreadLocalRandom.current().nextDouble();
            if (roll <= this.dropChance) {
                final ItemStack drop = stackToDrop.clone();
                final Location location = event.getBlock().getLocation().clone().add(.5, .5, .5);
                location.getWorld().dropItem(location, drop);
            }
        }
    }
}
