package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.util.ArrayReader;

import java.util.List;

/**
 * Created by JunHyeong on 2018-10-27
 */
public interface TabCompler<T, U extends ArrayReader<String>> {
    default List<String> tabComplete(T sender, U args) {
        return null;
    }
}
