package com.github.entrypointkr.junlib.bukkit.event.custom;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created by JunHyeong on 2018-11-09
 */
public class PlayerWalkEvent extends PlayerMoveEvent {
    private static final HandlerList handlers = new HandlerList();

    public PlayerWalkEvent(Player player, Location from, Location to) {
        super(player, from, to);
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
