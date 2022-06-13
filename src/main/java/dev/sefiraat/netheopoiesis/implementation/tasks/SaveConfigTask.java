package dev.sefiraat.netheopoiesis.implementation.tasks;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * This Runnable is used to periodically save any custom Configuration files in case
 * of an unexpected shutdown.
 */
public class SaveConfigTask extends BukkitRunnable {

    @Override
    public void run() {
        Netheopoiesis.getConfigManager().saveAll();
    }
}
