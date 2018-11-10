package com.github.entrypointkr.junlib.bukkit.legacy;

import com.google.common.collect.ImmutableMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.util.NumberConversions;

import java.util.Map;

public class SerializableLocation extends Location implements ConfigurationSerializable {
    static {
        ConfigurationSerialization.registerClass(SerializableLocation.class);
    }

    public SerializableLocation(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public SerializableLocation(World world, double x, double y, double z, float yaw, float pitch) {
        super(world, x, y, z, yaw, pitch);
    }

    @Override
    public Map<String, Object> serialize() {
        return ImmutableMap.<String, Object>builder()
                .put("world", getWorld().getName())
                .put("x", getX()).put("y", getY()).put("z", getZ())
                .put("yaw", getYaw()).put("pitch", getPitch())
                .build();
    }

    public static Location deserialize(Map<String, Object> args) {
        World world = Bukkit.getWorld((String) args.get("world"));
        if (world == null) {
            throw new IllegalArgumentException("unknown world");
        }
        return new Location(
                world,
                NumberConversions.toDouble(args.get("x")),
                NumberConversions.toDouble(args.get("y")),
                NumberConversions.toDouble(args.get("z")),
                NumberConversions.toFloat(args.get("yaw")),
                NumberConversions.toFloat(args.get("pitch"))
        );
    }
}
