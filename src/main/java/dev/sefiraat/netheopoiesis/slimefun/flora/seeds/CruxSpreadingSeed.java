package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.Purification;
import dev.sefiraat.netheopoiesis.core.plant.GrowthDescription;
import dev.sefiraat.netheopoiesis.core.plant.SpreadingPlant;
import dev.sefiraat.netheopoiesis.runnables.UpdateCruxTask;
import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherCrux;
import dev.sefiraat.netheopoiesis.utils.ProtectionUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.block.Block;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This seed will spread crux to nearby blocks when placed and grown
 */
public class CruxSpreadingSeed extends NetherSeed implements SpreadingPlant {

    private final NetherCrux convertTo;
    private final double spreadChance;

    @ParametersAreNonnullByDefault
    public CruxSpreadingSeed(SlimefunItemStack item,
                             double spreadChance,
                             NetherCrux convertTo,
                             GrowthDescription description
    ) {
        super(item, description);
        this.convertTo = convertTo;
        this.spreadChance = spreadChance;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onTickFullyGrown(Location location, NetherSeed seed, Config data) {
        spread(location, seed, data);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void spread(Location sourceLocation, NetherSeed seed, Config data) {
        double randomChance = ThreadLocalRandom.current().nextDouble();

        if (randomChance > (this.spreadChance * Netheopoiesis.CRUX_SPREAD_MULTIPLIER)) {
            // Fails chance roll
            return;
        }

        final double randomX = ThreadLocalRandom.current().nextInt(-3, 4);
        final double randomY = ThreadLocalRandom.current().nextInt(-2, 1);
        final double randomZ = ThreadLocalRandom.current().nextInt(-3, 4);
        // For loop to make sure the purification can creep up and down.

        final Block block = sourceLocation.clone().add(randomX, randomY, randomZ).getBlock();
        final SlimefunItem possibleCrux = BlockStorage.check(block);
        if (possibleCrux instanceof NetherCrux currentCrux
            && getPlacements().contains(currentCrux.getId())
            && ProtectionUtils.hasPermission(getOwner(sourceLocation), block, Interaction.BREAK_BLOCK)
        ) {
            BlockStorage.clearBlockInfo(block);
            Purification.removeValue(block);
            // Schedule a task to ensure the new block storage happens only AFTER deletion
            UpdateCruxTask task = new UpdateCruxTask(block, convertTo);
            task.runTaskTimer(Netheopoiesis.getInstance(), 1, 20);
            afterSpread(sourceLocation, seed, data, block);
        }
    }
}
