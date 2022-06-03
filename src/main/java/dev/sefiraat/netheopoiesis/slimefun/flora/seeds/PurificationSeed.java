package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class PurificationSeed extends NetherSeed {

    protected PurificationSeed(@Nonnull ItemGroup itemGroup,
                               @Nonnull SlimefunItemStack item,
                               @Nonnull RecipeType recipeType,
                               @Nonnull ItemStack[] recipe
    ) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void onTickFullyGrown(@Nonnull Location location, @Nonnull Config data) {
        double randomChance = ThreadLocalRandom.current().nextDouble();
        if (randomChance <= 0.1) {
            final double randomX = ThreadLocalRandom.current().nextInt(-3, 4);
            final double randomZ = ThreadLocalRandom.current().nextDouble(-3, 4);
            final Block block = location.clone().add(randomX, -1, randomZ).getBlock();
            if (getValidPlaces().contains(block.getType())) {

            }
        }
    }

    @Nonnull
    @Override
    public Set<Material> getValidPlaces() {
        return Set.of(
            Material.NETHERRACK,
            Material.CRIMSON_NYLIUM,
            Material.WARPED_NYLIUM
        );
    }

    @Override
    public double getGrowthRate() {
        return 0.1;
    }
}
