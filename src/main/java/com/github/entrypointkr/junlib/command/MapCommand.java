package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.command.util.Reader;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * Created by JunHyeong Lim on 2019-01-20
 */
public class MapCommand<T extends CommandSource> implements Command<T>, Combined {
    private final Map<String, Command<T>> commandMap;
    private final Map<Command<T>, List<String>> keyMap;
    private Executable<T> defaultExecutor;

    private MapCommand(Map<String, Command<T>> commandMap, Map<Command<T>, List<String>> keyMap, Executable<T> defaultExecutor) {
        this.commandMap = commandMap;
        this.keyMap = keyMap;
        this.defaultExecutor = defaultExecutor;
    }

    public static <T extends CommandSource> MapCommand<T> of(
            Map<String, Command<T>> commandMap,
            Map<Command<T>, List<String>> keyMap,
            Executable<T> defaultExecutor
    ) {
        return new MapCommand<>(commandMap, keyMap, defaultExecutor);
    }

    public static <T extends CommandSource> MapCommand<T> of(
            Map<String, Command<T>> commandMap,
            Map<Command<T>, List<String>> keyMap
    ) {
        return of(commandMap, keyMap, (receiver, args) -> {
            throw new CommandException("명령어 도움말");
        });
    }

    public static <T extends CommandSource> MapCommand<T> of(Map<String, Command<T>> commandMap) {
        Map<Command<T>, List<String>> keyMap = new HashMap<>();
        for (Map.Entry<String, Command<T>> entry : commandMap.entrySet()) {
            keyMap.computeIfAbsent(entry.getValue(), k -> new ArrayList<>()).add(entry.getKey());
        }
        return of(commandMap, keyMap);
    }

    public static <T extends CommandSource> MapCommand<T> of() {
        return of(new LinkedHashMap<>(), new HashMap<>());
    }

    private Executable getSource(Executable executed) {
        return executed != defaultExecutor ? executed : this;
    }

    @Override
    public void execute(T receiver, Reader<String> args) {
        Executable<T> executor = readExecutor(args);
        try {
            executor.execute(receiver, args);
        } catch (CommandException ex) {
            Executable source = ex.getSource();
            ex.setSource(source != null ? source : getSource(executor));
            throw ex;
        } catch (Exception ex) {
            throw new CommandException(ex, getSource(executor));
        }
    }

    @Override
    public List<String> tabComplete(T receiver, Reader<String> args) {
        if (args.remain() > 0) {
            String argument = args.read();
            Command<T> command = commandMap.get(argument);
            if (command != null) {
                return command.tabComplete(receiver, args);
            } else if (args.remain() <= 0) {
                return commandMap.keySet().stream()
                        .filter(arg -> arg.startsWith(argument))
                        .collect(Collectors.toList());
            }
        }
        return Collections.emptyList();
    }

    public MapCommand<T> defaultExecutor(Executable<T> defaultExecutor) {
        this.defaultExecutor = defaultExecutor;
        return this;
    }

    public MapCommand<T> put(Command<T> command, String... aliases) {
        for (String alias : aliases) {
            commandMap.put(alias, command);
            keyMap.computeIfAbsent(command, k -> new ArrayList<>())
                    .addAll(Arrays.asList(aliases));
        }
        return this;
    }

    public Executable<T> readExecutor(Reader<String> args) {
        if (args.size() > 0) {
            Command<T> command = commandMap.get(args.get(0));
            if (command != null) {
                args.read();
                return command;
            }
        }
        return defaultExecutor;
    }

    @Override
    public void forEach(BiConsumer<Command<?>, List<String>> consumer) {
        for (Map.Entry<Command<T>, List<String>> entry : keyMap.entrySet()) {
            consumer.accept(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public int size() {
        return commandMap.size();
    }
}
