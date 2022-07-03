package com.doontcare.me.instance;

import com.doontcare.me.BridgeGame;
import com.doontcare.me.enums.GameState;
import com.doontcare.me.managers.ConfigManager;
import com.doontcare.me.utils.Chat;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
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

        this.spawn = spawn;

        this.id=id;
        this.player=null;

        this.state = GameState.EMPTY;
        this.countdown = new Countdown(bridgeGame, this, Countdown.Type.START);
        this.game = new Game(bridgeGame, this);
    }

    // GAME

    public void start() { game.start(); }

    public void reset(boolean kickPlayers) {
        state = GameState.ENDING;
        int countdown = ConfigManager.getCountdown(String.valueOf(Countdown.Type.END));
        sendTitle(new String[]{"&6&lBRIDGING PRACTICE", "&7Arena closing in " + countdown + " second" + (countdown == 1 ? "" : "s")},20, 20, 20);
        Bukkit.getScheduler().runTaskLater(bridgeGame, new Runnable(){
            @Override
            public void run() {
                empty(true);
            }

        }, ConfigManager.getCountdown(String.valueOf(Countdown.Type.END)));
    }

    private void empty(boolean kickPlayers) {
        if (kickPlayers) {
            removePlayer();
            removeSpectators();
        }
        state = GameState.EMPTY;
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
        this.player = null;
        this.state = GameState.EMPTY;
    }

    public void addSpectator(Player player) {
        sendMessage("&aYou have " + spectators.size() + " spectators");
        spectators.add(player);

        player.teleport(spawn);

        player.setGameMode(GameMode.CREATIVE);
        player.setInvisible(true);
        player.setInvulnerable(true);
    }

    public void removeSpectator(Player player) {
        player.teleport(ConfigManager.getLobbySpawn());

        player.setGameMode(GameMode.SURVIVAL);
        player.setInvisible(false);
        player.setInvulnerable(false);

        spectators.remove(player);
    }

    private void removeSpectators() {
        for (Player player : spectators) {
            removeSpectator(player);
        }
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
