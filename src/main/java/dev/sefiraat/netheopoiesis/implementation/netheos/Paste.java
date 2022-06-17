package dev.sefiraat.netheopoiesis.implementation.netheos;

import dev.sefiraat.netheopoiesis.api.plant.netheos.Flavour;
import dev.sefiraat.netheopoiesis.api.plant.netheos.FlavourProfile;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemConsumptionHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.RandomizedSet;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;
import java.util.stream.Collectors;

public class Paste extends SlimefunItem {

    @Nonnull
    private final FlavourProfile profile;
    @Nonnull
    private final RandomizedSet<Flavour> dominantFlavours;
    private final int flavourVolume;

    @ParametersAreNonnullByDefault
    public Paste(ItemGroup itemGroup,
                 SlimefunItemStack item,
                 RecipeType recipeType,
                 ItemStack[] recipe,
                 FlavourProfile profile
    ) {
        super(itemGroup, item, recipeType, recipe);
        this.profile = profile;

        // Get the dominant flavour(s) present in this paste
        final int highest = profile.getFlavourMap().values().stream()
                                   .mapToInt(Integer::intValue)
                                   .max()
                                   .orElse(-1);

        final Set<Flavour> filtered = profile.getFlavourMap().keySet().stream()
                                             .filter(key -> profile.getFlavourMap().get(key) == highest)
                                             .collect(Collectors.toSet());

        this.dominantFlavours = new RandomizedSet<>(filtered);

        // Get the total flavour volume in this paste
        this.flavourVolume = profile.getFlavourMap().values().stream()
                                    .mapToInt(Integer::intValue)
                                    .sum();
    }

    @Override
    public void preRegister() {
        addItemHandler(new ItemConsumptionHandler() {
            @Override
            @ParametersAreNonnullByDefault
            public void onConsume(PlayerItemConsumeEvent event, Player player, ItemStack item) {
                event.setCancelled(true);
            }
        });
    }

    @Nonnull
    public Flavour getDominantFlavour() {
        return dominantFlavours.getRandom();
    }

    @Nonnull
    public FlavourProfile getProfile() {
        return profile;
    }

    public int getFlavourVolume() {
        return flavourVolume;
    }
}
