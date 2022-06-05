package dev.sefiraat.netheopoiesis.slimefun.flora.seeds.progression;

import dev.sefiraat.netheopoiesis.core.plants.GrowthDescription;
import dev.sefiraat.netheopoiesis.core.plants.Placement;
import dev.sefiraat.netheopoiesis.slimefun.NpsSlimefunItemStacks;
import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherSeedCrux;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.NetherSeed;
import dev.sefiraat.netheopoiesis.utils.Keys;
import dev.sefiraat.netheopoiesis.utils.Theme;
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
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class PurificationSeed extends NetherSeed {

    private final Set<Material> materials;

    public PurificationSeed(@Nonnull ItemGroup itemGroup,
                            @Nonnull SlimefunItemStack item,
                            @Nonnull RecipeType recipeType,
                            @Nonnull ItemStack[] recipe,
                            @Nonnull GrowthDescription growthDescription,
                            @Nonnull Placement placement
    ) {
        super(itemGroup, item, recipeType, recipe, growthDescription, placement);
        materials = Set.of(
            Material.NETHERRACK,
            Material.CRIMSON_NYLIUM,
            Material.WARPED_NYLIUM,
            Material.NETHER_QUARTZ_ORE,
            Material.NETHER_GOLD_ORE
        );
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onTickFullyGrown(Location location, NetherSeed seed, Config data) {
        double randomChance = ThreadLocalRandom.current().nextDouble();
        if (randomChance <= 0.15) {
            final double randomX = ThreadLocalRandom.current().nextInt(-3, 4);
            final double randomZ = ThreadLocalRandom.current().nextInt(-3, 4);
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

    @Nonnull
    @Override
    public Theme getTheme() {
        return Theme.SEED_BLUE;
    }

    @Override
    public void whenPlaced(@Nonnull BlockPlaceEvent event) {
        // We override this as this is the only one able to be placed on both vanilla and crux'
        final Block block = event.getBlock();
        final Block blockBelow = block.getRelative(BlockFace.DOWN);
        final SlimefunItem possibleCrux = BlockStorage.check(blockBelow);
        final Location location = block.getLocation();
        if (location.getWorld().getEnvironment() == World.Environment.NETHER
            && (materials.contains(blockBelow.getType()) || possibleCrux instanceof NetherSeedCrux)
        ) {
            BlockStorage.addBlockInfo(location, Keys.SEED_GROWTH_STAGE, "0");
        } else {
            event.setCancelled(true);
        }
    }

    @Override
    public double getGrowthRate() {
        return 0.15;
    }

    @Override
    public int getPurificationValue() {
        return 1;
    }
}
