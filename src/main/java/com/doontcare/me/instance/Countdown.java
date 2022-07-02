package com.doontcare.me.instance;

import com.doontcare.me.BridgeGame;
import com.doontcare.me.enums.GameState;
import com.doontcare.me.managers.ConfigManager;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {

    private BridgeGame bridgeGame;
    private Arena arena;

    private Type type;
    private int countdownSeconds;

    protected enum Type {START, END}

    public Countdown(BridgeGame bridgeGame, Arena arena, Countdown.Type type) {
        this.bridgeGame = bridgeGame;
        this.arena = arena;
        this.type = type;

        countdownSeconds = ConfigManager.getCountdown(String.valueOf(type).toLowerCase());
    }

    public void start() {
        arena.setState(GameState.STARTING);
        runTaskTimer(bridgeGame, 0, 20);
    }

    @Override
    public void run() {
        if (countdownSeconds == 0) {
            cancel();
            if (type == Type.START) {
                arena.start();
            } else {
                arena.reset();
            }
            return;
        }

        arena.sendTitle(new String[] {
                "&c&lBRIDGING PRACTICE",
                "&7"
                        + String.valueOf(type).toUpperCase().substring(0,1) +String.valueOf(type).toLowerCase()
                        + "s in " + countdownSeconds + " second" + (countdownSeconds == 1 ? "" : "s")},
                0, 20, 0);
        countdownSeconds--;
    }
}
