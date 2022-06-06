package dev.sefiraat.netheopoiesis.managers;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.listeners.MobSpawnListener;
import dev.sefiraat.netheopoiesis.listeners.PlayerSleepListener;
import dev.sefiraat.netheopoiesis.listeners.SeedPlacementListener;
import dev.sefiraat.netheopoiesis.listeners.VanillaDropListener;
import org.apache.commons.lang.Validate;
import org.bukkit.event.Listener;

import javax.annotation.Nonnull;

public class ListenerManager {

    private static ListenerManager instance;

    public ListenerManager() {
        Validate.isTrue(instance == null, "Cannot create a new instance of the ListenerManager");
        instance = this;
        addListener(new VanillaDropListener());
        addListener(new SeedPlacementListener());
        addListener(new MobSpawnListener());
        addListener(new PlayerSleepListener());
    }

    private void addListener(@Nonnull Listener listener) {
        Netheopoiesis.getPluginManager().registerEvents(listener, Netheopoiesis.getInstance());
    }

    public static ListenerManager getInstance() {
        return instance;
    }
}
