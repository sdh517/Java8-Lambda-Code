package com.sdh.java8;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class FilterApple {
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    // 如果一个接口中只用一个方法（静态方法和default方法除外），可以使用lambda作业的。 @FunctionalInterface也也可以不写
    @FunctionalInterface
    public static interface AppleFilter {
        boolean filter(Apple apple);
    }
    
    // 传入Filter来筛选结果
    public static List<Apple> findApples(List<Apple> apples, AppleFilter appleFilter) {
        List<Apple> list = new ArrayList<Apple>();
        for (Apple apple : apples) {
            if (appleFilter.filter(apple)) {
                list.add(apple);
            }
        }
        return list;
    }
    
    // Filter扩展
    public static class GreenAndWeightGreater150Filter implements AppleFilter {
        @Override
        public boolean filter(Apple apple) {
            if (apple.getColor().equals("green") && apple.getWeight() > 150) {
                return true;
            }
            return false;
        }
        
    }
    
    // Filter扩展
    public static class redAndWeightLess140Filter implements AppleFilter {
        @Override
        public boolean filter(Apple apple) {
            if (apple.getColor().equals("red") && apple.getWeight() < 140) {
                return true;
            }
            return false;
        }
        
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static List<Apple> findGreenApple(List<Apple> apples) {
        return findApples(apples, "green");
    }
    
    public static List<Apple> findApples(List<Apple> apples, String color) {
        
        List<Apple> list = new ArrayList<Apple>();
        
        for (Apple apple : apples) {
            if (color.equals(apple.getColor())) {
                list.add(apple);
            }
        }
        return list;
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple("green", 150), 
                                           new Apple("red", 120), 
                                           new Apple("green", 130),
                                           new Apple("green", 200));
        
        List<Apple> greenApples = findGreenApple(apples);
        System.out.println(greenApples);
        
        List<Apple> redApples = findApples(apples, "red");
        System.out.println(redApples);
        
        List<Apple> result = findApples(apples, new GreenAndWeightGreater150Filter());
        System.out.println(result);
        
        result = findApples(apples, new redAndWeightLess140Filter());
        System.out.println(result);
        
        // 使用匿名内部类
        result = findApples(apples, new AppleFilter() {
            @Override
            public boolean filter(Apple apple) {
                return "green".equals(apple.getColor());
            }
        });
        System.out.println(result);
        
        // 使用lambda表达式
        result = findApples(apples, (Apple apple) -> {
           return apple.getColor().equals("red"); 
        });
        // 或者
        result = findApples(apples, apple -> apple.getColor().equals("red"));
        System.out.println(result);
        
        // 只有一个参数可以简写成这样
        result = findApples(apples, apple -> {
            return apple.getColor().equals("red"); 
        });
        System.out.println(result);
        
        // Java很多接口加了@FunctionalInterface，例如：Runnable、Comparator
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}
