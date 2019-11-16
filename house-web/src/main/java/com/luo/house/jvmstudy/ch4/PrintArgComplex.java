package com.luo.house.jvmstudy.ch4;

import com.luo.house.jvmstudy.ch2.User;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.*;

import java.lang.reflect.Field;

/**
 * btrace -cp "F:\githubpro\house\house-web\target\classes" 13032 PrintArgComplex.java
 */
@BTrace
public class PrintArgComplex {


    @OnMethod(
            clazz = "com.luo.house.jvmstudy.ch4.Ch4Controller",
            method = "constructor",
            location = @Location(Kind.ENTRY)
    )
    public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, User user) {
        //print all fields
        BTraceUtils.printFields(user);
        //print one field
        Field filed2 = BTraceUtils.field("com.luo.house.jvmstudy.ch2.User", "name");
        BTraceUtils.println(BTraceUtils.get(filed2, user));
        BTraceUtils.println(pcn + "," + pmn);
        BTraceUtils.println();
    }
}
