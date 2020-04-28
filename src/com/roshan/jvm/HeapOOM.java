package com.roshan.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出异常测试
 * VM args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=dumpFiles
 */
public class HeapOOM {
    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
