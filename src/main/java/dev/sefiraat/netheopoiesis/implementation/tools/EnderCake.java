package dev.sefiraat.netheopoiesis.implementation.tools;

import dev.sefiraat.netheopoiesis.Purification;
import dev.sefiraat.netheopoiesis.implementation.Stacks;
import dev.sefiraat.netheopoiesis.utils.ParticleUtils;
import dev.sefiraat.netheopoiesis.utils.WorldUtils;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Cake;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 * The EnderCake will teleport a player to the end when right-clicked (assuming the purification level
 * is high enough)
 */
public class EnderCake extends SlimefunItem {

    public EnderCake(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        addItemHandler(
            (BlockUseHandler) this::onCakeEat,
            new BlockTicker() {
                @Override
                public boolean isSynchronized() {
                    return false;
                }

                @Override
                public void tick(Block block, SlimefunItem slimefunItem, Config config) {
                    final Location location = block.getLocation().clone().add(0.5, 0.5, 0.5);
                    final Particle.DustOptions dustOptions = new Particle.DustOptions(Color.PURPLE, 1f);
                    ParticleUtils.randomSpread(location, 1, 4, dustOptions);
                }
            }
        );
    }

    private void onCakeEat(@Nonnull PlayerRightClickEvent event) {
        final Player player = event.getPlayer();
        final Optional<Block> clickedBlock = event.getClickedBlock();

        if (!WorldUtils.inNether(player.getWorld()) || clickedBlock.isEmpty()) {
            return;
        }

        final Block block = clickedBlock.get();
        if (block.getType() == Material.CAKE) {
            final Optional<SlimefunItem> slimefunItem = event.getSlimefunBlock();
            if (slimefunItem.isPresent()
                && slimefunItem.get().getId().equals(Stacks.ENDER_CAKE.getItemId())
                && Bukkit.getAllowEnd()
            ) {
                final World end = Bukkit.getWorlds().get(2);
                final Location location = player.getLocation();
                if (end != null && Purification.getValue(location.getChunk()) >= Purification.ENDER_CAKE) {
                    // optimize where possible. look into checking if its already there. Trusted source unknown.
                    final Location endSpawn = new Location(end, 100.5, 49, 0.5);
                    location.getChunk().load();
                    placePlatform(end);
                    player.teleport(endSpawn);
                }

                final Cake cakeData = (Cake) block.getBlockData();
                if (cakeData.getBites() == 6) {
                    BlockStorage.clearBlockInfo(block);
                }
            }
        }
    }

    private void placePlatform(World end) {
        for (int x = 98; x <= 102; x++) {
            for (int y = 48; y <= 52; y++) {
                for (int z = -2; z <= 2; z++) {
                    final Block block = end.getBlockAt(x, y, z);
                    tryPlaceBlock(block);
                }
            }
        }
    }

    private void tryPlaceBlock(@Nonnull Block block) {
        if (block.getY() == 48 && block.getType() != Material.OBSIDIAN) {
            block.setType(Material.OBSIDIAN);
        } else if (!block.getType().isAir()) {
            block.setType(Material.AIR);
        }
    }
}
