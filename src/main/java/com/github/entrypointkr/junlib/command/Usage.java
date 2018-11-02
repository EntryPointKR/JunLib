package com.github.entrypointkr.junlib.command;

import java.util.stream.Stream;

public class Usage {
    private final String usage;
    private final boolean require;

    public Usage(String usage, boolean require) {
        this.usage = usage;
        this.require = require;
    }

    public static Usage of(String usage, boolean require) {
        return new Usage(usage, require);
    }

    public static Usage ofRequire(String usage) {
        return new Usage(usage, true);
    }

    public static Usage[] ofRequires(String... usages) {
        return Stream.of(usages).map(Usage::ofRequire).toArray(Usage[]::new);
    }

    public static Usage ofOption(String usage) {
        return new Usage(usage, false);
    }

    public static Usage[] ofOptions(String... options) {
        return Stream.of(options).map(Usage::ofOption).toArray(Usage[]::new);
    }

    public String getUsage() {
        return usage;
    }

    public boolean isRequire() {
        return require;
    }
}
