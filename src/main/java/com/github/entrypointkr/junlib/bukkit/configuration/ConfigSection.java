package com.github.entrypointkr.junlib.bukkit.configuration;

import org.bukkit.Color;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    @NotNull
    public Set<String> getKeys(boolean deep) {
        return delegate.getKeys(deep);
    }

    @Override
    @NotNull
    public Map<String, Object> getValues(boolean deep) {
        return delegate.getValues(deep);
    }

    @Override
    public boolean contains(@NotNull String path) {
        return delegate.contains(path);
    }

    @Override
    public boolean contains(@NotNull String path, boolean ignoreDefault) {
        return delegate.contains(path, ignoreDefault);
    }

    @Override
    public boolean isSet(@NotNull String path) {
        return delegate.isSet(path);
    }

    @Override
    @Nullable
    public String getCurrentPath() {
        return delegate.getCurrentPath();
    }

    @Override
    @NotNull
    public String getName() {
        return delegate.getName();
    }

    @Override
    @Nullable
    public Configuration getRoot() {
        return delegate.getRoot();
    }

    @Override
    @Nullable
    public ConfigurationSection getParent() {
        return delegate.getParent();
    }

    @Override
    @Nullable
    public Object get(@NotNull String path) {
        return delegate.get(path);
    }

    @Override
    @Nullable
    public Object get(@NotNull String path, @Nullable Object def) {
        return delegate.get(path, def);
    }

    @Override
    public void set(@NotNull String path, @Nullable Object value) {
        delegate.set(path, value);
    }

    @Override
    @NotNull
    public ConfigurationSection createSection(@NotNull String path) {
        return delegate.createSection(path);
    }

    @Override
    @NotNull
    public ConfigurationSection createSection(@NotNull String path, @NotNull Map<?, ?> map) {
        return delegate.createSection(path, map);
    }

    @Override
    @Nullable
    public String getString(@NotNull String path) {
        return delegate.getString(path);
    }

    @Override
    @Nullable
    public String getString(@NotNull String path, @Nullable String def) {
        return delegate.getString(path, def);
    }

    @Override
    public boolean isString(@NotNull String path) {
        return delegate.isString(path);
    }

    @Override
    public int getInt(@NotNull String path) {
        return delegate.getInt(path);
    }

    @Override
    public int getInt(@NotNull String path, int def) {
        return delegate.getInt(path, def);
    }

    @Override
    public boolean isInt(@NotNull String path) {
        return delegate.isInt(path);
    }

    @Override
    public boolean getBoolean(@NotNull String path) {
        return delegate.getBoolean(path);
    }

    @Override
    public boolean getBoolean(@NotNull String path, boolean def) {
        return delegate.getBoolean(path, def);
    }

    @Override
    public boolean isBoolean(@NotNull String path) {
        return delegate.isBoolean(path);
    }

    @Override
    public double getDouble(@NotNull String path) {
        return delegate.getDouble(path);
    }

    @Override
    public double getDouble(@NotNull String path, double def) {
        return delegate.getDouble(path, def);
    }

    @Override
    public boolean isDouble(@NotNull String path) {
        return delegate.isDouble(path);
    }

    @Override
    public long getLong(@NotNull String path) {
        return delegate.getLong(path);
    }

    @Override
    public long getLong(@NotNull String path, long def) {
        return delegate.getLong(path, def);
    }

    @Override
    public boolean isLong(@NotNull String path) {
        return delegate.isLong(path);
    }

    @Override
    @Nullable
    public List<?> getList(@NotNull String path) {
        return delegate.getList(path);
    }

    @Override
    @Nullable
    public List<?> getList(@NotNull String path, @Nullable List<?> def) {
        return delegate.getList(path, def);
    }

    @Override
    public boolean isList(@NotNull String path) {
        return delegate.isList(path);
    }

    @Override
    @NotNull
    public List<String> getStringList(@NotNull String path) {
        return delegate.getStringList(path);
    }

    @Override
    @NotNull
    public List<Integer> getIntegerList(@NotNull String path) {
        return delegate.getIntegerList(path);
    }

    @Override
    @NotNull
    public List<Boolean> getBooleanList(@NotNull String path) {
        return delegate.getBooleanList(path);
    }

    @Override
    @NotNull
    public List<Double> getDoubleList(@NotNull String path) {
        return delegate.getDoubleList(path);
    }

    @Override
    @NotNull
    public List<Float> getFloatList(@NotNull String path) {
        return delegate.getFloatList(path);
    }

    @Override
    @NotNull
    public List<Long> getLongList(@NotNull String path) {
        return delegate.getLongList(path);
    }

    @Override
    @NotNull
    public List<Byte> getByteList(@NotNull String path) {
        return delegate.getByteList(path);
    }

    @Override
    @NotNull
    public List<Character> getCharacterList(@NotNull String path) {
        return delegate.getCharacterList(path);
    }

    @Override
    @NotNull
    public List<Short> getShortList(@NotNull String path) {
        return delegate.getShortList(path);
    }

    @Override
    @NotNull
    public List<Map<?, ?>> getMapList(@NotNull String path) {
        return delegate.getMapList(path);
    }

    @Override
    @Nullable
    public <T> T getObject(@NotNull String path, @NotNull Class<T> clazz) {
        return delegate.getObject(path, clazz);
    }

    @Override
    @Nullable
    public <T> T getObject(@NotNull String path, @NotNull Class<T> clazz, @Nullable T def) {
        return delegate.getObject(path, clazz, def);
    }

    @Override
    @Nullable
    public <T extends ConfigurationSerializable> T getSerializable(@NotNull String path, @NotNull Class<T> clazz) {
        return delegate.getSerializable(path, clazz);
    }

    @Override
    @Nullable
    public <T extends ConfigurationSerializable> T getSerializable(@NotNull String path, @NotNull Class<T> clazz, @Nullable T def) {
        return delegate.getSerializable(path, clazz, def);
    }

    @Override
    @Nullable
    public Vector getVector(@NotNull String path) {
        return delegate.getVector(path);
    }

    @Override
    @Nullable
    public Vector getVector(@NotNull String path, @Nullable Vector def) {
        return delegate.getVector(path, def);
    }

    @Override
    public boolean isVector(@NotNull String path) {
        return delegate.isVector(path);
    }

    @Override
    @Nullable
    public OfflinePlayer getOfflinePlayer(@NotNull String path) {
        return delegate.getOfflinePlayer(path);
    }

    @Override
    @Nullable
    public OfflinePlayer getOfflinePlayer(@NotNull String path, @Nullable OfflinePlayer def) {
        return delegate.getOfflinePlayer(path, def);
    }

    @Override
    public boolean isOfflinePlayer(@NotNull String path) {
        return delegate.isOfflinePlayer(path);
    }

    @Override
    @Nullable
    public ItemStack getItemStack(@NotNull String path) {
        return delegate.getItemStack(path);
    }

    @Override
    @Nullable
    public ItemStack getItemStack(@NotNull String path, @Nullable ItemStack def) {
        return delegate.getItemStack(path, def);
    }

    @Override
    public boolean isItemStack(@NotNull String path) {
        return delegate.isItemStack(path);
    }

    @Override
    @Nullable
    public Color getColor(@NotNull String path) {
        return delegate.getColor(path);
    }

    @Override
    @Nullable
    public Color getColor(@NotNull String path, @Nullable Color def) {
        return delegate.getColor(path, def);
    }

    @Override
    public boolean isColor(@NotNull String path) {
        return delegate.isColor(path);
    }

    @Override
    @Nullable
    public ConfigurationSection getConfigurationSection(@NotNull String path) {
        return delegate.getConfigurationSection(path);
    }

    @Override
    public boolean isConfigurationSection(@NotNull String path) {
        return delegate.isConfigurationSection(path);
    }

    @Override
    @Nullable
    public ConfigurationSection getDefaultSection() {
        return delegate.getDefaultSection();
    }

    @Override
    public void addDefault(@NotNull String path, @Nullable Object value) {
        delegate.addDefault(path, value);
    }
}
