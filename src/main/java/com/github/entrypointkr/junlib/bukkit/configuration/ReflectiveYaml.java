package com.github.entrypointkr.junlib.bukkit.configuration;

import com.github.entrypointkr.junlib.util.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConstructor;
import org.bukkit.configuration.file.YamlRepresenter;
import org.bukkit.plugin.Plugin;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Optional;
import java.util.logging.Level;

/**
 * Created by JunHyeong on 2018-11-03
 */
public class ReflectiveYaml {
    private final Yaml yaml = new Yaml(new YamlConstructor(), new YamlRepresenter(), new DumperOptions());

    public <T> Optional<T> read(Reader reader) {
        if (!(reader instanceof BufferedReader)) {
            reader = new BufferedReader(reader);
        }
        return Optional.ofNullable(yaml.load(reader));
    }

    public <T> Optional<T> read(File file) {
        if (file.isFile()) {
            try {
                return read(new BufferedReader(new FileReader(file)));
            } catch (FileNotFoundException e) {
                // Ignore
            }
        }
        return Optional.empty();
    }

    public <T> Optional<T> read(Plugin plugin, String fileName) {
        return read(new File(plugin.getDataFolder(), fileName));
    }

    public void write(Object object, Writer writer) {
        yaml.dump(object, writer);
    }

    public void write(Object object, File file) {
        try {
            FileUtils.ensure(file);
            write(object, new BufferedWriter(new FileWriter(file)));
        } catch (Exception ex) {
            Bukkit.getLogger().log(Level.WARNING, ex, () -> "Exception was thrown");
        }
    }
}
