package dev.sefiraat.netheopoiesis.managers;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.runnables.MobSpawnsRunnable;
import dev.sefiraat.netheopoiesis.runnables.RegenerationRunnable;
import dev.sefiraat.netheopoiesis.runnables.SaveConfigRunnable;
import org.apache.commons.lang.Validate;

public class RunnableManager {

    private static RunnableManager instance;

    public RunnableManager() {
        Validate.isTrue(instance == null, "Cannot create a new instance of the ListenerManager");
        instance = this;
        final Netheopoiesis plugin = Netheopoiesis.getInstance();

        new RegenerationRunnable().runTaskTimer(plugin, 0, 40);
        new MobSpawnsRunnable().runTaskTimer(plugin, 0, 20);
        new SaveConfigRunnable().runTaskTimer(plugin, 0, 12000);
    }

    public static RunnableManager getInstance() {
        return instance;
    }
}
