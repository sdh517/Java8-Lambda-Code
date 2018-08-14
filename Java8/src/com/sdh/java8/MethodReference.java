package com.sdh.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReference {
    public static void main(String[] args) {
        Consumer<String> consumer = s -> System.out.println(s);
        useConsumer(consumer, "Hello Tom");
        useConsumer(s -> System.out.println(s), "Hello Jack");
        useConsumer(System.out::println, "Hello World");
        
        List<Apple> apples = Arrays.asList(new Apple("green", 110), new Apple("red", 123), new Apple("aa", 231));
        System.out.println(apples);
        apples.sort((a1, a2) -> a1.getColor().compareTo(a2.getColor()));
        System.out.println(apples);
        
        System.out.println("==================================================");
        
        apples.stream().forEach(apple -> System.out.println(apple));
        apples.stream().forEach(System.out::println);
        
        System.out.println("======================方法推导=======================");
        // 通过类的静态方法
        Function<String, Integer> function = Integer::parseInt;
        Integer result = function.apply("123");
        System.out.println(result);
        
        // 通过类方法
        BiFunction<String, Integer, Character> biFunction = String::charAt;
        Character c = biFunction.apply("hello world", 2);
        System.out.println(c);
        
        // 通过对象方法
        String str = "Hello";
        Function<Integer, Character> function2 = str::charAt;
        c = function2.apply(4);
        System.out.println(c);
        
        System.out.println("======================构造函数推导=======================");
        Supplier<String> supplier = String::new;
        String s = supplier.get();
        System.out.println(s);
        
        BiFunction<String, Long, Apple> appleFunc = Apple::new;
        Apple apple = appleFunc.apply("red", 100L);
        System.out.println(apple);
        
        // 三个构造参数
        ThreeFunction<String, Long, String, ComplexApple> threeFunction = ComplexApple::new;
        ComplexApple complexApple = threeFunction.apple("Green", 123L, "FuShi");
        System.out.println(complexApple);
        
        // 排序
        apples = Arrays.asList(new Apple("green", 110), new Apple("red", 123), new Apple("aa", 231));
        apples.sort(Comparator.comparing(Apple::getColor));
        System.out.println(apples);
    }
    
    private static <T> void useConsumer(Consumer<T> consumer, T t) {
        consumer.accept(t);
        consumer.accept(t);
    }
}
