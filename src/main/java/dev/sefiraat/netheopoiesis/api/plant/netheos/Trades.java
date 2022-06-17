package dev.sefiraat.netheopoiesis.api.plant.netheos;

import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.RandomizedSet;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public final class Trades {

    private Trades() {
        throw new IllegalStateException("Utility class");
    }

    public static final TradePool SWEET = new TradePool()
        .addTrade(new ItemStack(Material.SWEET_BERRIES, 10), 0)
        .addTrade(new ItemStack(Material.COOKIE, 5), 0)
        .addTrade(new ItemStack(Material.SUGAR_CANE, 10), 0)
        .addTrade(new ItemStack(Material.SUGAR, 5), 0)
        .addTrade(new ItemStack(Material.DIAMOND_ORE), 20)
        .addTrade(new ItemStack(Material.EXPERIENCE_BOTTLE, 10), 40);

    public static final TradePool TANGY = new TradePool()
        .addTrade(new ItemStack(Material.BEETROOT, 10), 0)
        .addTrade(new ItemStack(Material.MELON, 5), 0)
        .addTrade(new ItemStack(Material.QUARTZ, 10), 0)
        .addTrade(new ItemStack(Material.GRASS_BLOCK, 5), 0)
        .addTrade(new ItemStack(Material.EMERALD_ORE), 20)
        .addTrade(new ItemStack(Material.MYCELIUM, 10), 40);

    public static final TradePool POPCORN = new TradePool()
        .addTrade(new ItemStack(Material.WHEAT_SEEDS, 10), 0)
        .addTrade(new ItemStack(Material.IRON_AXE), 0)
        .addTrade(new ItemStack(Material.IRON_SWORD), 0)
        .addTrade(new ItemStack(Material.FURNACE), 0)
        .addTrade(new ItemStack(Material.TINTED_GLASS, 20), 20)
        .addTrade(new ItemStack(Material.TINTED_GLASS, 64), 40);

    public static final TradePool BITTERSWEET = new TradePool()
        .addTrade(new ItemStack(Material.GREEN_WOOL, 10), 0)
        .addTrade(new ItemStack(Material.IRON_PICKAXE), 0)
        .addTrade(new ItemStack(Material.IRON_SHOVEL), 0)
        .addTrade(new ItemStack(Material.GLOW_LICHEN, 5), 0)
        .addTrade(new ItemStack(Material.GREEN_STAINED_GLASS, 20), 20)
        .addTrade(new ItemStack(Material.GREEN_STAINED_GLASS, 64), 40);

    public static final TradePool SWEETPEA = new TradePool()
        .addTrade(new ItemStack(Material.YELLOW_WOOL, 10), 0)
        .addTrade(new ItemStack(Material.IRON_HOE), 0)
        .addTrade(new ItemStack(Material.IRON_HELMET), 0)
        .addTrade(new ItemStack(Material.GLOW_INK_SAC, 5), 0)
        .addTrade(new ItemStack(Material.YELLOW_STAINED_GLASS, 20), 20)
        .addTrade(new ItemStack(Material.YELLOW_STAINED_GLASS, 64), 40);

    public static final TradePool CANDY = new TradePool()
        .addTrade(new ItemStack(Material.RED_WOOL, 10), 0)
        .addTrade(new ItemStack(Material.IRON_LEGGINGS), 0)
        .addTrade(new ItemStack(Material.IRON_CHESTPLATE), 0)
        .addTrade(new ItemStack(Material.GLOW_INK_SAC, 5), 0)
        .addTrade(new ItemStack(Material.RED_STAINED_GLASS, 20), 20)
        .addTrade(new ItemStack(Material.RED_STAINED_GLASS, 64), 40);

    public static final TradePool COMPLEX = new TradePool()
        .addTrade(new ItemStack(Material.BLUE_WOOL, 10), 0)
        .addTrade(new ItemStack(Material.IRON_HORSE_ARMOR), 0)
        .addTrade(new ItemStack(Material.IRON_BOOTS), 0)
        .addTrade(new ItemStack(Material.CLAY_BALL, 5), 0)
        .addTrade(new ItemStack(Material.BLUE_STAINED_GLASS, 20), 20)
        .addTrade(new ItemStack(Material.BLUE_STAINED_GLASS, 64), 40);


    public static class TradePool {
        private final Map<ItemStack, Integer> trades = new HashMap<>();

        public TradePool addTrade(@Nonnull ItemStack itemStack, int requiredFlavour) {
            this.trades.put(itemStack, requiredFlavour);
            return this;
        }

        public RandomizedSet<ItemStack> getTrades(int flavour) {
            final RandomizedSet<ItemStack> validTrades = new RandomizedSet<>();
            for (Map.Entry<ItemStack, Integer> entry : trades.entrySet()) {
                if (entry.getValue() <= flavour) {
                    validTrades.add(entry.getKey(), 1);
                }
            }
            return validTrades;
        }
    }
}
