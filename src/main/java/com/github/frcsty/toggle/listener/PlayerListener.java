package com.github.frcsty.toggle.listener;

import com.github.frcsty.toggle.TogglePlugin;
import com.github.frcsty.toggle.handle.InformationHandler;
import com.github.frcsty.toggle.handle.object.InformationPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class PlayerListener implements Listener {

    private final TogglePlugin plugin;

    public PlayerListener(final TogglePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final InformationHandler handler = plugin.getInformationHandler();
        final Player player = event.getPlayer();

        if (handler.getInformationPlayer(player.getUniqueId()) == null) {
            handler.setInformationPlayer(player.getUniqueId(), new InformationPlayer());
        }
    }
}
