package dev.sefiraat.netheopoiesis.implementation.blocks;

import dev.sefiraat.netheopoiesis.api.items.BeaconSiphoningBlock;
import dev.sefiraat.netheopoiesis.utils.Keys;
import dev.sefiraat.netheopoiesis.utils.ParticleUtils;
import dev.sefiraat.netheopoiesis.utils.ProtectionUtils;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.FallingBlock;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.ThreadLocalRandom;

public class NetherBeacon extends BeaconSiphoningBlock {

    private static final int NETHER_HIGHEST_BEDROCK = 127;

    @ParametersAreNonnullByDefault
    public NetherBeacon(ItemGroup itemGroup,
                        SlimefunItemStack item,
                        RecipeType recipeType,
                        ItemStack[] recipe,
                        int tier,
                        int purificationDraw,
                        int powerPerTick
    ) {
        super(itemGroup, item, recipeType, recipe, tier, purificationDraw, powerPerTick);
    }

    @Override
    @ParametersAreNonnullByDefault
    protected boolean onTick(Block block, SlimefunItem item, Config data) {
        // Call super to ensure the tick is valid
        if (!super.onTick(block, item, data)) {
            return false;
        }

        final int power = getPowerFromMap(block.getLocation());
        final int radius = (int) Math.sqrt(power);

        for (int i = 0; i < 10; i++) {
            final int randX = ThreadLocalRandom.current().nextInt(-radius, radius + 1);
            final int randZ = ThreadLocalRandom.current().nextInt(-radius, radius + 1);
            Block test = block.getWorld().getBlockAt(
                block.getX() + randX,
                NETHER_HIGHEST_BEDROCK,
                block.getZ() + randZ
            );

            // Bedrock is already broken here, we will stop now.
            if (test.getType() == Material.AIR) {
                continue;
            }

            Block testBelow = test.getRelative(BlockFace.DOWN);
            Material material = testBelow.getType();
            while (!material.isAir() && testBelow.getY() > 0) {
                test = testBelow;
                testBelow = test.getRelative(BlockFace.DOWN);
                material = testBelow.getType();
            }

            // If player doesn't have permission to break block, skip this one
            if (!ProtectionUtils.hasPermission(getOwner(block.getLocation()), testBelow, Interaction.BREAK_BLOCK)) {
                continue;
            }

            final FallingBlock fallingBlock = test.getWorld().spawnFallingBlock(
                test.getLocation().clone().add(0.5, 0.5, 0.5),
                test.getBlockData()
            );
            fallingBlock.setDropItem(false);
            PersistentDataAPI.setBoolean(fallingBlock, Keys.MANAGED_FALLING_BLOCK, true);
            test.setType(Material.AIR);
            ParticleUtils.randomSpread(fallingBlock, Particle.EXPLOSION_LARGE, 2, 4);
            fallingBlock.getWorld().playSound(fallingBlock, Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
            drawBeam(block);
        }
        return true;
    }

    private void drawBeam(@Nonnull Block block) {
        final Location start = block.getLocation().clone().add(0.5, 1.2, 0.5);
        final Location end = start.clone();
        end.setY(NETHER_HIGHEST_BEDROCK);
        ParticleUtils.drawLine(start, end, 0.5, new Particle.DustOptions(Color.LIME, 1));
    }
}
