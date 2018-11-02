package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.util.ArrayReader;

/**
 * Created by JunHyeong on 2018-10-26
 */
public interface DetailedCommand<T, U extends ArrayReader<String>> extends TabCompleteCommand<T, U>, CommandInformation {
}
