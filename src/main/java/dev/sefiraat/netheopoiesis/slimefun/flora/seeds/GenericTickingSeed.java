package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import dev.sefiraat.netheopoiesis.core.plant.GrowthDescription;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Location;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;

/**
 * This plant will accept the given consumer each tick when fully matured
 *
 * @see {@link GenericTickingMethods}
 */
public class GenericTickingSeed extends NetherSeed {

    private final Consumer<GenericTickingMethods.TickParameters> consumer;

    @ParametersAreNonnullByDefault
    public GenericTickingSeed(SlimefunItemStack item,
                              Consumer<GenericTickingMethods.TickParameters> consumer,
                              GrowthDescription desc
    ) {
        super(item, desc);
        this.consumer = consumer;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onTickFullyGrown(Location location, NetherSeed seed, Config data) {
        final GenericTickingMethods.TickParameters tickParameters = new GenericTickingMethods.TickParameters(
            location,
            seed,
            data
        );
        this.consumer.accept(tickParameters);
    }

    public Consumer<GenericTickingMethods.TickParameters> getConsumer() {
        return consumer;
    }

}
