package dev.sefiraat.netheopoiesis.utils;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import org.bukkit.NamespacedKey;

import javax.annotation.Nonnull;

public final class Keys {

    // Region Slimefun BlockStorage Keys
    public static final String SEED_GROWTH_STAGE = "growth-stage";
    public static final String SEED_OWNER = "plant-owner";
    public static final String CRYSTALLINE_STEPS_REMAINING = "steps-left";

    public static final NamespacedKey COOLDOWN = newKey("cooldown");
    public static final NamespacedKey TRADE_COOLDOWN = newKey("trade-cooldown");
    public static final NamespacedKey DROPPED_PLAYER = newKey("dropped-player");
    public static final NamespacedKey FLAVOUR = newKey("flavour");

    private Keys() {
        throw new IllegalStateException("Utility class");
    }

    @Nonnull
    public static NamespacedKey newKey(@Nonnull String value) {
        return new NamespacedKey(Netheopoiesis.getInstance(), value);
    }
}
