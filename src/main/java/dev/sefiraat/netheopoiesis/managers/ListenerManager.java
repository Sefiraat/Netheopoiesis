package dev.sefiraat.netheopoiesis.managers;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.listeners.MobSpawnListener;
import dev.sefiraat.netheopoiesis.listeners.PlayerSleepListener;
import dev.sefiraat.netheopoiesis.listeners.SeedPlacementListener;
import dev.sefiraat.netheopoiesis.listeners.VanillaDropListener;
import dev.sefiraat.netheopoiesis.listeners.WaterPlaceListener;
import org.bukkit.event.Listener;

import javax.annotation.Nonnull;

public class ListenerManager {

    public ListenerManager() {
        addListener(new VanillaDropListener());
        addListener(new SeedPlacementListener());
        addListener(new MobSpawnListener());
        addListener(new PlayerSleepListener());
        addListener(new WaterPlaceListener());
    }

    private void addListener(@Nonnull Listener listener) {
        Netheopoiesis.getPluginManager().registerEvents(listener, Netheopoiesis.getInstance());
    }
}
