package com.doontcare.me.commands;

import com.doontcare.me.BridgeGame;
import com.doontcare.me.instance.Arena;
import com.doontcare.me.managers.ArenaManager;
import com.doontcare.me.managers.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {

    private BridgeGame bridgeGame;
    public TestCommand(BridgeGame bridgeGame) {
        this.bridgeGame = bridgeGame;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player to use this command");
            return false;
        }
        Player player = (Player) sender;
        if (!player.isOp()) {
            player.sendMessage(ChatColor.RED + "No perms");
            return false;
        }
        Arena arena = bridgeGame.getArenaManager().getArena(0);
        arena.setPlayer(player);
        return false;
    }

}
