package com.doontcare.me.instance;

import com.doontcare.me.GameState;
import com.doontcare.me.managers.ConfigManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Arena {

    private int id;
    private Player player;
    private Location spawn;

    private GameState state;
    private Timer timer;

    public Arena(int id, Location spawn) {
        this.id=id;
        this.player=null;

        this.state = GameState.EMPTY;
    }

    // TOOLS

    public void setPlayer(Player player) {
        this.player=player;
        player.teleport(spawn);
        this.state=GameState.STARTING;
        // create a couple second countdown before game starts.
    }

    public void removePlayer() {
        player.teleport(ConfigManager.getLobbySpawn());
        this.player=null;
        this.state = GameState.EMPTY;
    }

    // INFO

    public int getId(){return id;}
    public Player getPlayer(){return player;}
    public GameState getState() {return state;}

    public void setState(GameState state){this.state=state;}

}
