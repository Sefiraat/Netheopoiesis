package dev.sefiraat.netheopoiesis.managers;

import io.github.sefiraat.emctech.EmcTech;
import io.github.sefiraat.emctech.runnables.SaveConfigRunnable;

public class RunnableManager {

    public RunnableManager() {
        final EmcTech plugin = EmcTech.getInstance();

        final SaveConfigRunnable saveConfigRunnable = new SaveConfigRunnable();

        saveConfigRunnable.runTaskTimer(plugin, 1, 12000);

    }
}
