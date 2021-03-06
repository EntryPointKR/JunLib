package com.github.entrypointkr.junlib.bukkit.event;

import com.github.entrypointkr.junlib.bukkit.event.custom.PlayerWalkEvent;
import com.github.entrypointkr.junlib.bukkit.location.Locations;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

/**
 * Created by JunHyeong on 2018-11-09
 */
public class CustomEventNotifier implements Listener {
    private static final Listener INSTANCE = new CustomEventNotifier();

    public static void register(Plugin plugin) {
        Bukkit.getPluginManager().registerEvents(INSTANCE, plugin);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Location from = Locations.toBlockLocation(e.getFrom());
        Location to = Locations.toBlockLocation(e.getTo());
        if (from.equals(to)) {
            return;
        }
        PlayerWalkEvent event = new PlayerWalkEvent(e.getPlayer(), from, to);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            e.getPlayer().teleport(from);
        }
    }
}
