package dev.sefiraat.netheopoiesis.implementation.tools;

import dev.sefiraat.netheopoiesis.api.plant.netheos.Flavour;
import dev.sefiraat.netheopoiesis.api.plant.netheos.NetheoBalls;
import dev.sefiraat.netheopoiesis.implementation.netheos.Paste;
import dev.sefiraat.netheopoiesis.utils.Keys;
import dev.sefiraat.netheopoiesis.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.LimitedUseItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

/**
 * The MixingQuartz is the manual method for converting {@link dev.sefiraat.netheopoiesis.implementation.netheos.Paste}
 * into {@link NetheoBalls}
 */
public class MixingQuartz extends LimitedUseItem {

    private static final NamespacedKey KEY = Keys.newKey("uses");
    private static final Pattern LORE_FLAVOUR = Pattern.compile(Theme.CLICK_INFO + "Flavour: [0-9]+");

    public MixingQuartz(ItemGroup group,
                        SlimefunItemStack item,
                        RecipeType recipeType,
                        ItemStack[] recipe,
                        int amount
    ) {
        super(group, item, recipeType, recipe);
        setMaxUseCount(amount);
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return this::onUse;
    }

    // Todo cognitive
    private void onUse(@Nonnull PlayerRightClickEvent event) {
        event.cancel();
        final Player player = event.getPlayer();
        final World world = player.getWorld();
        final Location location = player.getLocation().add(player.getEyeLocation().getDirection().multiply(1.5));
        final Collection<Entity> entities = world.getNearbyEntities(location, 1.5, 1.5, 1.5);
        final List<Flavour> flavours = new ArrayList<>();
        final List<Item> validItems = new ArrayList<>();
        int volume = 0;
        boolean isEnough = false;

        // Check all the entities and collate the ones that are Pastes. Escape when 3 are found
        for (Entity entity : entities) {
            if (entity instanceof Item item) {
                final SlimefunItem slimefunItem = SlimefunItem.getByItem(item.getItemStack());
                if (slimefunItem instanceof Paste paste) {
                    validItems.add(item);
                    flavours.add(paste.getDominantFlavour());
                    volume += paste.getFlavourVolume();
                    if (flavours.size() == 3) {
                        isEnough = true;
                        break;
                    }
                }
            }
        }

        // If three were found, lets remove them and spawn the ball
        if (isEnough) {
            final NetheoBalls ball = NetheoBalls.getMatchingBall(flavours);

            // No ball found, shouldn't be possible - but we have to escape
            if (ball == null) {
                return;
            }

            // Get the itemstack then add the flavour volume to the PDC, line to the lore
            final ItemStack stack = ball.getSlimefunItemStack().clone();
            final ItemMeta itemMeta = stack.getItemMeta();
            final List<String> lore = itemMeta.getLore();
            // Set PDC
            PersistentDataAPI.setInt(itemMeta, Keys.FLAVOUR, volume);
            // Scan lore for the flavour line and replace
            for (int i = 0; i < lore.size(); i++) {
                final String string = lore.get(i);
                if (LORE_FLAVOUR.matcher(string).matches()) {
                    lore.set(i, Theme.CLICK_INFO.asTitle("Flavour", volume));
                }
            }
            // Set meta and drop item
            itemMeta.setLore(lore);
            stack.setItemMeta(itemMeta);
            world.dropItem(validItems.get(0).getLocation(), stack);

            // Loop through all the previously found items then remove one from each
            for (Item item : validItems) {
                item.getItemStack().setAmount(item.getItemStack().getAmount() - 1);
            }
            damageItem(player, event.getItem());
        }
    }

    @Override
    protected @Nonnull
    NamespacedKey getStorageKey() {
        return KEY;
    }
}
