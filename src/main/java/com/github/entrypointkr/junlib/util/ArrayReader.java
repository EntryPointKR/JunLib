package com.github.entrypointkr.junlib.util;

import java.util.Optional;

/**
 * Created by JunHyeong on 2018-10-14
 */
public interface ArrayReader<T> { // TODO: implement List?
    int remain();

    boolean hasRemain();

    void reset();

    T read();

    T peek();

    T get(int index);

    T getExplicit(int index);

    Optional<T> getOptional(int index);

    T[] getInternalArray();
}
