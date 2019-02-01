package com.github.entrypointkr.junlib;

import com.github.entrypointkr.junlib.mock.MockCommandSender;
import com.github.entrypointkr.junlib.mock.MockPluginManager;
import com.github.entrypointkr.junlib.mock.MockServer;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.PluginManager;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Logger;

public class MockFactory {
    public static void injectServer(Server server) {
        try {
            Field serverField = Bukkit.class.getDeclaredField("server");
            serverField.setAccessible(true);
            serverField.set(null, server);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    public static PluginManager createPluginManager() {
        return new MockPluginManager();
    }

    public static CommandSender createSender() {
        return new MockCommandSender();
    }

    public static Plugin createPlugin() {
        return new Plugin() {
            @Override
            public File getDataFolder() {
                return null;
            }

            @Override
            public PluginDescriptionFile getDescription() {
                return null;
            }

            @Override
            public FileConfiguration getConfig() {
                return null;
            }

            @Override
            public InputStream getResource(String filename) {
                return null;
            }

            @Override
            public void saveConfig() {

            }

            @Override
            public void saveDefaultConfig() {

            }

            @Override
            public void saveResource(String resourcePath, boolean replace) {

            }

            @Override
            public void reloadConfig() {

            }

            @Override
            public PluginLoader getPluginLoader() {
                return null;
            }

            @Override
            public Server getServer() {
                return null;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void onDisable() {

            }

            @Override
            public void onLoad() {

            }

            @Override
            public void onEnable() {

            }

            @Override
            public boolean isNaggable() {
                return false;
            }

            @Override
            public void setNaggable(boolean canNag) {

            }

            @Override
            public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
                return null;
            }

            @Override
            public Logger getLogger() {
                return null;
            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
                return false;
            }

            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                return null;
            }
        };
    }

    public static Server createServer(PluginManager pluginManager) {
        return new MockServer(pluginManager);
    }
}
