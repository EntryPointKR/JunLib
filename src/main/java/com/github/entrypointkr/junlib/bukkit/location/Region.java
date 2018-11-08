package com.github.entrypointkr.junlib.bukkit.location;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
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

    static {
        ConfigurationSerialization.registerClass(Region.class);
    }

    private Region(String world, Position min, Position max) {
        this.world = world;
        this.min = min;
        this.max = max;
    }

    public static Region of(String world, Position posA, Position posB) {
        if (posA.getX() > posB.getX()
                || (posA.getX() == posB.getX() && posA.getZ() > posB.getZ())) {
            return new Region(world, posB, posA);
        } else {
            return new Region(world, posA, posB);
        }
    }

    public static Region of(Location locA, Location locB) {
        Validate.isTrue(locA.getWorld().equals(locB.getWorld()));
        return of(locA.getWorld().getName(), LocationWrapper.of(locA).toPosition(), LocationWrapper.of(locB).toPosition());
    }

    public boolean isIn(Entity entity) {
        Location loc = entity.getLocation();
        double x = loc.getBlockX();
        double z = loc.getBlockZ();
        return world.equals(loc.getWorld().getName())
                && x >= min.getX() && x <= max.getX()
                && z >= min.getZ() && z <= max.getZ();
    }

    public Region(Map<String, Object> map) {
        this(((String) map.get("world")), ((Position) map.get("min")), ((Position) map.get("max")));
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
