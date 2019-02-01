package com.github.entrypointkr.junlib.bukkit.util;

import com.github.entrypointkr.junlib.util.ArrayReader;
import com.github.entrypointkr.junlib.util.Converter;

/**
 * Created by JunHyeong on 2018-10-14
 */
public interface BukkitArrayReader extends ArrayReader<String> {
    default Converter<BukkitStringConverter> readConvert() {
        return Converter.ofCommand(hasRemain() ? read() : null, BukkitStringConverter::new, str -> "인자가 부족합니다.");
    }

    default Converter<BukkitStringConverter> getConvert(int index) {
        return Converter.ofCommand(getOptional(index).orElse(null), BukkitStringConverter::new, str -> (index + 1) + " 번째 인자가 누락되었습니다..");
    }
}
