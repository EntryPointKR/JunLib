package com.github.entrypointkr.junlib.bukkit.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;

/**
 * Created by JunHyeong on 2018-10-24
 */
public class Bukkits {
    private Bukkits() {
    }

    public static CommandMap getMainCommandMap() {
        try {
            Field field = SimplePluginManager.class.getDeclaredField("commandMap");
            field.setAccessible(true);
            return (CommandMap) field.get(Bukkit.getPluginManager());
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
