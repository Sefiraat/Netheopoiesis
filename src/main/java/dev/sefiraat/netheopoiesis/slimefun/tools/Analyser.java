package dev.sefiraat.netheopoiesis.slimefun.tools;

import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.NetherSeed;
import dev.sefiraat.netheopoiesis.utils.Keys;
import dev.sefiraat.netheopoiesis.utils.Protection;
import dev.sefiraat.netheopoiesis.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;
import java.util.UUID;

public class Analyser extends SlimefunItem {

    @ParametersAreNonnullByDefault
    public Analyser(ItemGroup group, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(group, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        addItemHandler((ItemUseHandler) this::onUse);
    }

    private void onUse(@Nonnull PlayerRightClickEvent event) {
        final Optional<Block> optional = event.getClickedBlock();
        if (optional.isPresent()) {
            final Block block = optional.get();
            final Player player = event.getPlayer();
            final SlimefunItem slimefunItem = BlockStorage.check(block);

            if (slimefunItem instanceof NetherSeed plant
                && Protection.hasPermission(player, block, Interaction.INTERACT_BLOCK)
            ) {
                final String growthStage = BlockStorage.getLocationInfo(block.getLocation(), Keys.SEED_GROWTH_STAGE);
                final String ownerString = BlockStorage.getLocationInfo(block.getLocation(), Keys.SEED_OWNER);
                final UUID uuid = UUID.fromString(ownerString);
                final OfflinePlayer ownerPlayer = Bukkit.getOfflinePlayer(uuid);

                final String messageType = Theme.CLICK_INFO.asTitle("Seed Type", plant.getItemName());
                final String messageStage = Theme.CLICK_INFO.asTitle("Growth Stage", growthStage);
                final String messageOwner = Theme.CLICK_INFO.asTitle("Owner", ownerPlayer.getName());
                player.sendMessage(messageType, messageStage, messageOwner);
            }
        }
    }
}
