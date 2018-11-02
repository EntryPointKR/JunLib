package com.github.entrypointkr.junlib.util;

import org.apache.commons.lang.StringUtils;

/**
 * Created by JunHyeong on 2018-10-19
 */
public abstract class AbstractStringArrayReader extends AbstractArrayReader<String> {
    public AbstractStringArrayReader(String[] array) {
        super(array);
    }

    public AbstractStringArrayReader(String string, String splitRegex, int limit) {
        this(string.split(splitRegex, limit));
    }

    public AbstractStringArrayReader(String string, String splitRegex) {
        this(string, splitRegex, 0);
    }

    public AbstractStringArrayReader(String spaced) {
        this(StringUtils.split(spaced, ' '));
    }
}
