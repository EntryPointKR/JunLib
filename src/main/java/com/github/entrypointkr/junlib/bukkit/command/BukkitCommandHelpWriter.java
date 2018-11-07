package com.github.entrypointkr.junlib.bukkit.command;

import com.github.entrypointkr.junlib.bukkit.util.BukkitArrayReader;
import com.github.entrypointkr.junlib.command.CommandHelpException;
import com.github.entrypointkr.junlib.command.CommandHelpWriter;
import com.github.entrypointkr.junlib.command.DetailedCommand;
import com.github.entrypointkr.junlib.command.ICommand;
import com.github.entrypointkr.junlib.command.Usage;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandException;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by JunHyeong on 2018-10-22
 */
public class BukkitCommandHelpWriter implements CommandHelpWriter<CommandSenderEx, BukkitArrayReader> {
    private final CommandHelpWriter<CommandSenderEx, BukkitArrayReader> headerWriter;

    public BukkitCommandHelpWriter(CommandHelpWriter<CommandSenderEx, BukkitArrayReader> headerWriter) {
        this.headerWriter = headerWriter;
    }

    public BukkitCommandHelpWriter() {
        this((builder, from, exception, flattedPairsSupplier, sender, flattedArgs) -> {
            if (!(exception instanceof CommandException)) {
                builder.append(ChatColor.RED).append(sender.isOp() ? ExceptionUtils.getFullStackTrace(exception) : exception.getMessage()).append('\n');
            } else if (exception.getMessage() != null) {
                builder.append(ChatColor.RED).append(exception.getMessage()).append('\n');
            } else {
                builder.append(ChatColor.GREEN).append("명령어 도움말").append('\n');
            }
        });
    }

    @Override
    public void write(StringBuilder builder, ICommand<CommandSenderEx, BukkitArrayReader> from, Exception exception, Supplier<List<Map.Entry<String, ICommand<CommandSenderEx, BukkitArrayReader>>>> flattedPairsSupplier, CommandSenderEx sender, String flattedArgs) {
        headerWriter.write(builder, from, exception, flattedPairsSupplier, sender, flattedArgs);
        List<Map.Entry<String, ICommand<CommandSenderEx, BukkitArrayReader>>> entries = flattedPairsSupplier.get();
        if (exception instanceof CommandHelpException) {
            CommandHelpException helpException = ((CommandHelpException) exception);
            if (!helpException.isFullHelpRequire()) {
                List<Map.Entry<String, ICommand<CommandSenderEx, BukkitArrayReader>>> formated = entries.stream()
                        .filter(entry -> entry.getKey().startsWith(flattedArgs))
                        .collect(Collectors.toList());
                if (!formated.isEmpty()) {
                    entries = formated;
                }
            }
        }
        Set<Integer> commandHashSet = new HashSet<>();
        for (Map.Entry<String, ICommand<CommandSenderEx, BukkitArrayReader>> entry : entries) {
            String argument = entry.getKey();
            ICommand<CommandSenderEx, BukkitArrayReader> command = entry.getValue();
            if (!commandHashSet.add(command.hashCode())) {
                continue;
            }
            if (builder.length() > 0) {
                builder.append('\n');
            }
            builder.append(ChatColor.WHITE).append(" /").append(argument);
            writeCommand(builder, command);
        }
    }

    private void writeCommand(StringBuilder builder, ICommand<CommandSenderEx, BukkitArrayReader> command) {
        if (command instanceof DetailedCommand) {
            DetailedCommand<CommandSenderEx, BukkitArrayReader> detailed = ((DetailedCommand<CommandSenderEx, BukkitArrayReader>) command);
            Usage[] usages = detailed.usage();
            String desc = detailed.description();
            if (usages != null && usages.length > 0) {
                for (Usage usage : usages) {
                    builder.append(' ');
                    if (usage.isRequire()) {
                        builder.append('(').append(usage.getUsage()).append(')');
                    } else {
                        builder.append('[').append(usage.getUsage()).append(']');
                    }
                }
            }
            if (desc != null) {
                builder.append(": ").append(desc);
            }
        }
    }
}
