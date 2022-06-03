package dev.sefiraat.netheopoiesis.utils;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import org.bukkit.NamespacedKey;

import javax.annotation.Nonnull;

public final class Keys {

    // Region Slimefun BlockStorage Keys
    public static final String SEED_GROWTH_STAGE = "growth-stage";

    private Keys() {
        throw new IllegalStateException("Utility class");
    }

    @Nonnull
    public static NamespacedKey newKey(@Nonnull String value) {
        return new NamespacedKey(Netheopoiesis.getInstance(), value);
    }
}
