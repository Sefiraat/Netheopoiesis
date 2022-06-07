package dev.sefiraat.netheopoiesis.events;

import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.NetherSeed;
import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * This event is fired before a plant grows a stage. This event can be cancelled to stop growth
 */
public class PlantBeforeGrowthEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Location location;
    private final NetherSeed growingPlant;
    private final int growthStage;
    private boolean cancelled;

    @ParametersAreNonnullByDefault
    public PlantBeforeGrowthEvent(Location location,
                                  NetherSeed growingPlant,
                                  int growthStage
    ) {
        this.location = location;
        this.growingPlant = growingPlant;
        this.growthStage = growthStage;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    /**
     * @return The {@link Location} of the currently growing plant
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @return The {@link NetherSeed} that is about to grow
     */
    public NetherSeed getGrowingPlant() {
        return growingPlant;
    }

    /**
     * @return The current growth stage between 0-4
     */
    public int getGrowthStage() {
        return growthStage;
    }
}
