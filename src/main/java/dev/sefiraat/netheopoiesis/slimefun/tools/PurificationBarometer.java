package dev.sefiraat.netheopoiesis.slimefun.tools;

import dev.sefiraat.netheopoiesis.PurificationMemory;
import dev.sefiraat.netheopoiesis.utils.Cooldowns;
import dev.sefiraat.netheopoiesis.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

/**
 * The PurificationBarometer will display the player's currently located chunks purification level
 * @see {@link dev.sefiraat.netheopoiesis.core.purification.PurifyingObject}
 * Coolsdown after use @see {@link Cooldowns}
 */
public class PurificationBarometer extends SimpleSlimefunItem<ItemUseHandler> {

    public PurificationBarometer(ItemGroup itemGroup,
                                 SlimefunItemStack item,
                                 RecipeType recipeType,
                                 ItemStack[] recipe
    ) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return event -> {
            final Player player = event.getPlayer();
            final Chunk chunk = player.getLocation().getChunk();
            final ItemStack barometer = event.getItem();

            if (Cooldowns.isOnCooldown(barometer)) {
                player.sendMessage(Theme.WARNING + "This item is still on cooldown.");
                return;
            }

            final String message = Theme.CLICK_INFO.asTitle(
                "Chunk Purification Amount",
                PurificationMemory.getInstance().getValue(chunk)
            );
            player.sendMessage(message);
            Cooldowns.addCooldown(barometer, 5);
        };
    }
}
