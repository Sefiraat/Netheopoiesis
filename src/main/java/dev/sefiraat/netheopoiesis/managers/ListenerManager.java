package dev.sefiraat.netheopoiesis.managers;

import com.google.common.base.Preconditions;
import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.listeners.BlockProtectionListener;
import dev.sefiraat.netheopoiesis.listeners.CrushingListener;
import dev.sefiraat.netheopoiesis.listeners.CrystallineSeedListener;
import dev.sefiraat.netheopoiesis.listeners.DropListener;
import dev.sefiraat.netheopoiesis.listeners.FriendlyMobsListener;
import dev.sefiraat.netheopoiesis.listeners.ManagedMobListener;
import dev.sefiraat.netheopoiesis.listeners.MobSpawnListener;
import dev.sefiraat.netheopoiesis.listeners.PlayerSleepListener;
import dev.sefiraat.netheopoiesis.listeners.SeedPlacementListener;
import dev.sefiraat.netheopoiesis.listeners.WanderingPiglinListener;
import dev.sefiraat.netheopoiesis.listeners.WaterPlaceListener;
import org.bukkit.event.Listener;

import javax.annotation.Nonnull;

/**
 * This class is used to register all listeners in one place
 */
public class ListenerManager {

    private static ListenerManager instance;

    public ListenerManager() {
        Preconditions.checkArgument(instance == null, "Cannot create a new instance of the ListenerManager");
        instance = this;
        addListener(new DropListener());
        addListener(new SeedPlacementListener());
        addListener(new MobSpawnListener());
        addListener(new PlayerSleepListener());
        addListener(new WaterPlaceListener());
        addListener(new FriendlyMobsListener());
        addListener(new BlockProtectionListener());
        addListener(new CrystallineSeedListener());
        addListener(new ManagedMobListener());
        addListener(new CrushingListener());
        addListener(new WanderingPiglinListener());
    }

    private void addListener(@Nonnull Listener listener) {
        Netheopoiesis.getPluginManager().registerEvents(listener, Netheopoiesis.getInstance());
    }

    public static ListenerManager getInstance() {
        return instance;
    }
}
