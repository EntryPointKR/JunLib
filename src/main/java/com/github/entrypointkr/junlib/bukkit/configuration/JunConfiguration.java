package com.github.entrypointkr.junlib.bukkit.configuration;

import com.github.entrypointkr.junlib.bukkit.util.Runnables;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.nio.charset.StandardCharsets;

/**
 * Created by JunHyeong on 2018-11-09
 */
public class JunConfiguration extends YamlConfiguration {
    public void load(Plugin plugin, String fileName) {
        Runnables.runOrLog(plugin, () -> Configurations.load(this, plugin, fileName, StandardCharsets.UTF_8));
    }

    public void save(Plugin plugin, String fileName) {
        Runnables.runOrLog(plugin, () -> Configurations.save(this, plugin, fileName, StandardCharsets.UTF_8));
    }
}
