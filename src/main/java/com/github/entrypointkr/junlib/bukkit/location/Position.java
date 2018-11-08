package com.github.entrypointkr.junlib.bukkit.location;

import com.google.common.collect.ImmutableMap;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.Map;

/**
 * Created by JunHyeong on 2018-11-08
 */
@SerializableAs("JLPosition")
public class Position implements ConfigurationSerializable {
    private final double x;
    private final double z;

    static {
        ConfigurationSerialization.registerClass(Position.class);
    }

    private Position(double x, double z) {
        this.x = x;
        this.z = z;
    }

    public static Position of(double x, double z) {
        return new Position(x, z);
    }

    public static Position ofFloor(double x, double z) {
        return new Position(Location.locToBlock(x), Location.locToBlock(z));
    }

    public double getX() {
        return x;
    }

    public double getZ() {
        return z;
    }

    public Position toFloor() {
        return of(Location.locToBlock(x), Location.locToBlock(z));
    }

    public Position(Map<String, Object> map) {
        this(((double) map.get("x")), ((double) map.get("z")));
    }

    @Override
    public Map<String, Object> serialize() {
        return ImmutableMap.<String, Object>builder()
                .put("x", x)
                .put("z", z)
                .build();
    }
}
