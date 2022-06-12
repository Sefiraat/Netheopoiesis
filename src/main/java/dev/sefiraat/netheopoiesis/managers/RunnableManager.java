package dev.sefiraat.netheopoiesis.managers;

import com.google.common.base.Preconditions;
import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.runnables.MobSpawnsRunnable;
import dev.sefiraat.netheopoiesis.runnables.PurificationEffects;
import dev.sefiraat.netheopoiesis.runnables.SaveConfigRunnable;

/**
 * This class is used to run Runnables from one place
 */
public class RunnableManager {

    private static RunnableManager instance;

    private final PurificationEffects regenerationRunnable;
    private final MobSpawnsRunnable spawnsRunnable;
    private final SaveConfigRunnable saveConfigRunnable;

    public RunnableManager() {
        Preconditions.checkNotNull(instance == null, "Cannot create a new instance of the ListenerManager");
        instance = this;
        final Netheopoiesis plugin = Netheopoiesis.getInstance();

        regenerationRunnable = new PurificationEffects();
        regenerationRunnable.runTaskTimer(plugin, 0, 40);

        spawnsRunnable = new MobSpawnsRunnable();
        spawnsRunnable.runTaskTimer(plugin, 0, 20);

        saveConfigRunnable = new SaveConfigRunnable();
        saveConfigRunnable.runTaskTimer(plugin, 0, 12000);
    }

    public PurificationEffects getRegenerationRunnable() {
        return regenerationRunnable;
    }

    public MobSpawnsRunnable getSpawnsRunnable() {
        return spawnsRunnable;
    }

    public SaveConfigRunnable getSaveConfigRunnable() {
        return saveConfigRunnable;
    }

    public static RunnableManager getInstance() {
        return instance;
    }
}
