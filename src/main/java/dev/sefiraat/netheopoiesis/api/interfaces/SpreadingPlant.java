package dev.sefiraat.netheopoiesis.api.interfaces;

import dev.sefiraat.netheopoiesis.api.items.CruxSpreadingSeed;
import dev.sefiraat.netheopoiesis.api.items.NetherSeed;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Location;
import org.bukkit.block.Block;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This interface represents a plant that spreads out into BlockStorage
 *
 * @see CruxSpreadingSeed
 */
public interface SpreadingPlant {

    @ParametersAreNonnullByDefault
    void spread(Location sourceLocation, NetherSeed seed, Config data);

    @ParametersAreNonnullByDefault
    default void afterSpread(Location sourceLocation, NetherSeed seed, Config data, Block spreadTo) {

    }
}
