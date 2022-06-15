package dev.sefiraat.netheopoiesis.implementation.tasks;

import dev.sefiraat.netheopoiesis.utils.Keys;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nonnull;

/**
 * When trying to swap out a Slimefun Block in BlockStorage, the deletion will happen after a delay removing
 * the newly stored data. Not removing the old data will cause a duplicate block in storage reverting
 * to its previous state after a restart. This task will ensure the new block data is added only after the
 * removed block has left the deletion queue.
 */
public class UpdateCruxTask extends BukkitRunnable {

    @Nonnull
    private final Block block;
    @Nonnull
    private final SlimefunItemStack crux;
    private final int steps;

    public UpdateCruxTask(@Nonnull Block block, @Nonnull SlimefunItemStack crux) {
        this(block, crux, -1);
    }

    public UpdateCruxTask(@Nonnull Block block, @Nonnull SlimefunItemStack crux, int spreadStepsRemaining) {
        this.block = block;
        this.crux = crux;
        this.steps = spreadStepsRemaining;
    }

    @Override
    public void run() {
        if (Slimefun.getTickerTask().isDeletedSoon(block.getLocation())) {
            // BlockStorage still has not been cleared
            return;
        }
        // Store the new data then cancel task
        block.setType(crux.getType());
        BlockStorage.store(block, crux.getItemId());
        if (steps > -1) {
            BlockStorage.addBlockInfo(block, Keys.CRYSTALLINE_STEPS_REMAINING, String.valueOf(this.steps));
        }
        cancel();
    }
}
