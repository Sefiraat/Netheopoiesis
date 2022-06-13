package dev.sefiraat.netheopoiesis.listeners;

import dev.sefiraat.netheopoiesis.api.RecipeTypes;
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

/**
 * The purpose of this listener is to drop registered items when breaking the specified vanilla
 * block.
 * Recipes should be registered using {@link DropListener#createRecipe(ItemStack, ItemStack, double)}
 * which returns an ItemStack array used for Slimefun's recipe
 * {@link RecipeTypes#VANILLA_DROP}
 */
public class DropListener implements Listener {

    private static final Map<Material, BlockDrop> DROP_MAP = new EnumMap<>(Material.class);

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onBlockBreak(@Nonnull BlockBreakEvent event) {
        final BlockDrop blockDrop = DROP_MAP.get(event.getBlock().getType());

        if (blockDrop == null) {
            return;
        }
        blockDrop.rollDrop(event);
    }

    /**
     * This method both registers the drop and returns an ItemStack array that can be used
     * for Slimefun's recipe system. {@link RecipeTypes#VANILLA_DROP}
     *
     * @param stackToDrop The {@link ItemStack} to drop in the world
     * @param dropFrom    The {@link ItemStack} to drop from (#getType() is used) and the stack is used in the recipe.
     * @param dropChance  The chance (0-1) for the drop to occur
     * @return A {@link ItemStack[]} used for Slimefun's Recipe registration with the dropFrom item in the middle.
     */
    @Nonnull
    public static ItemStack[] createRecipe(@Nonnull ItemStack stackToDrop,
                                           @Nonnull ItemStack dropFrom,
                                           double dropChance
    ) {
        final Material material = dropFrom.getType();
        DROP_MAP.put(material, new BlockDrop(stackToDrop, material, dropChance));
        return new ItemStack[]{
            null, null, null,
            null, dropFrom, null,
            null, null, null
        };
    }

    /**
     * This class represents a drop including its source, the item to drop and the chance for it to occur
     * Including a method to roll for and spawn the drop itself.
     */
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
