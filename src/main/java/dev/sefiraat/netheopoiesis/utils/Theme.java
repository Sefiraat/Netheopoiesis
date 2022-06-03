package dev.sefiraat.netheopoiesis.utils;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

public enum Theme {
    WARNING(ChatColor.YELLOW, "Warning"),
    ERROR(ChatColor.RED, "Error"),
    NOTICE(ChatColor.WHITE, "Notice"),
    PASSIVE(ChatColor.GRAY, ""),
    SUCCESS(ChatColor.GREEN, "Success"),
    MAIN(ChatColor.of("#21588f"), "Netheopoiesis"),
    CLICK_INFO(ChatColor.of("#e4ed32"), "Click here"),
    RESEARCH(ChatColor.of("#a60e03"), "Research"),
    CRAFTING(ChatColor.of("#dbcea9"), "Crafting Material"),
    RECIPE_TYPE(ChatColor.of("#ffe89c"), "Recipe Type"),
    MACHINE(ChatColor.of("#3295a8"), "Machine"),
    SEED(ChatColor.of("#a241bf"), "Seed");

    private static final Theme[] cachedValues = values();
    private final ChatColor color;
    private final String loreLine;

    @ParametersAreNonnullByDefault
    Theme(ChatColor color, String loreLine) {
        this.color = color;
        this.loreLine = loreLine;
    }

    public String color(String string) {
        return this + string;
    }

    /**
     * Applies the theme color to a given string
     *
     * @param value The Object (as string) to apply the color to
     * @return Returns the string provides preceded by the color
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public String applyToString(@Nonnull Object value) {
        return this.color + String.valueOf(value);
    }

    /**
     * Applies the theme color to the first string and PASSIVE to the second
     *
     * @param value1 The Object (as string) to apply the color to
     * @param value2 The Object (as string) to apply PASSIVE to
     * @return Returns the string provides preceded by the color
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public String applyAsTitle(@Nonnull Object value1, @Nonnull Object value2) {
        return this.color + String.valueOf(value1) + ": " + Theme.PASSIVE + value2;
    }

    public static Theme[] getCachedValues() {
        return cachedValues;
    }

    /**
     * Gets a SlimefunItemStack with a pre-populated lore and name with themed colors.
     *
     * @param id        The ID for the new {@link SlimefunItemStack}
     * @param itemStack The vanilla {@link ItemStack} used to base the {@link SlimefunItemStack} on
     * @param themeType The {@link Theme} {@link ChatColor} to apply to the {@link SlimefunItemStack} name
     * @param name      The name to apply to the {@link SlimefunItemStack}
     * @param lore      The lore lines for the {@link SlimefunItemStack}. Lore is book-ended with empty strings.
     * @return Returns the new {@link SlimefunItemStack}
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static SlimefunItemStack themedSlimefunItemStack(String id,
                                                            ItemStack itemStack,
                                                            Theme themeType,
                                                            String name,
                                                            String... lore
    ) {
        ChatColor passiveColor = Theme.PASSIVE.getColor();
        List<String> finalLore = new ArrayList<>();
        finalLore.add("");
        for (String s : lore) {
            finalLore.add(passiveColor + s);
        }
        finalLore.add("");
        finalLore.add(applyThemeToString(Theme.CLICK_INFO, themeType.getLoreLine()));
        return new SlimefunItemStack(
            id,
            itemStack,
            Theme.applyThemeToString(themeType, name),
            finalLore.toArray(new String[finalLore.size() - 1])
        );
    }

    public ChatColor getColor() {
        return color;
    }

    /**
     * Applies the theme color to a given string
     *
     * @param themeType The {@link Theme} to apply the color from
     * @param value     The object (as string) to apply the color to
     * @return Returns the string provides preceded by the color
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static String applyThemeToString(@Nonnull Theme themeType, @Nonnull Object value) {
        return themeType.getColor() + String.valueOf(value);
    }

    /**
     * Applies the theme color to the first string and PASSIVE to the second
     *
     * @param themeType The {@link Theme} to apply the color from
     * @param string1   The string to apply the color to
     * @param value     The object to apply PASSIVE to as string
     * @return Returns the string provides preceded by the color
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static String applyThemeAsTitle(@Nonnull Theme themeType, @Nonnull String string1, Object value) {
        return themeType.getColor() + string1 + ": " + Theme.PASSIVE + value;
    }

    public String getLoreLine() {
        return loreLine;
    }

    /**
     * Gets an ItemStack with a pre-populated lore and name with themed colors.
     *
     * @param material  The {@link Material} used to base the {@link ItemStack} on
     * @param themeType The {@link Theme} {@link ChatColor} to apply to the {@link ItemStack} name
     * @param name      The name to apply to the {@link ItemStack}
     * @param lore      The lore lines for the {@link ItemStack}. Lore is book-ended with empty strings.
     * @return Returns the new {@link ItemStack}
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static ItemStack themedItemStack(Material material, Theme themeType, String name, String... lore) {
        ChatColor passiveColor = Theme.PASSIVE.getColor();
        List<String> finalLore = new ArrayList<>();
        finalLore.add("");
        for (String s : lore) {
            finalLore.add(passiveColor + s);
        }
        finalLore.add("");
        finalLore.add(applyThemeToString(Theme.CLICK_INFO, themeType.getLoreLine()));
        return new CustomItemStack(
            material,
            Theme.applyThemeToString(themeType, name),
            finalLore.toArray(new String[finalLore.size() - 1])
        );
    }

    @Nonnull
    public Particle.DustOptions getDustOptions(float size) {
        return new Particle.DustOptions(
            Color.fromRGB(
                color.getColor().getRed(),
                color.getColor().getGreen(),
                color.getColor().getBlue()
            ),
            size
        );
    }

    @Override
    public String toString() {
        return this.color.toString();
    }
}
