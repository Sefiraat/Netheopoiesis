package dev.sefiraat.netheopoiesis.utils;

import javax.annotation.Nonnull;

public final class TextUtils {

    private TextUtils() {
        throw new IllegalStateException("Utility class");
    }

    @Nonnull
    public static String toTitleCase(@Nonnull String string) {
        return toTitleCase(string, true);
    }

    @Nonnull
    public static String toTitleCase(@Nonnull String string, boolean delimiterToSpace) {
        return toTitleCase(string, delimiterToSpace, " _'-/");
    }

    @Nonnull
    public static String toTitleCase(@Nonnull String string, boolean delimiterToSpace, @Nonnull String delimiters) {
        final StringBuilder builder = new StringBuilder();
        boolean capNext = true;

        for (char character : string.toCharArray()) {
            character = (capNext) ? Character.toUpperCase(character) : Character.toLowerCase(character);
            builder.append(character);
            capNext = (delimiters.indexOf(character) >= 0);
        }

        String built = builder.toString();

        if (delimiterToSpace) {
            final char space = ' ';
            for (char c : delimiters.toCharArray()) {
                built = built.replace(c, space);
            }
        }
        return built;
    }
}
