package com.github.entrypointkr.junlib.util;

import java.io.File;
import java.io.IOException;

/**
 * Created by JunHyeong on 2018-11-03
 */
public class FileUtils {
    private FileUtils() {
    }

    public static void ensure(File file) throws IOException {
        if (!file.isFile()) {
            File parent = file.getParentFile();
            if (!parent.isDirectory() && !parent.mkdirs()) {
                throw new IllegalStateException("Failed mkdirs");
            }
            if (!file.createNewFile()) {
                throw new IllegalStateException("Failed createNewFile");
            }
        }
    }
}
