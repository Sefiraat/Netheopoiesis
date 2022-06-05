package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import dev.sefiraat.netheopoiesis.core.plants.GrowthDescription;
import dev.sefiraat.netheopoiesis.core.plants.NetherPlant;
import dev.sefiraat.netheopoiesis.core.plants.Placement;
import dev.sefiraat.netheopoiesis.core.plants.breeding.BreedResult;
import dev.sefiraat.netheopoiesis.core.plants.breeding.BreedingDefinitions;
import dev.sefiraat.netheopoiesis.events.NetherPlantBeforeGrowthEvent;
import dev.sefiraat.netheopoiesis.slimefun.NpsSlimefunItems;
import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherSeedCrux;
import dev.sefiraat.netheopoiesis.utils.Keys;
import dev.sefiraat.netheopoiesis.utils.Particles;
import dev.sefiraat.netheopoiesis.utils.Skulls;
import dev.sefiraat.netheopoiesis.utils.Theme;
import io.github.bakedlibs.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.papermc.lib.PaperLib;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class is used to define a Seed item that will grow as a {@link NetherPlant}
 */
public abstract class NetherSeed extends SlimefunItem implements NetherPlant {

    public static final Set<BlockFace> BREEDING_DIRECTIONS = Set.of(
        BlockFace.NORTH,
        BlockFace.SOUTH,
        BlockFace.EAST,
        BlockFace.WEST
    );

    private final GrowthDescription growthStages;
    private final Placement placement;

    protected NetherSeed(@Nonnull ItemGroup itemGroup,
                         @Nonnull SlimefunItemStack item,
                         @Nonnull RecipeType recipeType,
                         @Nonnull ItemStack[] recipe,
                         @Nonnull GrowthDescription growthDescription,
                         @Nonnull Placement placement
    ) {
        super(itemGroup, item, recipeType, recipe);
        this.growthStages = growthDescription;
        this.placement = placement;
    }

    protected NetherSeed(@Nonnull ItemGroup itemGroup,
                         @Nonnull SlimefunItemStack item,
                         @Nonnull RecipeType recipeType,
                         @Nonnull ItemStack[] recipe,
                         @Nullable ItemStack recipeOutput,
                         @Nonnull GrowthDescription growthDescription,
                         @Nonnull Placement placement
    ) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
        this.growthStages = growthDescription;
        this.placement = placement;
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
            },
            new BlockBreakHandler(false, false) {
                @Override
                @ParametersAreNonnullByDefault
                public void onPlayerBreak(BlockBreakEvent blockBreakEvent, ItemStack itemStack, List<ItemStack> list) {
                    removePurificationRegistry(blockBreakEvent.getBlock());
                }
            }
        );
    }

    @ParametersAreNonnullByDefault
    private void onTick(Block block, NetherSeed seed, Config data) {
        final Location location = block.getLocation();
        int growthStage = Integer.parseInt(data.getString(Keys.SEED_GROWTH_STAGE));
        onTickAlways(location, seed, data);
        if (growthStage >= getGrowthDescription().stages()) {
            onTickFullyGrown(location, seed, data);
            tryBreed(block, seed);
        } else {
            tryGrow(block, seed, data, location, growthStage);
        }
        registerPurificationValue(block);
    }

    @ParametersAreNonnullByDefault
    private void tryGrow(Block block, NetherSeed seed, Config data, Location location, int growthStage) {
        final double growthRandom = ThreadLocalRandom.current().nextDouble();
        if (growthRandom <= getGrowthRate() && getGrowthDescription().stages() > growthStage) {
            NetherPlantBeforeGrowthEvent event = new NetherPlantBeforeGrowthEvent(location, seed, growthStage);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                return;
            }
            updateGrowthStage(block, growthStage + 1);
            if (getGrowthDescription().stages() == growthStage) {
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
        PlayerHead.setSkin(cloneBlock, childSeed.getGrowthDescription().get(0).getPlayerSkin(), false);
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
        return growthStage >= getGrowthDescription().stages();
    }

    public void updateGrowthStage(@Nonnull Location location, int growthStage) {
        updateGrowthStage(location.getBlock(), growthStage);
    }

    public void updateGrowthStage(@Nonnull Block block, int growthStage) {
        final Location location = block.getLocation().clone().add(0.5, 0.5, 0.5);
        final Skulls nextTexture = getGrowthDescription().get(growthStage - 1);
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
            && location.getWorld().getEnvironment() == World.Environment.NETHER && getPlacement().contains(crux)
        ) {
            BlockStorage.addBlockInfo(location, Keys.SEED_GROWTH_STAGE, "0");
            return;
        }
        // Wasn't placable, so cancel the event
        event.setCancelled(true);
    }

    @Override
    public GrowthDescription getGrowthDescription() {
        return growthStages;
    }

    @Nonnull
    @Override
    public Theme getTheme() {
        return growthStages.getTheme();
    }

    @Nonnull
    @Override
    public Placement getPlacement() {
        return this.placement;
    }

    private void displayGrowthParticles(@Nonnull Location location) {
        final Location centered = location.clone().add(0.5, 0.5, 0.5);
        Particles.displayParticleRandomly(centered, 0.5, 4, getTheme().getDustOptions(1f));
    }
}
