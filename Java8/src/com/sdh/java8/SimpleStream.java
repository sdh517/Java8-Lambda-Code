package com.sdh.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleStream {
    public static void main(String[] args) {
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
        
        menu.stream().forEach(System.out::println);
        
        ///////////////////////////////////////////////////////////////////////
        
        Stream<Dish> dishStream = Stream.of(new Dish("tttt", false, 300, Dish.Type.FISH),
                                            new Dish("wwww", false, 450, Dish.Type.FISH));
        dishStream.forEach(System.out::println);
        
        ///////////////////////////////////////////////////////////////////////
        
        List<String> dishNames = getDishNames(menu);
        System.out.println(dishNames);
        
        ///////////////////////////////////////////////////////////////////////
        
        dishNames = getDishNameByStream(menu);
        System.out.println(dishNames);
    }

    // 传统写法
    private static List<String> getDishNames(List<Dish> menu) {
        List<Dish> lowCalories = new ArrayList<Dish>();

        for (Dish dish : menu) {
            if (dish.getCalories() < 400) {
                lowCalories.add(dish);
            }
        }

        // 排序
        Collections.sort(lowCalories, (d1, d2) -> Integer.compare(d1.getCalories(), d2.getCalories()));

        List<String> dishNameList = new ArrayList<String>();
        for (Dish dish : lowCalories) {
            dishNameList.add(dish.getName());
        }

        return dishNameList;
    }
    
    // Stream
    private static List<String> getDishNameByStream(List<Dish> menu) {
        // 并行处理
        return menu.parallelStream().filter(dish -> dish.getCalories() < 400)
                                    .sorted(Comparator.comparing(Dish::getCalories))
                                    .map(Dish::getName)
                                    .collect(Collectors.toList());
    }
}
