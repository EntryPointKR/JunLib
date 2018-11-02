package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.util.ArrayReader;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by JunHyeong on 2018-10-22
 */
public interface CommandHelpWriter<T, U extends ArrayReader<String>> {
    void write(StringBuilder builder, ICommand<T, U> from, Supplier<List<Map.Entry<String, ICommand<T, U>>>> flattedPairsSupplier, T sender, String flattedArgs);
}
