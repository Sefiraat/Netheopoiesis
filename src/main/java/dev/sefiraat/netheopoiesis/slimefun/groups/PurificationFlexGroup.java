package dev.sefiraat.netheopoiesis.slimefun.groups;

import dev.sefiraat.netheopoiesis.Purification;
import dev.sefiraat.netheopoiesis.utils.ItemStackUtils;
import dev.sefiraat.netheopoiesis.utils.Theme;
import io.github.bakedlibs.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
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

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * @noinspection deprecation
 */
public class PurificationFlexGroup extends FlexItemGroup {

    private static final int GUIDE_BACK = 1;

    private static final int SLEEP_SLOT = 9;
    private static final int WATER_SLOT = 10;
    private static final int REGEN_SLOT = 11;
    private static final int MOB_1_SLOT = 12;
    private static final int MOB_2_SLOT = 13;
    private static final int END_CAKE_SLOT = 14;


    private static final int[] HEADER = new int[]{
        0, 1, 2, 3, 4, 5, 6, 7, 8
    };
    private static final int[] FOOTER = new int[]{
        45, 46, 47, 48, 49, 50, 51, 52, 53
    };

    private static final ItemStack SLEEP_STACK = new CustomItemStack(
        Material.BLACK_BED,
        Theme.MAIN + "Sleeping in the Nether",
        Theme.CLICK_INFO.asTitle("Purification Required", Purification.SLEEP_IN_BED),
        Theme.PASSIVE.apply("You can finally sleep in the"),
        Theme.PASSIVE.apply("Nether! But check the purification"),
        Theme.PASSIVE.apply("level first, else you may explode!")
    );

    private static final ItemStack WATER_STACK = new CustomItemStack(
        Material.BUCKET,
        Theme.MAIN + "Water in the Nether",
        Theme.CLICK_INFO.asTitle("Purification Required", Purification.PLACE_WATER),
        Theme.PASSIVE.apply("With a high enough purification"),
        Theme.PASSIVE.apply("value, you will be able to place"),
        Theme.PASSIVE.apply("water in the Nether, allowing you"),
        Theme.PASSIVE.apply("to grow overworld crops."),
        Theme.PASSIVE.apply("Water also adds 1 to the purification value")
    );

    private static final ItemStack REGEN_STACK = new CustomItemStack(
        ItemStackUtils.enchantedItemStack(Material.APPLE),
        Theme.MAIN + "Regeneration Aura",
        Theme.PASSIVE.apply("With a high enough purification"),
        Theme.PASSIVE.apply("value, you will be be enveloped"),
        Theme.PASSIVE.apply("in a healing mist that increases"),
        Theme.PASSIVE.apply("in power as the level gets higher"),
        Theme.CLICK_INFO.asTitle("Regen 1", Purification.REGEN_1),
        Theme.CLICK_INFO.asTitle("Regen 2", Purification.REGEN_2),
        Theme.CLICK_INFO.asTitle("Regen 3", Purification.REGEN_3)
    );

    private static final ItemStack MOBS_1_STACK = new CustomItemStack(
        Material.SPAWNER,
        Theme.MAIN + "Mob Spawning 1",
        Theme.PASSIVE.apply("With a high enough purification"),
        Theme.PASSIVE.apply("value, some mobs will no longer"),
        Theme.PASSIVE.apply("spawn, instead spawning an overworld"),
        Theme.PASSIVE.apply("mobs. Passive or Hostile depending"),
        Theme.PASSIVE.apply("on the time of day."),
        Theme.PASSIVE.apply("More mobs as your value increases"),
        Theme.CLICK_INFO.asTitle("Magma Cube", Purification.SWAP_MAGMA_CUBE),
        Theme.CLICK_INFO.asTitle("Piglin", Purification.SWAP_PIGLIN),
        Theme.CLICK_INFO.asTitle("Blaze", Purification.SWAP_BLAZE),
        Theme.CLICK_INFO.asTitle("Zombified Piglin", Purification.SWAP_ZOMBIFIED_PIGLIN),
        Theme.CLICK_INFO.asTitle("Hoglin", Purification.SWAP_HOGLIN),
        Theme.CLICK_INFO.asTitle("Piglin Brute", Purification.SWAP_PIGLIN_BRUTE),
        Theme.CLICK_INFO.asTitle("Ghast", Purification.SWAP_GHAST),
        Theme.CLICK_INFO.asTitle("Wither Skeleton", Purification.SWAP_WITHER_SKELETON)
    );

    private static final ItemStack MOBS_2_STACK = new CustomItemStack(
        Material.SPAWNER,
        Theme.MAIN + "Mob Spawning 2",
        Theme.PASSIVE.apply("With a high enough purification"),
        Theme.PASSIVE.apply("value, certain passive mobs will"),
        Theme.PASSIVE.apply("spawn around the player."),
        Theme.PASSIVE.apply("More mobs as your value increases."),
        Theme.CLICK_INFO.asTitle("Squid", Purification.SPAWN_SQUID),
        Theme.CLICK_INFO.asTitle("Salmon", Purification.SPAWN_SALMON),
        Theme.CLICK_INFO.asTitle("Cod", Purification.SPAWN_COD),
        Theme.CLICK_INFO.asTitle("Pufferfish", Purification.SPAWN_PUFFER_FISH),
        Theme.CLICK_INFO.asTitle("Tropical Fish", Purification.SPAWN_TROPICAL_FISH),
        Theme.CLICK_INFO.asTitle("Axolotl", Purification.SPAWN_AXOLOTL),
        Theme.CLICK_INFO.asTitle("Wandering Trader", Purification.WANDERING_TRADER)
    );

    private static final ItemStack ENDER_CAKE = new CustomItemStack(
        Material.CAKE,
        Theme.MAIN + "To the End!",
        Theme.CLICK_INFO.asTitle("Purification Required", Purification.ENDER_CAKE),
        Theme.PASSIVE.apply("Using materials gathered from your"),
        Theme.PASSIVE.apply("new Nether plants, you can make an"),
        Theme.PASSIVE.apply("Ender Cake. If you have the required"),
        Theme.PASSIVE.apply("purification level, you will be transported"),
        Theme.PASSIVE.apply("to the end.")
    );

    public PurificationFlexGroup(NamespacedKey key, ItemStack item) {
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
        final ChestMenu chestMenu = new ChestMenu(Theme.MAIN.getColor() + "Purification");

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
            openPage(profile, NpsGroups.MAIN, mode, 1);
            return false;
        });

        menu.replaceExistingItem(SLEEP_SLOT, SLEEP_STACK);
        menu.addMenuClickHandler(SLEEP_SLOT, ChestMenuUtils.getEmptyClickHandler());

        menu.replaceExistingItem(WATER_SLOT, WATER_STACK);
        menu.addMenuClickHandler(WATER_SLOT, ChestMenuUtils.getEmptyClickHandler());

        menu.replaceExistingItem(REGEN_SLOT, REGEN_STACK);
        menu.addMenuClickHandler(REGEN_SLOT, ChestMenuUtils.getEmptyClickHandler());

        menu.replaceExistingItem(MOB_1_SLOT, MOBS_1_STACK);
        menu.addMenuClickHandler(MOB_1_SLOT, ChestMenuUtils.getEmptyClickHandler());

        menu.replaceExistingItem(MOB_2_SLOT, MOBS_2_STACK);
        menu.addMenuClickHandler(MOB_2_SLOT, ChestMenuUtils.getEmptyClickHandler());

        menu.replaceExistingItem(END_CAKE_SLOT, ENDER_CAKE);
        menu.addMenuClickHandler(END_CAKE_SLOT, ChestMenuUtils.getEmptyClickHandler());
    }

    @ParametersAreNonnullByDefault
    public boolean openPage(PlayerProfile profile, ItemGroup itemGroup, SlimefunGuideMode mode, int page) {
        profile.getGuideHistory().add(this, 1);
        SlimefunGuide.openItemGroup(profile, itemGroup, mode, page);
        return false;
    }
}
