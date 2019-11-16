package com.luo.house.jvmstudy.ch4;

import com.sun.btrace.AnyType;
import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;

/**
 * 执行他：
 * cd 到把他的包路径，
 * f:
 * dir
 * jps -l 查看 pid
 * btrace pid PrintArgSimple.java
 * 结果 会打印到控制台:cmd
 */
@BTrace
public class PrintArgSimple {
    // 字节码层面 拦截 test方法：普通方法
    @OnMethod(
            clazz = "com.luo.house.jvmstudy.ch4.Ch4Controller",
            method = "test",
            location = @Location(Kind.ENTRY)//拦截的时候
    )
    public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, AnyType[] args) {
        BTraceUtils.printArray(args);
        BTraceUtils.println(pcn + "," + pmn);//类名字+方法名
        BTraceUtils.println();
    }
}
