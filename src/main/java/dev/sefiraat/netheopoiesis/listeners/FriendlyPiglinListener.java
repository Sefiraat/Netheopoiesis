package dev.sefiraat.netheopoiesis.listeners;

import dev.sefiraat.netheopoiesis.Purification;
import dev.sefiraat.netheopoiesis.utils.WorldUtils;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Piglin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.PiglinBarterEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.concurrent.ThreadLocalRandom;

public class FriendlyPiglinListener implements Listener {

    @EventHandler
    public void onPiglinTarget(@Nonnull EntityTargetLivingEntityEvent event) {
        final LivingEntity livingEntity = event.getTarget();
        if (livingEntity != null && WorldUtils.inNether(livingEntity) && event.getEntityType() == EntityType.PIGLIN) {
            final int purificationLevel = Purification.getValue(livingEntity.getLocation().getChunk());
            if (purificationLevel >= Purification.FRIENDLY_PIGLINS) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBarter(@Nonnull PiglinBarterEvent event) {
        final Piglin piglin = event.getEntity();
        if (WorldUtils.inNether(piglin)) {
            final int purificationLevel = Purification.getValue(piglin.getLocation().getChunk());
            final double doubleChance = (purificationLevel / 750D) * 0.05;
            for (ItemStack stack : event.getOutcome()) {
                final double random = ThreadLocalRandom.current().nextDouble();
                if (random <= doubleChance) {
                    stack.setAmount(stack.getAmount() * 2);
                }
            }
        }
    }
}
