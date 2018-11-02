package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.util.ArrayReader;

/**
 * Created by JunHyeong on 2018-10-27
 */
public interface TabCompleteCommand<T, U extends ArrayReader<String>> extends ICommand<T, U>, TabCompler<T, U> {
}
