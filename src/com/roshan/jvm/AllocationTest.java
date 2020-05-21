package com.roshan.jvm;

/**
 * 测试对象内存分配规则
 * VM args: -XX:+PrintGC -XX:+PrintGCDetails -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
 */
public class AllocationTest {
    public static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        /**
         * 此时Eden区已经存在3个2MB对象,共10MB剩余不足以分配4MB,会触发gc,gc时发现to区只
         * 有1MB不足以放下2MB的对象,会把allocation123都移入老年代中,gc结束Eden足以分配4MB
         */
        allocation4 = new byte[4 * _1MB];
    }
}
