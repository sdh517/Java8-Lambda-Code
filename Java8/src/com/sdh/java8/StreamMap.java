package com.sdh.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMap {
    
    public static void main(String[] args) {
        // 里面的每个元素增大两倍
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 55, 66, 55, 7, 8, 8, 9);
        List<Integer> result = list.stream().map(i -> i * 2).collect(Collectors.toList());
        System.out.println(result);
        
        //////////////////////////////////////////////////////////////////////////////////////
        
        listDish().stream().map(d -> d.getName()).forEach(System.out::println);
        
        //////////////////////////////////////////////////////////////////////////////////////
        
        String[] words = {"hello", "World"};
        // {h, e, l, l, o} {W, o, r, l, d}
        Stream<String[]> stream = Arrays.stream(words).map(w -> w.split(""));
        // h, e, l, l, o, W, o, r, l, d
        Stream<String> stringStream = stream.flatMap(Arrays::stream); // flatMap可以理解为扁平化
        stringStream.distinct().forEach(System.out::print);
    }
    
    private static List<Dish> listDish() {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
        return menu;
    }
}
