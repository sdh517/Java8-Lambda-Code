package com.sdh.java8;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.security.auth.x500.X500Principal;

public class NumericStream {
    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[] {1, 2, 3, 4, 5, 6, 7});
        int result = stream.filter(i -> i > 3).reduce(0, Integer::sum);
        System.out.println(result);
        
        ////////////////////////////////////////////////////////////////////////////////
        
        stream = Arrays.stream(new Integer[] {1, 2, 3, 4, 5, 6, 7});
        // 将Integer的Stream转换为int Stream，这样更省内存
        IntStream intStream = stream.mapToInt(i -> i.intValue());
        result = intStream.filter(i -> i > 3).sum();
        System.out.println(result);
        
        ////////////////////////////////////////////////////////////////////////////////
        
        // 勾股定理
        int a = 9;
        IntStream.rangeClosed(1, 100)
                 .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                 .boxed() // int --> Integer
                 .map(b -> new int[] {a, b, (int)Math.sqrt(a*a + b*b)})
                 .forEach(r -> System.out.println("a=" + r[0] + ", b=" + r[1] + ", c=" + r[2]));
        
        ////////////////////////////////////////////////////////////////////////////////
        
        IntStream.rangeClosed(1, 100)
                 .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                 .mapToObj(b -> new int[] {a, b, (int)Math.sqrt(a*a + b*b)})
                 .forEach(r -> System.out.println("a=" + r[0] + ", b=" + r[1] + ", c=" + r[2]));
    }
}
