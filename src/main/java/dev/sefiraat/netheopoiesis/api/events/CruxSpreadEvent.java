package dev.sefiraat.netheopoiesis.api.events;

import dev.sefiraat.netheopoiesis.api.items.NetherCrux;
import dev.sefiraat.netheopoiesis.api.items.NetherSeed;
import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This event is fired when a Crux is due to spread to another block
 *
 * @see dev.sefiraat.netheopoiesis.api.items.CruxSpreadingSeed
 * @see dev.sefiraat.netheopoiesis.implementation.flora.PurificationSeed
 */
public class CruxSpreadEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    @Nonnull
    private final Block block;
    @Nonnull
    private final NetherSeed growingPlant;
    @Nonnull
    private final NetherCrux crux;
    private boolean cancelled;

    @ParametersAreNonnullByDefault
    public CruxSpreadEvent(Block block, NetherSeed growingPlant, NetherCrux crux) {
        this.block = block;
        this.growingPlant = growingPlant;
        this.crux = crux;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Nonnull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    /**
     * @return The {@link Block} of the potential new crux
     */
    @Nonnull
    public Block getBlock() {
        return block;
    }

    /**
     * @return The {@link NetherSeed} that the growth originated from
     */
    @Nonnull
    public NetherSeed getGrowingPlant() {
        return growingPlant;
    }

    /**
     * @return The crux type that will be spread
     */
    @Nonnull
    public NetherCrux getCrux() {
        return crux;
    }
}
