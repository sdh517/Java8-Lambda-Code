package com.sdh.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import com.sdh.java8.Dish.Type;

public class CollectorsAction {
    private final static List<Dish> menu = Arrays.asList(
        new Dish("pork", false, 800, Dish.Type.MEAT),
        new Dish("beef", false, 700, Dish.Type.MEAT),
        new Dish("chicken", false, 400, Dish.Type.MEAT),
        new Dish("french fries", true, 530, Dish.Type.OTHER),
        new Dish("rice", true, 350, Dish.Type.OTHER),
        new Dish("season fruit", true, 120, Dish.Type.OTHER),
        new Dish("pizza", true, 550, Dish.Type.OTHER),
        new Dish("prawns", false, 300, Dish.Type.FISH),
        new Dish("salmon", false, 450, Dish.Type.FISH));
    
    public static void main(String[] args) {
        testAveragingDouble();
        testAveragingInt();
        testAveragingLong();
        testCollectingAndThen();
        testCounting();
        testGroupingByFunction();
        testGroupingByFunctionAndCollector();
        testGroupingByFunctionAndSupplierAndCollector();
        testSummarizingInt();
        testGroupingByConcurrentWithFunction();
        testGroupingByConcurrentWithFunctionAndCollector();
        testGroupingByConcurrentWithFunctionAndSupplierAndCollector();
        testJoining();
        testJoiningWithDelimiter();
        testJoiningWithDelimiterPrefixAndSuffix();
        testMapping();
        testMaxBy();
        testMinBy();
        testPartitioningByWithPredicate();
        testPartitioningByWithPredicateAndCollect();
        testReducingBinaryOperator();
        testReducingBinaryOperatorAndIdentiy();
        testReducingBinaryOperatorAndIdentiyAndFunction();
        testSummarizingDouble();
        testSummingDouble();
        testToCollection();
        testToMap();
        testToConcurrentMap();
        testToConcurrentMapWithBinaryOperator();
        testToConcurrentMapWithBinaryOperatorAndSupplier();
        testToList();
        testToSet();
    }
    
    /**
     * 求所有食物卡路里的平均数
     * Collectors.averagingDouble方法的返回值是Double类型
     */
    private static void testAveragingDouble() {
        System.out.println("testAveragingDouble");
        Optional.ofNullable(menu.stream().collect(Collectors.averagingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);
    }
    
    /**
     * 求所有食物卡路里的平均数
     * Collectors.averagingDouble方法的返回值是Integer类型
     */
    private static void testAveragingInt() {
        System.out.println("testAveragingInt");
        Optional.ofNullable(menu.stream().collect(Collectors.averagingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }
    
    /**
     * 求所有食物卡路里的平均数
     * Collectors.averagingDouble方法的返回值是Long类型
     */
    private static void testAveragingLong() {
        System.out.println("testAveragingLong");
        Optional.ofNullable(menu.stream().collect(Collectors.averagingLong(Dish::getCalories)))
                .ifPresent(System.out::println);
    }
    
    /**
     * CollectingAndThen
     */
    private static void testCollectingAndThen() {
        System.out.println("testCollectingAndThen");
        Optional.ofNullable(menu.stream().collect(Collectors.collectingAndThen(Collectors.averagingInt(Dish::getCalories), a -> "The Average Calories is: " + a)))
                .ifPresent(System.out::println);;
        
        // 返回的list不能添加元素
        List<Dish> list = menu.stream().filter(d -> d.getType().equals(Type.MEAT)).collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
        // 相当于Collections.unmodifiableList(list);
        System.out.println(list);
    }
    
    /**
     * 相当于size()
     */
    private static void testCounting() {
        System.out.println("testCounting");
        Optional.of(menu.stream().collect(Collectors.counting())).ifPresent(System.out::println);
    }
    
    /**
     * 分组
     */
    private static void testGroupingByFunction() {
        System.out.println("testGroupingByFunction");
        Optional.of(menu.stream().collect(Collectors.groupingBy(Dish::getType)))
                .ifPresent(System.out::println);
    }
    
    private static void testGroupingByFunctionAndCollector() {
        System.out.println("testGroupingByFunctionAndCollector");
        // 分组并且计算出每组中元素的个数
        Optional.of(menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting())))
                .ifPresent(System.out::println);
        // 分组并且计算每组中的平均值
        Optional.of(menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.averagingInt(Dish::getCalories))))
                .ifPresent(System.out::println);
    }
    
    private static void testGroupingByFunctionAndSupplierAndCollector() {
        System.out.println("testGroupingByFunctionAndSupplierAndCollector");
        // 转换成TreeMap
        Map<Dish.Type, Double> map = menu.stream().collect(Collectors.groupingBy(Dish::getType, TreeMap::new, Collectors.averagingInt(Dish::getCalories)));
        Optional.of(map.getClass()).ifPresent(System.out::println);
        Optional.of(map).ifPresent(System.out::println);
    }
    
    /**
     * 统计信息：count, sum, min, average, max
     */
    private static void testSummarizingInt() {
        System.out.println("testSummarizingInt");
        Optional.of(menu.stream().collect(Collectors.summarizingInt(Dish::getCalories)))
                .ifPresent(System.out::println);;
    }
    
    private static void testGroupingByConcurrentWithFunction() {
        System.out.println("testGroupingByConcurrent");
        // 返回的是ConcurrentMap类型
        ConcurrentMap<Dish.Type, List<Dish>> collect = menu.stream().collect(Collectors.groupingByConcurrent(Dish::getType));
        Optional.ofNullable(collect.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }
    
    private static void testGroupingByConcurrentWithFunctionAndCollector() {
        System.out.println("testGroupingByConcurrentWithFunctionAndCollector");
        // 分组并且统计每组卡路里的平均值
        ConcurrentMap<Dish.Type, Double> collect = menu.stream().collect(Collectors.groupingByConcurrent(Dish::getType, Collectors.averagingInt(Dish::getCalories)));
        Optional.of(collect).ifPresent(System.out::println);
    }
    
    private static void testGroupingByConcurrentWithFunctionAndSupplierAndCollector() {
        System.out.println("testGroupingByConcurrentWithFunctionAndSupplierAndCollector");
        // 转换成ConcurrentSkipListMap
        Map<Dish.Type, Double> map = menu.stream().collect(Collectors.groupingByConcurrent(Dish::getType, ConcurrentSkipListMap::new, Collectors.averagingInt(Dish::getCalories)));
        Optional.of(map.getClass()).ifPresent(System.out::println);
        Optional.of(map).ifPresent(System.out::println);
    }
    
    private static void testJoining() {
        System.out.println("testJoining");
        Optional.of(menu.stream().map(Dish::getName).collect(Collectors.joining()))
                .ifPresent(System.out::println);
    }
    
    private static void testJoiningWithDelimiter() {
        System.out.println("testJoiningWithDelimiter");
        Optional.of(menu.stream().map(Dish::getName).collect(Collectors.joining(",")))
                .ifPresent(System.out::println);
    }
    
    private static void testJoiningWithDelimiterPrefixAndSuffix() {
        System.out.println("testJoiningWithDelimiterPrefixAndSuffix");
        // join后加prefix和suffix
        Optional.of(menu.stream().map(Dish::getName).collect(Collectors.joining(",", "Names[", "]")))
                .ifPresent(System.out::println);
    }
    
    private static void testMapping() {
        System.out.println("testMapping");
        Optional.of(menu.stream().collect(Collectors.mapping(Dish::getName, Collectors.joining(","))))
                .ifPresent(System.out::println);
    }
    
    private static void testMaxBy() {
        System.out.println("testMaxBy");
        menu.stream().collect(Collectors.maxBy(Comparator.comparing(Dish::getCalories))).ifPresent(System.out::println);
    }
    
    private static void testMinBy() {
        System.out.println("testMinBy");
        menu.stream().collect(Collectors.minBy(Comparator.comparing(Dish::getCalories))).ifPresent(System.out::println);
    }
    
    private static void testPartitioningByWithPredicate() {
        System.out.println("testPartitioningByWithPredicate");
        // 按照是不是水果进行分类
        Map<Boolean, List<Dish>> collect = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        Optional.of(collect).ifPresent(System.out::println);
    }
    
    private static void testPartitioningByWithPredicateAndCollect() {
        System.out.println("testPartitioningByWithPredicateAndCollect");
        // 按照水果和非水果进行分类并且求彼此的卡路里的平均值
        Map<Boolean, Double> collect = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.averagingInt(Dish::getCalories)));
        Optional.of(collect).ifPresent(System.out::println);
    }
    
    private static void testReducingBinaryOperator() {
        System.out.println("testReducingBinaryOperator");
        menu.stream().collect(Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(Dish::getCalories)))).ifPresent(System.out::println);;
    }
    
    private static void testReducingBinaryOperatorAndIdentiy() {
        System.out.println("testReducingBinaryOperatorAndIdentiy");
        Integer result = menu.stream().map(Dish::getCalories).collect(Collectors.reducing(0, (d1, d2) -> d1 + d2));
        System.out.println(result);
    }
    
    private static void testReducingBinaryOperatorAndIdentiyAndFunction() {
        System.out.println("testReducingBinaryOperatorAndIdentiyAndFunction");
        Integer result = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (d1, d2) -> d1 + d2));
        System.out.println(result);
    }
    
    private static void testSummarizingDouble() {
        System.out.println("testSummarizingDouble");
        Optional.of(menu.stream().collect(Collectors.summarizingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);;
    }
    
    private static void testSummingDouble() {
        System.out.println("testSummingDouble");
        Optional.of(menu.stream().collect(Collectors.summingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);
        // 等价于
        Optional.of(menu.stream().map(Dish::getCalories).mapToInt(Integer::intValue).sum())
                .ifPresent(System.out::println);;
    }
    
    private static void testToCollection() {
        System.out.println("testToCollection");
        // 返回LinkedList
        Optional.of(menu.stream().collect(Collectors.toCollection(LinkedList::new)))
                .ifPresent(System.out::println);
    }
    
    private static void testToMap() {
        System.out.println("testToMap");
        // 将集合中数据转换成Map
        Optional.of(menu.stream().collect(Collectors.toMap(Dish::getName, Dish::getCalories)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
        
        // 将HashMap转换成线程安全的SynchronizedMap
        Optional.of(menu.stream().collect(Collectors.collectingAndThen(Collectors.toMap(Dish::getName, Dish::getCalories), Collections::synchronizedMap)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });;
    }
    
    private static void testToConcurrentMap() {
        System.out.println("testToConcurrentMap");
        // 将集合中数据转换成ConcurrentMap
        Optional.of(menu.stream().collect(Collectors.toConcurrentMap(Dish::getName, Dish::getCalories)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
    }
    
    private static void testToConcurrentMapWithBinaryOperator() {
        System.out.println("testToConcurrentMapWithBinaryOperator");
        Optional.of(menu.stream().collect(Collectors.toConcurrentMap(Dish::getType, v -> 1L, (a, b) -> a + b)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
    }
    
    private static void testToConcurrentMapWithBinaryOperatorAndSupplier() {
        System.out.println("testToConcurrentMapWithBinaryOperatorAndSupplier");
        Optional.of(menu.stream().collect(Collectors.toConcurrentMap(Dish::getType, v -> 1L, (a, b) -> a + b, ConcurrentSkipListMap::new)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
    }
    
    private static void testToList() {
        System.out.println("testToList");
        Optional.of(menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList()))
                .ifPresent(r -> {
                    System.out.println(r);
                    System.out.println(r.getClass());
                });
    }
    
    private static void testToSet() {
        System.out.println("testToList");
        Optional.of(menu.stream().filter(Dish::isVegetarian).collect(Collectors.toSet()))
                .ifPresent(r -> {
                    System.out.println(r);
                    System.out.println(r.getClass());
                });
    }
}
