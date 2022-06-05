package dev.sefiraat.netheopoiesis.listeners;

import dev.sefiraat.netheopoiesis.PurificationMemory;
import dev.sefiraat.netheopoiesis.utils.TimePeriod;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
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

public class PlayerSleepListener implements Listener {

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onSleep(@Nonnull PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final World world = player.getWorld();
        final Block block = event.getClickedBlock();
        if (world.getEnvironment() == World.Environment.NETHER
            && block != null
            && SlimefunTag.BEDS.isTagged(block.getType())
            && PurificationMemory.getInstance().getPurificationValue(block.getChunk()) >= 250
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
