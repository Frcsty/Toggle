package com.github.frcsty.toggle.handle;

import com.github.frcsty.toggle.TogglePlugin;
import com.github.frcsty.toggle.handle.object.InformationPlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

public final class InformationHandler {

    private static final String FILE_NAME = "data";
    private static final String DATA_SECTION = "users";

    private final Map<UUID, InformationPlayer> information = new HashMap<>();

    public void load(final TogglePlugin plugin) {
        final FileConfiguration config = new FileHandler(plugin, FILE_NAME).getConfiguration();

        final ConfigurationSection data = config.getConfigurationSection(DATA_SECTION);
        if (data == null) {
            config.createSection(DATA_SECTION);
            return;
        }

        for (final String uuidString : data.getKeys(false)) {
            final InformationPlayer iPlayer = new InformationPlayer();

            final ConfigurationSection userSection = data.getConfigurationSection(DATA_SECTION + "." + uuidString);
            if (userSection == null) {
                continue;
            }

            for (final String key : userSection.getKeys(false)) {
                iPlayer.withStatus(key, userSection.getBoolean(key));
            }

            information.put(UUID.fromString(uuidString), iPlayer);
        }
    }

    public void save(final TogglePlugin plugin) {
        final FileHandler handler = new FileHandler(plugin, FILE_NAME);
        final FileConfiguration config = handler.getConfiguration();

        final ConfigurationSection data = config.getConfigurationSection(DATA_SECTION);
        if (data != null) {
            config.set(DATA_SECTION, null);
        }

        for (final UUID uuid : information.keySet()) {
            final InformationPlayer iPlayer = information.get(uuid);

            if (iPlayer == null) {
                continue;
            }

            final Map<String, Boolean> playerData = iPlayer.getData();
            for (final String key : playerData.keySet()) {
                config.set(DATA_SECTION + "." + uuid + "." + key, playerData.get(key));
            }
        }

        try {
            config.save(handler.getFile());
        } catch (final IOException ex) {
            plugin.getLogger().log(Level.WARNING, "Ax exception has occurred while saving player data!", ex);
        }
    }

    public InformationPlayer getInformationPlayer(final UUID identifier) {
        return this.information.get(identifier);
    }

    public void setInformationPlayer(final UUID identifier, final InformationPlayer iPlayer) {
        this.information.put(identifier, iPlayer);
    }
}
