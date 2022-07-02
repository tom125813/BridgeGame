package com.doontcare.me.utils;

import org.bukkit.ChatColor;

public class Chat {

    public static String translate(String message) {
        return ChatColor.translateAlternateColorCodes('&',message);
    }

}
