package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import dev.sefiraat.netheopoiesis.core.plant.GrowthDescription;
import dev.sefiraat.netheopoiesis.slimefun.NpsRecipeTypes;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;
import java.util.function.Consumer;

/**
 * This plant will accept the given consumer each tick when fully matured
 * @see {@link GenericTickingMethods}
 */
public class GenericTickingSeed extends NetherSeed {

    private final Consumer<TickParameters> consumer;
    private final double growthRate;
    private final int purificationValue;

    public GenericTickingSeed(@Nonnull ItemGroup itemGroup,
                              @Nonnull SlimefunItemStack item,
                              @Nonnull GrowthDescription growthDescription,
                              @Nonnull Set<String> placement,
                              @Nonnull Consumer<TickParameters> consumer,
                              double growthRate,
                              int purificationValue
    ) {
        super(itemGroup, item, NpsRecipeTypes.PLANT_BREEDING, new ItemStack[0], growthDescription, placement);
        this.consumer = consumer;
        this.growthRate = growthRate;
        this.purificationValue = purificationValue;
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

    @Override
    public double getGrowthRate() {
        return growthRate;
    }

    @Override
    public int getPurificationValue() {
        return purificationValue;
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
