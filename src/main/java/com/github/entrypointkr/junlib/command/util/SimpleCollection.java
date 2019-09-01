package com.github.entrypointkr.junlib.command.util;

/**
 * Created by JunHyeong Lim on 2019-01-20
 */
public interface SimpleCollection<T> extends Iterable<T> {
    T get(int index);

    int size();
}
