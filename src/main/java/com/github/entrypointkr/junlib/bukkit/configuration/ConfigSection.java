package com.github.entrypointkr.junlib.bukkit.configuration;

import org.bukkit.Color;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public class ConfigSection implements ConfigurationSection {
    private final ConfigurationSection delegate;

    public ConfigSection(ConfigurationSection delegate) {
        this.delegate = delegate;
    }

    public ConfigurationSection getSectionOrCreate(String key, Supplier<ConfigurationSection> supplier) {
        ConfigurationSection ret = delegate.getConfigurationSection(key);
        if (ret == null) {
            delegate.set(key, ret = supplier.get());
        }
        return ret;
    }

    public ConfigurationSection getSectionOrCreate(String key) {
        return getSectionOrCreate(key, MemoryConfiguration::new);
    }

    @Override
    public Set<String> getKeys(boolean deep) {
        return delegate.getKeys(deep);
    }

    @Override
    public Map<String, Object> getValues(boolean deep) {
        return delegate.getValues(deep);
    }

    @Override
    public boolean contains(String path) {
        return delegate.contains(path);
    }

    @Override
    public boolean contains(String path, boolean ignoreDefault) {
        return delegate.contains(path, ignoreDefault);
    }

    @Override
    public boolean isSet(String path) {
        return delegate.isSet(path);
    }

    @Override
    public String getCurrentPath() {
        return delegate.getCurrentPath();
    }

    @Override
    public String getName() {
        return delegate.getName();
    }

    @Override
    public Configuration getRoot() {
        return delegate.getRoot();
    }

    @Override
    public ConfigurationSection getParent() {
        return delegate.getParent();
    }

    @Override
    public Object get(String path) {
        return delegate.get(path);
    }

    @Override
    public Object get(String path, Object def) {
        return delegate.get(path, def);
    }

    @Override
    public void set(String path, Object value) {
        delegate.set(path, value);
    }

    @Override
    public ConfigurationSection createSection(String path) {
        return delegate.createSection(path);
    }

    @Override
    public ConfigurationSection createSection(String path, Map<?, ?> map) {
        return delegate.createSection(path, map);
    }

    @Override
    public String getString(String path) {
        return delegate.getString(path);
    }

    @Override
    public String getString(String path, String def) {
        return delegate.getString(path, def);
    }

    @Override
    public boolean isString(String path) {
        return delegate.isString(path);
    }

    @Override
    public int getInt(String path) {
        return delegate.getInt(path);
    }

    @Override
    public int getInt(String path, int def) {
        return delegate.getInt(path, def);
    }

    @Override
    public boolean isInt(String path) {
        return delegate.isInt(path);
    }

    @Override
    public boolean getBoolean(String path) {
        return delegate.getBoolean(path);
    }

    @Override
    public boolean getBoolean(String path, boolean def) {
        return delegate.getBoolean(path, def);
    }

    @Override
    public boolean isBoolean(String path) {
        return delegate.isBoolean(path);
    }

    @Override
    public double getDouble(String path) {
        return delegate.getDouble(path);
    }

    @Override
    public double getDouble(String path, double def) {
        return delegate.getDouble(path, def);
    }

    @Override
    public boolean isDouble(String path) {
        return delegate.isDouble(path);
    }

    @Override
    public long getLong(String path) {
        return delegate.getLong(path);
    }

    @Override
    public long getLong(String path, long def) {
        return delegate.getLong(path, def);
    }

    @Override
    public boolean isLong(String path) {
        return delegate.isLong(path);
    }

    @Override
    public List<?> getList(String path) {
        return delegate.getList(path);
    }

    @Override
    public List<?> getList(String path, List<?> def) {
        return delegate.getList(path, def);
    }

    @Override
    public boolean isList(String path) {
        return delegate.isList(path);
    }

    @Override
    public List<String> getStringList(String path) {
        return delegate.getStringList(path);
    }

    @Override
    public List<Integer> getIntegerList(String path) {
        return delegate.getIntegerList(path);
    }

    @Override
    public List<Boolean> getBooleanList(String path) {
        return delegate.getBooleanList(path);
    }

    @Override
    public List<Double> getDoubleList(String path) {
        return delegate.getDoubleList(path);
    }

    @Override
    public List<Float> getFloatList(String path) {
        return delegate.getFloatList(path);
    }

    @Override
    public List<Long> getLongList(String path) {
        return delegate.getLongList(path);
    }

    @Override
    public List<Byte> getByteList(String path) {
        return delegate.getByteList(path);
    }

    @Override
    public List<Character> getCharacterList(String path) {
        return delegate.getCharacterList(path);
    }

    @Override
    public List<Short> getShortList(String path) {
        return delegate.getShortList(path);
    }

    @Override
    public List<Map<?, ?>> getMapList(String path) {
        return delegate.getMapList(path);
    }

    @Override
    public <T extends ConfigurationSerializable> T getSerializable(String path, Class<T> clazz) {
        return delegate.getSerializable(path, clazz);
    }

    @Override
    public <T extends ConfigurationSerializable> T getSerializable(String path, Class<T> clazz, T def) {
        return delegate.getSerializable(path, clazz, def);
    }

    @Override
    public Vector getVector(String path) {
        return delegate.getVector(path);
    }

    @Override
    public Vector getVector(String path, Vector def) {
        return delegate.getVector(path, def);
    }

    @Override
    public boolean isVector(String path) {
        return delegate.isVector(path);
    }

    @Override
    public OfflinePlayer getOfflinePlayer(String path) {
        return delegate.getOfflinePlayer(path);
    }

    @Override
    public OfflinePlayer getOfflinePlayer(String path, OfflinePlayer def) {
        return delegate.getOfflinePlayer(path, def);
    }

    @Override
    public boolean isOfflinePlayer(String path) {
        return delegate.isOfflinePlayer(path);
    }

    @Override
    public ItemStack getItemStack(String path) {
        return delegate.getItemStack(path);
    }

    @Override
    public ItemStack getItemStack(String path, ItemStack def) {
        return delegate.getItemStack(path, def);
    }

    @Override
    public boolean isItemStack(String path) {
        return delegate.isItemStack(path);
    }

    @Override
    public Color getColor(String path) {
        return delegate.getColor(path);
    }

    @Override
    public Color getColor(String path, Color def) {
        return delegate.getColor(path, def);
    }

    @Override
    public boolean isColor(String path) {
        return delegate.isColor(path);
    }

    @Override
    public ConfigurationSection getConfigurationSection(String path) {
        return delegate.getConfigurationSection(path);
    }

    @Override
    public boolean isConfigurationSection(String path) {
        return delegate.isConfigurationSection(path);
    }

    @Override
    public ConfigurationSection getDefaultSection() {
        return delegate.getDefaultSection();
    }

    @Override
    public void addDefault(String path, Object value) {
        delegate.addDefault(path, value);
    }
}
