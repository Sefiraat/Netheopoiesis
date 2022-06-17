package dev.sefiraat.netheopoiesis.api.plant.netheos;

import org.jetbrains.annotations.Unmodifiable;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class FlavourProfile {

    private final Map<Flavour, Integer> flavourMap = new EnumMap<>(Flavour.class);

    public FlavourProfile(int sweet, int sour, int salty, int bitter, int umami) {
        flavourMap.put(Flavour.SWEET, sweet);
        flavourMap.put(Flavour.SOUR, sour);
        flavourMap.put(Flavour.SALTY, salty);
        flavourMap.put(Flavour.BITTER, bitter);
        flavourMap.put(Flavour.UMAMI, umami);
    }

    public int getSweet() {
        return flavourMap.getOrDefault(Flavour.SWEET, 0);
    }

    public int getSour() {
        return flavourMap.getOrDefault(Flavour.SOUR, 0);
    }

    public int getSalty() {
        return flavourMap.getOrDefault(Flavour.SALTY, 0);
    }

    public int getBitter() {
        return flavourMap.getOrDefault(Flavour.BITTER, 0);
    }

    public int getUmami() {
        return flavourMap.getOrDefault(Flavour.UMAMI, 0);
    }

    @Nonnull
    @Unmodifiable
    public Map<Flavour, Integer> getFlavourMap() {
        return Collections.unmodifiableMap(flavourMap);
    }
}
