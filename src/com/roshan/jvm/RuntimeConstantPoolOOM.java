package com.roshan.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池导致的内存溢出异常 需要使用jdk1.6
 * VM args: -XX:PermSize=10M -XX:MaxPermSize=10M
 */
public class RuntimeConstantPoolOOM {
//    public static void main(String[] args) {
//        //使用List保持着常量池的引用,避免full GC回收常量池
//        List<String> list = new ArrayList<>();
//        int i = 0;
//        while (true) {
//            list.add(String.valueOf(i++).intern());
//        }
//    }

    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
