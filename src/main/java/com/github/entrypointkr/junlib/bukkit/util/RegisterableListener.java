package com.github.entrypointkr.junlib.bukkit.util;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public interface RegisterableListener extends Listener {
    default void register(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
}
