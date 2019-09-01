package com.github.entrypointkr.junlib.bukkit.location;

import com.google.common.collect.ImmutableMap;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.Map;

/**
 * Created by JunHyeong on 2018-11-08
 */
@SerializableAs("JLPosition")
public class Position implements ConfigurationSerializable {
    private double x;
    private double y;

    private Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(double x, double y) {
        return new Position(x, y);
    }

    public static Position of(Map<String, Object> map) {
        return of(((Number) map.get("x")).doubleValue(), ((Number) map.get("y")).doubleValue());
    }

    public static Position ofFloor(double x, double y) {
        return of(Location.locToBlock(x), Location.locToBlock(y));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Position setX(double x) {
        this.x = x;
        return this;
    }

    public Position setY(double y) {
        this.y = y;
        return this;
    }

    public Position add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Position toFloor() {
        return of(Location.locToBlock(x), Location.locToBlock(y));
    }

    @Override
    public Map<String, Object> serialize() {
        return ImmutableMap.<String, Object>builder()
                .put("x", x)
                .put("y", y)
                .build();
    }
}
