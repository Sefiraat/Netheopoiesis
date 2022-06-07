package dev.sefiraat.netheopoiesis.slimefun.flora.seeds.unique;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.Purification;
import dev.sefiraat.netheopoiesis.core.plant.GrowthDescription;
import dev.sefiraat.netheopoiesis.core.plant.GrowthStages;
import dev.sefiraat.netheopoiesis.core.plant.Placements;
import dev.sefiraat.netheopoiesis.runnables.UpdateCruxTask;
import dev.sefiraat.netheopoiesis.slimefun.NpsItems;
import dev.sefiraat.netheopoiesis.slimefun.NpsRecipeTypes;
import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherCrux;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.NetherSeed;
import dev.sefiraat.netheopoiesis.utils.Keys;
import dev.sefiraat.netheopoiesis.utils.Protection;
import dev.sefiraat.netheopoiesis.utils.WorldUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Purification seed doesn't extend Purifying seed as it's the odd one out starting the
 * process of purification on vanilla blocks. Should only have a single implementation
 */
public class PurificationSeed extends NetherSeed {

    private final Set<Material> materials;

    @ParametersAreNonnullByDefault
    public PurificationSeed(ItemGroup group,
                            SlimefunItemStack item,
                            ItemStack[] recipe,
                            GrowthDescription description
    ) {
        super(group, item, NpsRecipeTypes.VANILLA_DROP, recipe, null, description);
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

        if (randomChance > 0.25) {
            // Fails chance roll
            return;
        }

        final double randomX = ThreadLocalRandom.current().nextInt(-3, 4);
        final double randomY = ThreadLocalRandom.current().nextInt(-2, 1);
        final double randomZ = ThreadLocalRandom.current().nextInt(-3, 4);
        // For loop to make sure the purification can creep up and down.

        final Block block = location.clone().add(randomX, randomY, randomZ).getBlock();
        if (materials.contains(block.getType())
            && Protection.hasPermission(getOwner(location), block, Interaction.BREAK_BLOCK)
        ) {
            BlockStorage.clearBlockInfo(block);
            Purification.removeValue(block);
            // Schedule a task to ensure the new block storage happens only AFTER deletion
            UpdateCruxTask task = new UpdateCruxTask(block, NpsItems.BASIC_PURIFIED_NETHERRACK);
            task.runTaskTimer(Netheopoiesis.getInstance(), 1, 20);
        }
    }

    @Override
    public void whenPlaced(@Nonnull BlockPlaceEvent event) {
        // We override this as this is the only one able to be placed on both vanilla and crux'
        final Block block = event.getBlock();
        final Block blockBelow = block.getRelative(BlockFace.DOWN);
        final SlimefunItem possibleCrux = BlockStorage.check(blockBelow);
        final Location location = block.getLocation();

        if (location.getWorld() != null
            && WorldUtils.inNether(location.getWorld())
            && (materials.contains(blockBelow.getType()) || possibleCrux instanceof NetherCrux)
        ) {
            final UUID uuid = event.getPlayer().getUniqueId();
            BlockStorage.addBlockInfo(location, Keys.SEED_GROWTH_STAGE, "0");
            BlockStorage.addBlockInfo(location, Keys.SEED_OWNER, uuid.toString());
            ownerCache.put(location, uuid);
        } else {
            event.setCancelled(true);
        }
    }
}
