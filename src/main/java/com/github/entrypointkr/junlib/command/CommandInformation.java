package com.github.entrypointkr.junlib.command;

/**
 * Created by JunHyeong on 2018-10-27
 */
public interface CommandInformation {
    default Usage[] usage() {
        return null;
    }

    default String description() {
        return null;
    }
}
