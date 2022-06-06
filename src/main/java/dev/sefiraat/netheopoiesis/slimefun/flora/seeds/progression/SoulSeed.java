package dev.sefiraat.netheopoiesis.slimefun.flora.seeds.progression;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.core.plant.GrowthDescription;
import dev.sefiraat.netheopoiesis.core.plant.Placement;
import dev.sefiraat.netheopoiesis.slimefun.NpsRecipeTypes;
import dev.sefiraat.netheopoiesis.slimefun.NpsItems;
import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherSeedCrux;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.NetherSeed;
import dev.sefiraat.netheopoiesis.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.ThreadLocalRandom;

public class SoulSeed extends NetherSeed {

    public SoulSeed(@Nonnull ItemGroup itemGroup,
                    @Nonnull SlimefunItemStack item,
                    @Nonnull GrowthDescription growthDescription,
                    @Nonnull Placement placement
    ) {
        super(itemGroup, item, NpsRecipeTypes.PLANT_BREEDING, new ItemStack[0], growthDescription, placement);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onTickFullyGrown(Location location, NetherSeed seed, Config data) {
        double randomChance = ThreadLocalRandom.current().nextDouble();
        if (randomChance <= 0.1) {
            final double randomX = ThreadLocalRandom.current().nextInt(-3, 4);
            final double randomZ = ThreadLocalRandom.current().nextInt(-3, 4);
            // For loop to make sure the purification can creep up and down.
            for (int i = -1; i < 2; i++) {
                final Block block = location.clone().add(randomX, i, randomZ).getBlock();
                final SlimefunItem possibleCrux = BlockStorage.check(block);
                if (possibleCrux instanceof NetherSeedCrux crux && getPlacement().contains(crux.getId())) {
                    BlockStorage.clearBlockInfo(block);
                    // Schedule a task to ensure the new block storage happens only AFTER deletion
                    UpdateCruxTask task = new UpdateCruxTask(block, NpsItems.PURIFIED_NETHERRACK);
                    task.runTaskTimer(Netheopoiesis.getInstance(), 1, 20);
                    // Return so we only effect the one block per valid tick
                    return;
                }
            }
        }
    }

    @Nonnull
    @Override
    public Theme getTheme() {
        return Theme.SEED_BLUE;
    }

    @Override
    public double getGrowthRate() {
        return 0.7;
    }

    @Override
    public int getPurificationValue() {
        return 2;
    }
}