package com.sdh.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CollectorIntroduce {
    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple("green", 150), 
                new Apple("red", 120), 
                new Apple("green", 130),
                new Apple("green", 131),
                new Apple("green", 125),
                new Apple("green", 200));
        
        List<Apple> greenList = apples.stream().filter(a -> a.getColor().equals("green")).collect(Collectors.toList());
        Optional.ofNullable(greenList).ifPresent(System.out::println);
        
        //////////////////////////////////////////////////////////////////////////////
        
        System.out.println(groupByFunction(apples));
        
        //////////////////////////////////////////////////////////////////////////////
        
        System.out.println(groupByCollector(apples));
        
    }
    
    /**
     * 按照颜色分类
     * @param apples
     * @return
     */
    private static Map<String, List<Apple>> groupByFunction(List<Apple> apples) {
        Map<String, List<Apple>> map = new HashMap<String, List<Apple>>();
        apples.stream().forEach(apple -> {
            List<Apple> colorList = Optional.ofNullable(map.get(apple.getColor())).orElseGet(() -> {
                List<Apple> list = new ArrayList<>();
                map.put(apple.getColor(), list);
                return list;
            });
            colorList.add(apple);
        });
        return map;
    }
    
    /**
     * 按照颜色分类
     * @param apples
     * @return
     */
    private static Map<String, List<Apple>> groupByCollector(List<Apple> apples) {
        return apples.stream().collect(Collectors.groupingBy(Apple::getColor));
    }
}
