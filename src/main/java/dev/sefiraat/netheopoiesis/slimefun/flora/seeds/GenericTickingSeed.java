package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import com.google.common.base.Preconditions;
import dev.sefiraat.netheopoiesis.Netheopoiesis;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Location;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;

/**
 * This plant will accept the given consumer each tick when fully matured
 *
 * @see {@link GenericTickingMethods}
 */
public class GenericTickingSeed extends NetherSeed {

    @Nullable
    private Consumer<GenericTickingMethods.TickParameters> consumer;

    public GenericTickingSeed(@Nonnull SlimefunItemStack item) {
        super(item);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onTickFullyGrown(Location location, NetherSeed seed, Config data) {
        final GenericTickingMethods.TickParameters tickParameters = new GenericTickingMethods.TickParameters(
            location,
            seed,
            data
        );
        Preconditions.checkNotNull(this.consumer);
        this.consumer.accept(tickParameters);
    }

    @Nonnull
    public GenericTickingSeed setConsumer(@Nonnull Consumer<GenericTickingMethods.TickParameters> consumer) {
        this.consumer = consumer;
        return this;
    }

    @Nullable
    public Consumer<GenericTickingMethods.TickParameters> getConsumer() {
        return consumer;
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    protected boolean validateSeed() {
        if (this.consumer == null) {
            Netheopoiesis.logWarning(this.getId() + " has no Consumer, it will not be registered.");
            return false;
        }
        return true;
    }
}
