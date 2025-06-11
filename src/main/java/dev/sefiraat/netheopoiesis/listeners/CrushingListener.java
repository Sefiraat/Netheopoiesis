package dev.sefiraat.netheopoiesis.listeners;

import dev.sefiraat.netheopoiesis.api.RecipeTypes;
import dev.sefiraat.netheopoiesis.api.interfaces.WorldCrushable;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * The purpose of this listener is to drop registered items when crushing the specified recipe with an anvil
 * Recipes should be registered using {@link RecipeTypes#createCrushingRecipe(SlimefunItem)}
 * which returns an ItemStack array used for Slimefun's recipe
 * {@link RecipeTypes#CRUSHING}
 */
public class CrushingListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onCrush(@Nonnull EntityChangeBlockEvent event) {
        if (event.getEntity() instanceof FallingBlock anvil && Tag.ANVIL.isTagged(anvil.getBlockData().getMaterial())) {
            Location center = event.getBlock().getLocation().add(0.5, 0.5, 0.5);
            for (Entity entity : center.getWorld().getNearbyEntities(center, 0.5, 0.5, 0.5, Item.class::isInstance)) {
                Item item = (Item) entity;
                final SlimefunItem slimefunItem = SlimefunItem.getByItem(item.getItemStack());
                if (slimefunItem instanceof WorldCrushable crushable) {
                    final ItemStack stackToDrop = crushable.crushingDrop();
                    if (stackToDrop == null) {
                        continue;
                    }
                    for (int i = 0; i < item.getItemStack().getAmount(); i++) {
                        item.getWorld().dropItem(item.getLocation(), stackToDrop);
                    }
                    item.remove();
                }
            }
        }
    }
}
