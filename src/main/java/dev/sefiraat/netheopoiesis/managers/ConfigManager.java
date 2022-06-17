package dev.sefiraat.netheopoiesis.managers;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * This class is used to create and manage/save custom configuration files
 */
public class ConfigManager {

    // Player breeding discoveries
    private final FileConfiguration discoveries;

    public ConfigManager() {
        setupDefaultConfig();
        this.discoveries = getConfig("discoveries.yml");
    }

    private void setupDefaultConfig() {
        final Netheopoiesis plugin = Netheopoiesis.getInstance();
        final InputStream inputStream = plugin.getResource("config.yml");
        final File existingFile = new File(plugin.getDataFolder(), "config.yml");

        if (inputStream == null) {
            // Not sure how? Regardless cannot copy over new keys
            return;
        }

        final Reader reader = new InputStreamReader(inputStream);
        final FileConfiguration resourceConfig = YamlConfiguration.loadConfiguration(reader);
        final FileConfiguration existingConfig = YamlConfiguration.loadConfiguration(existingFile);

        for (String key : resourceConfig.getKeys(false)) {
            checkKey(existingConfig, resourceConfig, key);
        }

        try {
            existingConfig.save(existingFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ParametersAreNonnullByDefault
    private void checkKey(FileConfiguration existingConfig, FileConfiguration resourceConfig, String key) {
        final Object currentValue = existingConfig.get(key);
        final Object newValue = resourceConfig.get(key);
        if (newValue instanceof ConfigurationSection section) {
            for (String sectionKey : section.getKeys(false)) {
                checkKey(existingConfig, resourceConfig, key + "." + sectionKey);
            }
        } else if (currentValue == null) {
            existingConfig.set(key, newValue);
        }
    }

    @Nonnull
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private FileConfiguration getConfig(@Nonnull String fileName) {
        final Netheopoiesis plugin = Netheopoiesis.getInstance();
        final File file = new File(plugin.getDataFolder(), fileName);

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public void saveAll() {
        Netheopoiesis.getInstance().getLogger().info("Netheopoiesis saving data.");
        saveDiscoveries();
    }

    private void saveDiscoveries() {
        File file = new File(Netheopoiesis.getInstance().getDataFolder(), "discoveries.yml");
        try {
            discoveries.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public FileConfiguration getDiscoveries() {
        return discoveries;
    }

    public boolean isAutoUpdate() {
        return Netheopoiesis.getInstance().getConfig().getBoolean("auto-update");
    }

    public boolean isDebugMessages() {
        return Netheopoiesis.getInstance().getConfig().getBoolean("debug-messages");
    }

    public int getPlayerMobCapWaterAmbient() {
        return Netheopoiesis.getInstance().getConfig().getInt("spawning.player-cap-water-ambient");
    }

    public int getPlayerMobCapWaterAnimal() {
        return Netheopoiesis.getInstance().getConfig().getInt("spawning.player-cap-water-animal");
    }

    public int getPlayerMobCapWaterHostile() {
        return Netheopoiesis.getInstance().getConfig().getInt("spawning.player-cap-water-hostile");
    }

    public int getPlayerMobCapLandAmbient() {
        return Netheopoiesis.getInstance().getConfig().getInt("spawning.player-cap-land-ambient");
    }

    public int getPlayerMobCapLandAnimal() {
        return Netheopoiesis.getInstance().getConfig().getInt("spawning.player-cap-land-animal");
    }

    public int getPlayerMobCapLandHostile() {
        return Netheopoiesis.getInstance().getConfig().getInt("spawning.player-cap-land-hostile");
    }

    public int getPlayerMobCapVillager() {
        return Netheopoiesis.getInstance().getConfig().getInt("spawning.player-cap-villager");
    }

    public int getPlayerMobCapPiglinTrader() {
        return Netheopoiesis.getInstance().getConfig().getInt("spawning.player-cap-piglin-trader");
    }

    public int getPlayerMobCapWanderingTrader() {
        return Netheopoiesis.getInstance().getConfig().getInt("spawning.player-cap-wandering-trader");
    }
}
