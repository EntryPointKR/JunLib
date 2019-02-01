package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.command.util.Reader;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.function.Function;
import java.util.function.Supplier;

public class Argument<T> {
    private final String name;
    private final Function<Reader<String>, T> mapper;
    private final TabCompletable<CommandSource> tabCompletar;
    private Supplier<T> def;

    public Argument(String name, Function<Reader<String>, T> mapper, TabCompletable<CommandSource> tabCompletar, Supplier<T> def) {
        this.name = name;
        this.mapper = mapper;
        this.tabCompletar = tabCompletar;
        this.def = def;
    }

    public Argument(String name, Function<Reader<String>, T> mapper, TabCompletable<CommandSource> tabCompletar) {
        this(name, mapper, tabCompletar, null);
    }

    public Argument(String name, Function<Reader<String>, T> mapper) {
        this(name, mapper, new TabCompletable<CommandSource>() {
        });
    }

    public static Argument<String> string(String name) {
        return new Argument<>(name, Reader::read);
    }

    public static Argument<Player> player(String name) {
        return new Argument<>(name, reader -> Bukkit.getPlayer(reader.read()));
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

    public Argument<T> def(Supplier<T> def) {
        this.def = def;
        return this;
    }

    public T parse(Reader<String> reader) {
        T ret = mapper.apply(reader);
        return ret != null ? ret : def.get();
    }

    public String getName() {
        return name;
    }

    public Supplier<T> getDefault() {
        return def;
    }
}
