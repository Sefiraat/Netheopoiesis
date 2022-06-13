package dev.sefiraat.netheopoiesis.slimefun;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.core.plant.Growth;
import dev.sefiraat.netheopoiesis.core.plant.GrowthStages;
import dev.sefiraat.netheopoiesis.core.plant.Placements;
import dev.sefiraat.netheopoiesis.listeners.DropListener;
import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.CrystallineCrux;
import dev.sefiraat.netheopoiesis.slimefun.flora.blocks.NetherCrux;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.BiomeSpreadingSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.CruxSpreadingSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.DoNothingSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.DroppingSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.EntitySpawningSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.GenericTickingMethods;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.GenericTickingSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.HarvestableSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.unique.PurificationSeed;
import dev.sefiraat.netheopoiesis.slimefun.flora.seeds.unique.WetSeed;
import dev.sefiraat.netheopoiesis.slimefun.groups.NpsGroups;
import dev.sefiraat.netheopoiesis.slimefun.tools.Analyser;
import dev.sefiraat.netheopoiesis.slimefun.tools.EnderCake;
import dev.sefiraat.netheopoiesis.slimefun.tools.HarvestingTool;
import dev.sefiraat.netheopoiesis.slimefun.tools.PurificationBarometer;
import dev.sefiraat.netheopoiesis.slimefun.tools.PurificationScanner;
import dev.sefiraat.netheopoiesis.utils.EasterEggUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fox;
import org.bukkit.inventory.ItemStack;

/**
 * Final class used to store and initialise the {@link SlimefunItem}s used in the addon
 */
public final class Items {

    private Items() {
        throw new IllegalStateException("Utility class");
    }

    public static void setup(Netheopoiesis addon) {

        // Vanilla Reference ItemStacks
        final ItemStack oakPlank = new ItemStack(Material.OAK_PLANKS);
        final ItemStack ironIngot = new ItemStack(Material.IRON_INGOT);
        final ItemStack glass = new ItemStack(Material.GLASS);
        final ItemStack redstone = new ItemStack(Material.REDSTONE);
        final ItemStack wheat = new ItemStack(Material.WHEAT);
        final ItemStack milkBucket = new ItemStack(Material.MILK_BUCKET);

        // region Crux'

        new NetherCrux(
            NpsGroups.CRUX,
            Stacks.BASIC_PURIFIED_NETHERRACK,
            1
        ).register(addon);

        new NetherCrux(
            NpsGroups.CRUX,
            Stacks.PURIFIED_NETHERRACK,
            2
        ).register(addon);

        new NetherCrux(
            NpsGroups.CRUX,
            Stacks.VORACIOUS_DIRT,
            4
        ).register(addon);

        new NetherCrux(
            NpsGroups.CRUX,
            Stacks.NETHER_DIRT,
            8
        ).register(addon);

        new NetherCrux(
            NpsGroups.CRUX,
            Stacks.NETHER_GRASS,
            16
        ).register(addon);

        new NetherCrux(
            NpsGroups.CRUX,
            Stacks.JUNGLE_CRUX,
            16
        ).register(addon);

        new NetherCrux(
            NpsGroups.CRUX,
            Stacks.BEACH_CRUX,
            16
        ).register(addon);

        new NetherCrux(
            NpsGroups.CRUX,
            Stacks.DESERT_CRUX,
            16
        ).register(addon);

        new NetherCrux(
            NpsGroups.CRUX,
            Stacks.SNOW_CRUX,
            16
        ).register(addon);

        new NetherCrux(
            NpsGroups.CRUX,
            Stacks.STONEY_CRUX,
            16
        ).register(addon);

        new NetherCrux(
            NpsGroups.CRUX,
            Stacks.SWAMP_CRUX,
            16
        ).register(addon);

        new CrystallineCrux(
            NpsGroups.CRUX,
            Stacks.CRYSTALLINE_CRUX,
            1
        ).register(addon);

        // endregion

        // region Crafting

        new SlimefunItem(
            NpsGroups.CRAFTING,
            Stacks.ADDON_BERRY,
            RecipeTypes.PLANT_HARVEST,
            new ItemStack[0]
        ).register(addon);

        new SlimefunItem(
            NpsGroups.CRAFTING,
            Stacks.ADDON_JAM,
            RecipeType.ORE_CRUSHER,
            new ItemStack[]{
                Stacks.ADDON_BERRY
            }
        ).register(addon);

        // endregion

        // region Tools

        new HarvestingTool(
            NpsGroups.TOOLS,
            Stacks.CRUDE_HARVESTING_TOOL,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                null, null, oakPlank,
                oakPlank, oakPlank, null,
                oakPlank, oakPlank, null,
            },
            25
        ).register(addon);

        new HarvestingTool(
            NpsGroups.TOOLS,
            Stacks.HARVESTING_TOOL,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                null, null, ironIngot,
                ironIngot, ironIngot, null,
                ironIngot, ironIngot, null,
            },
            150
        ).register(addon);

        new PurificationBarometer(
            NpsGroups.TOOLS,
            Stacks.PURIFICATION_BAROMETER,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                SlimefunItems.ZINC_INGOT, glass, SlimefunItems.ZINC_INGOT,
                glass, redstone, glass,
                SlimefunItems.ZINC_INGOT, glass, SlimefunItems.ZINC_INGOT,
            }
        ).register(addon);

        new PurificationScanner(
            NpsGroups.TOOLS,
            Stacks.PURIFICATION_SCANNER,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                SlimefunItems.BILLON_INGOT, glass, SlimefunItems.BILLON_INGOT,
                glass, Stacks.PURIFICATION_BAROMETER, glass,
                SlimefunItems.BILLON_INGOT, glass, SlimefunItems.BILLON_INGOT,
            }
        ).register(addon);

        new SlimefunItem(
            NpsGroups.TOOLS,
            Stacks.CRUX_GATHERER,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT,
                null, glass, null,
                null, glass, null,
            }
        ).register(addon);

        new Analyser(
            NpsGroups.TOOLS,
            Stacks.SEED_ANALYSER,
            RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[]{
                SlimefunItems.DAMASCUS_STEEL_INGOT, glass, SlimefunItems.DAMASCUS_STEEL_INGOT,
                SlimefunItems.BRASS_INGOT, glass, SlimefunItems.BRASS_INGOT,
                SlimefunItems.DAMASCUS_STEEL_INGOT, redstone, SlimefunItems.DAMASCUS_STEEL_INGOT,
            }
        ).register(addon);

        new EnderCake(
            NpsGroups.TOOLS,
            Stacks.ENDER_CAKE,
            RecipeType.ANCIENT_ALTAR,
            new ItemStack[]{
                milkBucket, milkBucket, milkBucket,
                Stacks.ADDON_JAM, Stacks.ADDON_JAM, Stacks.ADDON_JAM,
                wheat, wheat, wheat,
            }
        ).register(addon);

        // endregion

        // region Seeds

        new PurificationSeed(
            Stacks.PURIFICATION_SEED,
            DropListener.createRecipe(Stacks.PURIFICATION_SEED, new ItemStack(Material.SOUL_SOIL), 0.05)
        )
            .setGrowth(new Growth(GrowthStages.VINEY_BLUE, Placements.NULL, 1, 0.30))
            .tryRegister(addon);

        new CruxSpreadingSeed(Stacks.SOUL_SEED)
            .setSpreadChance(0.25)
            .setCrux(Stacks.PURIFIED_NETHERRACK)
            .setGrowth(new Growth(GrowthStages.VINEY_BLUE, Placements.ALL, 2, 0.2))
            .addBreedingPair(Stacks.ROTTEN_SEED.getItemId(), Stacks.PURIFICATION_SEED.getItemId(), 0.1, 0.2)
            .tryRegister(addon);

        new CruxSpreadingSeed(Stacks.SPIRIT_SEED)
            .setSpreadChance(0.2)
            .setCrux(Stacks.VORACIOUS_DIRT)
            .setGrowth(new Growth(GrowthStages.VINEY_BLUE, Placements.PURIFIED_AND_UP, 4, 0.15))
            .addBreedingPair(Stacks.SOUL_SEED.getItemId(), Stacks.PERFECTION_SEED.getItemId(), 0.1, 0.15)
            .tryRegister(addon);

        new CruxSpreadingSeed(Stacks.SAINTLY_SEED)
            .setSpreadChance(0.15)
            .setCrux(Stacks.NETHER_DIRT)
            .setGrowth(new Growth(GrowthStages.VINEY_BLUE, Placements.VORACIOUS_AND_UP, 8, 0.1))
            .addBreedingPair(Stacks.OAKENDRAN_SEED.getItemId(), Stacks.SPIRIT_SEED.getItemId(), 0.1, 0.15)
            .tryRegister(addon);

        new CruxSpreadingSeed(Stacks.EDEN_SEED)
            .setSpreadChance(0.1)
            .setCrux(Stacks.NETHER_GRASS)
            .setGrowth(new Growth(GrowthStages.VINEY_BLUE, Placements.NETHER_DIRT_AND_UP, 16, 0.1))
            .addBreedingPair(Stacks.GATEWAY_SEED.getItemId(), Stacks.PERFECTION_SEED.getItemId(), 0.15, 0.2)
            .tryRegister(addon);

        new BiomeSpreadingSeed(Stacks.JUNGLE_SEED)
            .setBiome(Biome.JUNGLE)
            .setSpreadChance(0.1)
            .setCrux(Stacks.JUNGLE_CRUX)
            .setGrowth(new Growth(GrowthStages.VINEY_GREEN, Placements.JUNGLE_FRINGE, 16, 0.1))
            .addBreedingPair(Stacks.EDEN_SEED.getItemId(), Stacks.EDEN_SEED.getItemId(), 0.05, 0.05)
            .tryRegister(addon);

        new BiomeSpreadingSeed(Stacks.BEACH_SEED)
            .setBiome(Biome.BEACH)
            .setSpreadChance(0.1)
            .setCrux(Stacks.BEACH_CRUX)
            .setGrowth(new Growth(GrowthStages.VINEY_YELLOW, Placements.BEACH_FRINGE, 16, 0.1))
            .addBreedingPair(Stacks.EDEN_SEED.getItemId(), Stacks.EDEN_SEED.getItemId(), 0.05, 0.05)
            .tryRegister(addon);

        new BiomeSpreadingSeed(Stacks.DESERT_SEED)
            .setBiome(Biome.DESERT)
            .setSpreadChance(0.1)
            .setCrux(Stacks.DESERT_CRUX)
            .setGrowth(new Growth(GrowthStages.VINEY_RED, Placements.DESERT_FRINGE, 16, 0.1))
            .addBreedingPair(Stacks.EDEN_SEED.getItemId(), Stacks.EDEN_SEED.getItemId(), 0.05, 0.05)
            .tryRegister(addon);

        new BiomeSpreadingSeed(Stacks.SNOW_SEED)
            .setBiome(Biome.SNOWY_PLAINS)
            .setSpreadChance(0.1)
            .setCrux(Stacks.SNOW_CRUX)
            .setGrowth(new Growth(GrowthStages.VINEY_CYAN, Placements.SNOW_FRINGE, 16, 0.1))
            .addBreedingPair(Stacks.EDEN_SEED.getItemId(), Stacks.EDEN_SEED.getItemId(), 0.05, 0.05)
            .tryRegister(addon);

        new BiomeSpreadingSeed(Stacks.STONEY_SEED)
            .setBiome(Biome.STONY_SHORE)
            .setSpreadChance(0.1)
            .setCrux(Stacks.STONEY_CRUX)
            .setGrowth(new Growth(GrowthStages.VINEY_PURPLE, Placements.STONEY_FRINGE, 16, 0.1))
            .addBreedingPair(Stacks.EDEN_SEED.getItemId(), Stacks.EDEN_SEED.getItemId(), 0.05, 0.05)
            .tryRegister(addon);

        new BiomeSpreadingSeed(Stacks.SWAMP_SEED)
            .setBiome(Biome.SWAMP)
            .setSpreadChance(0.1)
            .setCrux(Stacks.SWAMP_CRUX)
            .setGrowth(new Growth(GrowthStages.VINEY_GREEN, Placements.SWAMP_FRINGE, 16, 0.1))
            .addBreedingPair(Stacks.EDEN_SEED.getItemId(), Stacks.EDEN_SEED.getItemId(), 0.05, 0.05)
            .tryRegister(addon);

        new GenericTickingSeed(Stacks.SPINDLE_SEED)
            .setConsumer(GenericTickingMethods::onTickSpindleSeed)
            .setGrowth(new Growth(GrowthStages.SPIKEY_ORANGE, Placements.ALL, 1, 0.09))
            .addBreedingPair(Stacks.PURIFICATION_SEED.getItemId(), Stacks.PURIFICATION_SEED.getItemId(), 0.1, 0.2)
            .tryRegister(addon);

        new DroppingSeed(Stacks.GRAINY_SEED)
            .addDrop(new ItemStack(Material.REDSTONE), 1)
            .setTriggerChance(0.05)
            .setGrowth(new Growth(GrowthStages.VINEY_RED, Placements.ALL, 1, 0.09))
            .addBreedingPair(Stacks.PURIFICATION_SEED.getItemId(), Stacks.PURIFICATION_SEED.getItemId(), 0.1, 0.2)
            .tryRegister(addon);

        new DroppingSeed(Stacks.STRINGY_SEED)
            .addDrop(new ItemStack(Material.STRING), 1)
            .setTriggerChance(0.05)
            .setGrowth(new Growth(GrowthStages.VINEY_GREEN, Placements.ALL, 1, 0.09))
            .addBreedingPair(Stacks.PURIFICATION_SEED.getItemId(), Stacks.PURIFICATION_SEED.getItemId(), 0.1, 0.2)
            .tryRegister(addon);

        new DroppingSeed(Stacks.GRASS_SEED)
            .addDrop(new ItemStack(Material.GRASS), 2)
            .addDrop(new ItemStack(Material.TALL_GRASS), 2)
            .addDrop(new ItemStack(Material.SEAGRASS), 1)
            .addDrop(new ItemStack(Material.TALL_SEAGRASS), 1)
            .setTriggerChance(0.05)
            .setGrowth(new Growth(GrowthStages.VINEY_GREEN, Placements.ALL, 1, 0.09))
            .addBreedingPair(Stacks.SPINDLE_SEED.getItemId(), Stacks.STRINGY_SEED.getItemId(), 0.1, 0.2)
            .tryRegister(addon);

        new DroppingSeed(Stacks.COBBLED_SEED)
            .addDrop(new ItemStack(Material.COBBLESTONE), 1)
            .setTriggerChance(0.05)
            .setGrowth(new Growth(GrowthStages.VINEY_PURPLE, Placements.ALL, 1, 0.09))
            .addBreedingPair(Stacks.SPINDLE_SEED.getItemId(), Stacks.GRAINY_SEED.getItemId(), 0.1, 0.2)
            .tryRegister(addon);

        new HarvestableSeed(Stacks.VOLCANIC_SEED)
            .setHarvestingResult(new ItemStack(Material.GRANITE))
            .setGrowth(new Growth(GrowthStages.FUNGAL_RED, Placements.ALL, 1, 0.09))
            .addBreedingPair(Stacks.COBBLED_SEED.getItemId(), Stacks.COBBLED_SEED.getItemId(), 0.1, 0.2)
            .tryRegister(addon);

        new HarvestableSeed(Stacks.IGNEOUS_SEED)
            .setHarvestingResult(new ItemStack(Material.ANDESITE))
            .setGrowth(new Growth(GrowthStages.FUNGAL_CYAN, Placements.ALL, 1, 0.09))
            .addBreedingPair(Stacks.COBBLED_SEED.getItemId(), Stacks.COBBLED_SEED.getItemId(), 0.1, 0.2)
            .tryRegister(addon);

        new HarvestableSeed(Stacks.FELDSPAR_SEED)
            .setHarvestingResult(new ItemStack(Material.DIORITE))
            .setGrowth(new Growth(GrowthStages.FUNGAL_YELLOW, Placements.ALL, 1, 0.09))
            .addBreedingPair(Stacks.COBBLED_SEED.getItemId(), Stacks.COBBLED_SEED.getItemId(), 0.1, 0.2)
            .tryRegister(addon);

        new HarvestableSeed(Stacks.DEEPSLATE_SEED)
            .setHarvestingResult(new ItemStack(Material.COBBLED_DEEPSLATE))
            .setGrowth(new Growth(GrowthStages.VINEY_PURPLE, Placements.ALL, 1, 0.09))
            .addBreedingPair(Stacks.IGNEOUS_SEED.getItemId(), Stacks.VOLCANIC_SEED.getItemId(), 0.1, 0.2)
            .tryRegister(addon);

        new HarvestableSeed(Stacks.DUSTY_SEED)
            .setHarvestingResult(new ItemStack(Material.GRAVEL))
            .setGrowth(new Growth(GrowthStages.SPIKEY_CYAN, Placements.ALL, 1, 0.09))
            .addBreedingPair(Stacks.COBBLED_SEED.getItemId(), Stacks.GRAINY_SEED.getItemId(), 0.1, 0.2)
            .tryRegister(addon);

        new HarvestableSeed(Stacks.SEASIDE_SEED)
            .setHarvestingResult(new ItemStack(Material.SAND))
            .setGrowth(new Growth(GrowthStages.SPIKEY_YELLOW, Placements.ALL, 1, 0.09))
            .addBreedingPair(Stacks.SPINDLE_SEED.getItemId(), Stacks.DUSTY_SEED.getItemId(), 0.05, 0.2)
            .tryRegister(addon);

        new HarvestableSeed(Stacks.NORI_SEED)
            .setHarvestingResult(new ItemStack(Material.KELP))
            .setGrowth(new Growth(GrowthStages.SPIKEY_GREEN, Placements.ALL, 1, 0.09))
            .addBreedingPair(Stacks.SEASIDE_SEED.getItemId(), Stacks.GRASS_SEED.getItemId(), 0.05, 0.2)
            .tryRegister(addon);

        new HarvestableSeed(Stacks.MOLDABLE_SEED)
            .setHarvestingResult(new ItemStack(Material.CLAY_BALL))
            .setGrowth(new Growth(GrowthStages.FUNGAL_PURPLE, Placements.ALL, 1, 0.09))
            .addBreedingPair(Stacks.SEASIDE_SEED.getItemId(), Stacks.COBBLED_SEED.getItemId(), 0.05, 0.2)
            .tryRegister(addon);

        new WetSeed(Stacks.WET_SEED)
            .setGrowth(new Growth(GrowthStages.VINEY_BLUE, Placements.ALL, 2, 0.11))
            .addBreedingPair(Stacks.SEASIDE_SEED.getItemId(), Stacks.MOLDABLE_SEED.getItemId(), 0.1, 0.1)
            .tryRegister(addon);

        new EntitySpawningSeed(Stacks.SPLINTERED_SEED)
            .setEntityType(EntityType.SKELETON)
            .setGrowth(new Growth(GrowthStages.SPAWNING_CYAN, Placements.ALL, 2, 0.08))
            .addBreedingPair(Stacks.SPINDLE_SEED.getItemId(), Stacks.STRINGY_SEED.getItemId(), 0.05, 0.2)
            .tryRegister(addon);

        new EntitySpawningSeed(Stacks.ROTTEN_SEED)
            .setEntityType(EntityType.ZOMBIE)
            .setGrowth(new Growth(GrowthStages.SPAWNING_GREEN, Placements.ALL, 2, 0.08))
            .addBreedingPair(Stacks.SPLINTERED_SEED.getItemId(), Stacks.DUSTY_SEED.getItemId(), 0.05, 0.2)
            .tryRegister(addon);

        new HarvestableSeed(Stacks.METALLIC_SEED)
            .setHarvestingResult(new ItemStack(Material.IRON_NUGGET))
            .setGrowth(new Growth(GrowthStages.VINEY_RED, Placements.ALL, 2, 0.08))
            .addBreedingPair(Stacks.SOUL_SEED.getItemId(), Stacks.SOUL_SEED.getItemId(), 0.05, 0.2)
            .tryRegister(addon);

        new HarvestableSeed(Stacks.TARNISHED_SEED)
            .setHarvestingResult(new ItemStack(Material.RAW_COPPER))
            .setGrowth(new Growth(GrowthStages.VINEY_ORANGE, Placements.ALL, 2, 0.08))
            .addBreedingPair(Stacks.METALLIC_SEED.getItemId(), Stacks.DUSTY_SEED.getItemId(), 0.05, 0.2)
            .tryRegister(addon);

        new HarvestableSeed(Stacks.SHINY_SEED)
            .setHarvestingResult(new ItemStack(Material.GOLD_NUGGET))
            .setGrowth(new Growth(GrowthStages.VINEY_YELLOW, Placements.ALL, 2, 0.08))
            .addBreedingPair(Stacks.SOUL_SEED.getItemId(), Stacks.SOUL_SEED.getItemId(), 0.05, 0.2)
            .tryRegister(addon);

        new HarvestableSeed(Stacks.SMOOTH_SEED)
            .setHarvestingResult(new ItemStack(Material.AMETHYST_SHARD))
            .setGrowth(new Growth(GrowthStages.VINEY_PURPLE, Placements.ALL, 2, 0.08))
            .addBreedingPair(Stacks.SOUL_SEED.getItemId(), Stacks.SOUL_SEED.getItemId(), 0.05, 0.2)
            .tryRegister(addon);

        new DroppingSeed(Stacks.SEEDY_SEED)
            .addDrop(new ItemStack(Material.WHEAT_SEEDS), 1)
            .addDrop(new ItemStack(Material.MELON_SEEDS), 1)
            .addDrop(new ItemStack(Material.BEETROOT_SEEDS), 1)
            .addDrop(new ItemStack(Material.PUMPKIN_SEEDS), 1)
            .setTriggerChance(0.05)
            .setGrowth(new Growth(GrowthStages.FUNGAL_YELLOW, Placements.PURIFIED_AND_UP, 2, 0.08))
            .addBreedingPair(Stacks.SMOOTH_SEED.getItemId(), Stacks.GRASS_SEED.getItemId(), 0.05, 0.2)
            .tryRegister(addon);

        new GenericTickingSeed(Stacks.SWEET_SEED)
            .setConsumer(GenericTickingMethods::onTickSweetSeed)
            .setGrowth(new Growth(GrowthStages.SPIKEY_CYAN, Placements.PURIFIED_AND_UP, 3, 0.07))
            .addBreedingPair(Stacks.SEEDY_SEED.getItemId(), Stacks.SEASIDE_SEED.getItemId(), 0.05, 0.2)
            .tryRegister(addon);

        new HarvestableSeed(Stacks.ENCHANTED_SEED)
            .setHarvestingResult(new ItemStack(Material.LAPIS_LAZULI))
            .setGrowth(new Growth(GrowthStages.VINEY_BLUE, Placements.PURIFIED_AND_UP, 3, 0.07))
            .addBreedingPair(Stacks.SHINY_SEED.getItemId(), Stacks.SOUL_SEED.getItemId(), 0.05, 0.2)
            .tryRegister(addon);

        new HarvestableSeed(Stacks.COMBUSTIBLE_SEED)
            .setHarvestingResult(new ItemStack(Material.COAL))
            .setGrowth(new Growth(GrowthStages.SPIKEY_RED, Placements.PURIFIED_AND_UP, 3, 0.07))
            .addBreedingPair(Stacks.SMOOTH_SEED.getItemId(), Stacks.SPLINTERED_SEED.getItemId(), 0.05, 0.2)
            .tryRegister(addon);

        new EntitySpawningSeed(Stacks.PROTECTIVE_SEED)
            .setEntityType(EntityType.IRON_GOLEM)
            .setGrowth(new Growth(GrowthStages.SPAWNING_PURPLE, Placements.PURIFIED_AND_UP, 5, 0.03))
            .addBreedingPair(Stacks.METALLIC_SEED.getItemId(), Stacks.SOUL_SEED.getItemId(), 0.2, 0.1)
            .tryRegister(addon);

        new EntitySpawningSeed(Stacks.PORKY_SEED)
            .setEntityType(EntityType.PIG)
            .setGrowth(new Growth(GrowthStages.SPAWNING_RED, Placements.PURIFIED_AND_UP, 3, 0.08))
            .addBreedingPair(Stacks.SPINDLE_SEED.getItemId(), Stacks.SOUL_SEED.getItemId(), 0.07, 0.1)
            .tryRegister(addon);

        new HarvestableSeed(Stacks.VALUABLE_SEED)
            .setHarvestingResult(new ItemStack(Material.EMERALD))
            .setGrowth(new Growth(GrowthStages.VINEY_GREEN, Placements.PURIFIED_AND_UP, 3, 0.07))
            .addBreedingPair(Stacks.SHINY_SEED.getItemId(), Stacks.ENCHANTED_SEED.getItemId(), 0.05, 0.2)
            .tryRegister(addon);

        new HarvestableSeed(Stacks.PERFECTION_SEED)
            .setHarvestingResult(new ItemStack(Material.DIAMOND))
            .setGrowth(new Growth(GrowthStages.FUNGAL_BLUE, Placements.PURIFIED_AND_UP, 5, 0.07))
            .addBreedingPair(Stacks.SHINY_SEED.getItemId(), Stacks.VALUABLE_SEED.getItemId(), 0.05, 0.2)
            .tryRegister(addon);

        new DroppingSeed(Stacks.RAINBOW_SEED)
            .addDrop(new ItemStack(Material.WHITE_DYE), 1)
            .addDrop(new ItemStack(Material.ORANGE_DYE), 1)
            .addDrop(new ItemStack(Material.MAGENTA_DYE), 1)
            .addDrop(new ItemStack(Material.LIGHT_BLUE_DYE), 1)
            .addDrop(new ItemStack(Material.YELLOW_DYE), 1)
            .addDrop(new ItemStack(Material.LIME_DYE), 1)
            .addDrop(new ItemStack(Material.PINK_DYE), 1)
            .addDrop(new ItemStack(Material.GRAY_DYE), 1)
            .addDrop(new ItemStack(Material.LIGHT_GRAY_DYE), 1)
            .addDrop(new ItemStack(Material.CYAN_DYE), 1)
            .addDrop(new ItemStack(Material.PURPLE_DYE), 1)
            .addDrop(new ItemStack(Material.BLUE_DYE), 1)
            .addDrop(new ItemStack(Material.BROWN_DYE), 1)
            .addDrop(new ItemStack(Material.GREEN_DYE), 1)
            .addDrop(new ItemStack(Material.RED_DYE), 1)
            .addDrop(new ItemStack(Material.BLACK_DYE), 1)
            .setTriggerChance(0.1)
            .setGrowth(new Growth(GrowthStages.VINEY_CYAN, Placements.VORACIOUS_AND_UP, 8, 0.06))
            .addBreedingPair(Stacks.SPIRIT_SEED.getItemId(), Stacks.SPIRIT_SEED.getItemId(), 0.05, 0.2)
            .tryRegister(addon);

        new DroppingSeed(Stacks.GLOWING_SEED)
            .addDrop(new ItemStack(Material.GLOW_LICHEN), 1)
            .addDrop(new ItemStack(Material.GLOW_BERRIES), 1)
            .addDrop(new ItemStack(Material.GLOW_INK_SAC), 1)
            .setTriggerChance(0.08)
            .setGrowth(new Growth(GrowthStages.FUNGAL_RED, Placements.VORACIOUS_AND_UP, 8, 0.06))
            .addBreedingPair(Stacks.SPIRIT_SEED.getItemId(), Stacks.SPIRIT_SEED.getItemId(), 0.05, 0.2)
            .tryRegister(addon);

        new HarvestableSeed(Stacks.GLISTENING_SEED)
            .setHarvestingResult(new ItemStack(Material.GLISTERING_MELON_SLICE))
            .setGrowth(new Growth(GrowthStages.SPIKEY_ORANGE, Placements.VORACIOUS_AND_UP, 5, 0.07))
            .addBreedingPair(Stacks.GLOWING_SEED.getItemId(), Stacks.METALLIC_SEED.getItemId(), 0.05, 0.2)
            .tryRegister(addon);

        new EntitySpawningSeed(Stacks.ETHEREAL_SEED)
            .setEntityType(EntityType.ENDERMAN)
            .setGrowth(new Growth(GrowthStages.SPAWNING_GREEN, Placements.VORACIOUS_AND_UP, 6, 0.06))
            .addBreedingPair(Stacks.SPIRIT_SEED.getItemId(), Stacks.SPIRIT_SEED.getItemId(), 0.1, 0.1)
            .tryRegister(addon);

        new EntitySpawningSeed(Stacks.IGNITED_SEED)
            .setEntityType(EntityType.BLAZE)
            .setGrowth(new Growth(GrowthStages.SPAWNING_RED, Placements.VORACIOUS_AND_UP, 8, 0.07))
            .addBreedingPair(Stacks.ETHEREAL_SEED.getItemId(), Stacks.COMBUSTIBLE_SEED.getItemId(), 0.2, 0.25)
            .tryRegister(addon);

        new EntitySpawningSeed(Stacks.BARTERED_SEED)
            .setEntityType(EntityType.PIGLIN)
            .setGrowth(new Growth(GrowthStages.SPAWNING_CYAN, Placements.VORACIOUS_AND_UP, 8, 0.06))
            .addBreedingPair(Stacks.IGNEOUS_SEED.getItemId(), Stacks.PORKY_SEED.getItemId(), 0.2, 0.25)
            .tryRegister(addon);

        new DroppingSeed(Stacks.PRISMATIC_SEED)
            .addDrop(new ItemStack(Material.PRISMARINE_SHARD), 2)
            .addDrop(new ItemStack(Material.PRISMARINE_CRYSTALS), 1)
            .setTriggerChance(0.05)
            .setGrowth(new Growth(GrowthStages.SPIKEY_GREEN, Placements.VORACIOUS_AND_UP, 9, 0.06))
            .addBreedingPair(Stacks.RAINBOW_SEED.getItemId(), Stacks.SPLINTERED_SEED.getItemId(), 0.1, 0.15)
            .tryRegister(addon);

        new HarvestableSeed(Stacks.POROUS_SEED)
            .setHarvestingResult(new ItemStack(Material.SPONGE))
            .setGrowth(new Growth(GrowthStages.FUNGAL_YELLOW, Placements.VORACIOUS_AND_UP, 9, 0.06))
            .addBreedingPair(Stacks.PRISMATIC_SEED.getItemId(), Stacks.SEASIDE_SEED.getItemId(), 0.05, 0.05)
            .tryRegister(addon);

        new HarvestableSeed(Stacks.LEARNED_SEED)
            .setHarvestingResult(new ItemStack(Material.EXPERIENCE_BOTTLE))
            .setGrowth(new Growth(GrowthStages.VINEY_ORANGE, Placements.VORACIOUS_AND_UP, 9, 0.06))
            .addBreedingPair(Stacks.ETHEREAL_SEED.getItemId(), Stacks.ENCHANTED_SEED.getItemId(), 0.1, 0.15)
            .tryRegister(addon);

        new HarvestableSeed(Stacks.BUSY_SEED)
            .setHarvestingResult(new ItemStack(Material.COOKIE))
            .setGrowth(new Growth(GrowthStages.SPIKEY_RED, Placements.VORACIOUS_AND_UP, 9, 0.06))
            .addBreedingPair(Stacks.LEARNED_SEED.getItemId(), Stacks.SWEET_SEED.getItemId(), 0.1, 0.15)
            .tryRegister(addon);

        new GenericTickingSeed(Stacks.OAKENDRAN_SEED)
            .setConsumer(GenericTickingMethods::onTickOakendranSeed)
            .setGrowth(new Growth(GrowthStages.VINEY_ORANGE, Placements.VORACIOUS_AND_UP, 12, 0.04))
            .addBreedingPair(Stacks.ETHEREAL_SEED.getItemId(), Stacks.SPINDLE_SEED.getItemId(), 0.1, 0.15)
            .tryRegister(addon);

        new HarvestableSeed(Stacks.ADDON_BERRY_SEED)
            .setHarvestingResult(Stacks.ADDON_BERRY)
            .setGrowth(new Growth(GrowthStages.SPIKEY_RED, Placements.NETHER_DIRT_AND_UP, 10, 0.2))
            .addBreedingPair(Stacks.SAINTLY_SEED.getItemId(), Stacks.SAINTLY_SEED.getItemId(), 0.1, 0.15)
            .tryRegister(addon);

        new EntitySpawningSeed(Stacks.CUTE_SEED)
            .setEntityType(EntityType.AXOLOTL)
            .setGrowth(new Growth(GrowthStages.SPAWNING_BLUE, Placements.NETHER_DIRT_AND_UP, 15, 0.15))
            .addBreedingPair(Stacks.SAINTLY_SEED.getItemId(), Stacks.SAINTLY_SEED.getItemId(), 0.1, 0.15)
            .tryRegister(addon);

        new EntitySpawningSeed(Stacks.BEST_FRIEND_SEED)
            .setEntityType(EntityType.WOLF)
            .setGrowth(new Growth(GrowthStages.SPAWNING_CYAN, Placements.NETHER_DIRT_AND_UP, 16, 0.10))
            .addBreedingPair(Stacks.CUTE_SEED.getItemId(), Stacks.SPLINTERED_SEED.getItemId(), 0.1, 0.15)
            .tryRegister(addon);

        new GenericTickingSeed(Stacks.MATH_SEED)
            .setConsumer(GenericTickingMethods::onAlessioTeach)
            .setGrowth(new Growth(GrowthStages.SPIKEY_BLUE, Placements.NETHER_DIRT_AND_UP, 16, 0.10))
            .addBreedingPair(Stacks.BEST_FRIEND_SEED.getItemId(), Stacks.PERFECTION_SEED.getItemId(), 0.1, 0.15)
            .tryRegister(addon);

        new HarvestableSeed(Stacks.BUZZING_SEED)
            .setHarvestingResult(new ItemStack(Material.HONEYCOMB))
            .setGrowth(new Growth(GrowthStages.VINEY_YELLOW, Placements.NETHER_DIRT_AND_UP, 10, 0.2))
            .addBreedingPair(Stacks.CUTE_SEED.getItemId(), Stacks.SPINDLE_SEED.getItemId(), 0.15, 0.2)
            .tryRegister(addon);

        new EntitySpawningSeed(Stacks.TERRIFYING_SEED)
            .setEntityType(EntityType.WITHER_SKELETON)
            .setGrowth(new Growth(GrowthStages.SPAWNING_PURPLE, Placements.NETHER_DIRT_AND_UP, 15, 0.10))
            .addBreedingPair(Stacks.SAINTLY_SEED.getItemId(), Stacks.SAINTLY_SEED.getItemId(), 0.10, 0.3)
            .tryRegister(addon);

        new GenericTickingSeed(Stacks.HATE_FILLED_SEED)
            .setConsumer(GenericTickingMethods::onTickHateFilledSeed)
            .setGrowth(new Growth(GrowthStages.FUNGAL_ORANGE, Placements.NETHER_DIRT_AND_UP, 0, 0.2))
            .addBreedingPair(Stacks.TERRIFYING_SEED.getItemId(), Stacks.PROTECTIVE_SEED.getItemId(), 0.2, 0.05)
            .tryRegister(addon);

        new GenericTickingSeed(Stacks.PULSING_SEED)
            .setConsumer(GenericTickingMethods::onTickPulsingSeed)
            .setGrowth(new Growth(GrowthStages.VINEY_GREEN, Placements.NETHER_DIRT_AND_UP, 20, 0.08))
            .addBreedingPair(Stacks.HATE_FILLED_SEED.getItemId(), Stacks.GLOWING_SEED.getItemId(), 0.15, 0.2)
            .tryRegister(addon);

        new EntitySpawningSeed(Stacks.GATEWAY_SEED)
            .setEntityType(EntityType.VILLAGER)
            .setGrowth(new Growth(GrowthStages.SPAWNING_PURPLE, Placements.NETHER_DIRT_AND_UP, 20, 0.08))
            .addBreedingPair(Stacks.PULSING_SEED.getItemId(), Stacks.BARTERED_SEED.getItemId(), 0.15, 0.2)
            .tryRegister(addon);

        new DoNothingSeed(Stacks.CRYSTALLINE_SEED)
            .setGrowth(new Growth(GrowthStages.SPIKEY_ORANGE, Placements.NETHER_GRASS_AND_UP, 0, 0.02))
            .addBreedingPair(Stacks.EDEN_SEED.getItemId(), Stacks.WET_SEED.getItemId(), 0.15, 0.2)
            .tryRegister(addon);

        new EntitySpawningSeed(Stacks.BLACK_AND_WHITE_SEED)
            .setEntityType(EntityType.PANDA)
            .setCallback(livingEntity -> {
                             if (EasterEggUtils.isJeffBirthdayPeriod()) {
                                 livingEntity.setCustomName("✮ Jeff ✮");
                                 livingEntity.setCustomNameVisible(true);
                             }
                         }
            )
            .setGrowth(new Growth(GrowthStages.SPAWNING_BLUE, Placements.JUNGLE_BIOME, 25, 0.03))
            .addBreedingPair(Stacks.JUNGLE_SEED.getItemId(), Stacks.PORKY_SEED.getItemId(), 0.01, 0.05)
            .tryRegister(addon);

        new EntitySpawningSeed(Stacks.PARROT_SEED)
            .setEntityType(EntityType.PARROT)
            .setGrowth(new Growth(GrowthStages.SPAWNING_GREEN, Placements.JUNGLE_BIOME, 15, 0.10))
            .addBreedingPair(Stacks.JUNGLE_SEED.getItemId(), Stacks.RAINBOW_SEED.getItemId(), 0.09, 0.15)
            .tryRegister(addon);

        new EntitySpawningSeed(Stacks.WILD_SEED)
            .setEntityType(EntityType.OCELOT)
            .setGrowth(new Growth(GrowthStages.SPAWNING_YELLOW, Placements.JUNGLE_BIOME, 15, 0.10))
            .addBreedingPair(Stacks.JUNGLE_SEED.getItemId(), Stacks.CUTE_SEED.getItemId(), 0.05, 0.15)
            .tryRegister(addon);

        new EntitySpawningSeed(Stacks.SHELLED_SEED)
            .setEntityType(EntityType.TURTLE)
            .setGrowth(new Growth(GrowthStages.SPAWNING_GREEN, Placements.BEACH_BIOME, 15, 0.10))
            .addBreedingPair(Stacks.BEACH_SEED.getItemId(), Stacks.PROTECTIVE_SEED.getItemId(), 0.05, 0.15)
            .tryRegister(addon);

        new DroppingSeed(Stacks.TREASURED_SEED)
            .addDrop(new ItemStack(Material.HEART_OF_THE_SEA), 1)
            .addDrop(new ItemStack(Material.NAUTILUS_SHELL), 9)
            .setTriggerChance(0.01)
            .setGrowth(new Growth(GrowthStages.SPIKEY_GREEN, Placements.BEACH_BIOME, 15, 0.06))
            .addBreedingPair(Stacks.BEACH_SEED.getItemId(), Stacks.SHINY_SEED.getItemId(), 0.1, 0.5)
            .tryRegister(addon);

        new GenericTickingSeed(Stacks.SPINEY_SEED)
            .setConsumer(GenericTickingMethods::onTickSpineySeed)
            .setGrowth(new Growth(GrowthStages.SPIKEY_GREEN, Placements.DESERT_BIOME, 10, 0.03))
            .addBreedingPair(Stacks.DESERT_SEED.getItemId(), Stacks.SPINDLE_SEED.getItemId(), 0.2, 0.2)
            .tryRegister(addon);

        new EntitySpawningSeed(Stacks.HUSKY_SEED)
            .setEntityType(EntityType.HUSK)
            .setGrowth(new Growth(GrowthStages.SPAWNING_YELLOW, Placements.DESERT_BIOME, 15, 0.06))
            .addBreedingPair(Stacks.DESERT_SEED.getItemId(), Stacks.ROTTEN_SEED.getItemId(), 0.05, 0.25)
            .tryRegister(addon);

        new EntitySpawningSeed(Stacks.STRAY_SEED)
            .setEntityType(EntityType.STRAY)
            .setGrowth(new Growth(GrowthStages.SPAWNING_CYAN, Placements.SNOW_BIOME, 15, 0.06))
            .addBreedingPair(Stacks.SNOW_SEED.getItemId(), Stacks.SPLINTERED_SEED.getItemId(), 0.1, 0.25)
            .tryRegister(addon);

        new EntitySpawningSeed(Stacks.POLAR_SEED)
            .setEntityType(EntityType.POLAR_BEAR)
            .setGrowth(new Growth(GrowthStages.SPAWNING_YELLOW, Placements.SNOW_BIOME, 12, 0.08))
            .addBreedingPair(Stacks.SNOW_SEED.getItemId(), Stacks.PORKY_SEED.getItemId(), 0.05, 0.25)
            .tryRegister(addon);

        new EntitySpawningSeed(Stacks.CHILLY_SEED)
            .setEntityType(EntityType.FOX)
            .setCallback(livingEntity -> {
                             final Fox fox = (Fox) livingEntity;
                             fox.setFoxType(Fox.Type.SNOW);
                         }
            )
            .setGrowth(new Growth(GrowthStages.SPAWNING_PURPLE, Placements.SNOW_BIOME, 12, 0.08))
            .addBreedingPair(Stacks.SNOW_SEED.getItemId(), Stacks.BEST_FRIEND_SEED.getItemId(), 0.05, 0.1)
            .tryRegister(addon);

        new EntitySpawningSeed(Stacks.HEXED_SEED)
            .setEntityType(EntityType.WITCH)
            .setGrowth(new Growth(GrowthStages.SPAWNING_PURPLE, Placements.SWAMP_BIOME, 12, 0.04))
            .addBreedingPair(Stacks.SWAMP_SEED.getItemId(), Stacks.ENCHANTED_SEED.getItemId(), 0.03, 0.1)
            .tryRegister(addon);

        new EntitySpawningSeed(Stacks.SLIMY_SEED)
            .setEntityType(EntityType.SLIME)
            .setGrowth(new Growth(GrowthStages.SPAWNING_GREEN, Placements.SWAMP_BIOME, 12, 0.08))
            .addBreedingPair(Stacks.SWAMP_SEED.getItemId(), Stacks.GLISTENING_SEED.getItemId(), 0.05, 0.1)
            .tryRegister(addon);

        new GenericTickingSeed(Stacks.BLOB_SEED)
            .setConsumer(GenericTickingMethods::onWalshyIsMad)
            .setGrowth(new Growth(GrowthStages.FUNGAL_YELLOW, Placements.SWAMP_BIOME, 12, 0.08))
            .addBreedingPair(Stacks.SLIMY_SEED.getItemId(), Stacks.BEST_FRIEND_SEED.getItemId(), 0.05, 0.1)
            .tryRegister(addon);

        // endregion
    }
}
