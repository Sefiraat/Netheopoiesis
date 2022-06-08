package dev.sefiraat.netheopoiesis.runnables;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import org.bukkit.scheduler.BukkitRunnable;

public class SaveConfigRunnable extends BukkitRunnable {

    @Override
    public void run() {
        Netheopoiesis.getConfigManager().saveAll();
    }
}
