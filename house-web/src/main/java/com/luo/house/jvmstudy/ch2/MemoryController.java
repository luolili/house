package com.luo.house.jvmstudy.ch2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class MemoryController {

    private List<User> userList = new ArrayList<>();
    private List<Class<?>> classList = new ArrayList<>();

    /**
     * -Xmx32M -Xms32M
     * Exception in thread "http-nio-8080-exec-1" java.lang.OutOfMemoryError: Java heap space
     * <p>
     * 导出 内存溢出的文件
     * 方法1：
     * -Xmx32M -Xms32M  -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./
     * 导出 到项目跟目录下面
     * <p>
     * 2. cmd jmap -help
     * jps -l
     * result:
     * C:\Users\Administrator>jps -l
     * 9184 sun.tools.jps.Jps
     * 2504
     * 10252 org.gradle.launcher.daemon.bootstrap.GradleDaemon
     * 10524 com.luo.house.HomeApp  进程id: 10524
     * 12028 org.jetbrains.idea.maven.server.RemoteMavenServer
     * 8380 org.jetbrains.jps.cmdline.Launcher
     * 9244 org.jetbrains.kotlin.daemon.KotlinCompileDaemon
     * <p>
     * cd Desktop
     * C:\Users\Administrator\Desktop>jmap -dump:format=b,file=heap.hprof 10524
     * Dumping heap to C:\Users\Administrator\Desktop\heap.hprof ...
     * Heap dump file created
     * 在桌面有一个 文件： heap.hprof
     *
     * 如何分析这个文件？ 用mat:Memory Analyzer tool
     *
     */
    @GetMapping("/heap")
    public String heap() {
        int i = 0;
        while (true) {
            userList.add(new User(i++, UUID.randomUUID().toString()));
        }


    }

    /**
     * -XX:MetaspaceSize=32M -XX:MaxMetaspaceSize=32M
     * java.lang.OutOfMemoryError: Metaspace
     *
     * @return
     */
    @GetMapping("/nonheap")
    public String nonheap() {
        int i = 0;
        while (true) {
            classList.addAll(Metaspace.createClasses());
        }
    }
}
