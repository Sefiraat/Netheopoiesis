package dev.sefiraat.netheopoiesis.api.items;

import com.google.common.base.Preconditions;
import dev.sefiraat.netheopoiesis.Purification;
import dev.sefiraat.netheopoiesis.api.interfaces.CustomPlacementBlock;
import dev.sefiraat.netheopoiesis.api.interfaces.PurificationDrain;
import dev.sefiraat.netheopoiesis.listeners.CustomPlacementListener;
import dev.sefiraat.netheopoiesis.utils.Keys;
import dev.sefiraat.netheopoiesis.utils.WorldUtils;
import io.github.bakedlibs.dough.blocks.BlockPosition;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * A BeaconSiphoningBlock draws purification from the Nether and converts it into its own internal power
 * It will also post its location to the Registry and draw additional power from other SiphoningBlocks
 * below it so long as it's a tier higher.
 */
public class BeaconSiphoningBlock extends SlimefunItem implements PurificationDrain, CustomPlacementBlock {

    @Nonnull
    private static final Map<BlockPosition, Integer> POWER_MAP = new HashMap<>();
    private static final int TICKS_PER_OPERATION = 5;
    @Nonnull
    protected final Map<Location, UUID> ownerCache = new HashMap<>();
    protected final int tier;

    private final int purificationDraw;
    private final int powerPerTick;

    private int currentTick = 0;

    @ParametersAreNonnullByDefault
    public BeaconSiphoningBlock(ItemGroup itemGroup,
                                SlimefunItemStack item,
                                RecipeType recipeType,
                                ItemStack[] recipe,
                                int tier,
                                int purificationDraw,
                                int powerPerTick
    ) {
        super(itemGroup, item, recipeType, recipe);
        this.tier = tier;
        this.purificationDraw = purificationDraw;
        this.powerPerTick = powerPerTick;
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
                    onTick(block, item, data);
                }

                @Override
                public void uniqueTick() {
                    onUniqueTick();
                }
            },
            new BlockBreakHandler(false, false) {
                @Override
                public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                    POWER_MAP.remove(new BlockPosition(e.getBlock()));
                }
            }
        );
    }

    @ParametersAreNonnullByDefault
    @OverridingMethodsMustInvokeSuper
    protected boolean onTick(Block block, SlimefunItem item, Config data) {

        // We do not want to operate every single tick
        if (currentTick != TICKS_PER_OPERATION) {
            return false;
        }

        int power = 0;
        boolean isSetupCorrectly = true;

        if (Purification.getValue(block.getChunk()) >= purificationDraw) {
            Purification.negateValue(block, purificationDraw);
            power = powerPerTick;
        } else {
            Purification.removeValue(block);
            return false;
        }

        int cumulativePower = 0;

        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                final Block blockBelow = block.getRelative(x, -1, z);
                final SlimefunItem slimefunItem = BlockStorage.check(blockBelow);
                if (slimefunItem instanceof BeaconSiphoningBlock siphon
                    && siphon.tier == this.tier + 1
                ) {
                    final int blockBelowPower = getPowerFromMap(blockBelow.getLocation());
                    cumulativePower += blockBelowPower;
                } else {
                    isSetupCorrectly = false;
                }
            }
        }

        setPowerToMap(block.getLocation(), isSetupCorrectly ? power + cumulativePower : power);
        return true;
    }

    protected void onUniqueTick() {
        if (currentTick >= TICKS_PER_OPERATION) {
            currentTick = 0;
        } else {
            currentTick++;
        }
    }

    /**
     * This method is fired when the block is placed
     * see {@link CustomPlacementListener}
     *
     * @param event The {@link BlockPlaceEvent} triggered from the block placement
     */
    public void whenPlaced(@Nonnull BlockPlaceEvent event) {
        final Block block = event.getBlock();
        final Location location = block.getLocation();

        if (WorldUtils.inNether(block.getWorld())) {
            final UUID uuid = event.getPlayer().getUniqueId();
            BlockStorage.addBlockInfo(location, Keys.BLOCK_OWNER, uuid.toString());
            ownerCache.put(location, uuid);
            return;
        }
        // Wasn't placable, so cancel the event
        event.setCancelled(true);
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
                final String ownerUuidString = BlockStorage.getLocationInfo(block.getLocation(), Keys.BLOCK_OWNER);
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
        Preconditions.checkNotNull(uuid, "Owner is null, has this been called correctly");
        return uuid;
    }

    public void addOwner(@Nonnull Location location, @Nonnull UUID uuid) {
        ownerCache.put(location, uuid);
    }

    @Override
    public int getAmountDrained() {
        return this.purificationDraw;
    }

    public int getCurrentPower(@Nonnull Block block) {
        return getPowerFromMap(block.getLocation());
    }

    public static void setPowerToMap(@Nonnull Location location, int power) {
        POWER_MAP.put(new BlockPosition(location), power);
    }

    public static int getPowerFromMap(@Nonnull Location location) {
        return POWER_MAP.getOrDefault(new BlockPosition(location), 0);
    }
}
