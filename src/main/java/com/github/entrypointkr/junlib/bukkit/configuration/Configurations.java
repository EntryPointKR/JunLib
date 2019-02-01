package com.github.entrypointkr.junlib.bukkit.configuration;

import com.github.entrypointkr.junlib.util.FileUtils;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * Created by JunHyeong on 2018-11-03
 */
public class Configurations {
    private Configurations() {
    }

    public static void load(FileConfiguration config, File file, Charset charset) throws IOException, InvalidConfigurationException {
        if (file.isFile()) {
            StringBuilder builder = new StringBuilder();
            for (String line : Files.readAllLines(file.toPath(), charset)) {
                builder.append(line).append('\n');
            }
            config.loadFromString(builder.toString());
        }
    }

    public static void load(FileConfiguration config, Plugin plugin, String fileName, Charset charset) throws IOException, InvalidConfigurationException {
        load(config, FileUtils.createFile(plugin, fileName), charset);
    }

    public static void load(FileConfiguration config, Plugin plugin, String fileName) throws IOException, InvalidConfigurationException {
        load(config, plugin, fileName, StandardCharsets.UTF_8);
    }

    public static void load(FileConfiguration config, File file) throws IOException, InvalidConfigurationException {
        load(config, file, StandardCharsets.UTF_8);
    }

    public static void save(FileConfiguration config, File file, Charset charset) throws IOException {
        Files.write(FileUtils.writeEnsure(file).toPath(), config.saveToString().getBytes(charset));
    }

    public static void save(FileConfiguration config, Plugin plugin, String fileName, Charset charset) throws IOException {
        save(config, FileUtils.createFile(plugin, fileName), charset);
    }

    public static void save(FileConfiguration config, Plugin plugin, String fileName) throws IOException {
        save(config, FileUtils.createFile(plugin, fileName), StandardCharsets.UTF_8);
    }

    public static void save(FileConfiguration config, File file) throws IOException {
        save(config, file, StandardCharsets.UTF_8);
    }
}
