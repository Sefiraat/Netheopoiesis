package dev.sefiraat.netheopoiesis.slimefun.groups;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.utils.Keys;
import dev.sefiraat.netheopoiesis.utils.Theme;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class NpsGroups {

    private NpsGroups() {
        throw new IllegalStateException("Utility class");
    }

    public static final MainFlexGroup MAIN = new MainFlexGroup(
        Keys.newKey("main"),
        new CustomItemStack(
            new ItemStack(Material.WITHER_ROSE),
            Theme.MAIN.color("Netheopoiesis")
        )
    );

    public static final DummyItemGroup CRAFTING = new DummyItemGroup(
        Keys.newKey("crafting"),
        new CustomItemStack(
            new ItemStack(Material.STICK),
            Theme.MAIN.color("Netheopoiesis Crafting Items")
        )
    );

    public static final DummyItemGroup TOOLS = new DummyItemGroup(
        Keys.newKey("tools"),
        new CustomItemStack(
            new ItemStack(Material.COMPASS),
            Theme.MAIN.color("Netheopoiesis Tools")
        )
    );

    public static final DummyItemGroup SEEDS = new DummyItemGroup(
        Keys.newKey("seeds"),
        new CustomItemStack(
            new ItemStack(Material.MELON_SEEDS),
            Theme.MAIN.color("Netheopoiesis Seeds")
        )
    );

    public static final DummyItemGroup CRUX = new DummyItemGroup(
        Keys.newKey("crux"),
        new CustomItemStack(
            new ItemStack(Material.MYCELIUM),
            Theme.MAIN.color("Netheopoiesis Crux'")
        )
    );

    public static final DiscoveriesFlexGroup DISCOVERIES = new DiscoveriesFlexGroup(
        Keys.newKey("discoveries"),
        new CustomItemStack(
            new ItemStack(Material.WHEAT_SEEDS),
            Theme.MAIN.color("Breeding Discoveries'")
        )
    );

    public static final PurificationFlexGroup GUIDE = new PurificationFlexGroup(
        Keys.newKey("guide"),
        new CustomItemStack(
            new ItemStack(Material.BOOKSHELF),
            Theme.MAIN.color("Purification Information")
        )
    );

    static {
        final Netheopoiesis plugin = Netheopoiesis.getInstance();

        // Slimefun Registry
        MAIN.register(plugin);
        CRAFTING.register(plugin);
        TOOLS.register(plugin);
        SEEDS.register(plugin);
        CRUX.register(plugin);
    }
}
