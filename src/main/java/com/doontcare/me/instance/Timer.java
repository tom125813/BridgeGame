package com.doontcare.me.instance;

import com.doontcare.me.BridgeGame;
import com.doontcare.me.enums.GameState;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer extends BukkitRunnable {

    private BridgeGame bridgeGame;
    private Arena arena;

    private double seconds;

    private int period = 1;

    public Timer(BridgeGame bridgeGame, Arena arena) {
        this.bridgeGame = bridgeGame;
        this.arena = arena;

        seconds=0;
    }

    public void start() {
        arena.setState(GameState.BRIDGING);
        runTaskTimer(bridgeGame, 0, period);
    }

    @Override
    public void run() {
        seconds+=(period/20);
    }

    public double getTime() {return seconds;}

}
