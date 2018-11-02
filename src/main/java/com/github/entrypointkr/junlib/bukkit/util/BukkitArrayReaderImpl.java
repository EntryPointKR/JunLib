package com.github.entrypointkr.junlib.bukkit.util;

import com.github.entrypointkr.junlib.util.AbstractStringArrayReader;

/**
 * Created by JunHyeong on 2018-10-14
 */
public class BukkitArrayReaderImpl extends AbstractStringArrayReader implements BukkitArrayReader {
    public static final BukkitArrayReaderImpl EMPTY = new BukkitArrayReaderImpl(new String[0]);

    public BukkitArrayReaderImpl(String[] array) {
        super(array);
    }

    public BukkitArrayReaderImpl(String string, String splitRegex, int limit) {
        super(string, splitRegex, limit);
    }

    public BukkitArrayReaderImpl(String string, String splitRegex) {
        super(string, splitRegex);
    }

    public BukkitArrayReaderImpl(String spaced) {
        super(spaced);
    }
}
