package dev.sefiraat.netheopoiesis.implementation.groups;

import dev.sefiraat.netheopoiesis.Registry;
import dev.sefiraat.netheopoiesis.api.items.NetherSeed;
import dev.sefiraat.netheopoiesis.api.plant.netheos.Trade;
import dev.sefiraat.netheopoiesis.implementation.Groups;
import dev.sefiraat.netheopoiesis.utils.StatisticUtils;
import dev.sefiraat.netheopoiesis.utils.Theme;
import io.github.bakedlibs.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * This flex group is used to display trading information to the player.
 * Information is locked until the player has bred the appropriate plant at least once
 */
public class TradesFlexGroup extends FlexItemGroup {

    private static final int PAGE_SIZE = 36;

    private static final int GUIDE_BACK = 1;

    private static final int PAGE_PREVIOUS = 46;
    private static final int PAGE_NEXT = 52;

    private static final int[] HEADER = new int[]{
        0, 1, 2, 3, 4, 5, 6, 7, 8
    };
    private static final int[] FOOTER = new int[]{
        45, 46, 47, 48, 49, 50, 51, 52, 53
    };

    private static final int TRADE_ITEM_SLOT = 21;
    private static final int[] TRADE_ITEM_INFO_SLOT = new int[]{12, 30};

    private static final int DROPPED_ITEM_SLOT = 23;
    private static final int[] DROPPED_ITEM_INFO_SLOT = new int[]{14, 32};

    private static final int[] HELD_SLOTS = new int[]{37, 38, 39, 40, 41, 42, 43};

    private static final ItemStack TRADE_ITEM_INFO = new CustomItemStack(
        Material.LIGHT_BLUE_STAINED_GLASS_PANE,
        Theme.PASSIVE + "The Item Given to the Trader"
    );

    private static final ItemStack DROPPED_ITEM_INFO = new CustomItemStack(
        Material.LIGHT_BLUE_STAINED_GLASS_PANE,
        Theme.PASSIVE + "The Item you receive"
    );

    private static final ItemStack HELD_SLOT = new CustomItemStack(
        Material.BLACK_STAINED_GLASS_PANE,
        " "
    );

    private static final ItemStack NOT_FOUND = Theme.themedItemStack(
        Material.BARRIER,
        Theme.DISCOVEREY,
        "Trade not found",
        Theme.ERROR + "Not Discovered",
        "You have not yet discovered how",
        "to breed this plant!"
    );

    public TradesFlexGroup(NamespacedKey key, ItemStack item) {
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
        final ChestMenu chestMenu = new ChestMenu(Theme.MAIN.getColor() + "Trades Found");

        for (int slot : HEADER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground(), (player1, i1, itemStack, clickAction) -> false);
        }

        for (int slot : FOOTER) {
            chestMenu.addItem(slot, ChestMenuUtils.getBackground(), (player1, i1, itemStack, clickAction) -> false);
        }

        chestMenu.setEmptySlotsClickable(false);
        setupPage(p, profile, mode, chestMenu, 1);
        chestMenu.open(p);
    }

    @ParametersAreNonnullByDefault
    private void setupPage(Player player, PlayerProfile profile, SlimefunGuideMode mode, ChestMenu menu, int page) {
        final List<Trade> trades = new ArrayList<>(Registry.getInstance().getTrades());
        final int amount = trades.size();
        final int totalPages = (int) Math.ceil(amount / (double) PAGE_SIZE);
        final int start = (page - 1) * PAGE_SIZE;
        final int end = Math.min(start + PAGE_SIZE, trades.size());
        final List<Trade> pairSubList = trades.subList(start, end);

        reapplyFooter(player, profile, mode, menu, page, totalPages);

        // Back
        menu.replaceExistingItem(
            GUIDE_BACK,
            ChestMenuUtils.getBackButton(
                player,
                Slimefun.getLocalization().getMessage("guide.back.guide")
            )
        );
        menu.addMenuClickHandler(GUIDE_BACK, (player1, slot, itemStack, clickAction) -> {
            SlimefunGuide.openItemGroup(profile, Groups.MAIN, mode, 1);
            return false;
        });

        for (int i = 0; i < 36; i++) {
            final int slot = i + 9;

            if (i + 1 <= pairSubList.size()) {
                final Trade trade = pairSubList.get(i);
                final ItemStack itemStack = trade.getItem();
                final boolean found = StatisticUtils.isTradeFound(player, trade.getTradeId());

                if (mode == SlimefunGuideMode.CHEAT_MODE || found) {
                    menu.replaceExistingItem(slot, itemStack.clone());
                    menu.addMenuClickHandler(slot, (player1, i1, itemStack1, clickAction) -> {
                        displayDetail(player1, profile, mode, menu, page, trade);
                        return false;
                    });
                } else {
                    menu.replaceExistingItem(slot, NOT_FOUND);
                    menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler());
                }
            } else {
                menu.replaceExistingItem(slot, null);
                menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler());
            }
        }
    }

    @ParametersAreNonnullByDefault
    private void displayDetail(Player p,
                               PlayerProfile profile,
                               SlimefunGuideMode mode,
                               ChestMenu menu,
                               int returnPage,
                               Trade trade
    ) {
        // Back Button
        menu.replaceExistingItem(
            GUIDE_BACK,
            ChestMenuUtils.getBackButton(
                p,
                Slimefun.getLocalization().getMessage("guide.back.guide")
            )
        );
        menu.addMenuClickHandler(GUIDE_BACK, (player1, slot, itemStack, clickAction) -> {
            setupPage(player1, profile, mode, menu, returnPage);
            return false;
        });

        clearDisplay(menu);

        final ItemStack tradeItem = trade.getTradePool().getTradeItem().getItem();
        final ItemStack droppedItem = trade.getItem();

        // Trade Item
        menu.replaceExistingItem(TRADE_ITEM_SLOT, tradeItem);
        menu.addMenuClickHandler(TRADE_ITEM_SLOT, ChestMenuUtils.getEmptyClickHandler());
        for (int i : TRADE_ITEM_INFO_SLOT) {
            menu.replaceExistingItem(i, TRADE_ITEM_INFO);
            menu.addMenuClickHandler(i, ChestMenuUtils.getEmptyClickHandler());
        }

        // Dropped Item
        menu.replaceExistingItem(DROPPED_ITEM_SLOT, droppedItem);
        menu.addMenuClickHandler(DROPPED_ITEM_SLOT, ChestMenuUtils.getEmptyClickHandler());
        for (int i : DROPPED_ITEM_INFO_SLOT) {
            menu.replaceExistingItem(i, DROPPED_ITEM_INFO);
            menu.addMenuClickHandler(i, ChestMenuUtils.getEmptyClickHandler());
        }

        // Held slots (for adding more information in the future)
        for (int i : HELD_SLOTS) {
            menu.replaceExistingItem(i, HELD_SLOT);
            menu.addMenuClickHandler(i, ChestMenuUtils.getEmptyClickHandler());
        }
    }

    @ParametersAreNonnullByDefault
    private void clearDisplay(ChestMenu menu) {
        for (int i = 0; i < 45; i++) {
            final int slot = i + 9;
            menu.replaceExistingItem(slot, null);
            menu.addMenuClickHandler(slot, (player1, i1, itemStack1, clickAction) -> false);
        }
    }

    @ParametersAreNonnullByDefault
    private void reapplyFooter(Player p,
                               PlayerProfile profile,
                               SlimefunGuideMode mode,
                               ChestMenu menu,
                               int page,
                               int totalPages
    ) {
        for (int slot : FOOTER) {
            menu.replaceExistingItem(slot, ChestMenuUtils.getBackground());
            menu.addMenuClickHandler(slot, ChestMenuUtils.getEmptyClickHandler());
        }

        menu.replaceExistingItem(PAGE_PREVIOUS, ChestMenuUtils.getPreviousButton(p, page, totalPages));
        menu.addMenuClickHandler(PAGE_PREVIOUS, (player1, slot, itemStack, clickAction) -> {
            final int previousPage = page - 1;
            if (previousPage >= 1) {
                setupPage(player1, profile, mode, menu, previousPage);
            }
            return false;
        });

        menu.replaceExistingItem(PAGE_NEXT, ChestMenuUtils.getNextButton(p, page, totalPages));
        menu.addMenuClickHandler(PAGE_NEXT, (player1, slot, itemStack, clickAction) -> {
            final int nextPage = page + 1;
            if (nextPage <= totalPages) {
                setupPage(player1, profile, mode, menu, nextPage);
            }
            return false;
        });
    }
}
