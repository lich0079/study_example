package com.example.springboot;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.util.ArrayList;
import java.util.List;

public class StringInternMemoryTest {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

//        List l = withOutIntern();
        List l = withIntern();
        System.gc();

        System.out.println((System.currentTimeMillis() - start) + " ms");

        printMemory();
    }

    public static List withOutIntern() {
        List l = new ArrayList();
        for (int i = 0; i < 20000000; i++) {
            l.add(String.valueOf(1));
        }
        System.out.println("withOutIntern");
        return l;
        /**
         * withOutIntern
         * 697 ms
         * ##### Heap utilization statistics [MB] #####
         * Used Memory:1000
         * Free Memory:1899
         * Total Memory:2900
         * Max Memory:4096
         */
    }

    public static List withIntern() {
        List l = new ArrayList();
        for (int i = 0; i < 20000000; i++) {
            l.add(String.valueOf(1).intern());
        }
        System.out.println("withIntern");
        return l;
        /**
         * withIntern
         * 1904 ms
         * ##### Heap utilization statistics [MB] #####
         * Used Memory:84
         * Free Memory:203
         * Total Memory:288
         * Max Memory:4096
         */
    }


    public static void printMemory() {
        int mb = 1024*1024;

        //Getting the runtime reference from system
        Runtime runtime = Runtime.getRuntime();

        System.out.println("##### Heap utilization statistics [MB] #####");

        //Print used memory
        System.out.println("Used Memory:"
                + (runtime.totalMemory() - runtime.freeMemory()) / mb);

        //Print free memory
        System.out.println("Free Memory:"
                + runtime.freeMemory() / mb);

        //Print total available memory
        System.out.println("Total Memory:" + runtime.totalMemory() / mb);

        //Print Maximum available memory
        System.out.println("Max Memory:" + runtime.maxMemory() / mb);
    }

//    public static void main(String[] args) {
//        String str1= "abc";
//        String str2= new String("abc");
//        String str3= str2.intern();
//        assertSame(str1==str2);
//        assertSame(str2==str3);
//        assertSame(str1==str3);
//        System.out.println(System.identityHashCode(str1));
//        System.out.println(System.identityHashCode(str2));
//        System.out.println(System.identityHashCode(str3));
//
//        Order o1 = new Order(String.valueOf(1));
//        Order o2 = new Order(o1.name.intern());
//        Order o3 = new Order(o1.name);
//        assertSame(o1.name == o2.name);
//        System.out.println(System.identityHashCode(o1.name));
//        System.out.println(System.identityHashCode(o2.name));
//        System.out.println(System.identityHashCode(o3.name));
//
//
//        System.out.println(ClassLayout.parseInstance(o1).toPrintable());
//        System.out.println(ClassLayout.parseInstance(o2).toPrintable());
//        System.out.println(ClassLayout.parseInstance(o3).toPrintable());
//
//        System.out.println(VM.current().details());
//
//        List l = new ArrayList();
//        for (int i = 0; i < 20000000; i++) {
//            Order c = new Order(o1.name.intern());
//            l.add(c);
//        }
//
//
//        int mb = 1024*1024;
//
//        //Getting the runtime reference from system
//        Runtime runtime = Runtime.getRuntime();
//
//        System.out.println("##### Heap utilization statistics [MB] #####");
//
//        //Print used memory
//        System.out.println("Used Memory:"
//                + (runtime.totalMemory() - runtime.freeMemory()) / mb);
//
//        //Print free memory
//        System.out.println("Free Memory:"
//                + runtime.freeMemory() / mb);
//
//        //Print total available memory
//        System.out.println("Total Memory:" + runtime.totalMemory() / mb);
//
//        //Print Maximum available memory
//        System.out.println("Max Memory:" + runtime.maxMemory() / mb);
//    }
//
//    private static void assertSame(boolean b) {
//        System.out.println("result is " + b);
//    }
//
//
//    static class Order {
//        String name;
//
//        public Order(String name) {
//            this.name = name;
//        }
//    }
}
