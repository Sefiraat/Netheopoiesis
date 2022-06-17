package dev.sefiraat.netheopoiesis.implementation.tasks;

import dev.sefiraat.netheopoiesis.api.mobs.MobCapType;
import dev.sefiraat.netheopoiesis.api.plant.netheos.Trade;
import dev.sefiraat.netheopoiesis.implementation.netheos.NetheoBall;
import dev.sefiraat.netheopoiesis.managers.MobManager;
import dev.sefiraat.netheopoiesis.utils.Keys;
import dev.sefiraat.netheopoiesis.utils.StatisticUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Piglin;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class WanderingPiglinTask extends BukkitRunnable {

    private static final long TRADE_COOLDOWN = 3000;

    @Override
    public void run() {
        for (UUID mobUuid : MobManager.getInstance().getMobCap(MobCapType.PIGLIN_TRADER).getMobs()) {
            final Entity mob = Bukkit.getEntity(mobUuid);
            if (mob == null || !mob.isValid() || !(mob instanceof Piglin piglin)) {
                continue;
            }

            final long cooldownTime = PersistentDataAPI.getLong(piglin, Keys.TRADE_COOLDOWN, 1);
            final long timeNow = System.currentTimeMillis();

            if (cooldownTime > timeNow) {
                continue;
            }

            final Location location = piglin.getLocation();
            final World world = location.getWorld();
            for (Entity entity : world.getNearbyEntities(location, 1.5, 1.5, 1.5, Item.class::isInstance)) {
                final Item item = (Item) entity;
                final ItemStack itemStack = item.getItemStack();
                final SlimefunItem slimefunItem = SlimefunItem.getByItem(itemStack);
                if (slimefunItem instanceof NetheoBall ball) {
                    final ItemMeta itemMeta = itemStack.getItemMeta();
                    final int flavour = PersistentDataAPI.getInt(itemMeta, Keys.FLAVOUR, 0);
                    final Trade trade = ball.getTradePool().getRandomTrade(flavour);
                    final ItemStack stackToDrop = trade.getItem();

                    final String owner = PersistentDataAPI.getString(item, Keys.DROPPED_PLAYER);

                    if (owner != null) {
                        StatisticUtils.unlockTrade(UUID.fromString(owner), trade.getTradeId());
                    }

                    itemStack.setAmount(itemStack.getAmount() - 1);
                    world.dropItem(item.getLocation(), stackToDrop);
                    PersistentDataAPI.setLong(
                        piglin,
                        Keys.TRADE_COOLDOWN,
                        System.currentTimeMillis() + TRADE_COOLDOWN
                    );
                    break;
                }
            }
        }
    }
}
