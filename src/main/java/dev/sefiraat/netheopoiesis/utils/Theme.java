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

/**
 * This Enum is used to ensure theming across the addon with Methods for applying themes to ItemStacks
 */
public enum Theme {
    // Main Theme
    MAIN(ChatColor.of("#290612"), "Netheopoiesis"),
    // Basic chat elements
    WARNING(ChatColor.YELLOW, "Warning"),
    ERROR(ChatColor.RED, "Error"),
    NOTICE(ChatColor.WHITE, "Notice"),
    PASSIVE(ChatColor.GRAY),
    SUCCESS(ChatColor.GREEN, "Success"),
    // Item and SlimefunItem lore/themes
    CLICK_INFO(ChatColor.of("#e4ed32"), "Click here"),
    RESEARCH(ChatColor.of("#a60e03"), "Research"),
    CRAFTING(ChatColor.of("#dbcea9"), "Crafting Material"),
    RECIPE_TYPE(ChatColor.of("#ffe89c"), "Recipe Type"),
    MACHINE(ChatColor.of("#3295a8"), "Machine"),
    TOOL(ChatColor.of("#3295a8"), "Tool"),
    SEED(ChatColor.of("#a241bf"), "Seed"),
    CRUX(ChatColor.of("#a241bf"), "Crux"),
    // Seeds
    SEED_RED(ChatColor.of("#c41d1d")),
    SEED_ORANGE(ChatColor.of("#c4761d")),
    SEED_YELLOW(ChatColor.of("#c4b31d")),
    SEED_GREEN(ChatColor.of("#4ac41d")),
    SEED_BLUE(ChatColor.of("#1d36c4")),
    SEED_INDIGO(ChatColor.of("#551dc4")),
    SEED_VIOLET(ChatColor.of("#811dc4"));

    private static final Theme[] cachedValues = values();
    private final ChatColor color;
    private final String loreLine;

    Theme(@Nonnull ChatColor color) {
        this(color, "");
    }

    Theme(@Nonnull ChatColor color, @Nonnull String loreLine) {
        this.color = color;
        this.loreLine = loreLine;
    }

    public String color(@Nonnull String string) {
        return this + string;
    }

    /**
     * Applies the theme color to a given string
     *
     * @param value The Object (as string) to apply the color to
     * @return Returns the string provides preceded by the color
     */
    @Nonnull
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
    public String asTitle(@Nonnull Object value1, @Nonnull Object value2) {
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

    /**
     * Gets a SlimefunItemStack with a pre-populated lore and name with themed colors.
     *
     * @param id        The ID for the new {@link SlimefunItemStack}
     * @param material  The vanilla {@link ItemStack} used to base the {@link SlimefunItemStack} on
     * @param themeType The {@link Theme} {@link ChatColor} to apply to the {@link SlimefunItemStack} name
     * @param name      The name to apply to the {@link SlimefunItemStack}
     * @param lore      The lore lines for the {@link SlimefunItemStack}. Lore is book-ended with empty strings.
     * @return Returns the new {@link SlimefunItemStack}
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static SlimefunItemStack themedSlimefunItemStack(String id,
                                                            Material material,
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
            material,
            Theme.applyThemeToString(themeType, name),
            finalLore.toArray(new String[finalLore.size() - 1])
        );
    }

    /**
     * Gets a SlimefunItemStack with a pre-populated lore and name with themed colors with parameters
     * for the plantable items/materials for seeds
     *
     * @param id        The ID for the new {@link SlimefunItemStack}
     * @param seedStack The vanilla {@link ItemStack} used to base the {@link SlimefunItemStack} on
     * @param themeType The {@link Theme} {@link ChatColor} to apply to the {@link SlimefunItemStack} name
     * @param name      The name to apply to the {@link SlimefunItemStack}
     * @param lore      The lore lines for the {@link SlimefunItemStack}. Lore is book-ended with empty strings.
     * @return Returns the new {@link SlimefunItemStack}
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static SlimefunItemStack themedSeed(String id,
                                               ItemStack seedStack,
                                               Theme themeType,
                                               String name,
                                               String[] lore,
                                               String[] validPlacements
    ) {
        ChatColor passiveColor = Theme.PASSIVE.getColor();
        List<String> finalLore = new ArrayList<>();
        finalLore.add("");
        for (String s : lore) {
            finalLore.add(passiveColor + s);
        }
        for (String s : validPlacements) {
            finalLore.add(passiveColor + s);
        }
        finalLore.add("");
        finalLore.add(applyThemeToString(Theme.CLICK_INFO, themeType.getLoreLine()));
        return new SlimefunItemStack(
            id,
            seedStack,
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
    public static String applyThemeAsTitle(Theme themeType, String string1, Object value) {
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
    public static ItemStack themedItemStack(Material material,
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
    @Nonnull
    public String toString() {
        return this.color.toString();
    }
}
