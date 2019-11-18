package com.luo.house.jvmstudy.ch8;

/**
 * 基于 栈 架构：指令短而多
 * for i++,++i的字节码是一样的，效率一样高
 * jdk5:字符串+：StringBuffer,jdk5之后：StringBuilder
 */
public class Test1 {
    public static void main(String[] args) {
        int a = 2;
        int b = 1;
        int c = a + b;
        System.out.println(c);
    }
}
