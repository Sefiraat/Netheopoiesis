package dev.sefiraat.netheopoiesis.listeners;

import dev.sefiraat.netheopoiesis.api.RecipeTypes;
import dev.sefiraat.netheopoiesis.api.interfaces.WorldCrushable;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * The purpose of this listener is to drop registered items when breaking the specified vanilla
 * block.
 * Recipes should be registered using {@link RecipeTypes#createCrushingRecipe(SlimefunItem)}
 * which returns an ItemStack array used for Slimefun's recipe
 * {@link RecipeTypes#CRUSHING}
 */
public class CrushingListener implements Listener {

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onCrush(@Nonnull EntityDamageEvent event) {
        if (event.getEntity() instanceof Item item
            && event.getCause() == EntityDamageEvent.DamageCause.FALLING_BLOCK
        ) {
            final SlimefunItem slimefunItem = SlimefunItem.getByItem(item.getItemStack());
            if (slimefunItem instanceof WorldCrushable crushable) {
                final ItemStack stackToDrop = crushable.crushingDrop();
                if (stackToDrop == null) {
                    return;
                }
                for (int i = 0; i < item.getItemStack().getAmount(); i++) {
                    item.getWorld().dropItem(item.getLocation(), stackToDrop);
                }
                item.remove();
            }
        }
    }
}
