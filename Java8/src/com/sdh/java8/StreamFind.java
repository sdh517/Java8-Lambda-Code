package com.sdh.java8;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamFind {
    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[] {1, 2, 3, 4, 5});
        Optional<Integer> optional = stream.filter(i -> i > 4).findAny();
        System.out.println(optional.orElse(-1));
        optional.ifPresent(System.out::println);
        System.out.println(optional.filter(i -> i == 5).get());
        
        //////////////////////////////////////////////////////////////////////////////
        
        int result = find(new Integer[] {1, 2, 3, 4, 5}, -1, i -> i > 100);
        System.out.println(result);
    }
    
    private static int find(Integer[] values, int defaultValue, Predicate<Integer> predicate) {
        for (int i : values) {
            if (predicate.test(i)) {
                return i;
            }
        }
        return defaultValue;
    }
}
