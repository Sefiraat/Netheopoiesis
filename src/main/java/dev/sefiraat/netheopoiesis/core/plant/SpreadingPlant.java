package dev.sefiraat.netheopoiesis.core.plant;

import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.NetherSeed;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Location;
import org.bukkit.block.Block;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This interface represents a plant that spreads out into BlockStorage
 *
 * @see dev.sefiraat.netheopoiesis.slimefun.flora.seeds.CruxSpreadingSeed
 */
public interface SpreadingPlant {

    @ParametersAreNonnullByDefault
    void spread(Location sourceLocation, NetherSeed seed, Config data);

    @ParametersAreNonnullByDefault
    default void afterSpread(Location sourceLocation, NetherSeed seed, Config data, Block spreadTo) {

    }
}
