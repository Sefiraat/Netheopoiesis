package dev.sefiraat.netheopoiesis.utils;

import io.github.bakedlibs.dough.skins.PlayerHead;
import io.github.bakedlibs.dough.skins.PlayerSkin;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Stores the hashes for the skulls used in the addon with Methods for generating ItemStacks/Skins
 */
public enum Skulls {

    // https://minesk.in/420812538
    SEED_RED("660e8f971fcc83cf57a3e15b458317119e9f623d441bbf197e68125c80a23f30"),
    // https://minesk.in/1653180579
    SEED_ORANGE("db28b2cb42082d1cf1b31a26f9d3855c782d055482a43f675b42435152a95543"),
    // https://minesk.in/1452126246
    SEED_YELLOW("81170da7341f323f8e4a3d0f8ca379f9af31511f346699f4bf0d09db95f63c6f"),
    // https://minesk.in/345231516
    SEED_GREEN("95b92a13f01baece56654e817833f9829bc025ad733609c0bdd0d3a359c9d943"),
    // https://minesk.in/127668043
    SEED_BLUE("9fd6f27013701cd46bf32066ef2ecf90f1099be89f2e0bd9cc145479970c24b6"),
    // https://minesk.in/982733904
    SEED_CYAN("22be7169884ad820acaed7a717679cd0d91dcd3d6c1db81e6ac28a2bcd3534a0"),
    // https://minesk.in/2017750386
    SEED_PURPLE("981f7002b063a4b70360efa779eb58b0b3c9c8d59d57bc33f772eaf748e60a79"),

    // https://minesk.in/966096940
    VINEY_RED_1("4dbbfb342661c57c5c504f7c93debe3557757b034149a3ef72ce5236b56fc54c"),
    // https://minesk.in/1438648149
    VINEY_RED_2("dbd7d8b141bcad5deac3f139eebedb6acee9f9c8c3ae205eebe2c9c5f15839a6"),
    // https://minesk.in/946774698
    VINEY_RED_3("56aba8c4c42f71e1a9d929b27f137527ce6097978b0134cbc41e8da3c0d69b1a"),
    // https://minesk.in/1482065240
    VINEY_RED_4("83c8d346c45599c40ca284e7a0d549479488fabe3374458c8e7f47a20ff9f446"),
    // https://minesk.in/471953318
    VINEY_RED_5("b656ebee195ba6eea2a1ec7c732c2588c6c824ec569650b12d074d8402b624f"),

    // https://minesk.in/1495316923
    VINEY_ORANGE_1("768e05e07ff69c786e1a13809e7ced0ea659bf80b54e38ffdb498378e6f5e487"),
    // https://minesk.in/178089894
    VINEY_ORANGE_2("dc51b388a40ccaf00d40f454709085064953ae5bc42a46149ceaf41976cedc53"),
    // https://minesk.in/1074486879
    VINEY_ORANGE_3("6f25ea870a03f6ac0fc3692705bf85e1d24ff43dd3e050bf5a52d8529db9fcd8"),
    // https://minesk.in/1976056035
    VINEY_ORANGE_4("15531c663391ffb55bb257c2f53c5bc17b78d2695e516eda67971b5145c96393"),
    // https://minesk.in/519350049
    VINEY_ORANGE_5("688af28611e334a8afbe83490e1d6280a60b0ffe58c087104822d73a6b290cf0"),

    // https://minesk.in/170591458
    VINEY_YELLOW_1("73c87ede1d3fc20f6764762ba2fb274066b11c4390599c441ef7902ca5a59e40"),
    // https://minesk.in/1095069450
    VINEY_YELLOW_2("ca3e93bcac9c660f3bb647b0daa33ccd3695c6f20940ba7b30c730a9cbae9f2"),
    // https://minesk.in/503852470
    VINEY_YELLOW_3("46d229e0364533da2f685e554d2d352cb58d43daf21d63d919cc954a5f0a0f0"),
    // https://minesk.in/1011048560
    VINEY_YELLOW_4("7be60590c4784ede4148ffb058ad3948e050264931e341397f4870a0c8f850a4"),
    // https://minesk.in/356148734
    VINEY_YELLOW_5("fa95153fc73058dabee2181a3b2c10774428e5dfee6e82b324e84ea06ea8d272"),

    // https://minesk.in/834747675
    VINEY_GREEN_1("66bdd5e057eedf80b8e5a1e8243241bb91afffe1b65bf04d24647b3595969cb0"),
    // https://minesk.in/1526734309
    VINEY_GREEN_2("1da103af6aecef119fd9a551232615ff1fae725bdeb36eeb611efadd3fcf6349"),
    // https://minesk.in/858054997
    VINEY_GREEN_3("b77ca14b50c10fa1bc8d71c0d61f9e3022dfba6bdd87440d32e50bd43136a99"),
    // https://minesk.in/1824080260
    VINEY_GREEN_4("3589691376785c10327433e7810beef7912005c7517fd5d8977ed94078f56a14"),
    // https://minesk.in/297990353
    VINEY_GREEN_5("dff0e450b1bd59c2dc1e66d08202e8fa5d4839fd69e60491880a54dee0865ff8"),

    // https://minesk.in/1067572747
    VINEY_BLUE_1("da7799cbc025547f053928c0635456b06b4a66d4e088ba24216828d5d428487a"),
    // https://minesk.in/25450851
    VINEY_BLUE_2("5ee6ea1dbe7d9ebcee35cb9757faa9ef83d03290a794e357b31b4edec04cb3ce"),
    // https://minesk.in/1259553091
    VINEY_BLUE_3("6807c9a552dd86ac9166667b203d938b362e5a1a167676093822ef1197af095"),
    // https://minesk.in/1465152506
    VINEY_BLUE_4("5bcbc44bd5480eee1fa908cac20d52510fc3b04e7aad5455a235929f5e03a23e"),
    // https://minesk.in/1446710099
    VINEY_BLUE_5("b1c7c32ad07715a5e610dfb7d292b330f70cfc00551aca5cbf2a374c2aedcc75"),

    // https://minesk.in/504009261
    VINEY_CYAN_1("c2c2148da3ee74b17f9938937aed04de8d862ebff52e91b20421280ac27e0caf"),
    // https://minesk.in/1387395783
    VINEY_CYAN_2("476a7207882265e290f420704b8e5f9409ed3b0e03c0ea3173cb212d78a9d247"),
    // https://minesk.in/192061692
    VINEY_CYAN_3("8c04d5270cd260da455363cc0eeec3901bec8ef9f1d1f25200c681cc2e796d56"),
    // https://minesk.in/53256019
    VINEY_CYAN_4("521167044ffdc9127c0399a4daf74d0a59889804425042369258a9bcbd80dba7"),
    // https://minesk.in/596505149
    VINEY_CYAN_5("d71c02a3972ff608ecedb0dc92722d177ac89f25ba6f632ca38eda01fc4ea03c"),

    // https://minesk.in/1167642457
    VINEY_PURPLE_1("dd0d11751f3d31e6518fc586e1824fb57f8ee17ea45c0bff123a81d77089be80"),
    // https://minesk.in/673916336
    VINEY_PURPLE_2("8bcdd04b32b650ba86b6677e40432272a658dc198fbf26d75d6e72aba9475a09"),
    // https://minesk.in/1845663711
    VINEY_PURPLE_3("77430fece1a8c15ffa95fea4c3d61da7fbb7ff1006969183fa27f2c844eace16"),
    // https://minesk.in/247758607
    VINEY_PURPLE_4("6892e85b05a02e28d85cdec359547919d31fb71b9f70af89bb0ef0c710e9dddb"),
    // https://minesk.in/2112413733
    VINEY_PURPLE_5("93ee8ff4259fb10ea229399c0bc4b12e3c393d3dc94a0c4610a6bc2ec11940f9");

    private static final Skulls[] cachedValues = values();
    private final String hash;

    @ParametersAreNonnullByDefault
    Skulls(String hash) {
        this.hash = hash;
    }

    @Nonnull
    public ItemStack getPlayerHead() {
        return PlayerHead.getItemStack(PlayerSkin.fromHashCode(hash));
    }

    @Nonnull
    public PlayerSkin getPlayerSkin() {
        return PlayerSkin.fromHashCode(hash);
    }
}
