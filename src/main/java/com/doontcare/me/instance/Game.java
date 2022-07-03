package com.doontcare.me.instance;

import com.doontcare.me.BridgeGame;
import com.doontcare.me.enums.GameState;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import sun.util.logging.PlatformLogger;

import java.util.HashMap;

public class Game {

    private BridgeGame bridgeGame;

    private Arena arena;
    private Timer timer;

    public Game(BridgeGame bridgeGame, Arena arena) {
        this.bridgeGame = bridgeGame;
        this.arena = arena;
        this.timer = new Timer(bridgeGame, arena);
    }

    public void start() {
        arena.setState(GameState.BRIDGING);
        arena.sendTitle(new String[] {"&a&lBRIDGING PRACTICE", "&7Practice has begun"}, 0, 20, 20);
        timer.start();

        Bukkit.getScheduler().runTaskLater(bridgeGame, new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 55L);
    }

    public void finish() {
        arena.sendMessage("&aTime: &6" + timer.getTime() +"s");
        arena.reset(true);
    }

}
