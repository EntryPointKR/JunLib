package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.bukkit.command.BukkitCommandHelpWriter;
import com.github.entrypointkr.junlib.bukkit.command.CommandSenderEx;
import com.github.entrypointkr.junlib.bukkit.util.BukkitArrayReader;
import com.github.entrypointkr.junlib.util.ArrayReader;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by JunHyeong on 2018-10-26
 */
public class HelpableCommand<T, U extends ArrayReader<String>> implements TabCompleteCommand<T, U> {
    private final MapCommand<T, U> command;
    private final CommandHelpWriter<T, U> helper;
    private final BiConsumer<T, String> messageSender;
    private final Consumer<Exception> exceptionConsumer;

    private HelpableCommand(MapCommand<T, U> command, CommandHelpWriter<T, U> helper, BiConsumer<T, String> messageSender, Consumer<Exception> exceptionConsumer) {
        this.command = command;
        this.helper = helper;
        this.messageSender = messageSender;
        this.exceptionConsumer = exceptionConsumer;
    }

    public static <T, U extends ArrayReader<String>> HelpableCommand<T, U> of(MapCommand<T, U> command, CommandHelpWriter<T, U> helper, BiConsumer<T, String> messageSender, Consumer<Exception> exceptionConsumer) {
        return new HelpableCommand<>(command, helper, messageSender, exceptionConsumer);
    }

    public static HelpableCommand<CommandSenderEx, BukkitArrayReader> ofBukkit(MapCommand<CommandSenderEx, BukkitArrayReader> command, CommandHelpWriter<CommandSenderEx, BukkitArrayReader> helper, Logger logger) {
        return of(command, helper, CommandSender::sendMessage, ex -> {
            if (!(ex instanceof CommandException)) {
                logger.log(Level.WARNING, ex, () -> "Exception was thrown.");
            }
        });
    }

    public static HelpableCommand<CommandSenderEx, BukkitArrayReader> ofBukkit(MapCommand<CommandSenderEx, BukkitArrayReader> command, Logger logger) {
        return ofBukkit(command, new BukkitCommandHelpWriter(), logger);
    }

    @Override
    public void execute(T sender, U args) {
        try {
            command.execute(sender, args);
        } catch (Exception ex) {
            args.reset();
            if (ex.getMessage() != null) {
                messageSender.accept(sender, ex.getMessage());
            }
            String help = help(sender, args);
            messageSender.accept(sender, help);
            exceptionConsumer.accept(ex);
        }
    }

    @Override
    public List<String> tabComplete(T sender, U args) {
        return command.tabComplete(sender, args);
    }

    public String help(T sender, U args) {
        StringBuilder prefix = new StringBuilder();
        MapCommand<T, U> finded = findMapCommand(prefix, args);
        StringBuilder builder = new StringBuilder();
        helper.write(builder, command, () -> finded.createFlattenPairs(prefix), sender, StringUtils.join(args.getInternalArray(), ' '));
        return builder.toString();
    }

    public MapCommand<T, U> findMapCommand(StringBuilder prefixBuilder, U args) {
        MapCommand<T, U> target = command;
        for (int i = 0; i < args.remain(); i++) {
            String argument = args.get(i);
            ICommand<T, U> command = target.get(argument);
            if (command instanceof MapCommand) {
                if (prefixBuilder.length() > 0) {
                    prefixBuilder.append(' ');
                }
                prefixBuilder.append(argument);
                target = ((MapCommand<T, U>) command);
            }
        }
        return target;
    }
}
