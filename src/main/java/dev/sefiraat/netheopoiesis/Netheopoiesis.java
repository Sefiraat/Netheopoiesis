package dev.sefiraat.netheopoiesis;


import co.aikar.commands.PaperCommandManager;
import dev.sefiraat.netheopoiesis.commands.CommandMain;
import dev.sefiraat.netheopoiesis.managers.ConfigManager;
import dev.sefiraat.netheopoiesis.managers.ListenerManager;
import dev.sefiraat.netheopoiesis.managers.RunnableManager;
import dev.sefiraat.netheopoiesis.managers.SupportedPluginManager;
import dev.sefiraat.netheopoiesis.slimefun.NpsItems;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.GitHubBuildsUpdater;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.text.MessageFormat;

public class Netheopoiesis extends JavaPlugin implements SlimefunAddon {
    public static final int CRUX_SPREAD_MULTIPLIER = 5;
    public static final int GROWTH_RATE_MULTIPLIER = 5;

    private static Netheopoiesis instance;

    private final String username;
    private final String repo;
    private final String branch;

    private SupportedPluginManager supportedPluginManager;
    private ConfigManager configManager;
    private ListenerManager listenerManager;
    private RunnableManager runnableManager;
    private Purification purification;

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
        tryUpdate();

        this.configManager = new ConfigManager();
        this.supportedPluginManager = new SupportedPluginManager();
        this.listenerManager = new ListenerManager();
        this.runnableManager = new RunnableManager();
        this.purification = new Purification();
        PaperCommandManager commandManager = new PaperCommandManager(this);

        commandManager.registerCommand(new CommandMain());

        NpsItems.setup();

        setupStats();
    }

    @Override
    public void onDisable() {
        this.configManager.saveAll();
    }

    public void tryUpdate() {
        if (getConfig().getBoolean("auto-update")
            && getDescription().getVersion().startsWith("DEV")
        ) {
            String updateLocation = MessageFormat.format("{0}/{1}/{2}", this.username, this.repo, this.branch);
            GitHubBuildsUpdater updater = new GitHubBuildsUpdater(this, getFile(), updateLocation);
            updater.start();
        }
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

    public static RunnableManager getRunnableManager() {
        return Netheopoiesis.getInstance().runnableManager;
    }

    public static Purification getPurificationMemory() {
        return Netheopoiesis.getInstance().purification;
    }
}
