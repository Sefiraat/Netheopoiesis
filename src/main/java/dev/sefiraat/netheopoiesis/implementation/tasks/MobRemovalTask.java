package dev.sefiraat.netheopoiesis.implementation.tasks;

import dev.sefiraat.netheopoiesis.api.mobs.RandomSpawn;
import dev.sefiraat.netheopoiesis.managers.MobManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

/**
 * This Runnable is used to remove ManagedMobs if they have become invalid and/or there are no players around
 *
 * @see RandomSpawn
 */
public class MobRemovalTask extends BukkitRunnable {

    @Override
    public void run() {
        for (UUID mobUuid : MobManager.getInstance().getAllMobs()) {
            final Entity entity = Bukkit.getEntity(mobUuid);
            if (entity == null || !entity.isValid()) {
                MobManager.getInstance().removeMob(mobUuid, false);
                continue;
            }

            final Location location = entity.getLocation();
            final World world = location.getWorld();
            if (world.getNearbyEntities(location, 30, 30, 30, Player.class::isInstance).isEmpty()) {
                MobManager.getInstance().removeMob(mobUuid, true);
            }
        }
    }
}
