package com.github.entrypointkr.junlib.bukkit.location;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.util.function.Predicate;

/**
 * Created by JunHyeong on 2018-11-08
 */
public class Locations {
    private Locations() {
    }

    public static Vector toFloorVector(Location location) {
        return new Vector(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    public static Position toPosition(Location location) {
        return Position.of(location.getX(), location.getZ());
    }

    public static Location toBlockLocation(Location location) {
        return new Location(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    public static Location getGround(Location start, int loopCount, Predicate<Block> blockFilter) {
        Location loc = start.clone();
        for (int i = 0; i < loopCount; i++) {
            if (blockFilter.test(loc.getBlock())) {
                break;
            }
            loc.subtract(0, 1, 0);
        }
        return loc;
    }

    public static Location getGround(Location start, int loopCount) {
        return getGround(start, loopCount, block ->
                block.getType().isSolid()
                        || block.getType().name().endsWith("WATER"));
    }

    public static Location getGround(Location start) {
        return getGround(start, start.getWorld().getMaxHeight());
    }
}
