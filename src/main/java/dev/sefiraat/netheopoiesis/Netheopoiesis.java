package dev.sefiraat.netheopoiesis;


import co.aikar.commands.PaperCommandManager;
import dev.sefiraat.netheopoiesis.commands.CommandMain;
import dev.sefiraat.netheopoiesis.managers.ListenerManager;
import dev.sefiraat.netheopoiesis.managers.RunnableManager;
import dev.sefiraat.netheopoiesis.managers.SupportedPluginManager;
import dev.sefiraat.netheopoiesis.slimefun.NpsSlimefunItems;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.GitHubBuildsUpdater;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.text.MessageFormat;

public class Netheopoiesis extends JavaPlugin implements SlimefunAddon {
    private static Netheopoiesis instance;

    private final String username;
    private final String repo;
    private final String branch;

    private ListenerManager listenerManager;
    private SupportedPluginManager supportedPluginManager;
    private RunnableManager runnableManager;
    private PurificationMemory purificationMemory;

    public Netheopoiesis() {
        this.username = "Sefiraat";
        this.repo = "Netheopoiesis";
        this.branch = "master";
    }

    @Override
    public void onEnable() {
        instance = this;

        getLogger().info("########################################");
        getLogger().info("        Netheopoiesis by Sefiraat       ");
        getLogger().info("########################################");

        saveDefaultConfig();
        tryUpdate();

        this.supportedPluginManager = new SupportedPluginManager();
        this.runnableManager = new RunnableManager();
        this.listenerManager = new ListenerManager();
        this.purificationMemory = new PurificationMemory();
        PaperCommandManager commandManager = new PaperCommandManager(this);

        commandManager.registerCommand(new CommandMain());

        NpsSlimefunItems.setup();

        setupStats();
    }

    @Override
    public void onDisable() {

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

    public static ListenerManager getListenerManager() {
        return Netheopoiesis.getInstance().listenerManager;
    }

    public static SupportedPluginManager getSupportedPluginManager() {
        return Netheopoiesis.getInstance().supportedPluginManager;
    }

    public static RunnableManager getRunnableManager() {
        return Netheopoiesis.getInstance().runnableManager;
    }

    public static PurificationMemory getPurificationMemory() {
        return Netheopoiesis.getInstance().purificationMemory;
    }
}
