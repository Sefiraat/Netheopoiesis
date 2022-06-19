package dev.sefiraat.netheopoiesis.api.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class NetherBeacon extends BeaconSiphoningBlock {

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
    protected void onTick(Block block, SlimefunItem item, Config data) {
        super.onTick(block, item, data);
        // Beacon stuff here
    }
}
