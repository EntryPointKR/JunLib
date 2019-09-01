package com.github.entrypointkr.junlib;

import com.github.entrypointkr.junlib.bukkit.event.CustomEventNotifier;
import com.github.entrypointkr.junlib.bukkit.event.EventManager;
import com.github.entrypointkr.junlib.bukkit.util.Bukkits;
import com.github.entrypointkr.junlib.command.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by JunHyeong on 2018-10-14
 */
public class JunLibrary extends JavaPlugin {
    private final CommandManager commandManager = new CommandManager(Bukkits.getMainCommandMap());
    private final EventManager eventManager = new EventManager();

    public static JunLibrary get() {
        return (JunLibrary) Bukkit.getPluginManager().getPlugin("JunLib");
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    @Override
    public void onEnable() {
        eventManager.inject(this);
        CustomEventNotifier.register(this);
        JunLibraryCommand.register(this);
        new Metrics(this);
    }
}
