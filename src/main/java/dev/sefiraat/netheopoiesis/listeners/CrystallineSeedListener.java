package dev.sefiraat.netheopoiesis.listeners;

import dev.sefiraat.netheopoiesis.implementation.Stacks;
import dev.sefiraat.netheopoiesis.implementation.flora.CrystallineCrux;
import dev.sefiraat.netheopoiesis.utils.Keys;
import dev.sefiraat.netheopoiesis.utils.WorldUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import javax.annotation.Nonnull;

/**
 * This listener is to catch Crystalline seeds as they touch lava, at which point the
 * lava is converted to a CrystallineCrux
 */
public class CrystallineSeedListener implements Listener {

    @EventHandler
    public void onSeedEntersLava(@Nonnull EntityDamageEvent event) {
        final Entity entity = event.getEntity();

        if (!(entity instanceof Item item)
            || event.getCause() != EntityDamageEvent.DamageCause.LAVA
            || !WorldUtils.inNether(entity)
        ) {
            return;
        }

        final SlimefunItem slimefunItem = SlimefunItem.getByItem(item.getItemStack());

        if (slimefunItem == null || !slimefunItem.getId().equals(Stacks.CRYSTALLINE_SEED.getItemId())) {
            return;
        }

        final Block block = event.getEntity().getLocation().getBlock();

        event.setCancelled(true);
        if (block.getType() == Material.LAVA) {
            final SlimefunItemStack crux = Stacks.CRYSTALLINE_CRUX;
            block.setType(crux.getType());
            BlockStorage.store(block, crux.getItemId());
            BlockStorage.addBlockInfo(block, Keys.CRYSTALLINE_STEPS_REMAINING, String.valueOf(CrystallineCrux.STEPS));
            event.getEntity().remove();
        }
    }
}
