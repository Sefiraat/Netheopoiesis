package dev.sefiraat.netheopoiesis.runnables;

import dev.sefiraat.netheopoiesis.Purification;
import dev.sefiraat.netheopoiesis.core.spawning.RandomSpawn;
import dev.sefiraat.netheopoiesis.utils.WorldUtils;
import io.github.bakedlibs.dough.collections.RandomizedSet;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nonnull;

/**
 * This Runnable is used to spawn additional mobs in the Nether when above a certain
 * purification level.
 *
 * @see RandomSpawn
 */
public class MobSpawnsRunnable extends BukkitRunnable {

    private static final RandomSpawn SQUID = new RandomSpawn(
        EntityType.SQUID,
        Purification.SPAWN_SQUID,
        0.5,
        MobSpawnsRunnable::isWater
    );

    private static final RandomSpawn SALMON = new RandomSpawn(
        EntityType.SALMON,
        Purification.SPAWN_SALMON,
        0.5,
        MobSpawnsRunnable::isWater
    );

    private static final RandomSpawn COD = new RandomSpawn(
        EntityType.COD,
        Purification.SPAWN_COD,
        0.5,
        MobSpawnsRunnable::isWater
    );

    private static final RandomSpawn PUFFER_FISH = new RandomSpawn(
        EntityType.PUFFERFISH,
        Purification.SPAWN_PUFFER_FISH,
        0.4,
        MobSpawnsRunnable::isWater
    );

    private static final RandomSpawn TROPICAL_FISH = new RandomSpawn(
        EntityType.TROPICAL_FISH,
        Purification.SPAWN_TROPICAL_FISH,
        0.4,
        MobSpawnsRunnable::isWater
    );

    private static final RandomSpawn AXOLOTL = new RandomSpawn(
        EntityType.AXOLOTL,
        Purification.SPAWN_AXOLOTL,
        0.2,
        MobSpawnsRunnable::isWater
    );

    // Todo work out how to also spawn Llamas + leads (all for jeff)
    private static final RandomSpawn WANDERING_TRADER = new RandomSpawn(
        EntityType.WANDERING_TRADER,
        Purification.WANDERING_TRADER,
        0.1,
        MobSpawnsRunnable::isSafeGround
    );

    private final RandomizedSet<RandomSpawn> possibleSpawns = new RandomizedSet<>();

    public MobSpawnsRunnable() {
        possibleSpawns.add(SALMON, 1);
        possibleSpawns.add(COD, 1);
        possibleSpawns.add(PUFFER_FISH, 1);
        possibleSpawns.add(TROPICAL_FISH, 1);
        possibleSpawns.add(SQUID, 1);
        possibleSpawns.add(AXOLOTL, 1);
        possibleSpawns.add(WANDERING_TRADER, 1);
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {

            if (!WorldUtils.inNether(player.getWorld())) {
                // Not in Nether, not required
                return;
            }

            for (int i = 0; i < 10; i++) {
                final Location randomLocation = WorldUtils.randomLocation(player.getLocation(), 15);
                possibleSpawns.getRandom().trySpawn(randomLocation);
            }
        }
    }

    private static boolean isWater(@Nonnull Location location) {
        return location.getBlock().getType() == Material.WATER;
    }

    private static boolean isSafeGround(@Nonnull Location location) {
        final Block block = location.getBlock();
        final Block blockBelow = block.getRelative(BlockFace.DOWN);
        return blockBelow.getType().isAir() && blockBelow.getType().isSolid();
    }
}
