package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.util.ArrayReader;

/**
 * Created by JunHyeong on 2018-10-26
 */
public interface ICommand<T, U extends ArrayReader<String>> {
    void execute(T sender, U args);
}
