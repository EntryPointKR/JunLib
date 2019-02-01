package com.github.entrypointkr.junlib.command;

import com.github.entrypointkr.junlib.command.util.Reader;

import java.util.Collections;
import java.util.List;

/**
 * Created by JunHyeong Lim on 2019-01-20
 */
public interface TabCompletable<T extends CommandSource> {
    default List<String> tabComplete(T receiver, Reader<String> args) {
        return Collections.emptyList();
    }
}
