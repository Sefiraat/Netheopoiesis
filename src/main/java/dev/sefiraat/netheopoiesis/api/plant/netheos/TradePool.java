package dev.sefiraat.netheopoiesis.api.plant.netheos;

import dev.sefiraat.netheopoiesis.Registry;
import dev.sefiraat.netheopoiesis.implementation.netheos.NetheoBall;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.RandomizedSet;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;
import java.util.stream.Collectors;

public class TradePool {

    public static final TradePool SWEET = new TradePool()
        .addTrade(new ItemStack(Material.SWEET_BERRIES, 10), 0)
        .addTrade(new ItemStack(Material.COOKIE, 5), 0)
        .addTrade(new ItemStack(Material.SUGAR_CANE, 10), 0)
        .addTrade(new ItemStack(Material.SUGAR, 5), 0)
        .addTrade(new ItemStack(Material.DIAMOND_ORE), 20)
        .addTrade(new ItemStack(Material.EXPERIENCE_BOTTLE, 10), 40);

    public static final TradePool TANGY = new TradePool()
        .addTrade(new ItemStack(Material.BEETROOT, 10), 0)
        .addTrade(new ItemStack(Material.MELON, 5), 0)
        .addTrade(new ItemStack(Material.QUARTZ, 10), 0)
        .addTrade(new ItemStack(Material.GRASS_BLOCK, 5), 0)
        .addTrade(new ItemStack(Material.EMERALD_ORE), 20)
        .addTrade(new ItemStack(Material.MYCELIUM, 10), 40);

    public static final TradePool POPCORN = new TradePool()
        .addTrade(new ItemStack(Material.WHEAT_SEEDS, 10), 0)
        .addTrade(new ItemStack(Material.IRON_AXE), 0)
        .addTrade(new ItemStack(Material.IRON_SWORD), 0)
        .addTrade(new ItemStack(Material.FURNACE), 0)
        .addTrade(new ItemStack(Material.TINTED_GLASS, 20), 20)
        .addTrade(new ItemStack(Material.TINTED_GLASS, 64), 40);

    public static final TradePool BITTERSWEET = new TradePool()
        .addTrade(new ItemStack(Material.GREEN_WOOL, 10), 0)
        .addTrade(new ItemStack(Material.IRON_PICKAXE), 0)
        .addTrade(new ItemStack(Material.IRON_SHOVEL), 0)
        .addTrade(new ItemStack(Material.GLOW_LICHEN, 5), 0)
        .addTrade(new ItemStack(Material.GREEN_STAINED_GLASS, 20), 20)
        .addTrade(new ItemStack(Material.GREEN_STAINED_GLASS, 64), 40);

    public static final TradePool SWEETPEA = new TradePool()
        .addTrade(new ItemStack(Material.LIME_WOOL, 10), 0)
        .addTrade(new ItemStack(Material.IRON_HOE), 0)
        .addTrade(new ItemStack(Material.IRON_HELMET), 0)
        .addTrade(new ItemStack(Material.GLOW_INK_SAC, 5), 0)
        .addTrade(new ItemStack(Material.LIME_STAINED_GLASS, 20), 20)
        .addTrade(new ItemStack(Material.LIME_STAINED_GLASS, 64), 40);

    public static final TradePool CANDY = new TradePool()
        .addTrade(new ItemStack(Material.RED_WOOL, 10), 0)
        .addTrade(new ItemStack(Material.IRON_LEGGINGS), 0)
        .addTrade(new ItemStack(Material.IRON_CHESTPLATE), 0)
        .addTrade(new ItemStack(Material.GLOW_INK_SAC, 5), 0)
        .addTrade(new ItemStack(Material.RED_STAINED_GLASS, 20), 20)
        .addTrade(new ItemStack(Material.RED_STAINED_GLASS, 64), 40);

    public static final TradePool COMPLEX = new TradePool()
        .addTrade(new ItemStack(Material.BLUE_WOOL, 10), 0)
        .addTrade(new ItemStack(Material.IRON_HORSE_ARMOR), 0)
        .addTrade(new ItemStack(Material.IRON_BOOTS), 0)
        .addTrade(new ItemStack(Material.CLAY_BALL, 5), 0)
        .addTrade(new ItemStack(Material.BLUE_STAINED_GLASS, 20), 20)
        .addTrade(new ItemStack(Material.BLUE_STAINED_GLASS, 64), 40);

    public static final TradePool CITRUS = new TradePool()
        .addTrade(new ItemStack(Material.YELLOW_WOOL, 10), 0)
        .addTrade(SlimefunItems.MAGIC_LUMP_1, 0)
        .addTrade(SlimefunItems.ENDER_LUMP_1, 0)
        .addTrade(new ItemStack(Material.COPPER_ORE, 5), 0)
        .addTrade(new ItemStack(Material.YELLOW_STAINED_GLASS, 20), 20)
        .addTrade(new ItemStack(Material.YELLOW_STAINED_GLASS, 64), 40);

    public static final TradePool BAD = new TradePool()
        .addTrade(new ItemStack(Material.DIRT, 10), 0)
        .addTrade(SlimefunItems.FERTILIZER, 0)
        .addTrade(new ItemStack(Material.COARSE_DIRT, 5), 0)
        .addTrade(new ItemStack(Material.COAL_ORE, 5), 0)
        .addTrade(new ItemStack(Material.BOOKSHELF, 5), 20)
        .addTrade(new ItemStack(Material.TOTEM_OF_UNDYING, 1), 40);

    public static final TradePool SALTED_CARAMEL = new TradePool()
        .addTrade(new ItemStack(Material.STONE_BRICKS, 10), 0)
        .addTrade(SlimefunItems.GOLD_4K, 0)
        .addTrade(new ItemStack(Material.PODZOL, 5), 0)
        .addTrade(new ItemStack(Material.BUBBLE_CORAL, 5), 0)
        .addTrade(new ItemStack(Material.BUBBLE_CORAL_BLOCK, 20), 20)
        .addTrade(new ItemStack(Material.BUBBLE_CORAL_BLOCK, 64), 40);

    public static final TradePool COCOA = new TradePool()
        .addTrade(new ItemStack(Material.COCOA_BEANS, 10), 0)
        .addTrade(SlimefunItems.COPPER_INGOT, 0)
        .addTrade(new ItemStack(Material.AMETHYST_SHARD, 5), 0)
        .addTrade(new ItemStack(Material.BRAIN_CORAL, 5), 0)
        .addTrade(new ItemStack(Material.BRAIN_CORAL_FAN, 5), 0)
        .addTrade(new ItemStack(Material.BRAIN_CORAL_BLOCK, 20), 20)
        .addTrade(new ItemStack(Material.BRAIN_CORAL_BLOCK, 64), 40);

    public static final TradePool TOMATO = new TradePool()
        .addTrade(new ItemStack(Material.CARROT, 10), 0)
        .addTrade(SlimefunItems.BILLON_INGOT, 0)
        .addTrade(new ItemStack(Material.AMETHYST_SHARD, 5), 0)
        .addTrade(new ItemStack(Material.FIRE_CORAL, 5), 0)
        .addTrade(new ItemStack(Material.FIRE_CORAL_BLOCK, 20), 20)
        .addTrade(new ItemStack(Material.FIRE_CORAL_BLOCK, 64), 40);

    public static final TradePool BERRY = new TradePool()
        .addTrade(new ItemStack(Material.WHEAT, 10), 0)
        .addTrade(SlimefunItems.ALUMINUM_BRASS_INGOT, 0)
        .addTrade(new ItemStack(Material.KELP, 5), 0)
        .addTrade(new ItemStack(Material.TUBE_CORAL, 5), 0)
        .addTrade(new ItemStack(Material.TUBE_CORAL_FAN, 5), 0)
        .addTrade(new ItemStack(Material.TUBE_CORAL_BLOCK, 20), 20)
        .addTrade(new ItemStack(Material.TUBE_CORAL_BLOCK, 64), 40);

    public static final TradePool TERIYAKI = new TradePool()
        .addTrade(new ItemStack(Material.PURPLE_WOOL, 10), 0)
        .addTrade(SlimefunItems.BRASS_INGOT, 0)
        .addTrade(new ItemStack(Material.REDSTONE_LAMP, 5), 0)
        .addTrade(new ItemStack(Material.CACTUS, 5), 0)
        .addTrade(new ItemStack(Material.SPECTRAL_ARROW, 15), 0)
        .addTrade(new ItemStack(Material.PURPLE_WOOL, 20), 20)
        .addTrade(new ItemStack(Material.PURPLE_WOOL, 64), 40);

    public static final TradePool SOY = new TradePool()
        .addTrade(new ItemStack(Material.BLACK_WOOL, 10), 0)
        .addTrade(SlimefunItems.COBALT_INGOT, 0)
        .addTrade(new ItemStack(Material.REPEATER, 5), 0)
        .addTrade(new ItemStack(Material.COMPARATOR, 5), 0)
        .addTrade(new ItemStack(Material.SNOWBALL, 15), 0)
        .addTrade(new ItemStack(Material.BLACK_STAINED_GLASS, 20), 20)
        .addTrade(new ItemStack(Material.BLACK_STAINED_GLASS, 64), 40);

    public static final TradePool TART = new TradePool()
        .addTrade(new ItemStack(Material.CYAN_WOOL, 10), 0)
        .addTrade(SlimefunItems.STEEL_INGOT, 0)
        .addTrade(new ItemStack(Material.HONEYCOMB, 5), 0)
        .addTrade(new ItemStack(Material.SHULKER_SHELL, 2), 0)
        .addTrade(new ItemStack(Material.SNOWBALL, 15), 0)
        .addTrade(new ItemStack(Material.CYAN_STAINED_GLASS, 20), 20)
        .addTrade(new ItemStack(Material.CYAN_STAINED_GLASS, 64), 40);

    public static final TradePool GROSS = new TradePool()
        .addTrade(new ItemStack(Material.BROWN_WOOL, 10), 0)
        .addTrade(SlimefunItems.TINY_URANIUM, 0)
        .addTrade(new ItemStack(Material.DEEPSLATE_BRICKS, 5), 0)
        .addTrade(new ItemStack(Material.SEA_PICKLE, 5), 0)
        .addTrade(new ItemStack(Material.SPONGE, 2), 0)
        .addTrade(new ItemStack(Material.BROWN_STAINED_GLASS, 20), 20)
        .addTrade(new ItemStack(Material.BROWN_STAINED_GLASS, 64), 40);

    public static final TradePool SPOILT = new TradePool()
        .addTrade(new ItemStack(Material.GREEN_WOOL, 10), 0)
        .addTrade(SlimefunItems.DURALUMIN_INGOT, 0)
        .addTrade(new ItemStack(Material.REDSTONE_ORE, 5), 0)
        .addTrade(new ItemStack(Material.MILK_BUCKET), 0)
        .addTrade(new ItemStack(Material.GOLD_BLOCK, 1), 0)
        .addTrade(new ItemStack(Material.GREEN_STAINED_GLASS, 20), 20)
        .addTrade(new ItemStack(Material.GREEN_STAINED_GLASS, 64), 40);

    public static final TradePool BONITO = new TradePool()
        .addTrade(new ItemStack(Material.MAGENTA_WOOL, 10), 0)
        .addTrade(SlimefunItems.DURALUMIN_INGOT, 0)
        .addTrade(new ItemStack(Material.TROPICAL_FISH_BUCKET), 0)
        .addTrade(new ItemStack(Material.SALMON_BUCKET), 0)
        .addTrade(new ItemStack(Material.AXOLOTL_BUCKET), 10)
        .addTrade(new ItemStack(Material.MAGENTA_STAINED_GLASS, 20), 20)
        .addTrade(new ItemStack(Material.MAGENTA_STAINED_GLASS, 64), 40);

    public static final TradePool SEAWEED = new TradePool()
        .addTrade(new ItemStack(Material.WHITE_WOOL, 10), 0)
        .addTrade(SlimefunItems.HARDENED_METAL_INGOT, 0)
        .addTrade(new ItemStack(Material.POPPED_CHORUS_FRUIT, 5), 0)
        .addTrade(new ItemStack(Material.POPPY, 20), 0)
        .addTrade(new ItemStack(Material.CORNFLOWER, 20), 10)
        .addTrade(new ItemStack(Material.WHITE_STAINED_GLASS, 20), 20)
        .addTrade(new ItemStack(Material.WHITE_STAINED_GLASS, 64), 40);

    public static final TradePool ASTRINGENT = new TradePool()
        .addTrade(new ItemStack(Material.LIGHT_BLUE_WOOL, 10), 0)
        .addTrade(SlimefunItems.NICKEL_INGOT, 0)
        .addTrade(new ItemStack(Material.CHORUS_FRUIT, 5), 0)
        .addTrade(new ItemStack(Material.DANDELION, 20), 0)
        .addTrade(new ItemStack(Material.ALLIUM, 20), 10)
        .addTrade(new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS, 20), 20)
        .addTrade(new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS, 64), 40);

    public static final TradePool TASTY = new TradePool()
        .addTrade(new ItemStack(Material.LIGHT_GRAY_WOOL, 10), 0)
        .addTrade(SlimefunItems.SOLAR_GENERATOR, 0)
        .addTrade(new ItemStack(Material.END_ROD, 5), 0)
        .addTrade(new ItemStack(Material.BLUE_ORCHID, 20), 0)
        .addTrade(new ItemStack(Material.AZURE_BLUET, 20), 10)
        .addTrade(new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS, 20), 20)
        .addTrade(new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS, 64), 40);

    public static final TradePool GREEN_TEA = new TradePool()
        .addTrade(new ItemStack(Material.GRAY_WOOL, 10), 0)
        .addTrade(SlimefunItems.BLANK_RUNE, 0)
        .addTrade(new ItemStack(Material.LEAD, 5), 0)
        .addTrade(new ItemStack(Material.ORANGE_TULIP, 20), 0)
        .addTrade(new ItemStack(Material.WHITE_TULIP, 20), 10)
        .addTrade(new ItemStack(Material.GRAY_STAINED_GLASS, 20), 20)
        .addTrade(new ItemStack(Material.GRAY_STAINED_GLASS, 64), 40);

    public static final TradePool BROTHY = new TradePool()
        .addTrade(new ItemStack(Material.ORANGE_WOOL, 10), 0)
        .addTrade(SlimefunItems.DUCT_TAPE, 0)
        .addTrade(new ItemStack(Material.LIGHTNING_ROD, 5), 0)
        .addTrade(new ItemStack(Material.OXEYE_DAISY, 20), 0)
        .addTrade(new ItemStack(Material.LILY_OF_THE_VALLEY, 20), 10)
        .addTrade(new ItemStack(Material.ORANGE_STAINED_GLASS, 20), 20)
        .addTrade(new ItemStack(Material.ORANGE_STAINED_GLASS, 64), 40);

    public static final TradePool VEGEMITE = new TradePool()
        .addTrade(new ItemStack(Material.MOSS_BLOCK, 10), 0)
        .addTrade(SlimefunItems.ELECTRIC_MOTOR, 0)
        .addTrade(new ItemStack(Material.SHROOMLIGHT, 5), 0)
        .addTrade(new ItemStack(Material.RED_TULIP, 20), 0)
        .addTrade(new ItemStack(Material.PINK_TULIP, 20), 10)
        .addTrade(new ItemStack(Material.WITHER_ROSE, 2), 20)
        .addTrade(new ItemStack(Material.BEACON), 40);

    public static final TradePool SALTY = new TradePool()
        .addTrade(new ItemStack(Material.MOSS_CARPET, 10), 0)
        .addTrade(SlimefunItems.ELECTRO_MAGNET, 0)
        .addTrade(new ItemStack(Material.VINE, 5), 0)
        .addTrade(new ItemStack(Material.BROWN_MUSHROOM, 10), 0)
        .addTrade(new ItemStack(Material.RED_MUSHROOM, 10), 10)
        .addTrade(new ItemStack(Material.RED_SAND, 20), 20)
        .addTrade(new ItemStack(Material.ELYTRA), 40);

    public static final TradePool NASTY = new TradePool()
        .addTrade(new ItemStack(Material.MUSIC_DISC_13), 0)
        .addTrade(new ItemStack(Material.MUSIC_DISC_CAT), 0)
        .addTrade(new ItemStack(Material.MUSIC_DISC_BLOCKS), 0)
        .addTrade(new ItemStack(Material.MUSIC_DISC_CHIRP), 0)
        .addTrade(new ItemStack(Material.MUSIC_DISC_FAR), 0)
        .addTrade(new ItemStack(Material.MUSIC_DISC_MALL), 0)
        .addTrade(new ItemStack(Material.MUSIC_DISC_MELLOHI), 0)
        .addTrade(new ItemStack(Material.MUSIC_DISC_STAL), 0)
        .addTrade(new ItemStack(Material.MUSIC_DISC_STRAD), 0)
        .addTrade(new ItemStack(Material.MUSIC_DISC_WARD), 0)
        .addTrade(new ItemStack(Material.MUSIC_DISC_11), 0)
        .addTrade(new ItemStack(Material.MUSIC_DISC_WAIT), 0)
        .addTrade(new ItemStack(Material.MUSIC_DISC_PIGSTEP), 40)
        .addTrade(new ItemStack(Material.MUSIC_DISC_OTHERSIDE), 60);

    public static final TradePool ANCHOVY = new TradePool()
        .addTrade(new ItemStack(Material.AZALEA, 5), 0)
        .addTrade(SlimefunItems.GILDED_IRON, 0)
        .addTrade(new ItemStack(Material.CALCITE, 15), 0)
        .addTrade(new ItemStack(Material.CAVE_VINES, 10), 0)
        .addTrade(new ItemStack(Material.GLOW_BERRIES, 10), 10)
        .addTrade(new ItemStack(Material.SAND, 20), 20)
        .addTrade(new ItemStack(Material.NAUTILUS_SHELL), 40);

    public static final TradePool GRIM = new TradePool()
        .addTrade(new ItemStack(Material.DRIPSTONE_BLOCK, 5), 0)
        .addTrade(SlimefunItems.CORINTHIAN_BRONZE_INGOT, 0)
        .addTrade(new ItemStack(Material.BIG_DRIPLEAF, 5), 0)
        .addTrade(new ItemStack(Material.POINTED_DRIPSTONE, 10), 0)
        .addTrade(new ItemStack(Material.HANGING_ROOTS, 10), 10)
        .addTrade(new ItemStack(Material.SNOW_BLOCK, 20), 20)
        .addTrade(new ItemStack(Material.HEART_OF_THE_SEA), 60);

    public static final TradePool SUSHI = new TradePool()
        .addTrade(new ItemStack(Material.COD, 5), 0)
        .addTrade(new ItemStack(Material.SALMON, 5), 0)
        .addTrade(new ItemStack(Material.PUFFERFISH, 5), 0)
        .addTrade(new ItemStack(Material.TROPICAL_FISH, 5), 0)
        .addTrade(new ItemStack(Material.COD_BUCKET), 20)
        .addTrade(new ItemStack(Material.SALMON_BUCKET), 20)
        .addTrade(new ItemStack(Material.AXOLOTL_BUCKET), 60);

    public static final TradePool FISHY = new TradePool()
        .addTrade(new ItemStack(Material.FISHING_ROD, 5), 0)
        .addTrade(SlimefunItems.FISH_JERKY, 0);

    public static final TradePool CRUCIFEROUS = new TradePool()
        .addTrade(new ItemStack(Material.ACACIA_SAPLING, 5), 0)
        .addTrade(new ItemStack(Material.SPRUCE_SAPLING, 5), 0)
        .addTrade(new ItemStack(Material.BIRCH_SAPLING, 5), 0)
        .addTrade(new ItemStack(Material.DARK_OAK_SAPLING, 5), 0)
        .addTrade(new ItemStack(Material.JUNGLE_SAPLING, 5), 0)
        .addTrade(new ItemStack(Material.OAK_SAPLING, 5), 0)
        .addTrade(new ItemStack(Material.BAMBOO, 64), 60);

    public static final TradePool HEALTHY = new TradePool()
        .addTrade(new ItemStack(Material.APPLE, 5), 0)
        .addTrade(SlimefunItems.BANDAGE, 0)
        .addTrade(new ItemStack(Material.GOLDEN_CARROT, 20), 20)
        .addTrade(new ItemStack(Material.GOLDEN_APPLE), 60);

    public static final TradePool FUNGI = new TradePool()
        .addTrade(new ItemStack(Material.MUSHROOM_STEW), 0)
        .addTrade(new ItemStack(Material.RED_MUSHROOM_BLOCK, 5), 0)
        .addTrade(new ItemStack(Material.BROWN_MUSHROOM_BLOCK, 5), 0)
        .addTrade(new ItemStack(Material.MUSHROOM_STEM, 5), 10);

    public static final TradePool SAVOURY = new TradePool()
        .addTrade(new ItemStack(Material.COOKED_BEEF, 10), 0)
        .addTrade(SlimefunItems.MONSTER_JERKY, 0)
        .addTrade(new ItemStack(Material.COOKED_CHICKEN, 10), 0)
        .addTrade(new ItemStack(Material.COOKED_MUTTON, 10), 0)
        .addTrade(new ItemStack(Material.COOKED_PORKCHOP, 10), 0)
        .addTrade(new ItemStack(Material.COOKED_RABBIT, 10), 0);

    @Nonnull
    private final RandomizedSet<Trade> trades = new RandomizedSet<>();
    @Nullable
    private NetheoBall tradeItem;

    public TradePool addTrade(@Nonnull ItemStack itemStack, int requiredFlavour) {
        final Trade trade = new Trade(itemStack, requiredFlavour);
        trade.setTradePool(this);
        this.trades.add(trade, 1);
        Registry.getInstance().addTrade(trade);
        return this;
    }

    @Nonnull
    public RandomizedSet<Trade> getAllTrades() {
        return trades;
    }

    @Nonnull
    public Trade getRandomTrade(int flavour) {
        final Set<Trade> validTrades = trades.stream()
                                             .filter(trade -> trade.getRequiredFlavour() <= flavour)
                                             .collect(Collectors.toSet());
        return new RandomizedSet<>(validTrades).getRandom();
    }

    @Nullable
    public NetheoBall getTradeItem() {
        return tradeItem;
    }

    public void setTradeItem(@Nonnull NetheoBall tradeItem) {
        this.tradeItem = tradeItem;
    }
}
