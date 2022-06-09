package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.core.plant.GrowthDescription;
import dev.sefiraat.netheopoiesis.core.plant.GrowthStages;
import dev.sefiraat.netheopoiesis.core.plant.NetherPlant;
import dev.sefiraat.netheopoiesis.core.plant.breeding.BreedResult;
import dev.sefiraat.netheopoiesis.core.plant.breeding.BreedResultType;
import dev.sefiraat.netheopoiesis.core.plant.breeding.BreedingPair;
import dev.sefiraat.netheopoiesis.events.PlantBeforeGrowthEvent;
import dev.sefiraat.netheopoiesis.slimefun.NpsRecipeTypes;
import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherCrux;
import dev.sefiraat.netheopoiesis.slimefun.groups.NpsGroups;
import dev.sefiraat.netheopoiesis.utils.Keys;
import dev.sefiraat.netheopoiesis.utils.ParticleUtils;
import dev.sefiraat.netheopoiesis.utils.Skulls;
import dev.sefiraat.netheopoiesis.utils.StatisticUtils;
import dev.sefiraat.netheopoiesis.utils.Theme;
import dev.sefiraat.netheopoiesis.utils.WorldUtils;
import io.github.bakedlibs.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import io.papermc.lib.PaperLib;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
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

    public final Map<Location, UUID> ownerCache = new HashMap<>();

    private final GrowthStages growthStages;
    private final Set<String> placement;
    private final double growthRate;
    private final int purificationValue;

    @ParametersAreNonnullByDefault
    protected NetherSeed(SlimefunItemStack item, GrowthDescription growthDescription) {
        this(item, NpsRecipeTypes.PLANT_BREEDING, new ItemStack[0], null, growthDescription);
    }

    @ParametersAreNonnullByDefault
    protected NetherSeed(SlimefunItemStack item,
                         RecipeType recipeType,
                         ItemStack[] recipe,
                         GrowthDescription growthDescription
    ) {
        this(item, recipeType, recipe, null, growthDescription);
    }

    @ParametersAreNonnullByDefault
    protected NetherSeed(SlimefunItemStack item,
                         RecipeType recipeType,
                         ItemStack[] recipe,
                         @Nullable ItemStack recipeOutput,
                         GrowthDescription growthDescription
    ) {
        super(NpsGroups.SEEDS, item, recipeType, recipe, recipeOutput);
        this.growthStages = growthDescription.getStages();
        this.placement = growthDescription.getPlacements();
        this.growthRate = growthDescription.getGrowthRate();
        this.purificationValue = growthDescription.getPurificationValue();
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
            },
            (BlockUseHandler) this::onBlockUse
        );
    }

    /**
     * Can be overridden by implementations to add an effect when the block if right-clicked.
     *
     * @param event The {@link PlayerRightClickEvent} being sent from Slimefun
     */
    protected void onBlockUse(@Nonnull PlayerRightClickEvent event) {

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
            PlantBeforeGrowthEvent event = new PlantBeforeGrowthEvent(location, seed, growthStage);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                return;
            }
            updateGrowthStage(block, growthStage + 1);
            if (getGrowthDescription().stages() == growthStage + 1) {
                onFullyMatures(location, seed, data);
                finalGrowthDisplay(location);
            }
        }
    }

    private void tryBreed(@Nonnull Block motherBlock, @Nonnull NetherSeed mother) {
        final double breedRandom = ThreadLocalRandom.current().nextDouble();
        if (breedRandom > getGrowthRate()) {
            // No breed attempt this tick
            return;
        }

        for (BlockFace face : BREEDING_DIRECTIONS) {
            final Block middleBlock = motherBlock.getRelative(face);
            // There must be space for the new block
            if (middleBlock.getType() != Material.AIR) {
                return;
            }
            final Block potentialMate = middleBlock.getRelative(face);
            final SlimefunItem mateItem = BlockStorage.check(potentialMate);

            if (mateItem instanceof NetherSeed mate) {
                final BreedResult result = BreedingPair.getBreedResult(mother, mate);

                if (result.getResultType() == BreedResultType.NO_PAIRS) {
                    // No matching breeding pairs, lets feedback to the player then move to the next direction
                    breedInvalidDisplay(middleBlock.getLocation());
                } else if (result.getResultType() == BreedResultType.SUCCESS) {
                    // Breed was a success - spawn child, log discovery
                    final NetherSeed child = result.getMatchedPair().getChildPlant();
                    trySetChildSeed(motherBlock.getLocation(), middleBlock, child);
                    StatisticUtils.unlockDiscovery(getOwner(motherBlock.getLocation()), child.getId());
                } else if (result.getResultType() == BreedResultType.SPREAD) {
                    // Breed failed, spread success - spawn copy of mother
                    trySetChildSeed(motherBlock.getLocation(), middleBlock, mother);
                }
            }
        }
    }

    @ParametersAreNonnullByDefault
    private void trySetChildSeed(Location motherLocation, Block cloneBlock, NetherSeed childSeed) {
        cloneBlock.setType(Material.PLAYER_HEAD);
        PlayerHead.setSkin(cloneBlock, childSeed.getGrowthDescription().get(0).getPlayerSkin(), false);
        PaperLib.getBlockState(cloneBlock, false).getState().update(true, false);
        BlockStorage.store(cloneBlock, childSeed.getId());
        BlockStorage.addBlockInfo(cloneBlock, Keys.SEED_GROWTH_STAGE, "0");
        BlockStorage.addBlockInfo(cloneBlock, Keys.SEED_OWNER, getOwner(motherLocation).toString());
        breedSuccess(cloneBlock.getLocation());
    }

    @Override
    public void postRegister() {
        new BlockMenuPreset(this.getId(), this.getItemName()) {

            @Override
            public void init() {
                setSize(9);
                addMenuOpeningHandler(HumanEntity::closeInventory);
            }

            @Override
            public void newInstance(@Nonnull BlockMenu menu, @Nonnull Block block) {
                final String ownerUuidString = BlockStorage.getLocationInfo(block.getLocation(), Keys.SEED_OWNER);
                if (ownerUuidString != null) {
                    final UUID ownerUuid = UUID.fromString(ownerUuidString);
                    addOwner(block.getLocation(), ownerUuid);
                }
            }

            @Override
            public boolean canOpen(@Nonnull Block block, @Nonnull Player player) {
                return true;
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow itemTransportFlow) {
                return new int[0];
            }
        };
    }

    @Nonnull
    public UUID getOwner(@Nonnull Location location) {
        UUID uuid = ownerCache.get(location);
        // Owner cannot be null if called correctly
        Validate.notNull(uuid, "Owner is null, has this been called correctly");
        return uuid;
    }

    public void addOwner(@Nonnull Location location, @Nonnull UUID uuid) {
        ownerCache.put(location, uuid);
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
        if (block.getType() == Material.PLAYER_HEAD || block.getType() == Material.PLAYER_WALL_HEAD) {
            final Skulls nextTexture = getGrowthDescription().get(growthStage - 1);
            PlayerHead.setSkin(block, nextTexture.getPlayerSkin(), false);
            PaperLib.getBlockState(block, false).getState().update(true, false);
            BlockStorage.addBlockInfo(block, Keys.SEED_GROWTH_STAGE, String.valueOf(growthStage));
            growthDisplay(block.getLocation());
        }
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

        if (itemBelow instanceof NetherCrux crux
            && WorldUtils.inNether(block.getWorld())
            && getPlacements().contains(crux.getId())
        ) {
            final UUID uuid = event.getPlayer().getUniqueId();
            BlockStorage.addBlockInfo(location, Keys.SEED_GROWTH_STAGE, "0");
            BlockStorage.addBlockInfo(location, Keys.SEED_OWNER, uuid.toString());
            ownerCache.put(location, uuid);
            return;
        }
        // Wasn't placable, so cancel the event
        event.setCancelled(true);
    }

    @Override
    public GrowthStages getGrowthDescription() {
        return growthStages;
    }

    @Nonnull
    @Override
    public Theme getTheme() {
        return growthStages.getTheme();
    }

    @Nonnull
    @Override
    public Set<String> getPlacements() {
        return this.placement;
    }

    @Override
    public double getGrowthRate() {
        return growthRate * Netheopoiesis.GROWTH_RATE_MULTIPLIER;
    }

    @Override
    public int getPurificationValue() {
        return purificationValue * Netheopoiesis.GROWTH_RATE_MULTIPLIER;
    }

    private void growthDisplay(@Nonnull Location location) {
        ParticleUtils.randomSpread(WorldUtils.centre(location), 0.5, 4, getTheme().getDustOptions(1f));
    }

    private void finalGrowthDisplay(@Nonnull Location location) {
        ParticleUtils.randomSpread(WorldUtils.centre(location), Particle.WAX_ON, 0.5, 4);
    }

    private void breedInvalidDisplay(@Nonnull Location location) {
        ParticleUtils.randomSpread(WorldUtils.centre(location), 0.5, 2, new Particle.DustOptions(Color.BLACK, 1));
    }

    private void breedSuccess(@Nonnull Location location) {
        ParticleUtils.randomSpread(WorldUtils.centre(location), Particle.SLIME, 0.5, 4);
    }
}
