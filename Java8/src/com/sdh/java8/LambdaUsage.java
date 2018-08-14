package com.sdh.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaUsage 
{

    
    
    public static void main(String[] args) 
    {
        Runnable r1 = () -> System.out.println("Hello");
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        };
        
        process(r1);
        process(r2);
        
        ///////////////////////////////////////////////////////////////////
        
        List<Apple> apples = Arrays.asList(new Apple("green", 120), new Apple("red", 150));
        List<Apple> greenList = filter(apples, apple -> apple.getColor().equals("green"));
        System.out.println(greenList);
        
        ///////////////////////////////////////////////////////////////////
        
        List<Apple> result = filterByWeight(apples, w -> w > 130);
        System.out.println(result);
        
        ///////////////////////////////////////////////////////////////////
        
        result = filterByBiPredicate(apples, (s, w) -> s.equals("green") && w > 100);
        System.out.println(result);
        
        ///////////////////////////////////////////////////////////////////
        
        simpleTestConsumer(apples, apple -> System.out.println(apple));
        
        ///////////////////////////////////////////////////////////////////
        
        simpleBiConsumer("deep ", apples, (apple, str) -> System.out.println(str + apple.getColor() + " Weight:" + apple.getWeight()));
        
        ///////////////////////////////////////////////////////////////////
        
        String string = testFunction(new Apple("red", 100), apple -> apple.toString());
        System.out.println(string);
        
        ///////////////////////////////////////////////////////////////////
        
        IntFunction<Double> function = i -> i * 100.0d;
        double d = function.apply(10);
        System.out.println(d);
        
        ///////////////////////////////////////////////////////////////////
        
        Apple apple = testBiFunction("green", 130, (s, w) -> new Apple(s, w));
        System.out.println(apple);
        
        ///////////////////////////////////////////////////////////////////
        
        Supplier<String> supplier = String::new;
        System.out.println(supplier.get().getClass());
        
        ///////////////////////////////////////////////////////////////////
        
        Apple apple2 = createApple(() -> new Apple("green", 100));
        System.out.println(apple2);
    }
    
    private static void process(Runnable runnable)
    {
        runnable.run();
    }
    
    private static List<Apple> filter(List<Apple> apples, Predicate<Apple> predicate)
    {
        List<Apple> result = new ArrayList<Apple>();
        for (Apple apple : apples) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
    
    private static List<Apple> filterByWeight(List<Apple> apples, LongPredicate predicate)
    {
        List<Apple> result = new ArrayList<Apple>();
        for (Apple apple : apples) {
            if (predicate.test(apple.getWeight())) {
                result.add(apple);
            }
        }
        return result;
    }
    
    private static List<Apple> filterByBiPredicate(List<Apple> apples, BiPredicate<String, Long> predicate)
    {
        List<Apple> result = new ArrayList<Apple>();
        for (Apple apple : apples) {
            if (predicate.test(apple.getColor(), apple.getWeight())) {
                result.add(apple);
            }
        }
        return result;
    }
    
    private static void simpleTestConsumer(List<Apple> apples, Consumer<Apple> consumer)
    {
        for (Apple apple : apples) {
            consumer.accept(apple);
        }
    }
    
    private static void simpleBiConsumer(String c, List<Apple> apples, BiConsumer<Apple, String> consumer)
    {
        for (Apple apple : apples) {
            consumer.accept(apple, c);
        }
    }
    
    private static String testFunction(Apple apple, Function<Apple, String> function)
    {
        return function.apply(apple);
    } 
    
    private static Apple testBiFunction(String color, long weight, BiFunction<String, Long, Apple> function)
    {
        return function.apply(color, weight);
    }
    
    private static Apple createApple(Supplier<Apple> supplier)
    {
        return supplier.get();
    }
}
