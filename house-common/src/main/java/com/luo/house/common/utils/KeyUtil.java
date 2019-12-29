package com.luo.house.common.utils;

import java.util.Random;

public class KeyUtil {

    public static String genUniqueKey() {
        Random r = new Random();
        int i = r.nextInt(900000) + 10;
        long l = System.currentTimeMillis();
        return l + String.valueOf(i);
    }
}
