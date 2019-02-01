package com.github.entrypointkr.junlib;

import com.github.entrypointkr.junlib.command.*;
import org.bukkit.Server;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.entity.Player;
import org.junit.Test;

/**
 * Created by JunHyeong on 2018-10-14
 */
public class CommandTest {
    private final Server server = MockFactory.createServer(MockFactory.createPluginManager());
    private final CommandMap commandMap = new SimpleCommandMap(server);
    private final CommandManager commandManager = new CommandManager(commandMap);

    public CommandTest() {
        MockFactory.injectServer(server);
    }

    @Test
    public void command() {
        Command<BukkitSource> healCommand = CommandBuilder.ofBukkit()
                .perm("junlibrary.heal")
                .executor((sender, args) -> {
                    Player player = sender.toPlayer();
                    player.setHealth(player.getMaxHealth());
                    player.sendMessage("완료");
                })
                .build();
        Command<BukkitSource> parent = CommandBuilder.ofBukkit()
                .child(healCommand, "heal")
                .helper(new DefaultCommandHelper())
                .build();
        commandManager.registerCommand(MockFactory.createPlugin(), parent, "junlibrary", "junlib", "jl");
        commandMap.dispatch(MockFactory.createSender(), "junlibrary");
    }
}
