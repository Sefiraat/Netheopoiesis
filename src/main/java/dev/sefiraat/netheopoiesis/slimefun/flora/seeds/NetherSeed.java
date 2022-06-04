package dev.sefiraat.netheopoiesis.slimefun.flora.seeds;

import dev.sefiraat.netheopoiesis.utils.Keys;
import dev.sefiraat.netheopoiesis.utils.Skulls;
import io.github.bakedlibs.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.papermc.lib.PaperLib;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.ThreadLocalRandom;

public abstract class NetherSeed extends SlimefunItem implements Seed {

    protected NetherSeed(@Nonnull ItemGroup itemGroup,
                         @Nonnull SlimefunItemStack item,
                         @Nonnull RecipeType recipeType,
                         @Nonnull ItemStack[] recipe
    ) {
        super(itemGroup, item, recipeType, recipe);
    }

    protected NetherSeed(@Nonnull ItemGroup itemGroup,
                         @Nonnull SlimefunItemStack item,
                         @Nonnull RecipeType recipeType,
                         @Nonnull ItemStack[] recipe,
                         @Nullable ItemStack recipeOutput
    ) {
        super(itemGroup, item, recipeType, recipe, recipeOutput);
    }

    @Override
    public void preRegister() {
        addItemHandler(
            new BlockPlaceHandler(false) {
                @Override
                public void onPlayerPlace(@Nonnull BlockPlaceEvent event) {
                    whenPlaced(event);
                }
            },
            new BlockTicker() {
                @Override
                public boolean isSynchronized() {
                    return true;
                }

                @Override
                public void tick(Block block, SlimefunItem item, Config data) {
                    final Location location = block.getLocation();
                    int growthStage = Integer.parseInt(data.getString(Keys.SEED_GROWTH_STAGE));
                    onTickAlways(location, data);
                    if (growthStage >= 5) {
                        onTickFullyGrown(location, data);
                    } else {
                        final double growthRandom = ThreadLocalRandom.current().nextDouble();
                        if (growthRandom <= getGrowthRate()) {
                            growthStage++;
                            BlockStorage.addBlockInfo(
                                location,
                                Keys.SEED_GROWTH_STAGE,
                                String.valueOf(growthStage)
                            );
                            if (getGrowthPhases().size() >= growthStage) {
                                final Skulls nextTexture = getGrowthPhases().get(growthStage);
                                PlayerHead.setSkin(block, nextTexture.getPlayerSkin(), false);
                                PaperLib.getBlockState(block, false).getState().update(true, false);
                            }
                        }
                    }
                }
            }
        );
    }

    protected void whenPlaced(@Nonnull BlockPlaceEvent event) {
        final Block block = event.getBlock();
        final Location location = block.getLocation();
        if (location.getWorld().getEnvironment() == World.Environment.NETHER
            && getValidPlaces().contains(block.getType())
        ) {
            BlockStorage.addBlockInfo(location, Keys.SEED_GROWTH_STAGE, "0");
        } else {
            event.setCancelled(true);
        }
    }

    public static class BreedingPotential {
        public final NetherSeed motherSeed;
        public final NetherSeed fatherSeed;
        public final double breedChance;
        public final double failBreedChance;

        public BreedingPotential(NetherSeed motherSeed,
                                 NetherSeed fatherSeed,
                                 double breedChance,
                                 double failBreedChance
        ) {
            this.motherSeed = motherSeed;
            this.fatherSeed = fatherSeed;
            this.breedChance = breedChance;
            this.failBreedChance = failBreedChance;
        }

        public NetherSeed getMotherSeed() {
            return motherSeed;
        }

        public NetherSeed getFatherSeed() {
            return fatherSeed;
        }

        public double getBreedChance() {
            return breedChance;
        }

        public double getFailBreedChance() {
            return failBreedChance;
        }
    }
}
