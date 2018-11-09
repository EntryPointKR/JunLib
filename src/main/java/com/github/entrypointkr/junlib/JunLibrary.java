package com.github.entrypointkr.junlib;

import com.github.entrypointkr.junlib.bukkit.command.CommandManager;
import com.github.entrypointkr.junlib.bukkit.event.CustomEventNotifier;
import com.github.entrypointkr.junlib.bukkit.event.Events;
import com.github.entrypointkr.junlib.bukkit.util.Bukkits;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by JunHyeong on 2018-10-14
 */
public class JunLibrary extends JavaPlugin {
    private static final CommandManager commandManager = new CommandManager(Bukkits.getMainCommandMap());

    public static JunLibrary getPlugin() {
        return (JunLibrary) Bukkit.getPluginManager().getPlugin("JunLib");
    }

    public static CommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public void onEnable() {
        Events.inject(this);
        JunLibraryCommand.register(this);
        CustomEventNotifier.register(this);
        new Metrics(this);
    }
}
