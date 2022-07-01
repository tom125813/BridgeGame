package com.doontcare.me;

import com.doontcare.me.managers.ArenaManager;
import com.doontcare.me.managers.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class BridgeGame extends JavaPlugin {

    private ArenaManager arenaManager;

    @Override
    public void onEnable() {
        ConfigManager.setupConfig(this);
        arenaManager = new ArenaManager(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ArenaManager getArenaManager() {return arenaManager;}

}
