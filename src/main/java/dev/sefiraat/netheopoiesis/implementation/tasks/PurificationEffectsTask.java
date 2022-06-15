package dev.sefiraat.netheopoiesis.implementation.tasks;

import dev.sefiraat.netheopoiesis.Purification;
import dev.sefiraat.netheopoiesis.utils.ParticleUtils;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nonnull;

/**
 * This Runnable is used to;
 * - Provide a scaling regeneration effect to all players within a purified region
 * - Display a few particles around players in a purified region
 */
public class PurificationEffectsTask extends BukkitRunnable {

    private static final PotionEffect REGEN_1 = new PotionEffect(PotionEffectType.REGENERATION, 50, 0);
    private static final PotionEffect REGEN_2 = new PotionEffect(PotionEffectType.REGENERATION, 50, 1);
    private static final PotionEffect REGEN_3 = new PotionEffect(PotionEffectType.REGENERATION, 50, 2);

    private static final Particle.DustOptions OPTIONS_1 = new Particle.DustOptions(Color.RED, 1);
    private static final Particle.DustOptions OPTIONS_2 = new Particle.DustOptions(Color.ORANGE, 1);
    private static final Particle.DustOptions OPTIONS_3 = new Particle.DustOptions(Color.WHITE, 1);

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            final int purificationLevel = Purification.getValue(player.getLocation().getChunk());
            applyRegen(player, purificationLevel);
            displayParticles(player, purificationLevel);
        }
    }

    private void applyRegen(@Nonnull Player player, int purificationLevel) {
        if (purificationLevel >= Purification.REGEN_3) {
            player.addPotionEffect(REGEN_3);
        } else if (purificationLevel >= Purification.REGEN_2) {
            player.addPotionEffect(REGEN_2);
        } else if (purificationLevel >= Purification.REGEN_1) {
            player.addPotionEffect(REGEN_1);
        }
    }

    private void displayParticles(@Nonnull Player player, int purificationLevel) {
        if (purificationLevel >= Purification.PARTICLES_3) {
            ParticleUtils.randomSpread(player, 15, 7, OPTIONS_3);
        } else if (purificationLevel >= Purification.PARTICLES_2) {
            ParticleUtils.randomSpread(player, 15, 6, OPTIONS_2);
        } else if (purificationLevel >= Purification.PARTICLES_1) {
            ParticleUtils.randomSpread(player, 15, 5, OPTIONS_1);
        }
    }
}
