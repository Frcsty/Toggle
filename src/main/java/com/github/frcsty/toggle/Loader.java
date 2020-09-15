package com.github.frcsty.toggle;

import com.github.frcsty.toggle.listener.PlayerListener;
import me.mattstudios.mf.base.CommandBase;
import me.mattstudios.mf.base.CommandManager;
import org.bukkit.event.Listener;

import java.util.*;

final class Loader {

    private final TogglePlugin plugin;
    private final List<Listener> listeners;
    private final List<CommandBase> commands;

    Loader(final TogglePlugin plugin) {
        this.plugin = plugin;

        this.commands = Arrays.asList(

        );

        this.listeners = Collections.singletonList(
                new PlayerListener(plugin)
        );
    }

    void load() {
        final CommandManager manager = new CommandManager(plugin);
        commands.forEach(manager::register);

        listeners.forEach(listener -> plugin.getServer().getPluginManager().registerEvents(listener, plugin));
    }

}
