package com.github.entrypointkr.junlib.bukkit.util;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;

/**
 * Created by JunHyeong on 2018-10-24
 */
public class Bukkits {
    public static CommandMap getMainCommandMap() {
        try {
            Field field = SimplePluginManager.class.getDeclaredField("commandMap");
            field.setAccessible(true);
            return (CommandMap) field.get(Bukkit.getPluginManager());
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }
}
