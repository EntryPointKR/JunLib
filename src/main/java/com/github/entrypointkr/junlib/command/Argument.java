package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.command.util.Reader;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Argument<T> {
    private final Function<Reader<String>, T> mapper;
    private final TabCompletable<CommandSource> tabCompletar;
    private String name;
    private UnaryOperator<String> failMessageFunc;
    private boolean require;

    public Argument(String name, Function<Reader<String>, T> mapper, TabCompletable<CommandSource> tabCompletar, boolean require) {
        this.name = name;
        this.mapper = mapper;
        this.tabCompletar = tabCompletar;
        this.require = require;
    }

    public Argument(String name, Function<Reader<String>, T> mapper, TabCompletable<CommandSource> tabCompletor) {
        this(name, mapper, tabCompletor, true);
    }

    public Argument(String name, Function<Reader<String>, T> mapper) {
        this(name, mapper, new TabCompletable<CommandSource>() {
        });
    }

    public static Argument<String> string(String name) {
        return new Argument<>(name, Reader::read);
    }

    public static Argument<Player> player(String name) {
        return new Argument<>(name, reader -> Bukkit.getPlayer(reader.read()))
                .message(p -> p + " 는 존재하지 않는 플레이어입니다.");
    }

    public static Argument<World> world(String name) {
        return new Argument<>(name, reader -> Bukkit.getWorld(reader.read()))
                .message(w -> w + " 는 존재하지 않는 월드입니다.");
    }

    public static Argument<String> joinString(String name, String join) {
        return new Argument<>(name, reader -> {
            StringBuilder builder = new StringBuilder();
            while (reader.remain() > 0) {
                if (builder.length() > 0) {
                    builder.append(join);
                }
                builder.append(reader.read());
            }
            return builder.toString();
        });
    }

    public Argument<T> name(String name) {
        this.name = name;
        return this;
    }

    public Argument<T> message(UnaryOperator<String> messageFunc) {
        this.failMessageFunc = messageFunc;
        return this;
    }

    public Argument<T> require(boolean require) {
        this.require = require;
        return this;
    }

    public Argument<T> require() {
        return require(true);
    }

    public Argument<T> optional() {
        return require(false);
    }

    public T parse(Reader<String> reader) {
        int pos = reader.getPosition();
        T ret = mapper.apply(reader);
        if (ret == null) {
            String consumed = reader.getPreviousArguments(pos);
            String message = failMessageFunc != null ? failMessageFunc.apply(consumed) : "";
            throw new ArgumentException(message, this);
        }
        return ret;
    }

    public String getName() {
        return name;
    }

    public boolean isRequire() {
        return require;
    }
}
