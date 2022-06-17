package dev.sefiraat.netheopoiesis.managers;

import com.google.common.base.Preconditions;
import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.implementation.tasks.MobSpawnTask;
import dev.sefiraat.netheopoiesis.implementation.tasks.PurificationEffectsTask;
import dev.sefiraat.netheopoiesis.implementation.tasks.SaveConfigTask;
import dev.sefiraat.netheopoiesis.implementation.tasks.WanderingPiglinTask;

/**
 * This class is used to run Runnables from one place
 */
public class TaskManager {

    private static TaskManager instance;

    private final PurificationEffectsTask regenerationRunnable;
    private final MobSpawnTask spawnsRunnable;
    private final SaveConfigTask saveConfigRunnable;
    private final WanderingPiglinTask wanderingPiglinTask;

    public TaskManager() {
        Preconditions.checkArgument(instance == null, "Cannot create a new instance of the ListenerManager");
        instance = this;
        final Netheopoiesis plugin = Netheopoiesis.getInstance();

        regenerationRunnable = new PurificationEffectsTask();
        regenerationRunnable.runTaskTimer(plugin, 0, 40);

        spawnsRunnable = new MobSpawnTask();
        spawnsRunnable.runTaskTimer(plugin, 0, 20);

        saveConfigRunnable = new SaveConfigTask();
        saveConfigRunnable.runTaskTimer(plugin, 0, 12000);

        wanderingPiglinTask = new WanderingPiglinTask();
        wanderingPiglinTask.runTaskTimer(plugin, 0, 20);
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

    public static TaskManager getInstance() {
        return instance;
    }
}
