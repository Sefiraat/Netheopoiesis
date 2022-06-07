package dev.sefiraat.netheopoiesis.core.plant;

import dev.sefiraat.netheopoiesis.utils.Skulls;
import dev.sefiraat.netheopoiesis.utils.Theme;

import javax.annotation.Nonnull;
import java.util.List;

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


    @Nonnull
    private final Theme theme;
    @Nonnull
    private final List<Skulls> stages;

    /**
     * This class is used to store the possible stages of growth a plant can go through
     * starting with a seed to its final step.
     *
     * @param theme        The {@link Theme} that will be used for particle effects and other appropriate needs
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
