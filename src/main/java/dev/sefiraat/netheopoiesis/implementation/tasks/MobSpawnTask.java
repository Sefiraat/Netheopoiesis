package dev.sefiraat.netheopoiesis.implementation.tasks;

import dev.sefiraat.netheopoiesis.Purification;
import dev.sefiraat.netheopoiesis.api.mobs.MobCapType;
import dev.sefiraat.netheopoiesis.api.mobs.RandomSpawn;
import dev.sefiraat.netheopoiesis.managers.MobManager;
import dev.sefiraat.netheopoiesis.utils.WorldUtils;
import io.github.bakedlibs.dough.collections.RandomizedSet;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nonnull;

/**
 * This Runnable is used to spawn additional mobs in the Nether when above a certain
 * purification level.
 *
 * @see RandomSpawn
 */
public class MobSpawnTask extends BukkitRunnable {

    private static final RandomSpawn SQUID = new RandomSpawn(
        EntityType.SQUID,
        MobCapType.WATER_ANIMAL,
        Purification.SPAWN_SQUID,
        0.5,
        MobSpawnTask::isWater
    );

    private static final RandomSpawn SALMON = new RandomSpawn(
        EntityType.SALMON,
        MobCapType.WATER_AMBIENT,
        Purification.SPAWN_SALMON,
        0.5,
        MobSpawnTask::isWater
    );

    private static final RandomSpawn COD = new RandomSpawn(
        EntityType.COD,
        MobCapType.WATER_AMBIENT,
        Purification.SPAWN_COD,
        0.5,
        MobSpawnTask::isWater
    );

    private static final RandomSpawn PUFFER_FISH = new RandomSpawn(
        EntityType.PUFFERFISH,
        MobCapType.WATER_AMBIENT,
        Purification.SPAWN_PUFFER_FISH,
        0.4,
        MobSpawnTask::isWater
    );

    private static final RandomSpawn TROPICAL_FISH = new RandomSpawn(
        EntityType.TROPICAL_FISH,
        MobCapType.WATER_AMBIENT,
        Purification.SPAWN_TROPICAL_FISH,
        0.4,
        MobSpawnTask::isWater
    );

    private static final RandomSpawn AXOLOTL = new RandomSpawn(
        EntityType.AXOLOTL,
        MobCapType.WATER_ANIMAL,
        Purification.SPAWN_AXOLOTL,
        0.2,
        MobSpawnTask::isWater
    );

    // Todo work out how to also spawn Llamas + leads (all for jeff)
    private static final RandomSpawn WANDERING_TRADER = new RandomSpawn(
        EntityType.WANDERING_TRADER,
        MobCapType.WANDERING_TRADER,
        Purification.WANDERING_TRADER,
        0.1,
        MobSpawnTask::isSafeGround
    );

    private static final RandomSpawn WANDERING_PIGLIN = new RandomSpawn(
        EntityType.PIGLIN,
        MobCapType.PIGLIN_TRADER,
        Purification.WANDERING_PIGLIN,
        0.1,
        false,
        MobSpawnTask::isSafeGround,
        livingEntity -> {
            final Location location = livingEntity.getLocation();
            final World world = location.getWorld();
            final LivingEntity strider1 = (LivingEntity) world.spawnEntity(location, EntityType.STRIDER, false);
            final LivingEntity strider2 = (LivingEntity) world.spawnEntity(location, EntityType.STRIDER, false);

            strider1.setLeashHolder(livingEntity);
            strider2.setLeashHolder(livingEntity);

            MobManager.getInstance().addMob(MobCapType.MISC, strider1, true);
            MobManager.getInstance().addMob(MobCapType.MISC, strider2, true);
        }
    );

    private final RandomizedSet<RandomSpawn> possibleSpawns = new RandomizedSet<>();

    public MobSpawnTask() {
        possibleSpawns.add(SALMON, 1);
        possibleSpawns.add(COD, 1);
        possibleSpawns.add(PUFFER_FISH, 1);
        possibleSpawns.add(TROPICAL_FISH, 1);
        possibleSpawns.add(SQUID, 1);
        possibleSpawns.add(AXOLOTL, 1);
        possibleSpawns.add(WANDERING_TRADER, 1);
        possibleSpawns.add(WANDERING_PIGLIN, 1);
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {

            if (!WorldUtils.inNether(player.getWorld())) {
                // Not in Nether, not required
                return;
            }

            for (int i = 0; i < 20; i++) {
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
        return block.getType().isAir() && blockBelow.getType().isSolid();
    }
}
