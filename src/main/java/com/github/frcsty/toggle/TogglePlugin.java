package com.github.frcsty.toggle;

import com.github.frcsty.toggle.handle.InformationHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class TogglePlugin extends JavaPlugin {

    private final Loader loader = new Loader(this);
    private final InformationHandler handler = new InformationHandler();

    @Override
    public void onEnable() {
        saveDefaultConfig();

        loader.load();
        handler.load(this);
    }

    @Override
    public void onDisable() {
        reloadConfig();

        handler.save(this);
    }

    public InformationHandler getInformationHandler() {
        return this.handler;
    }

}
