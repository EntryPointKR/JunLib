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

    public MapCommand(Map<String, Command<T>> commandMap, Map<Command<T>, List<String>> keyMap, Executable<T> defaultExecutor) {
        this.commandMap = commandMap;
        this.keyMap = keyMap;
        this.defaultExecutor = defaultExecutor;
    }

    public MapCommand(Map<String, Command<T>> commandMap, Map<Command<T>, List<String>> keyMap) {
        this(commandMap, keyMap, (receiver, args) -> {
            throw new CommandException("명령어 도움말");
        });
    }

    public MapCommand() {
        this(new LinkedHashMap<>(), new HashMap<>());
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
    public void forEach(StringBuilder argumentsBuilder, BiConsumer<Command<?>, List<String>> consumer) {
        for (Map.Entry<Command<T>, List<String>> entry : keyMap.entrySet()) {

        }
//        for (Map.Entry<String, Command<T>> entry : commandMap.entrySet()) {
//            String argument = entry.getKey();
//            Command command = entry.getValue();
//            int prevSize = argumentsBuilder.length();
//            if (argumentsBuilder.length() > 0) {
//                argumentsBuilder.append(' ');
//            }
//            argumentsBuilder.append(argument);
//            if (command instanceof MapCommand) {
//                ((MapCommand) command).forEach(argumentsBuilder, prefixConsumer);
//            } else {
//                prefixConsumer.accept(argumentsBuilder.toString(), command);
//            }
//            argumentsBuilder.setLength(prevSize);
//        }
    }

    @Override
    public int size() {
        return commandMap.size();
    }
}
