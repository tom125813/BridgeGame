package com.doontcare.me.managers;

import com.doontcare.me.BridgeGame;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private static FileConfiguration config;

    public static void setupConfig(BridgeGame bridgeGame) {
        ConfigManager.config = bridgeGame.getConfig();
        bridgeGame.saveDefaultConfig();
    }

    public static int getCountdown(String type) {
        return config.getInt("countdowns." + type);
    }

    public static Location getLobbySpawn() {
        return new Location(
                Bukkit.getWorld(config.getString("lobby.world")),
                config.getDouble("lobby.x"),
                config.getDouble("lobby.y"),
                config.getDouble("lobby.z"),
                (float)config.getDouble("lobby.yaw"),
                (float)config.getDouble("lobby.pitch")
        );
    }

}
