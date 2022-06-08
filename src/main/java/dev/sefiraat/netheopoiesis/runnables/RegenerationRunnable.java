package dev.sefiraat.netheopoiesis.runnables;

import dev.sefiraat.netheopoiesis.Purification;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * This Runnable is used to provide a scaling regeneration effect to all players within a
 * purified region
 */
public class RegenerationRunnable extends BukkitRunnable {

    private static final PotionEffect REGEN_1 = new PotionEffect(PotionEffectType.REGENERATION, 50, 0);
    private static final PotionEffect REGEN_2 = new PotionEffect(PotionEffectType.REGENERATION, 50, 1);
    private static final PotionEffect REGEN_3 = new PotionEffect(PotionEffectType.REGENERATION, 50, 2);

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            final int purificationLevel = Purification.getValue(player.getLocation().getChunk());
            if (purificationLevel >= Purification.REGEN_3) {
                player.addPotionEffect(REGEN_3);
            } else if (purificationLevel >= Purification.REGEN_2) {
                player.addPotionEffect(REGEN_2);
            } else if (purificationLevel >= Purification.REGEN_1) {
                player.addPotionEffect(REGEN_1);
            }
        }
    }
}
