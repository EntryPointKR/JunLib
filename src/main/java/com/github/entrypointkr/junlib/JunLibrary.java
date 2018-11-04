package com.github.entrypointkr.junlib;

import com.github.entrypointkr.junlib.bukkit.command.CommandManager;
import com.github.entrypointkr.junlib.bukkit.event.Events;
import com.github.entrypointkr.junlib.bukkit.util.Bukkits;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by JunHyeong on 2018-10-14
 */
public class JunLibrary extends JavaPlugin {
    private static final CommandManager COMMAND_MANAGER = new CommandManager(Bukkits.getMainCommandMap());

    public static CommandManager getCommandManager() {
        return COMMAND_MANAGER;
    }

    @Override
    public void onEnable() {
        Events.init(this);
        JunLibraryCommand.register(this);
        new Metrics(this);
    }
}
