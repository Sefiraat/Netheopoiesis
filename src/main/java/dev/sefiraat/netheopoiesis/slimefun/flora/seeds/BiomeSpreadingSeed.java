package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import dev.sefiraat.netheopoiesis.core.plant.GrowthDescription;
import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherCrux;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;

public class BiomeSpreadingSeed extends CruxSpreadingSeed {

    private final Biome biome;

    @ParametersAreNonnullByDefault
    public BiomeSpreadingSeed(ItemGroup itemGroup,
                              SlimefunItemStack item,
                              double spreadChance,
                              NetherCrux convertTo,
                              GrowthDescription description,
                              Biome biome
    ) {
        super(itemGroup, item, spreadChance, convertTo, description);
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
        spreadTo.setBiome(this.biome);
    }
}
