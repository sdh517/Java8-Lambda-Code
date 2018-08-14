package com.sdh.java8;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamMatch {
    
    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[] {1, 2, 3, 4, 5});
        // 每个都满足才返回true
        boolean matched = stream.allMatch(i -> i > 0);
        System.out.println(matched);
        
        stream = Arrays.stream(new Integer[] {1, 2, 3, 4, 5});
        // 只要有一个满足要求返回true
        matched = stream.anyMatch(i -> i > 6);
        System.out.println(matched);
    }
}
