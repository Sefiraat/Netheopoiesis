package dev.sefiraat.netheopoiesis.core.plant;

import dev.sefiraat.netheopoiesis.utils.Skulls;
import dev.sefiraat.netheopoiesis.utils.Theme;

import javax.annotation.Nonnull;
import java.util.List;

public class GrowthDescription {

    @Nonnull
    private final Theme theme;
    @Nonnull
    private final List<Skulls> growthStages;

    /**
     * This class is used to store the possible stages of growth a plant can go through
     * starting with a seed to its final step.
     *
     * @param theme        The {@link Theme} that will be used for particle effects and other appropriate needs
     * @param growthStages The {@link List} of Skulls that will be used as textures for each stage
     */
    public GrowthDescription(@Nonnull Theme theme, @Nonnull List<Skulls> growthStages) {
        this.theme = theme;
        this.growthStages = growthStages;
    }

    @Nonnull
    public Theme getTheme() {
        return theme;
    }

    @Nonnull
    public List<Skulls> getGrowthStages() {
        return growthStages;
    }

    public int stages() {
        return growthStages.size();
    }

    @Nonnull
    public Skulls get(int index) {
        return growthStages.get(index);
    }
}
