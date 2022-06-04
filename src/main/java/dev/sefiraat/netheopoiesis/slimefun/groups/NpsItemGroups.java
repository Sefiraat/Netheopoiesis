package dev.sefiraat.netheopoiesis.slimefun.groups;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.utils.Keys;
import dev.sefiraat.netheopoiesis.utils.Theme;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class NpsItemGroups {

    private NpsItemGroups() {
        throw new IllegalStateException("Utility class");
    }

    public static final MainFlexGroup MAIN = new MainFlexGroup(
        Keys.newKey("main"),
        new CustomItemStack(
            new ItemStack(Material.WITHER_ROSE),
            Theme.MAIN.color("Netheopoiesis")
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

    static {
        final Netheopoiesis plugin = Netheopoiesis.getInstance();

        // Slimefun Registry
        MAIN.register(plugin);
        SEEDS.register(plugin);
        CRUX.register(plugin);
    }
}
