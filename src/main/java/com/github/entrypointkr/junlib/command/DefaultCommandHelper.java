package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.command.util.Reader;
import com.github.entrypointkr.junlib.util.Pair;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by JunHyeong Lim on 2019-01-20
 */
public class DefaultCommandHelper implements CommandHelper {
    private String getArgumentFormat(Argument<?> argument) {
        return argument.getDefault() != null ? "[%s]" : "(%s)";
    }

    private String getInformation(String arguments, Executable executable) {
        StringBuilder builder = new StringBuilder("/").append(arguments);
        if (executable instanceof Detailed) {
            Detailed detailed = ((Detailed) executable);
            Argument<?>[] detailedArgs = detailed.arguments();
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

    private String getHeader(Exception ex) {
        if (ex instanceof NoPermissionException) {
            return String.format("권한 %s 이 없습니다.", ((NoPermissionException) ex).getPermission());
        } else if (ex instanceof ArgumentParseException) {
            return "사용법이 잘못되었습니다.";
        } else {
            return ex.getMessage();
        }
    }

    @Override
    public void help(CommandSource receiver, Reader<String> args, String prefix, CommandException ex) {
        StringBuilder prefixBuilder = new StringBuilder(prefix);
        Executable source = ex.getSource();
        Throwable cause = ex.getCause();
        receiver.sendMessage(ChatColor.RED + getHeader(ex));
        if (cause != null && !(cause instanceof CommandException)) {
            Bukkit.getLogger().log(Level.WARNING, ex.getMessage(), ex);
        }
        if (source instanceof Combined) {
            List<Pair<String, Executable>> executables = new ArrayList<>();
            ((Combined) source).forEach(new StringBuilder(), (arguments, command) -> executables.add(new Pair<>(arguments, command)));
            for (Pair<String, Executable> pair : executables) {
                int prev = prefixBuilder.length();
                if (prefixBuilder.length() > 0) {
                    prefixBuilder.append(' ');
                }
                prefixBuilder.append(pair.getFirst());
                receiver.sendMessage(getInformation(prefixBuilder.toString(), pair.getSecond()));
                prefixBuilder.setLength(prev);
            }
        } else {
            receiver.sendMessage(getInformation(prefix, source));
        }
    }
}
