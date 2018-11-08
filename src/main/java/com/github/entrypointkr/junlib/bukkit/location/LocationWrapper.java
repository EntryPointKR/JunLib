package com.github.entrypointkr.junlib.bukkit.location;

import org.bukkit.Location;
import org.bukkit.util.Vector;

/**
 * Created by JunHyeong on 2018-11-08
 */
public class LocationWrapper {
    private final Location location;

    public LocationWrapper(Location location) {
        this.location = location;
    }

    public static LocationWrapper of(Location location) {
        return new LocationWrapper(location);
    }

    public Vector toFloorVector() {
        return new Vector(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    public Position toPosition() {
        return Position.of(location.getX(), location.getZ());
    }
}
