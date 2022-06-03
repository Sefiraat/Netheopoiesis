package dev.sefiraat.netheopoiesis.managers;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.listeners.VanillaDropListener;
import org.bukkit.event.Listener;

import javax.annotation.Nonnull;

public class ListenerManager {

    public ListenerManager() {
        addListener(new VanillaDropListener());
    }

    private void addListener(@Nonnull Listener listener) {
        Netheopoiesis.getPluginManager().registerEvents(listener, Netheopoiesis.getInstance());
    }
}
