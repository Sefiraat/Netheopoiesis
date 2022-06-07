package dev.sefiraat.netheopoiesis.runnables;

import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherCrux;
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
    private final NetherCrux crux;

    public UpdateCruxTask(@Nonnull Block block, @Nonnull NetherCrux crux) {
        this.block = block;
        this.crux = crux;
    }

    @Override
    public void run() {
        if (Slimefun.getTickerTask().isDeletedSoon(block.getLocation())) {
            // BlockStorage still has not been cleared
            return;
        }
        // Store the new data then cancel task
        block.setType(crux.getItem().getType());
        BlockStorage.store(block, crux.getId());
        cancel();
    }
}
