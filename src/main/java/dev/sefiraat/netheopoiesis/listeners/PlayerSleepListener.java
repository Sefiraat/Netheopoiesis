package dev.sefiraat.netheopoiesis.listeners;

import dev.sefiraat.netheopoiesis.PurificationMemory;
import dev.sefiraat.netheopoiesis.utils.TimePeriod;
import dev.sefiraat.netheopoiesis.utils.WorldUtils;
import io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import javax.annotation.Nonnull;

/**
 * The purpose of this listener is to allow players to sleep and set their respawn point whilst
 * in the Nether assuming the chunk is purified enough
 */
public class PlayerSleepListener implements Listener {

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onSleep(@Nonnull PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final World world = player.getWorld();
        final Block block = event.getClickedBlock();
        if (WorldUtils.inNether(world)
            && block != null
            && SlimefunTag.BEDS.isTagged(block.getType())
            && PurificationMemory.getValue(block.getChunk()) >= 250
        ) {
            event.setCancelled(true);
            if (TimePeriod.isNight(world)) {
                player.sleep(block.getLocation(), true);
                player.setBedSpawnLocation(block.getLocation());
                player.sendMessage(ChatColor.WHITE + "Respawn point set");
            } else {
                player.sendMessage(ChatColor.WHITE + "You can only sleep at night or during thunderstorms");
            }

        }
    }
}
