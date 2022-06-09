package dev.sefiraat.netheopoiesis.utils;

import java.time.LocalDate;

public final class EasterEggUtils {

    private EasterEggUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isJeffBirthdayPeriod() {
        final LocalDate now = LocalDate.now();
        final int year = now.getYear();
        final LocalDate start = LocalDate.of(year, 6, 1);
        final LocalDate end = LocalDate.of(year, 6, 14);

        return now.isAfter(start) && now.isBefore(end);
    }
}
