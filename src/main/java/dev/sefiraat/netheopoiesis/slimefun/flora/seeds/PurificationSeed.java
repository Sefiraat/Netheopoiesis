package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import dev.sefiraat.netheopoiesis.slimefun.NpsSlimefunItemStacks;
import dev.sefiraat.netheopoiesis.utils.Keys;
import dev.sefiraat.netheopoiesis.utils.Skulls;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class PurificationSeed extends NetherSeed {

    private final Set<Material> materials;
    private final LinkedList<Skulls> growthPhases = new LinkedList<>();

    public PurificationSeed(@Nonnull ItemGroup itemGroup,
                            @Nonnull SlimefunItemStack item,
                            @Nonnull RecipeType recipeType,
                            @Nonnull ItemStack[] recipe
    ) {
        super(itemGroup, item, recipeType, recipe);
        materials = Set.of(
            Material.NETHERRACK,
            Material.CRIMSON_NYLIUM,
            Material.WARPED_NYLIUM
        );
        growthPhases.add(Skulls.SEED_BLUE);
        growthPhases.add(Skulls.PLANT_VINES_GROWTH_1);
        growthPhases.add(Skulls.PLANT_VINES_GROWTH_2);
        growthPhases.add(Skulls.PLANT_VINES_GROWTH_3);
        growthPhases.add(Skulls.PLANT_VINES_GROWTH_4);
        growthPhases.add(Skulls.PLANT_VINES_GROWTH_5);
    }

    @Override
    public void onTickFullyGrown(@Nonnull Location location, @Nonnull Config data) {
        double randomChance = ThreadLocalRandom.current().nextDouble();
        if (randomChance <= 0.1) {
            final double randomX = ThreadLocalRandom.current().nextInt(-3, 4);
            final double randomZ = ThreadLocalRandom.current().nextDouble(-3, 4);
            // For loop to make sure the purification can creep up and down.
            for (int i = -1; i < 2; i++) {
                final Block block = location.clone().add(randomX, i, randomZ).getBlock();
                if (materials.contains(block.getType())) {
                    block.setType(NpsSlimefunItemStacks.BASIC_PURIFIED_NETHERRACK.getType());
                    BlockStorage.store(block, NpsSlimefunItemStacks.BASIC_PURIFIED_NETHERRACK.getItemId());
                    // Return so we only effect the one block per valid tick
                    return;
                }
            }
        }
    }

    @Override
    protected void whenPlaced(@Nonnull BlockPlaceEvent event) {
        final Block block = event.getBlock();
        final Location location = block.getLocation();
        final Block blockBelow = block.getRelative(BlockFace.DOWN);
        if (location.getWorld().getEnvironment() == World.Environment.NETHER
            && materials.contains(blockBelow.getType())
        ) {
            BlockStorage.addBlockInfo(location, Keys.SEED_GROWTH_STAGE, "0");
        } else {
            event.setCancelled(true);
        }
    }

    @Nonnull
    @Override
    public Set<SlimefunItem> getValidPlaces() {
        // Purification is the odd one out as it has to be on a vanilla block to begin the cycle
        return Collections.emptySet();
    }

    @Override
    public double getGrowthRate() {
        return 0.1;
    }

    @Override
    public LinkedList<Skulls> getGrowthPhases() {
        return this.growthPhases;
    }
}
