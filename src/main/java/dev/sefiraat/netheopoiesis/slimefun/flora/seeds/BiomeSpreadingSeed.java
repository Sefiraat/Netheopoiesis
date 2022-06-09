package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import dev.sefiraat.netheopoiesis.core.plant.GrowthDescription;
import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherCrux;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This seed will spread crux like the CruxSpreadingSeed whilst also setting the spread-to block's
 * biome
 *
 * @see CruxSpreadingSeed
 */
public class BiomeSpreadingSeed extends CruxSpreadingSeed {

    private final Biome biome;

    @ParametersAreNonnullByDefault
    public BiomeSpreadingSeed(SlimefunItemStack item,
                              double spreadChance,
                              NetherCrux convertTo,
                              Biome biome,
                              GrowthDescription description
    ) {
        super(item, spreadChance, convertTo, description);
        this.biome = biome;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onTickFullyGrown(Location location, NetherSeed seed, Config data) {
        spread(location, seed, data);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void afterSpread(Location sourceLocation, NetherSeed seed, Config data, Block spreadTo) {
        for (int i = spreadTo.getY() - 2; i < spreadTo.getY() + 5; i++) {
            spreadTo.getWorld().setBiome(spreadTo.getX(), i, spreadTo.getZ(), this.biome);
        }
    }
}
