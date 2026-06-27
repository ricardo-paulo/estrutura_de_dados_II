package io.ricardo_paulo.HashTable.utils;

@FunctionalInterface
public interface MultiFunction<T, U, V, W, R> {

    R apply(T t, U u, V v, W w);
}
