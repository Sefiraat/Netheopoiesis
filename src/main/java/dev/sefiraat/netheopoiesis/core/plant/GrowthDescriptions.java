package dev.sefiraat.netheopoiesis.core.plant;

import dev.sefiraat.netheopoiesis.utils.Skulls;
import dev.sefiraat.netheopoiesis.utils.Theme;

import java.util.List;

public final class GrowthDescriptions {

    private GrowthDescriptions() {
        throw new IllegalStateException("Utility class");
    }

    public static final GrowthDescription HARDY_RED = new GrowthDescription(
        Theme.SEED_RED,
        List.of(
            Skulls.SEED_RED,
            Skulls.RED_HARDY_1,
            Skulls.RED_HARDY_2,
            Skulls.RED_HARDY_3,
            Skulls.RED_HARDY_4,
            Skulls.RED_HARDY_5
        )
    );

    public static final GrowthDescription HARDY_ORANGE = new GrowthDescription(
        Theme.SEED_ORANGE,
        List.of(
            Skulls.SEED_ORANGE,
            Skulls.ORANGE_HARDY_1,
            Skulls.ORANGE_HARDY_2,
            Skulls.ORANGE_HARDY_3,
            Skulls.ORANGE_HARDY_4,
            Skulls.ORANGE_HARDY_5
        )
    );

    public static final GrowthDescription HARDY_YELLOW = new GrowthDescription(
        Theme.SEED_YELLOW,
        List.of(
            Skulls.SEED_YELLOW,
            Skulls.YELLOW_HARDY_1,
            Skulls.YELLOW_HARDY_2,
            Skulls.YELLOW_HARDY_3,
            Skulls.YELLOW_HARDY_4,
            Skulls.YELLOW_HARDY_5
        )
    );

    public static final GrowthDescription HARDY_GREEN = new GrowthDescription(
        Theme.SEED_GREEN,
        List.of(
            Skulls.SEED_GREEN,
            Skulls.GREEN_HARDY_1,
            Skulls.GREEN_HARDY_2,
            Skulls.GREEN_HARDY_3,
            Skulls.GREEN_HARDY_4,
            Skulls.GREEN_HARDY_5
        )
    );

    public static final GrowthDescription HARDY_BLUE = new GrowthDescription(
        Theme.SEED_BLUE,
        List.of(
            Skulls.SEED_BLUE,
            Skulls.BLUE_HARDY_1,
            Skulls.BLUE_HARDY_2,
            Skulls.BLUE_HARDY_3,
            Skulls.BLUE_HARDY_4,
            Skulls.BLUE_HARDY_5
        )
    );

    public static final GrowthDescription HARDY_INDIGO = new GrowthDescription(
        Theme.SEED_INDIGO,
        List.of(
            Skulls.SEED_INDIGO,
            Skulls.INDIGO_HARDY_1,
            Skulls.INDIGO_HARDY_2,
            Skulls.INDIGO_HARDY_3,
            Skulls.INDIGO_HARDY_4,
            Skulls.INDIGO_HARDY_5
        )
    );

    public static final GrowthDescription HARDY_VIOLET = new GrowthDescription(
        Theme.SEED_VIOLET,
        List.of(
            Skulls.SEED_VIOLET,
            Skulls.VIOLET_HARDY_1,
            Skulls.VIOLET_HARDY_2,
            Skulls.VIOLET_HARDY_3,
            Skulls.VIOLET_HARDY_4,
            Skulls.VIOLET_HARDY_5
        )
    );
}
