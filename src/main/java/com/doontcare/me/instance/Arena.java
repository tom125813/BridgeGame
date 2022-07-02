package com.doontcare.me.instance;

import com.doontcare.me.BridgeGame;
import com.doontcare.me.enums.GameState;
import com.doontcare.me.managers.ConfigManager;
import com.doontcare.me.utils.Chat;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import sun.util.logging.PlatformLogger;

import java.util.List;

public class Arena {

    private BridgeGame bridgeGame;

    private int id;
    private Player player;
    private List<Player> spectators; //tbd
    private Location spawn;

    private GameState state;
    private Countdown countdown;
    private Game game;

    public Arena(BridgeGame bridgeGame, int id, Location spawn) {
        this.bridgeGame = bridgeGame;

        this.id=id;
        this.player=null;

        this.state = GameState.EMPTY;
        this.countdown = new Countdown(bridgeGame, this, Countdown.Type.START);
        this.game = new Game(bridgeGame, this);
    }

    // GAME

    public void start() { game.start(); }

    public void reset() {
        state = GameState.ENDING;
    }

    // TOOLS

    public void setPlayer(Player player) {
        this.player=player;
        player.teleport(spawn);

        this.state=GameState.STARTING;
        countdown.start();
    }

    public void removePlayer() {
        player.teleport(ConfigManager.getLobbySpawn());
        this.player=null;
        this.state = GameState.EMPTY;
    }

    // UTILS

    public void sendMessage(String message) {
        String msg = Chat.translate(message);
        List<Player> players = spectators;
        players.add(player);
        for (Player p : players)
            p.sendMessage(msg);
    }

    public void sendTitle(String[] message, int fadeIn, int stay, int fadeOut) {
        String title = Chat.translate(message[0]);
        String subtitle = Chat.translate(message[1]);
        List<Player> players = spectators;
        players.add(player);
        for (Player p : players)
            p.sendTitle(title,subtitle,fadeIn,stay,fadeOut);
    }

    // INFO

    public int getId(){return id;}
    public Player getPlayer(){return player;}
    public GameState getState() {return state;}

    public void setState(GameState state){this.state=state;}

}
