package com.github.entrypointkr.junlib.bukkit.event;

import com.github.entrypointkr.junlib.JunLibrary;
import com.github.entrypointkr.junlib.bukkit.event.custom.PlayerWalkEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created by JunHyeong on 2018-11-09
 */
public class CustomEventNotifier implements Listener {
    private static final Listener INSTANCE = new CustomEventNotifier();

    public static void register(JunLibrary plugin) {
        Bukkit.getPluginManager().registerEvents(INSTANCE, plugin);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Location from = e.getFrom();
        Location to = e.getTo();
        if (from.getBlockX() == to.getBlockX()
                && from.getBlockY() == to.getBlockY()
                && from.getBlockZ() == to.getBlockZ()) {
            return;
        }

        PlayerWalkEvent event = new PlayerWalkEvent(e.getPlayer(), from, to);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            e.setCancelled(true);
        }
    }
}
