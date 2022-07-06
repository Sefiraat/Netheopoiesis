package dev.sefiraat.netheopoiesis;

import com.google.common.primitives.Ints;
import dev.sefiraat.netheopoiesis.api.plant.netheos.NetheoBalls;
import dev.sefiraat.netheopoiesis.implementation.Items;
import dev.sefiraat.netheopoiesis.managers.ConfigManager;
import dev.sefiraat.netheopoiesis.managers.DispatchManager;
import dev.sefiraat.netheopoiesis.managers.ListenerManager;
import dev.sefiraat.netheopoiesis.managers.MobManager;
import dev.sefiraat.netheopoiesis.managers.SupportedPluginManager;
import dev.sefiraat.netheopoiesis.managers.TaskManager;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.GitHubBuildsUpdater;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Netheopoiesis extends JavaPlugin implements SlimefunAddon {
    // Todo replace with config
    public static final int CRUX_SPREAD_MULTIPLIER = 1;
    public static final int CRYSTALLINE_SPREAD_MULTIPLIER = 1;
    public static final int GROWTH_RATE_MULTIPLIER = 1;

    private static Netheopoiesis instance;

    private final String username;
    private final String repo;
    private final String branch;

    private ConfigManager configManager;
    private SupportedPluginManager supportedPluginManager;
    private ListenerManager listenerManager;
    private TaskManager taskManager;
    private MobManager mobManager;
    private DispatchManager dispatchManager;
    private Purification purification;
    private Registry registry;
    private int versionNumber = 0;

    public Netheopoiesis() {
        this.username = "Sefiraat";
        this.repo = "Netheopoiesis";
        this.branch = "master";
    }

    @Override
    public void onEnable() {
        instance = this;

        getLogger().info("########################################");
        getLogger().info("    Netheopoiesis by Sefiraat, J3fftw   ");
        getLogger().info("########################################");

        saveDefaultConfig();
        this.configManager = new ConfigManager();

        tryUpdate();
        setupVersion();

        this.supportedPluginManager = new SupportedPluginManager();
        this.listenerManager = new ListenerManager();
        this.taskManager = new TaskManager();
        this.mobManager = new MobManager();
        this.dispatchManager = new DispatchManager(this);
        this.purification = new Purification();
        this.registry = new Registry();

        Items.setup(this);
        NetheoBalls.setup();

        setupStats();
    }

    @Override
    public void onDisable() {
        this.mobManager.shutdown();
        this.configManager.saveAll();
    }

    public void tryUpdate() {
        if (configManager.isAutoUpdate() && getDescription().getVersion().startsWith("DEV")) {
            String updateLocation = MessageFormat.format("{0}/{1}/{2}", this.username, this.repo, this.branch);
            GitHubBuildsUpdater updater = new GitHubBuildsUpdater(this, getFile(), updateLocation);
            updater.start();
        }
    }

    private void setupVersion() {
        final Pattern pattern = Pattern.compile("^[^\\d]*(\\d+)");
        final Matcher matcher = pattern.matcher(getPluginVersion());
        if (matcher.find()) {
            final Integer version = Ints.tryParse(matcher.group(1));
            this.versionNumber = version == null ? 0 : version;
        } else {
            this.versionNumber = 0;
        }
        logInfo("Running version: " + (this.versionNumber == 0 ? "MODIFIED" : this.versionNumber));
    }

    @Nonnull
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Nullable
    @Override
    public String getBugTrackerURL() {
        return MessageFormat.format("https://github.com/{0}/{1}/issues/", this.username, this.repo);
    }

    private void setupStats() {
        Metrics metrics = new Metrics(this, 15374);
    }

    public static Netheopoiesis getInstance() {
        return Netheopoiesis.instance;
    }

    public static void logError(@Nonnull String string) {
        instance.getLogger().severe(string);
    }

    public static void logWarning(@Nonnull String string) {
        instance.getLogger().warning(string);
    }

    public static void logInfo(@Nonnull String string) {
        instance.getLogger().info(string);
    }

    @Nonnull
    public static PluginManager getPluginManager() {
        return Netheopoiesis.getInstance().getServer().getPluginManager();
    }

    public static ConfigManager getConfigManager() {
        return Netheopoiesis.getInstance().configManager;
    }

    public static SupportedPluginManager getSupportedPluginManager() {
        return Netheopoiesis.getInstance().supportedPluginManager;
    }

    public static ListenerManager getListenerManager() {
        return Netheopoiesis.getInstance().listenerManager;
    }

    public static TaskManager getRunnableManager() {
        return Netheopoiesis.getInstance().taskManager;
    }

    public static MobManager getMobManager() {
        return Netheopoiesis.getInstance().mobManager;
    }

    public static Purification getPurificationMemory() {
        return Netheopoiesis.getInstance().purification;
    }

    public static Registry getPlantRegistry() {
        return Netheopoiesis.getInstance().registry;
    }

    public static int getVersionNumber() {
        return Netheopoiesis.getInstance().versionNumber;
    }
}
