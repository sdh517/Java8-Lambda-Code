package com.sdh.java8;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamReduce {
    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[] {1, 2, 3, 4, 5});
        int result = stream.reduce(0, (i ,j) -> i + j);
        System.out.println(result);
        
        //////////////////////////////////////////////////////////////////////////////
        
        stream = Arrays.stream(new Integer[] {1, 2, 3, 4, 5});
        // 累加
        stream.reduce((i, j) -> i + j).ifPresent(System.out::println);
        //等同上面的写法
        //stream.reduce(Integer::sum).ifPresent(System.out::println);
        
        //////////////////////////////////////////////////////////////////////////////
        stream = Arrays.stream(new Integer[] {1, 2, 3, 4, 5});
        // 获取最大值
        System.out.println(stream.reduce(Integer::max).get());
        
        //////////////////////////////////////////////////////////////////////////////
        stream = Arrays.stream(new Integer[] {1, 2, 3, 4, 5});
        // 偶数相乘
        result = stream.filter(i -> i % 2 == 0).reduce(1, (i, j) -> i * j);
        System.out.println(result);
    }
}
