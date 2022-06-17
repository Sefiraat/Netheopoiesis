package dev.sefiraat.netheopoiesis.api.plant.netheos;

import io.github.bakedlibs.dough.collections.Pair;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class Trade {

    @Nonnull
    private final Pair<ItemStack, Integer> tradePair;

    public Trade(@Nonnull ItemStack item, @Nonnull Integer requiredFlavour) {
        this.tradePair = new Pair<>(item, requiredFlavour);
    }

    @Nonnull
    public ItemStack getItem() {
        return tradePair.getFirstValue();
    }

    public int getRequiredFlavour() {
        return tradePair.getSecondValue();
    }

    @Nonnull
    public Pair<ItemStack, Integer> getTradePair() {
        return tradePair;
    }

    @Nonnull
    public static Trade of(@Nonnull ItemStack item, @Nonnull Integer requiredFlavour) {
        return new Trade(item, requiredFlavour);
    }
}
