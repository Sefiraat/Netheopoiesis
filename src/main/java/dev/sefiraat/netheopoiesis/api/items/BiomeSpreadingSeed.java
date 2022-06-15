package dev.sefiraat.netheopoiesis.api.items;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This seed will spread crux like the CruxSpreadingSeed whilst also setting the spread-to block's
 * biome
 *
 * @see CruxSpreadingSeed
 */
public class BiomeSpreadingSeed extends CruxSpreadingSeed {

    @Nullable
    private Biome biome;

    public BiomeSpreadingSeed(@Nonnull SlimefunItemStack item) {
        super(item);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onTickFullyGrown(Location location, NetherSeed seed, Config data) {
        spread(location, seed, data);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void afterSpread(Location sourceLocation, NetherSeed seed, Config data, Block spreadTo) {
        if (this.biome != null) {
            for (int i = spreadTo.getY() - 2; i < spreadTo.getY() + 5; i++) {
                spreadTo.getWorld().setBiome(spreadTo.getX(), i, spreadTo.getZ(), this.biome);
            }
        }
    }

    @Nonnull
    public BiomeSpreadingSeed setBiome(@Nonnull Biome biome) {
        this.biome = biome;
        return this;
    }

    @Nullable
    public Biome getBiome() {
        return this.biome;
    }

    @Override
    protected boolean validateSeed() {
        if (this.biome == null) {
            Netheopoiesis.logWarning(this.getId() + " has not has it's Biome set, will not be registered.");
            return false;
        }
        return super.validateSeed();
    }
}
