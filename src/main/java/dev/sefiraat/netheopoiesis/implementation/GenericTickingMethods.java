package dev.sefiraat.netheopoiesis.implementation;

import dev.sefiraat.netheopoiesis.api.items.GenericTickingSeed;
import dev.sefiraat.netheopoiesis.api.items.NetherCrux;
import dev.sefiraat.netheopoiesis.api.items.NetherSeed;
import dev.sefiraat.netheopoiesis.utils.Theme;
import dev.sefiraat.netheopoiesis.utils.WorldUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Holds the methods for the {@link GenericTickingSeed}'s consumer
 */
public final class GenericTickingMethods {

    private static final Set<String> WALSHY_MESSAGES = Set.of(
        ":NotLikeThis:",
        ":LuL:",
        ":PepeHands:",
        "This is why we can't have nice things...",
        "arrrrgggghhhhhhhhh",
        "reeeeeeeeee",
        "**screams into muted mic**",
        "Oh god, what even is this...",
        "Have you heard about our lord and saviour, Cloudflare?",
        "SlimeFun....",
        "zzz... Workers....  workers..... workers... zzz...",
        "zzz... Pages....  Pages..... Pages... zzz..."
    );

    private static final Set<String> ALESSIO_MESSAGES = Set.of(
        "beepboop",
        "*whips out MSPaint*",
        "(\uD835\uDC4E + \uD835\uDC4F)" +
            "\uD835\uDC5B = ∑(" +
            "\uD835\uDC5B" +
            "\uD835\uDC58" +
            ") \uD835\uDC4E" +
            "\uD835\uDC58\uD835\uDC4F" +
            "\uD835\uDC5B",
        "World Gen!!!!1!!!",
        "1 + 1 = 3... Me Smort",
        "Hawaiian Pizza is the best, yummy yummy in my tummy.",
        "Teaching Sefi how to make a sphere.... *internally screaming*"
    );

    private GenericTickingMethods() {
        throw new IllegalStateException("Utility class");
    }

    public static void onTickSpindleSeed(@Nonnull TickParameters params) {
        double randomChance = ThreadLocalRandom.current().nextDouble();
        if (randomChance <= 0.05) {
            final Block block = WorldUtils.randomLocation(params.getLocation(), 3, 2, 2).getBlock();

            // the first block we spawn on needs to be AIR
            if (block.getType() != Material.AIR) {
                return;
            }

            final Block blockBelow = block.getRelative(BlockFace.DOWN);
            final SlimefunItem possibleCrux = BlockStorage.check(blockBelow);

            // And the block below must be a valid crux
            if (possibleCrux instanceof NetherCrux crux
                && params.getSeed().getPlacements().contains(crux.getId())
            ) {
                block.setType(Material.OAK_LOG);
            }
        }
    }

    public static void onTickSweetSeed(@Nonnull TickParameters params) {
        double randomChance = ThreadLocalRandom.current().nextDouble();
        if (randomChance <= 0.1) {
            final Block block = WorldUtils.randomLocation(params.getLocation(), 3, 2, 2).getBlock();

            // the first block we spawn on needs to be AIR and Biome DESERT
            if (block.getType() != Material.AIR) {
                return;
            }

            final Block blockBelow = block.getRelative(BlockFace.DOWN);
            final SlimefunItem possibleCrux = BlockStorage.check(blockBelow);

            // And the block below must be a valid crux
            if (blockBelow.getType() == Material.SAND
                && possibleCrux instanceof NetherCrux crux
                && params.getSeed().getPlacements().contains(crux.getId())
            ) {
                block.setType(Material.SUGAR_CANE);
            }
        }
    }

    public static void onTickSpineySeed(@Nonnull TickParameters params) {
        double randomChance = ThreadLocalRandom.current().nextDouble();
        if (randomChance <= 0.1) {
            final Block block = WorldUtils.randomLocation(params.getLocation(), 3, 2, 2).getBlock();

            // the first block we spawn on needs to be AIR and Biome DESERT
            if (block.getType() != Material.AIR && block.getBiome() == Biome.DESERT) {
                return;
            }

            final Block blockBelow = block.getRelative(BlockFace.DOWN);
            final SlimefunItem possibleCrux = BlockStorage.check(blockBelow);

            // And the block below must be a valid crux
            if (possibleCrux instanceof NetherCrux crux
                && params.getSeed().getPlacements().contains(crux.getId())
            ) {
                block.setType(Material.CACTUS);
            }
        }
    }

    public static void onTickOakendranSeed(@Nonnull TickParameters params) {
        double randomChance = ThreadLocalRandom.current().nextDouble();
        if (randomChance <= 0.5) {
            final Block block = WorldUtils.randomLocation(params.getLocation(), 3, 2, 2).getBlock();

            // the first block we spawn on needs to be AIR
            if (block.getType() != Material.AIR) {
                return;
            }

            final Block blockBelow = block.getRelative(BlockFace.DOWN);
            final SlimefunItem possibleCrux = BlockStorage.check(blockBelow);

            // And the block below must be a valid crux
            if (possibleCrux instanceof NetherCrux crux
                && params.getSeed().getPlacements().contains(crux.getId())
            ) {
                block.getWorld().generateTree(block.getLocation(), TreeType.TREE);
            }
        }
    }

    public static void onTickHateFilledSeed(@Nonnull TickParameters params) {
        double randomChance = ThreadLocalRandom.current().nextDouble();
        if (randomChance <= 0.05) {
            final Location location = WorldUtils.randomLocation(params.getLocation(), 3, 2, 2);
            location.getWorld().createExplosion(location, 2F, true, false);
        }
    }

    public static void onTickPulsingSeed(@Nonnull TickParameters parameters) {
        // Todo set up beacon effects
    }

    public static void onWalshyIsMad(@Nonnull TickParameters params) {
        tryBroadcastRandomMessage(params.getLocation(), Theme.ERROR, WALSHY_MESSAGES);
    }

    public static void onAlessioTeach(@Nonnull TickParameters params) {
        tryBroadcastRandomMessage(params.getLocation(), Theme.SUCCESS, ALESSIO_MESSAGES);
    }

    @ParametersAreNonnullByDefault
    private static void tryBroadcastRandomMessage(Location location, Theme theme, Set<String> set) {
        final double chance = ThreadLocalRandom.current().nextDouble();

        if (chance > 0.1) {
            return;
        }

        final World world = location.getWorld();
        final Collection<Entity> entities = world.getNearbyEntities(location, 10, 10, 10);
        final String message = set.stream()
                                  .skip(ThreadLocalRandom.current().nextInt(set.size()))
                                  .findFirst()
                                  .orElse("");

        for (Entity entity : entities) {
            if (entity instanceof Player player) {
                player.sendMessage(theme + message);
            }
        }
    }

    public static class TickParameters {
        private final Location location;
        private final NetherSeed seed;
        private final Config data;

        @ParametersAreNonnullByDefault
        public TickParameters(Location location, NetherSeed seed, Config data) {
            this.location = location;
            this.seed = seed;
            this.data = data;
        }

        public Location getLocation() {
            return location.clone();
        }

        public NetherSeed getSeed() {
            return seed;
        }

        public Config getData() {
            return data;
        }
    }
}
