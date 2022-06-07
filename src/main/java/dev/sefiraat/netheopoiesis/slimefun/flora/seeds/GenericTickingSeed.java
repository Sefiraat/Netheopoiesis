package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import dev.sefiraat.netheopoiesis.core.plant.GrowthDescription;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
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

    private final Consumer<TickParameters> consumer;

    @ParametersAreNonnullByDefault
    public GenericTickingSeed(ItemGroup group,
                              SlimefunItemStack item,
                              Consumer<TickParameters> consumer,
                              GrowthDescription description
    ) {
        super(group, item, description);
        this.consumer = consumer;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onTickFullyGrown(Location location, NetherSeed seed, Config data) {
        final TickParameters tickParameters = new TickParameters(location, seed, data);
        this.consumer.accept(tickParameters);
    }

    public Consumer<TickParameters> getConsumer() {
        return consumer;
    }

    public static class TickParameters {
        private final Location location;
        private final NetherSeed seed;
        private final Config data;

        @ParametersAreNonnullByDefault
        public TickParameters(Location location, NetherSeed seed, Config data) {
            this.location = location;
            this.seed = seed;
            this.data = data;
        }

        public Location getLocation() {
            return location.clone();
        }

        public NetherSeed getSeed() {
            return seed;
        }

        public Config getData() {
            return data;
        }
    }
}
