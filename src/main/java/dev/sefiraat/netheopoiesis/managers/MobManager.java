package dev.sefiraat.netheopoiesis.managers;

import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Unmodifiable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MobManager {

    private static final int MOBS_PER_PLAYER = 20;
    private static MobManager instance;

    private final Map<UUID, PlayerMobs> mobPlayerMap = new HashMap<>();

    public MobManager() {
        Preconditions.checkArgument(instance == null, "Cannot create a new instance of the MobManager");
        instance = this;
    }

    @Nonnull
    @Unmodifiable
    public static List<UUID> getPlayerMobs(@Nonnull Player player) {
        return getPlayerMobs(player.getUniqueId());
    }

    @Nonnull
    @Unmodifiable
    public static List<UUID> getPlayerMobs(@Nonnull UUID playerUuid) {
        return instance.mobPlayerMap.get(playerUuid).getMobs();
    }

    public static int getPlayerMobCount(@Nonnull Player player) {
        return getPlayerMobCount(player.getUniqueId());
    }

    public static int getPlayerMobCount(@Nonnull UUID playerUuid) {
        return instance.mobPlayerMap.get(playerUuid).size();
    }

    public static void addMob(@Nonnull LivingEntity entity, @Nonnull Player player) {
        addMob(entity, player.getUniqueId());
    }

    public static void addMob(@Nonnull LivingEntity entity, @Nonnull UUID player) {
        addMob(entity.getUniqueId(), player);
    }

    public static void addMob(@Nonnull UUID mobUuid, @Nonnull Player player) {
        addMob(mobUuid, player.getUniqueId());
    }

    public static void addMob(@Nonnull UUID mobUuid, @Nonnull UUID playerUuid) {
        final PlayerMobs playerMobs = instance.mobPlayerMap.getOrDefault(playerUuid, new PlayerMobs(playerUuid));
        playerMobs.addMob(mobUuid);
    }

    public static void removeMob(@Nonnull LivingEntity entity, @Nonnull Player player) {
        removeMob(entity, player.getUniqueId());
    }

    public static void removeMob(@Nonnull LivingEntity entity, @Nonnull UUID player) {
        removeMob(entity.getUniqueId(), player);
    }

    public static void removeMob(@Nonnull UUID mobUuid, @Nonnull Player player) {
        removeMob(mobUuid, player.getUniqueId());
    }

    public static void removeMob(@Nonnull UUID mobUuid, @Nonnull UUID playerUuid) {
        final PlayerMobs playerMobs = instance.mobPlayerMap.getOrDefault(playerUuid, new PlayerMobs(playerUuid));
        playerMobs.removeMob(mobUuid);
    }

    @Nullable
    @ParametersAreNonnullByDefault
    public static LivingEntity spawnMob(EntityType type, Player player, Location spawnLocation, boolean rand) {
        if (type.isAlive() && type.isSpawnable()) {
            int mobCount = getPlayerMobCount(player);
            if (mobCount < MOBS_PER_PLAYER) {
                // Unchecked casting fine due to .isAlive()
                return (LivingEntity) spawnLocation.getWorld().spawnEntity(spawnLocation, type, rand);
            }
        }
        return null;
    }
    
    public void shutdown() {
        for (PlayerMobs mobs : mobPlayerMap.values()) {
            mobs.killAllMobs();
        }
    }

    private static class PlayerMobs {
        @Nonnull
        private final UUID playerUuid;
        @Nonnull
        private final List<UUID> mobs = new ArrayList<>();

        public PlayerMobs(@Nonnull UUID playerUuid) {
            this.playerUuid = playerUuid;
        }

        public int size() {
            return mobs.size();
        }

        public boolean contains(@Nonnull UUID mobUuid) {
            return mobs.contains(mobUuid);
        }

        public void addMob(@Nonnull UUID mobUuid) {
            mobs.add(mobUuid);
        }

        public void removeMob(@Nonnull UUID mobUuid) {
            mobs.remove(mobUuid);
        }

        public void killMob(@Nonnull UUID mobUuid) {
            mobs.remove(mobUuid);
            final Entity entity = Bukkit.getEntity(mobUuid);
            if (entity != null) {
                entity.remove();
            }
        }

        public void killAllMobs() {
            for (UUID mob : mobs) {
                final Entity entity = Bukkit.getEntity(mob);
                if (entity != null) {
                    entity.remove();
                }
            }
            mobs.clear();
        }

        @Nonnull
        public UUID getPlayerUuid() {
            return playerUuid;
        }

        @Nonnull
        @Unmodifiable
        public List<UUID> getMobs() {
            return Collections.unmodifiableList(mobs);
        }
    }

}
