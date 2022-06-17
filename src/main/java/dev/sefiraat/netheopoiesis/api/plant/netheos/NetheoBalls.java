package dev.sefiraat.netheopoiesis.api.plant.netheos;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import dev.sefiraat.netheopoiesis.api.RecipeTypes;
import dev.sefiraat.netheopoiesis.implementation.Groups;
import dev.sefiraat.netheopoiesis.implementation.netheos.NetheoBall;
import dev.sefiraat.netheopoiesis.utils.TextUtils;
import dev.sefiraat.netheopoiesis.utils.Theme;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class NetheoBalls {

    public static final NetheoBalls SWEET = new NetheoBalls(
        "SWEET",
        Flavour.SWEET,
        Flavour.SWEET,
        Flavour.SWEET
    );
    public static final NetheoBalls TANGY = new NetheoBalls(
        "TANGY",
        Flavour.SWEET,
        Flavour.SWEET,
        Flavour.SOUR
    );
    public static final NetheoBalls POPCORN = new NetheoBalls(
        "POPCORN",
        Flavour.SWEET,
        Flavour.SWEET,
        Flavour.SALTY
    );
    public static final NetheoBalls BITTERSWEET = new NetheoBalls(
        "BITTERSWEET",
        Flavour.SWEET,
        Flavour.SWEET,
        Flavour.BITTER
    );
    public static final NetheoBalls SWEETPEA = new NetheoBalls(
        "SWEETPEA",
        Flavour.SWEET,
        Flavour.SWEET,
        Flavour.UMAMI
    );
    public static final NetheoBalls CANDY = new NetheoBalls(
        "CANDY",
        Flavour.SWEET,
        Flavour.SOUR,
        Flavour.SOUR
    );
    public static final NetheoBalls COMPLEX = new NetheoBalls(
        "COMPLEX",
        Flavour.SWEET,
        Flavour.SOUR,
        Flavour.SALTY
    );
    public static final NetheoBalls CITRUS = new NetheoBalls(
        "CITRUS",
        Flavour.SWEET,
        Flavour.SOUR,
        Flavour.BITTER
    );
    public static final NetheoBalls BAD = new NetheoBalls(
        "BAD",
        Flavour.SWEET,
        Flavour.SOUR,
        Flavour.UMAMI
    );
    public static final NetheoBalls SALTED_CARAMEL = new NetheoBalls(
        "SALTED_CARAMEL",
        Flavour.SWEET,
        Flavour.SALTY,
        Flavour.SALTY
    );
    public static final NetheoBalls COCOA = new NetheoBalls(
        "COCOA",
        Flavour.SWEET,
        Flavour.SALTY,
        Flavour.BITTER
    );
    public static final NetheoBalls TOMATO = new NetheoBalls(
        "TOMATO",
        Flavour.SWEET,
        Flavour.SALTY,
        Flavour.UMAMI
    );
    public static final NetheoBalls BERRY = new NetheoBalls(
        "BERRY",
        Flavour.SWEET,
        Flavour.BITTER,
        Flavour.BITTER
    );
    public static final NetheoBalls TERIYAKI = new NetheoBalls(
        "TERIYAKI",
        Flavour.SWEET,
        Flavour.BITTER,
        Flavour.UMAMI
    );
    public static final NetheoBalls SOY = new NetheoBalls(
        "SOY",
        Flavour.SWEET,
        Flavour.UMAMI,
        Flavour.UMAMI
    );
    public static final NetheoBalls TART = new NetheoBalls(
        "TART",
        Flavour.SOUR,
        Flavour.SOUR,
        Flavour.SOUR
    );
    public static final NetheoBalls GROSS = new NetheoBalls(
        "GROSS",
        Flavour.SOUR,
        Flavour.SOUR,
        Flavour.SALTY
    );
    public static final NetheoBalls SPOILT = new NetheoBalls(
        "SPOILT",
        Flavour.SOUR,
        Flavour.SOUR,
        Flavour.BITTER
    );
    public static final NetheoBalls BONITO = new NetheoBalls(
        "BONITO",
        Flavour.SOUR,
        Flavour.SOUR,
        Flavour.UMAMI
    );
    public static final NetheoBalls SEAWEED = new NetheoBalls(
        "SEAWEED",
        Flavour.SOUR,
        Flavour.SALTY,
        Flavour.SALTY
    );
    public static final NetheoBalls ASTRINGENT = new NetheoBalls(
        "ASTRINGENT",
        Flavour.SOUR,
        Flavour.SALTY,
        Flavour.BITTER
    );
    public static final NetheoBalls TASTY = new NetheoBalls(
        "TASTY",
        Flavour.SOUR,
        Flavour.SALTY,
        Flavour.UMAMI
    );
    public static final NetheoBalls GREEN_TEA = new NetheoBalls(
        "GREEN_TEA",
        Flavour.SOUR,
        Flavour.BITTER,
        Flavour.BITTER
    );
    public static final NetheoBalls BROTHY = new NetheoBalls(
        "BROTHY",
        Flavour.SOUR,
        Flavour.BITTER,
        Flavour.UMAMI
    );
    public static final NetheoBalls VEGEMITE = new NetheoBalls(
        "VEGEMITE",
        Flavour.SOUR,
        Flavour.UMAMI,
        Flavour.UMAMI
    );
    public static final NetheoBalls SALTY = new NetheoBalls(
        "SALTY",
        Flavour.SALTY,
        Flavour.SALTY,
        Flavour.SALTY
    );
    public static final NetheoBalls NASTY = new NetheoBalls(
        "NASTY",
        Flavour.SALTY,
        Flavour.SALTY,
        Flavour.BITTER
    );
    public static final NetheoBalls ANCHOVY = new NetheoBalls(
        "ANCHOVY",
        Flavour.SALTY,
        Flavour.SALTY,
        Flavour.UMAMI
    );
    public static final NetheoBalls GRIM = new NetheoBalls(
        "GRIM",
        Flavour.SALTY,
        Flavour.BITTER,
        Flavour.BITTER
    );
    public static final NetheoBalls SUSHI = new NetheoBalls(
        "SUSHI",
        Flavour.SALTY,
        Flavour.BITTER,
        Flavour.UMAMI
    );
    public static final NetheoBalls FISHY = new NetheoBalls(
        "FISHY",
        Flavour.SALTY,
        Flavour.UMAMI,
        Flavour.UMAMI
    );
    public static final NetheoBalls CRUCIFEROUS = new NetheoBalls(
        "CRUCIFEROUS",
        Flavour.BITTER,
        Flavour.BITTER,
        Flavour.BITTER
    );
    public static final NetheoBalls HEALTHY = new NetheoBalls(
        "HEALTHY",
        Flavour.BITTER,
        Flavour.BITTER,
        Flavour.UMAMI
    );
    public static final NetheoBalls FUNGI = new NetheoBalls(
            "FUNGI",
            Flavour.BITTER,
            Flavour.UMAMI,
            Flavour.UMAMI
        );
    public static final NetheoBalls SAVOURY = new NetheoBalls(
            "SAVOURY",
            Flavour.UMAMI,
            Flavour.UMAMI,
            Flavour.UMAMI
        );

    private static final Set<NetheoBalls> CACHED_VALUES = Set.of(
        SWEET,
        TANGY,
        POPCORN,
        BITTERSWEET,
        SWEETPEA,
        CANDY,
        COMPLEX,
        CITRUS,
        BAD,
        SALTED_CARAMEL,
        COCOA,
        TOMATO,
        BERRY,
        TERIYAKI,
        SOY,
        TART,
        GROSS,
        SPOILT,
        BONITO,
        SEAWEED,
        ASTRINGENT,
        TASTY,
        GREEN_TEA,
        BROTHY,
        VEGEMITE,
        SALTY,
        NASTY,
        ANCHOVY,
        GRIM,
        SUSHI,
        FISHY,
        CRUCIFEROUS,
        HEALTHY,
        FUNGI,
        SAVOURY
    );

    @Nonnull
    private final String name;
    @Nonnull
    private final Flavour flavour1;
    @Nonnull
    private final Flavour flavour2;
    @Nonnull
    private final Flavour flavour3;
    @Nonnull
    private final List<Flavour> flavourList;
    @Nonnull
    private final SlimefunItemStack slimefunItemStack;

    @ParametersAreNonnullByDefault
    NetheoBalls(String name, Flavour flavour1, Flavour flavour2, Flavour flavour3) {
        this.name = name;
        this.flavour1 = flavour1;
        this.flavour2 = flavour2;
        this.flavour3 = flavour3;

        flavourList = List.of(flavour1, flavour2, flavour3);

        this.slimefunItemStack = Theme.themedSlimefunItemStack(
            "NPS_NETHEO_BALL_" + this.name,
            Material.SNOWBALL,
            Theme.NETHEO_BALL,
            TextUtils.toTitleCase(this.name) + " Netheo Ball",
            "A nutritious ball made up from",
            "ground up plants from the Nether.",
            "Each has a different effect when eaten.",
            "Wandering Piglin Traders love these!",
            "",
            Theme.CLICK_INFO.asTitle("Flavour", 0)
        );
        final NetheoBall ball = new NetheoBall(
            Groups.BALLS,
            slimefunItemStack,
            RecipeTypes.NETHEO_MIXING,
            new ItemStack[0],
            this
        );
        ball.register(Netheopoiesis.getInstance());
    }

    @ParametersAreNonnullByDefault
    public boolean test(@Nonnull List<Flavour> flavours) {
        final List<Flavour> thisList = new ArrayList<>(flavourList);
        thisList.sort(Comparator.comparing(Enum::name));
        flavours.sort(Comparator.comparing(Enum::name));
        return thisList.equals(flavours);
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public Flavour getFlavour1() {
        return flavour1;
    }

    @Nonnull
    public Flavour getFlavour2() {
        return flavour2;
    }

    @Nonnull
    public Flavour getFlavour3() {
        return flavour3;
    }

    @Nonnull
    public SlimefunItemStack getSlimefunItemStack() {
        return slimefunItemStack;
    }

    @ParametersAreNonnullByDefault
    @Nullable
    public static NetheoBalls getMatchingBall(List<Flavour> flavours) {
        for (NetheoBalls ball : CACHED_VALUES) {
            if (ball.test(flavours)) {
                return ball;
            }
        }
        return null;
    }

    public static void setup() {
        // todo This class needs to be instantiated, need the right way to do this
    }
}
