package com.github.entrypointkr.junlib.bukkit.util;

import com.github.entrypointkr.junlib.reflection.FieldEx;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.SimplePluginManager;


/**
 * Created by JunHyeong Lim on 2019-01-05
 */
public class Internals {
    private Internals() {
    }

    public static CommandMap getCommandMap() {
        return FieldEx.of(SimplePluginManager.class, "commandMap")
                .get(Bukkit.getPluginManager(), CommandMap.class);
    }
}
