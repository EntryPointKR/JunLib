package com.github.entrypointkr.junlib.util;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by JunHyeong on 2018-11-03
 */
public class FileUtils {
    private FileUtils() {
    }

    public static File writeEnsure(File file) throws IOException {
        File parent = file.getParentFile();
        if (!file.exists() && ((parent != null && !parent.exists() && !parent.mkdirs()) || !file.createNewFile())) {
            throw new IllegalStateException("Exception when ensuring file");
        }
        return file;
    }

    public static File createFile(Plugin plugin, String fileName) {
        return new File(plugin.getDataFolder(), fileName);
    }

    public static String read(File file, Charset charset) throws IOException {
        StringBuilder builder = new StringBuilder();
        for (String line : Files.readAllLines(file.toPath(), charset)) {
            if (builder.length() > 0) {
                builder.append('\n');
            }
            builder.append(line);
        }
        return builder.toString();
    }

    public static Optional<String> readOptional(File file, Charset charset) {
        try {
            return Optional.of(read(file, charset));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public static void readYaml(File file, Charset charset, YamlConfiguration config) {
        readOptional(file, charset).ifPresent(lines -> {
            try {
                config.loadFromString(lines);
            } catch (InvalidConfigurationException e) {
                // Ignore
            }
        });
    }

    public static void readYaml(File file, YamlConfiguration config) {
        readYaml(file, StandardCharsets.UTF_8, config);
    }

    public static void write(File file, Charset charset, String lines, Consumer<Exception> exceptionConsumer, OpenOption... options) {
        try {
            Files.write(file.toPath(), lines.getBytes(charset), options);
        } catch (IOException e) {
            exceptionConsumer.accept(e);
        }
    }

    public static void write(File file, Charset charset, String lines, Logger logger, OpenOption... options) {
        write(file, charset, lines, ex -> logger.log(Level.WARNING, "Errors when file write: " + file.getAbsolutePath()), options);
    }

    public static void write(File file, Charset charset, String lines, OpenOption... options) {
        write(file, charset, lines, ex -> {
            // Do nothing
        }, options);
    }

    public static void write(File file, String lines, OpenOption... options) {
        write(file, StandardCharsets.UTF_8, lines, options);
    }
}
