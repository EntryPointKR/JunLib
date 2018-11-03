package com.github.entrypointkr.junlib.bukkit.configuration;

import com.github.entrypointkr.junlib.util.FileUtils;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

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

    public static void load(FileConfiguration config, File file) throws IOException, InvalidConfigurationException {
        if (file.isFile()) {
            config.load(file);
        }
    }

    public static void save(FileConfiguration config, File file, Charset charset) throws IOException {
        FileUtils.ensure(file);
        Files.write(file.toPath(), config.saveToString().getBytes(charset));
    }

    public static void save(FileConfiguration config, File file) throws IOException {
        save(config, file, StandardCharsets.UTF_8);
    }
}
