package com.github.frcsty.toggle.handle;

import com.github.frcsty.toggle.TogglePlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

final class FileHandler {

    private final FileConfiguration config;
    private final File file;

    FileHandler(final TogglePlugin plugin, final String filePath) {
        this.file = new File(plugin.getDataFolder() + "/" + filePath + ".yml");

        if (!file.exists()) {
            file.mkdir();
        }

        this.config = YamlConfiguration.loadConfiguration(file);
    }

    FileConfiguration getConfiguration() {
        return this.config;
    }

    File getFile() {
        return this.file;
    }
}
