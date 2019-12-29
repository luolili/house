package com.luo.house.jvmstudy.memorylook;

import org.openjdk.jol.info.ClassLayout;

/**
 * jol 使用
 */
public class StringMemory {
    public static void main(String[] args) {
        System.out.println(ClassLayout.parseClass(String.class).toPrintable());
    }
}
