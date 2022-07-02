package com.doontcare.me.instance;

import com.doontcare.me.BridgeGame;
import com.doontcare.me.enums.GameState;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Game {

    private Arena arena;
    private Timer timer;

    public Game(BridgeGame bridgeGame, Arena arena) {
        this.arena = arena;
        this.timer = new Timer(bridgeGame, arena);
    }

    public void start() {
        arena.setState(GameState.BRIDGING);
        arena.sendTitle(new String[] {"&a&lBRIDGING PRACTICE", "&7Practice has begun"}, 0, 20, 20);
        timer.start();
    }

    public void finish() {
        arena.reset();
    }

}
