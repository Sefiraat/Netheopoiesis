package dev.sefiraat.netheopoiesis.api.plant;

import dev.sefiraat.netheopoiesis.utils.Skulls;
import dev.sefiraat.netheopoiesis.utils.Theme;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * This class is used to define the seed item for a plant as well as the textures to be used
 * as the plant grows. It also contains the static instances of GrowthStages used in the addon
 */
public class GrowthStages {

    public static final GrowthStages VINEY_RED = new GrowthStages(
        Theme.SEED_RED,
        List.of(
            Skulls.SEED_RED,
            Skulls.VINEY_RED_1,
            Skulls.VINEY_RED_2,
            Skulls.VINEY_RED_3,
            Skulls.VINEY_RED_4,
            Skulls.VINEY_RED_5
        )
    );

    public static final GrowthStages VINEY_ORANGE = new GrowthStages(
        Theme.SEED_ORANGE,
        List.of(
            Skulls.SEED_ORANGE,
            Skulls.VINEY_ORANGE_1,
            Skulls.VINEY_ORANGE_2,
            Skulls.VINEY_ORANGE_3,
            Skulls.VINEY_ORANGE_4,
            Skulls.VINEY_ORANGE_5
        )
    );

    public static final GrowthStages VINEY_YELLOW = new GrowthStages(
        Theme.SEED_YELLOW,
        List.of(
            Skulls.SEED_YELLOW,
            Skulls.VINEY_YELLOW_1,
            Skulls.VINEY_YELLOW_2,
            Skulls.VINEY_YELLOW_3,
            Skulls.VINEY_YELLOW_4,
            Skulls.VINEY_YELLOW_5
        )
    );

    public static final GrowthStages VINEY_GREEN = new GrowthStages(
        Theme.SEED_GREEN,
        List.of(
            Skulls.SEED_GREEN,
            Skulls.VINEY_GREEN_1,
            Skulls.VINEY_GREEN_2,
            Skulls.VINEY_GREEN_3,
            Skulls.VINEY_GREEN_4,
            Skulls.VINEY_GREEN_5
        )
    );

    public static final GrowthStages VINEY_BLUE = new GrowthStages(
        Theme.SEED_BLUE,
        List.of(
            Skulls.SEED_BLUE,
            Skulls.VINEY_BLUE_1,
            Skulls.VINEY_BLUE_2,
            Skulls.VINEY_BLUE_3,
            Skulls.VINEY_BLUE_4,
            Skulls.VINEY_BLUE_5
        )
    );

    public static final GrowthStages VINEY_CYAN = new GrowthStages(
        Theme.SEED_CYAN,
        List.of(
            Skulls.SEED_CYAN,
            Skulls.VINEY_CYAN_1,
            Skulls.VINEY_CYAN_2,
            Skulls.VINEY_CYAN_3,
            Skulls.VINEY_CYAN_4,
            Skulls.VINEY_CYAN_5
        )
    );

    public static final GrowthStages VINEY_PURPLE = new GrowthStages(
        Theme.SEED_PURPLE,
        List.of(
            Skulls.SEED_PURPLE,
            Skulls.VINEY_PURPLE_1,
            Skulls.VINEY_PURPLE_2,
            Skulls.VINEY_PURPLE_3,
            Skulls.VINEY_PURPLE_4,
            Skulls.VINEY_PURPLE_5
        )
    );

    public static final GrowthStages SPAWNING_RED = new GrowthStages(
        Theme.SEED_RED,
        List.of(
            Skulls.SEED_RED,
            Skulls.SPAWNING_RED_1,
            Skulls.SPAWNING_RED_2,
            Skulls.SPAWNING_RED_3,
            Skulls.SPAWNING_RED_4,
            Skulls.SPAWNING_RED_5
        )
    );

    public static final GrowthStages SPAWNING_ORANGE = new GrowthStages(
        Theme.SEED_ORANGE,
        List.of(
            Skulls.SEED_ORANGE,
            Skulls.SPAWNING_ORANGE_1,
            Skulls.SPAWNING_ORANGE_2,
            Skulls.SPAWNING_ORANGE_3,
            Skulls.SPAWNING_ORANGE_4,
            Skulls.SPAWNING_ORANGE_5
        )
    );

    public static final GrowthStages SPAWNING_YELLOW = new GrowthStages(
        Theme.SEED_YELLOW,
        List.of(
            Skulls.SEED_YELLOW,
            Skulls.SPAWNING_YELLOW_1,
            Skulls.SPAWNING_YELLOW_2,
            Skulls.SPAWNING_YELLOW_3,
            Skulls.SPAWNING_YELLOW_4,
            Skulls.SPAWNING_YELLOW_5
        )
    );

    public static final GrowthStages SPAWNING_GREEN = new GrowthStages(
        Theme.SEED_GREEN,
        List.of(
            Skulls.SEED_GREEN,
            Skulls.SPAWNING_GREEN_1,
            Skulls.SPAWNING_GREEN_2,
            Skulls.SPAWNING_GREEN_3,
            Skulls.SPAWNING_GREEN_4,
            Skulls.SPAWNING_GREEN_5
        )
    );

    public static final GrowthStages SPAWNING_BLUE = new GrowthStages(
        Theme.SEED_BLUE,
        List.of(
            Skulls.SEED_BLUE,
            Skulls.SPAWNING_BLUE_1,
            Skulls.SPAWNING_BLUE_2,
            Skulls.SPAWNING_BLUE_3,
            Skulls.SPAWNING_BLUE_4,
            Skulls.SPAWNING_BLUE_5
        )
    );

    public static final GrowthStages SPAWNING_CYAN = new GrowthStages(
        Theme.SEED_CYAN,
        List.of(
            Skulls.SEED_CYAN,
            Skulls.SPAWNING_CYAN_1,
            Skulls.SPAWNING_CYAN_2,
            Skulls.SPAWNING_CYAN_3,
            Skulls.SPAWNING_CYAN_4,
            Skulls.SPAWNING_CYAN_5
        )
    );

    public static final GrowthStages SPAWNING_PURPLE = new GrowthStages(
        Theme.SEED_PURPLE,
        List.of(
            Skulls.SEED_PURPLE,
            Skulls.SPAWNING_PURPLE_1,
            Skulls.SPAWNING_PURPLE_2,
            Skulls.SPAWNING_PURPLE_3,
            Skulls.SPAWNING_PURPLE_4,
            Skulls.SPAWNING_PURPLE_5
        )
    );

    public static final GrowthStages SPIKEY_RED = new GrowthStages(
        Theme.SEED_RED,
        List.of(
            Skulls.SEED_RED,
            Skulls.SPIKEY_RED_1,
            Skulls.SPIKEY_RED_2,
            Skulls.SPIKEY_RED_3,
            Skulls.SPIKEY_RED_4,
            Skulls.SPIKEY_RED_5
        )
    );

    public static final GrowthStages SPIKEY_ORANGE = new GrowthStages(
        Theme.SEED_ORANGE,
        List.of(
            Skulls.SEED_ORANGE,
            Skulls.SPIKEY_ORANGE_1,
            Skulls.SPIKEY_ORANGE_2,
            Skulls.SPIKEY_ORANGE_3,
            Skulls.SPIKEY_ORANGE_4,
            Skulls.SPIKEY_ORANGE_5
        )
    );

    public static final GrowthStages SPIKEY_YELLOW = new GrowthStages(
        Theme.SEED_YELLOW,
        List.of(
            Skulls.SEED_YELLOW,
            Skulls.SPIKEY_YELLOW_1,
            Skulls.SPIKEY_YELLOW_2,
            Skulls.SPIKEY_YELLOW_3,
            Skulls.SPIKEY_YELLOW_4,
            Skulls.SPIKEY_YELLOW_5
        )
    );

    public static final GrowthStages SPIKEY_GREEN = new GrowthStages(
        Theme.SEED_GREEN,
        List.of(
            Skulls.SEED_GREEN,
            Skulls.SPIKEY_GREEN_1,
            Skulls.SPIKEY_GREEN_2,
            Skulls.SPIKEY_GREEN_3,
            Skulls.SPIKEY_GREEN_4,
            Skulls.SPIKEY_GREEN_5
        )
    );

    public static final GrowthStages SPIKEY_BLUE = new GrowthStages(
        Theme.SEED_BLUE,
        List.of(
            Skulls.SEED_BLUE,
            Skulls.SPIKEY_BLUE_1,
            Skulls.SPIKEY_BLUE_2,
            Skulls.SPIKEY_BLUE_3,
            Skulls.SPIKEY_BLUE_4,
            Skulls.SPIKEY_BLUE_5
        )
    );

    public static final GrowthStages SPIKEY_CYAN = new GrowthStages(
        Theme.SEED_CYAN,
        List.of(
            Skulls.SEED_CYAN,
            Skulls.SPIKEY_CYAN_1,
            Skulls.SPIKEY_CYAN_2,
            Skulls.SPIKEY_CYAN_3,
            Skulls.SPIKEY_CYAN_4,
            Skulls.SPIKEY_CYAN_5
        )
    );

    public static final GrowthStages SPIKEY_PURPLE = new GrowthStages(
        Theme.SEED_PURPLE,
        List.of(
            Skulls.SEED_PURPLE,
            Skulls.SPIKEY_PURPLE_1,
            Skulls.SPIKEY_PURPLE_2,
            Skulls.SPIKEY_PURPLE_3,
            Skulls.SPIKEY_PURPLE_4,
            Skulls.SPIKEY_PURPLE_5
        )
    );


    public static final GrowthStages FUNGAL_RED = new GrowthStages(
        Theme.SEED_RED,
        List.of(
            Skulls.SEED_RED,
            Skulls.FUNGAL_RED_1,
            Skulls.FUNGAL_RED_2,
            Skulls.FUNGAL_RED_3,
            Skulls.FUNGAL_RED_4,
            Skulls.FUNGAL_RED_5
        )
    );

    public static final GrowthStages FUNGAL_ORANGE = new GrowthStages(
        Theme.SEED_ORANGE,
        List.of(
            Skulls.SEED_ORANGE,
            Skulls.FUNGAL_ORANGE_1,
            Skulls.FUNGAL_ORANGE_2,
            Skulls.FUNGAL_ORANGE_3,
            Skulls.FUNGAL_ORANGE_4,
            Skulls.FUNGAL_ORANGE_5
        )
    );

    public static final GrowthStages FUNGAL_YELLOW = new GrowthStages(
        Theme.SEED_YELLOW,
        List.of(
            Skulls.SEED_YELLOW,
            Skulls.FUNGAL_YELLOW_1,
            Skulls.FUNGAL_YELLOW_2,
            Skulls.FUNGAL_YELLOW_3,
            Skulls.FUNGAL_YELLOW_4,
            Skulls.FUNGAL_YELLOW_5
        )
    );

    public static final GrowthStages FUNGAL_GREEN = new GrowthStages(
        Theme.SEED_GREEN,
        List.of(
            Skulls.SEED_GREEN,
            Skulls.FUNGAL_GREEN_1,
            Skulls.FUNGAL_GREEN_2,
            Skulls.FUNGAL_GREEN_3,
            Skulls.FUNGAL_GREEN_4,
            Skulls.FUNGAL_GREEN_5
        )
    );

    public static final GrowthStages FUNGAL_BLUE = new GrowthStages(
        Theme.SEED_BLUE,
        List.of(
            Skulls.SEED_BLUE,
            Skulls.FUNGAL_BLUE_1,
            Skulls.FUNGAL_BLUE_2,
            Skulls.FUNGAL_BLUE_3,
            Skulls.FUNGAL_BLUE_4,
            Skulls.FUNGAL_BLUE_5
        )
    );

    public static final GrowthStages FUNGAL_CYAN = new GrowthStages(
        Theme.SEED_CYAN,
        List.of(
            Skulls.SEED_CYAN,
            Skulls.FUNGAL_CYAN_1,
            Skulls.FUNGAL_CYAN_2,
            Skulls.FUNGAL_CYAN_3,
            Skulls.FUNGAL_CYAN_4,
            Skulls.FUNGAL_CYAN_5
        )
    );

    public static final GrowthStages FUNGAL_PURPLE = new GrowthStages(
        Theme.SEED_PURPLE,
        List.of(
            Skulls.SEED_PURPLE,
            Skulls.FUNGAL_PURPLE_1,
            Skulls.FUNGAL_PURPLE_2,
            Skulls.FUNGAL_PURPLE_3,
            Skulls.FUNGAL_PURPLE_4,
            Skulls.FUNGAL_PURPLE_5
        )
    );

    @Nonnull
    private final Theme theme;
    @Nonnull
    private final List<Skulls> stages;

    /**
     * This class is used to store the possible stages of growth a plant can go through
     * starting with a seed to its final step.
     *
     * @param theme  The {@link Theme} that will be used for particle effects and other appropriate needs
     * @param stages The {@link List} of Skulls that will be used as textures for each stage
     */
    public GrowthStages(@Nonnull Theme theme, @Nonnull List<Skulls> stages) {
        this.theme = theme;
        this.stages = stages;
    }

    @Nonnull
    public Theme getTheme() {
        return theme;
    }

    @Nonnull
    public List<Skulls> getStages() {
        return stages;
    }

    public int stages() {
        return stages.size();
    }

    @Nonnull
    public Skulls get(int index) {
        return stages.get(index);
    }
}
