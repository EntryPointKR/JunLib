package com.github.entrypointkr.junlib.bukkit.location;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Entity;

import java.util.Map;

/**
 * Created by JunHyeong on 2018-11-08
 */
@SerializableAs("JLRegion")
public class Region implements ConfigurationSerializable {
    private final String world;
    private final Position min;
    private final Position max;

    private Region(String world, Position min, Position max) {
        this.world = world;
        this.min = min;
        this.max = max;
    }

    public Region(Map<String, Object> map) {
        this(((String) map.get("world")), ((Position) map.get("min")), ((Position) map.get("max")));
    }

    public static Region of(String world, Position posA, Position posB) {
        return new Region(world, posA, posB);
    }

    public static Region of(Location locA, Location locB) {
        Validate.isTrue(locA.getWorld().equals(locB.getWorld()));
        double x1 = locA.getX();
        double z1 = locA.getZ();
        double x2 = locB.getX();
        double z2 = locB.getZ();
        return of(
                locA.getWorld().getName(),
                Position.ofFloor(Math.min(x1, x2), Math.min(z1, z2)),
                Position.ofFloor(Math.max(x1, x2), Math.max(z1, z2))
        );
    }

    public boolean isIn(Location loc) {
        double x = loc.getBlockX();
        double z = loc.getBlockZ();
        return world.equals(loc.getWorld().getName())
                && x >= min.getX() && x <= max.getX()
                && z >= min.getY() && z <= max.getY();
    }

    public boolean isIn(Entity entity) {
        return isIn(entity.getLocation());
    }

    @Override
    public Map<String, Object> serialize() {
        return ImmutableMap.<String, Object>builder()
                .put("world", world)
                .put("min", min)
                .put("max", max)
                .build();
    }
}
