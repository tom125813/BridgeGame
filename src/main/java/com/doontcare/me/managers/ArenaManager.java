package com.doontcare.me.managers;

import com.doontcare.me.BridgeGame;
import com.doontcare.me.instance.Arena;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class ArenaManager {

    private BridgeGame bridgeGame;
    private List<Arena> arenas;

    public ArenaManager(BridgeGame bridgeGame) {
        this.bridgeGame = bridgeGame;
        FileConfiguration config = bridgeGame.getConfig();
        for (String ar : config.getConfigurationSection("maps").getKeys(false))
            arenas.add(new Arena(bridgeGame, Integer.valueOf(ar), new Location(
                    Bukkit.getWorld(config.getString("arenas."+ar+".world")),
                    config.getDouble("arenas."+ar+".x"),
                    config.getDouble("arenas."+ar+".y"),
                    config.getDouble("arenas."+ar+".z"),
                    (float)config.getDouble("arenas."+ar+".yaw"),
                    (float)config.getDouble("arenas."+ar+".pitch")
            )));
    }

    public Arena getArena(int id) {
        for (Arena arena : arenas) {
            if (arena.getId()==id) {
                return arena;
            }
        }
        return null;
    }

    public Arena getArena(Player player) {
        for (Arena arena : arenas) {
            if (arena.getPlayer()==player){
                return arena;
            }
        }
        return null;
    }

}
