package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.command.exception.ArgumentException;
import com.github.entrypointkr.junlib.command.exception.CommandException;
import com.github.entrypointkr.junlib.command.exception.NoPermissionException;
import com.github.entrypointkr.junlib.command.util.Reader;
import com.github.entrypointkr.junlib.util.Pair;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

/**
 * Created by JunHyeong Lim on 2019-01-20
 */
public class DefaultCommandHelper implements CommandHelper {
    private String getArgumentFormat(Argument<?> argument) {
        return argument.isRequire() ? "(%s)" : "[%s]";
    }

    private String getInformation(CommandSource source, String arguments, Executable executable) {
        StringBuilder builder = new StringBuilder("/").append(arguments);
        if (executable instanceof Detailed) {
            Detailed detailed = ((Detailed) executable);
            Argument<?>[] detailedArgs = detailed.dynamicArguments(source);
            String desc = detailed.description();
            if (detailedArgs != null) {
                for (Argument<?> argument : detailedArgs) {
                    if (builder.length() > 1) {
                        builder.append(' ');
                    }
                    builder.append(String.format(getArgumentFormat(argument), argument.getName()));
                }
            }
            if (desc != null) {
                builder.append(": ").append(desc);
            }
        }
        return builder.toString();
    }

    private Optional<String> getHeader(Throwable ex) {
        if (ex instanceof NoPermissionException) {
            return Optional.of(String.format("권한 %s 이 없습니다.", ((NoPermissionException) ex).getPermission()));
        } else if (ex instanceof ArgumentException) {
            return Optional.of("사용법이 잘못되었습니다.");
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void help(CommandSource receiver, Reader<String> args, String prefix, CommandException ex) {
        StringBuilder prefixBuilder = new StringBuilder(prefix);
        Executable source = ex.getSource();
        Throwable cause = ex.getCause();
        receiver.sendMessage(ChatColor.RED + getHeader(cause).orElse(ex.getMessage()));
        receiver.sendMessage(ChatColor.GRAY + "() = 필수, [] = 선택");
        if (cause != null && !cause.getClass().isAnnotationPresent(InternalException.class)) {
            Bukkit.getLogger().log(Level.WARNING, ex.getMessage(), ex);
        }
        if (source instanceof Combined) {
            List<Pair<String, Command<?>>> executables = new ArrayList<>();
            List<Pair<String, Command<?>>> noPermissions = new ArrayList<>();
            ((Combined) source).deepForEach(prefixBuilder, (arguments, command) -> {
                Pair<String, Command<?>> pair = new Pair<>(arguments, command);
                if (command instanceof Detailed) {
                    String permission = ((Detailed) command).permission();
                    if (permission != null && !receiver.hasPermission(permission)) {
                        noPermissions.add(pair);
                        return;
                    }
                }
                executables.add(pair);
            });
            for (Pair<String, Command<?>> pair : executables) {
                receiver.sendMessage(getInformation(receiver, pair.getFirst(), pair.getSecond()));
            }
            if (!noPermissions.isEmpty()) {
                for (String arg : args) {
                    if ("-all".equals(arg)) {
                        for (Pair<String, Command<?>> pair : noPermissions) {
                            receiver.sendMessage(ChatColor.GRAY + getInformation(receiver, pair.getFirst(), pair.getSecond()));
                        }
                        return;
                    }
                }
                receiver.sendMessage(ChatColor.GRAY + String.format("... 숨겨진 권한 외 명령어 %s 개, 모두 보려면 -all", noPermissions.size()));
            }
        } else {
            receiver.sendMessage(getInformation(receiver, prefix, source));
        }
    }
}
