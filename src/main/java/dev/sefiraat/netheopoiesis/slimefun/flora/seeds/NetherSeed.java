package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import dev.sefiraat.netheopoiesis.breeding.BreedResult;
import dev.sefiraat.netheopoiesis.breeding.BreedingDefinitions;
import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherSeedCrux;
import dev.sefiraat.netheopoiesis.utils.Keys;
import dev.sefiraat.netheopoiesis.utils.Particles;
import dev.sefiraat.netheopoiesis.utils.Skulls;
import io.github.bakedlibs.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.papermc.lib.PaperLib;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class is used to define a Seed item that will grow as a {@link NetherPlant}
 */
public abstract class NetherSeed extends SlimefunItem implements NetherPlant {

    public static Set<BlockFace> BREEDING_DIRECTIONS = Set.of(
        BlockFace.NORTH,
        BlockFace.SOUTH,
        BlockFace.EAST,
        BlockFace.WEST
    );

    protected NetherSeed(@Nonnull ItemGroup itemGroup,
                         @Nonnull SlimefunItemStack item,
                         @Nonnull RecipeType recipeType,
                         @Nonnull ItemStack[] recipe
    ) {
        super(itemGroup, item, recipeType, recipe);
    }

    protected NetherSeed(@Nonnull ItemGroup itemGroup,
                         @Nonnull SlimefunItemStack item,
                         @Nonnull RecipeType recipeType,
                         @Nonnull ItemStack[] recipe,
                         @Nullable ItemStack recipeOutput
    ) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
    }

    @Override
    public void preRegister() {
        addItemHandler(
            new BlockTicker() {
                @Override
                public boolean isSynchronized() {
                    return true;
                }

                @Override
                public void tick(Block block, SlimefunItem item, Config data) {
                    if (item instanceof NetherSeed seed) {
                        onTick(block, seed, data);
                    }
                }
            }
        );
    }

    @ParametersAreNonnullByDefault
    private void onTick(Block block, NetherSeed seed, Config data) {
        final Location location = block.getLocation();
        int growthStage = Integer.parseInt(data.getString(Keys.SEED_GROWTH_STAGE));
        onTickAlways(location, seed, data);
        if (growthStage >= getGrowthPhases().size()) {
            onTickFullyGrown(location, seed, data);
            tryBreed(block, seed);
        } else {
            tryGrow(block, seed, data, location, growthStage);
        }
    }

    @ParametersAreNonnullByDefault
    private void tryGrow(Block block, NetherSeed seed, Config data, Location location, int growthStage) {
        final double growthRandom = ThreadLocalRandom.current().nextDouble();
        if (growthRandom <= getGrowthRate() && getGrowthPhases().size() > growthStage) {
            updateGrowthStage(block, growthStage + 1);
            if (getGrowthPhases().size() == growthStage) {
                onFullyMatures(location, seed, data);
            }
        }
    }

    private void tryBreed(@Nonnull Block block, @Nonnull NetherSeed mother) {
        final double breedRandom = ThreadLocalRandom.current().nextDouble();
        if (breedRandom <= getGrowthRate()) {
            for (BlockFace face : BREEDING_DIRECTIONS) {
                final Block middleBlock = block.getRelative(face);
                // There must be space for the new block
                if (middleBlock.getType() != Material.AIR) {
                    return;
                }
                final Block potentialMate = middleBlock.getRelative(face);
                final SlimefunItem mateItem = BlockStorage.check(potentialMate);

                if (mateItem instanceof NetherSeed mate) {
                    final BreedResult result = BreedingDefinitions.getBreedResult(mother, mate);
                    if (result == null) {
                        return;
                    } else if (result.getResultType() == BreedResult.BreedResultType.BREED_SUCCESS) {
                        trySetChildSeed(middleBlock, result.getMatchedPair().getChildPlant());
                    } else if (result.getResultType() == BreedResult.BreedResultType.BREED_SPREAD) {
                        trySetChildSeed(middleBlock, mother);
                    }
                }
            }
        }
    }

    private void trySetChildSeed(Block cloneBlock, NetherSeed childSeed) {
        cloneBlock.setType(Material.PLAYER_HEAD);
        PlayerHead.setSkin(cloneBlock, childSeed.getGrowthPhases().get(0).getPlayerSkin(), false);
        PaperLib.getBlockState(cloneBlock, false).getState().update(true, false);
        BlockStorage.store(cloneBlock, childSeed.getId());
        BlockStorage.addBlockInfo(cloneBlock, Keys.SEED_GROWTH_STAGE, "0");
    }

    public boolean isMature(@Nonnull Block block) {
        return isMature(block.getLocation());
    }

    public boolean isMature(@Nonnull Location location) {
        final String stageString = BlockStorage.getLocationInfo(location, Keys.SEED_GROWTH_STAGE);
        if (stageString == null) {
            return false;
        }
        final int growthStage = Integer.parseInt(stageString);
        return growthStage >= getGrowthPhases().size();
    }

    public void updateGrowthStage(@Nonnull Location location, int growthStage) {
        updateGrowthStage(location.getBlock(), growthStage);
    }

    public void updateGrowthStage(@Nonnull Block block, int growthStage) {
        final Location location = block.getLocation().clone().add(0.5, 0.5, 0.5);
        final Skulls nextTexture = getGrowthPhases().get(growthStage - 1);
        PlayerHead.setSkin(block, nextTexture.getPlayerSkin(), false);
        PaperLib.getBlockState(block, false).getState().update(true, false);
        BlockStorage.addBlockInfo(block, Keys.SEED_GROWTH_STAGE, String.valueOf(growthStage));
        displayGrowthParticles(location);
    }

    /**
     * This method is fired when the block is placed
     * see {@link dev.sefiraat.netheopoiesis.listeners.SeedPlacementListener}
     *
     * @param event The {@link BlockPlaceEvent} triggered from the block placement
     */
    public void whenPlaced(@Nonnull BlockPlaceEvent event) {
        final Block block = event.getBlock();
        final Block blockBelow = block.getRelative(BlockFace.DOWN);
        final Location location = block.getLocation();
        final SlimefunItem itemBelow = BlockStorage.check(blockBelow);

        if (itemBelow instanceof NetherSeedCrux crux
            && location.getWorld().getEnvironment() == World.Environment.NETHER && getValidPlaces().contains(crux)
        ) {
            BlockStorage.addBlockInfo(location, Keys.SEED_GROWTH_STAGE, "0");
            return;
        }
        // Wasn't placable, so cancel the event
        event.setCancelled(true);
    }

    private void displayGrowthParticles(@Nonnull Location location) {
        final Location centered = location.clone().add(0.5, 0.5, 0.5);
        Particles.displayParticleRandomly(centered, 0.5, 4, getTheme().getDustOptions(1f));
    }
}
