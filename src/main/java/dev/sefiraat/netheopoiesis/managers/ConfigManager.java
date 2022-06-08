package dev.sefiraat.netheopoiesis.managers;

import dev.sefiraat.netheopoiesis.Netheopoiesis;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;

/**
 * This class is used to create and manage/save custom configuration files
 */
public class ConfigManager {

    // Player breeding discoveries
    private final FileConfiguration discoveries;

    public ConfigManager() {
        this.discoveries = getConfig("discoveries.yml");
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
}
