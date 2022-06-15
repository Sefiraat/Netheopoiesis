package dev.sefiraat.netheopoiesis.managers;

import com.google.common.base.Preconditions;
import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.implementation.tasks.MobSpawnTask;
import dev.sefiraat.netheopoiesis.implementation.tasks.PurificationEffectsTask;
import dev.sefiraat.netheopoiesis.implementation.tasks.SaveConfigTask;

/**
 * This class is used to run Runnables from one place
 */
public class RunnableManager {

    private static RunnableManager instance;

    private final PurificationEffectsTask regenerationRunnable;
    private final MobSpawnTask spawnsRunnable;
    private final SaveConfigTask saveConfigRunnable;

    public RunnableManager() {
        Preconditions.checkArgument(instance == null, "Cannot create a new instance of the ListenerManager");
        instance = this;
        final Netheopoiesis plugin = Netheopoiesis.getInstance();

        regenerationRunnable = new PurificationEffectsTask();
        regenerationRunnable.runTaskTimer(plugin, 0, 40);

        spawnsRunnable = new MobSpawnTask();
        spawnsRunnable.runTaskTimer(plugin, 0, 20);

        saveConfigRunnable = new SaveConfigTask();
        saveConfigRunnable.runTaskTimer(plugin, 0, 12000);
    }

    public PurificationEffectsTask getRegenerationRunnable() {
        return regenerationRunnable;
    }

    public MobSpawnTask getSpawnsRunnable() {
        return spawnsRunnable;
    }

    public SaveConfigTask getSaveConfigRunnable() {
        return saveConfigRunnable;
    }

    public static RunnableManager getInstance() {
        return instance;
    }
}
