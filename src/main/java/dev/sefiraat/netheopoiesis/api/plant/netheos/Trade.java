package dev.sefiraat.netheopoiesis.api.plant.netheos;

import io.github.bakedlibs.dough.collections.Pair;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class Trade {

    @Nonnull
    private final Pair<ItemStack, Integer> tradePair;
    @Nonnull
    private final String tradeId;
    @Nullable
    private TradePool tradePool;

    @ParametersAreNonnullByDefault
    public Trade(ItemStack item, Integer requiredFlavour) {
        this.tradePair = new Pair<>(item, requiredFlavour);
        final SlimefunItem slimefunItem = SlimefunItem.getByItem(item);
        this.tradeId = slimefunItem == null ? item.getType().name() : slimefunItem.getId();
    }

    @Nonnull
    public ItemStack getItem() {
        return tradePair.getFirstValue();
    }

    public int getRequiredFlavour() {
        return tradePair.getSecondValue();
    }

    public void setTradePool(@Nonnull TradePool tradePool) {
        this.tradePool = tradePool;
    }

    @Nonnull
    public String getTradeId() {
        return tradeId;
    }

    @Nullable
    public TradePool getTradePool() {
        return tradePool;
    }

    @Nonnull
    public Pair<ItemStack, Integer> getTradePair() {
        return tradePair;
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public static Trade of(ItemStack item, Integer requiredFlavour) {
        return new Trade(item, requiredFlavour);
    }
}
