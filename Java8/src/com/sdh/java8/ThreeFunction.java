package com.sdh.java8;

@FunctionalInterface
public interface ThreeFunction<T, U, K, R> {
    R apple(T t, U u, K k);
}
