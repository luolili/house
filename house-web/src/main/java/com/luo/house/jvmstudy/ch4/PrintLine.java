package com.luo.house.jvmstudy.ch4;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;

/**
 * 看哪些行 执行了，那些没有执行，只可测试本地的代码
 */
@BTrace
public class PrintLine {

    @OnMethod(
            clazz = "com.luo.house.jvmstudy.ch4.Ch4Controller",
            method = "exception",
            location = @Location(value = Kind.LINE, line = -1)
    )
    public static void anyRead(@ProbeClassName String pcn, @ProbeMethodName String pmn, int line) {
        BTraceUtils.println(pcn + "," + pmn + "," + line);
        BTraceUtils.println();
    }
}
