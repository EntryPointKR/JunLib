package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.bukkit.command.CommandSenderEx;
import com.github.entrypointkr.junlib.bukkit.util.BukkitArrayReader;
import com.github.entrypointkr.junlib.util.ArrayReader;
import org.bukkit.command.CommandException;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * Created by JunHyeong on 2018-10-26
 */
public class MapCommand<T, U extends ArrayReader<String>> implements TabCompleteCommand<T, U> {
    private final Map<String, ICommand<T, U>> commandMap = new LinkedHashMap<>();
    private final ICommand<T, U> defaultCommand;
    private int size = 0;

    private MapCommand(ICommand<T, U> defaultCommand) {
        this.defaultCommand = defaultCommand;
    }

    public static int computeSize(ICommand command) {
        return command instanceof MapCommand
                ? ((MapCommand) command).getSize()
                : 1;
    }

    public static <T, U extends ArrayReader<String>> MapCommand<T, U> of(ICommand<T, U> defaultCommand) {
        return new MapCommand<>(defaultCommand);
    }

    public static MapCommand<CommandSenderEx, BukkitArrayReader> ofBukkit() {
        return of((sender, args) -> {
            throw new CommandException();
        });
    }

    @Override
    public void execute(T sender, U args) {
        if (args.hasRemain() && commandMap.containsKey(args.peek())) {
            commandMap.get(args.read()).execute(sender, args);
        } else {
            defaultCommand.execute(sender, args);
        }
    }

    @Override
    public List<String> tabComplete(T sender, U args) {
        if (args.hasRemain()) {
            String argument = args.read();
            ICommand<T, U> command = commandMap.get(argument);
            if (command instanceof TabCompleteCommand) {
                return ((TabCompleteCommand<T, U>) command).tabComplete(sender, args);
            } else if (!args.hasRemain()) {
                return commandMap.keySet().stream()
                        .filter(arg -> arg.startsWith(argument))
                        .collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }

    public ICommand<T, U> get(String argument) {
        return commandMap.get(argument);
    }

    public ICommand<T, U> put(String argument, ICommand<T, U> command) {
        ICommand<T, U> original = commandMap.put(argument, command);
        if (original != null) {
            size -= computeSize(original);
        }
        size += computeSize(command);
        return original;
    }

    public MapCommand<T, U> put(ICommand<T, U> command, String... aliases) {
        for (String alias : aliases) {
            put(alias, command);
        }
        return this;
    }

    public ICommand<T, U> remove(String argument) {
        ICommand<T, U> removed = commandMap.remove(argument);
        if (removed != null) {
            size -= computeSize(removed);
        }
        return commandMap.remove(argument);
    }

    public MapCommand<T, U> remove(String... aliases) {
        for (String alias : aliases) {
            remove(alias);
        }
        return this;
    }

    public Set<Map.Entry<String, ICommand<T, U>>> entrySet() {
        return commandMap.entrySet();
    }

    public void forEach(StringBuilder prefixBuilder, BiConsumer<StringBuilder, ICommand<T, U>> prefixConsumer) {
        for (Map.Entry<String, ICommand<T, U>> entry : entrySet()) {
            String argument = entry.getKey();
            ICommand<T, U> command = entry.getValue();
            int prevSize = prefixBuilder.length();
            if (prefixBuilder.length() > 0) {
                prefixBuilder.append(' ');
            }
            prefixBuilder.append(argument);
            if (command instanceof MapCommand) {
                ((MapCommand<T, U>) command).forEach(prefixBuilder, prefixConsumer);
            } else {
                prefixConsumer.accept(prefixBuilder, command);
            }
            prefixBuilder.setLength(prevSize);
        }
    }

    public int getSize() {
        return size;
    }

    public List<Map.Entry<String, ICommand<T, U>>> createFlattenPairs(StringBuilder prefixBuilder) {
        List<Map.Entry<String, ICommand<T, U>>> list = new ArrayList<>(getSize());
        forEach(prefixBuilder, (prefix, command) -> list.add(new AbstractMap.SimpleImmutableEntry<>(prefix.toString(), command)));
        return list;
    }

    public List<Map.Entry<String, ICommand<T, U>>> createFlattenPairs() {
        return createFlattenPairs(new StringBuilder());
    }
}
