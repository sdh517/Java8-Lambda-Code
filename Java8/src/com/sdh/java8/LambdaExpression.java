package com.sdh.java8;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaExpression {
    public static void main(String[] args) {
        // 传统写法
        Comparator<Apple> byColor = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getColor().compareTo(o2.getColor());
            }
        };
        List<Apple> apples = Collections.emptyList();
        apples.sort(byColor);
        
        // 使用Lamdba表达式
        Comparator<Apple> byColor1 = (Apple o1, Apple o2) -> { return o1.getColor().compareTo(o2.getColor()); };
        // 使用类型推导
        Comparator<Apple> byColor2 = (o1, o2) -> { return o1.getColor().compareTo(o2.getColor()); };
        // 不带大括号可以省略return
        Comparator<Apple> byColor3 = (o1, o2) -> o1.getColor().compareTo(o2.getColor());
    }
}
