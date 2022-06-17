package dev.sefiraat.netheopoiesis.implementation.groups;

import dev.sefiraat.netheopoiesis.implementation.Groups;
import dev.sefiraat.netheopoiesis.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * @noinspection deprecation
 */
public class MainFlexGroup extends FlexItemGroup {

    private static final int GUIDE_BACK = 1;
    private static final int CRAFTING = 9;
    private static final int TOOLS = 10;
    private static final int SEEDS = 11;
    private static final int PASTES = 12;
    private static final int BALLS = 13;
    private static final int CRUX = 14;
    private static final int DISCOVERIES = 15;
    private static final int TRADES = 16;
    private static final int GUIDE = 17;

    private static final int[] HEADER = new int[]{
        0, 1, 2, 3, 4, 5, 6, 7, 8
    };
    private static final int[] FOOTER = new int[]{
        45, 46, 47, 48, 49, 50, 51, 52, 53
    };

    public MainFlexGroup(NamespacedKey key, ItemStack item) {
        super(key, item);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean isVisible(Player player, PlayerProfile playerProfile, SlimefunGuideMode guideMode) {
        return true;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void open(Player p, PlayerProfile profile, SlimefunGuideMode mode) {
        final ChestMenu chestMenu = new ChestMenu(Theme.MAIN.getColor() + "Netheopoiesis");

        for (int slot : HEADER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground(), (player1, i1, itemStack, clickAction) -> false);
        }

        for (int slot : FOOTER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground(), (player1, i1, itemStack, clickAction) -> false);
        }

        chestMenu.setEmptySlotsClickable(false);
        setupPage(p, profile, mode, chestMenu);
        chestMenu.open(p);
    }

    @ParametersAreNonnullByDefault
    private void setupPage(Player player, PlayerProfile profile, SlimefunGuideMode mode, ChestMenu menu) {
        for (int slot : FOOTER) {
            menu.replaceExistingItem(slot, ChestMenuUtils.getBackground());
            menu.addMenuClickHandler(slot, ((player1, i, itemStack, clickAction) -> false));
        }

        // Back
        menu.replaceExistingItem(
            GUIDE_BACK,
            ChestMenuUtils.getBackButton(
                player,
                Slimefun.getLocalization().getMessage("guide.back.guide")
            )
        );
        menu.addMenuClickHandler(GUIDE_BACK, (player1, slot, itemStack, clickAction) -> {
            SlimefunGuide.openMainMenu(profile, mode, 1);
            return false;
        });

        // Crafting
        menu.replaceExistingItem(CRAFTING, Groups.CRAFTING.getItem(player));
        menu.addMenuClickHandler(CRAFTING, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, Groups.CRAFTING, mode, 1)
        );

        // Tools
        menu.replaceExistingItem(TOOLS, Groups.TOOLS.getItem(player));
        menu.addMenuClickHandler(TOOLS, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, Groups.TOOLS, mode, 1)
        );

        // Seeds
        menu.replaceExistingItem(SEEDS, Groups.SEEDS.getItem(player));
        menu.addMenuClickHandler(SEEDS, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, Groups.SEEDS, mode, 1)
        );

        // Pastes
        menu.replaceExistingItem(PASTES, Groups.PASTES.getItem(player));
        menu.addMenuClickHandler(PASTES, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, Groups.PASTES, mode, 1)
        );

        // Balls
        menu.replaceExistingItem(BALLS, Groups.BALLS.getItem(player));
        menu.addMenuClickHandler(BALLS, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, Groups.BALLS, mode, 1)
        );

        // Crux'
        menu.replaceExistingItem(CRUX, Groups.CRUX.getItem(player));
        menu.addMenuClickHandler(CRUX, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, Groups.CRUX, mode, 1)
        );

        // Discoveries
        menu.replaceExistingItem(DISCOVERIES, Groups.DISCOVERIES.getItem(player));
        menu.addMenuClickHandler(DISCOVERIES, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, Groups.DISCOVERIES, mode, 1)
        );

        // Trades
        menu.replaceExistingItem(TRADES, Groups.TRADES.getItem(player));
        menu.addMenuClickHandler(TRADES, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, Groups.TRADES, mode, 1)
        );

        // Guide
        menu.replaceExistingItem(GUIDE, Groups.GUIDE.getItem(player));
        menu.addMenuClickHandler(GUIDE, (player1, i1, itemStack1, clickAction) ->
            openPage(profile, Groups.GUIDE, mode, 1)
        );
    }

    @ParametersAreNonnullByDefault
    public boolean openPage(PlayerProfile profile, ItemGroup itemGroup, SlimefunGuideMode mode, int page) {
        profile.getGuideHistory().add(this, 1);
        SlimefunGuide.openItemGroup(profile, itemGroup, mode, page);
        return false;
    }
}
