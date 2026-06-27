package io.ricardo_paulo.HashTable.enums;

import java.util.function.BiFunction;

public enum HashFunc {

    DIVISION((key, capacity) -> Math.abs(key.hashCode()) % capacity),
    MULTIPLICATION((key, capacity) -> {
        int k = Math.abs(key.hashCode());
        double a = (Math.sqrt(5) - 1) / 2.0;
        double multiplication = k * a;
        double decimal = multiplication - Math.floor(multiplication);

        return (int) Math.floor(capacity * decimal);
    }),
    DJB2((key, capacity) -> {
        long hash = 5381;

        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);

            hash = ((hash << 5) + hash) + c;
        }

        return (int) (Math.abs(hash) % capacity);
    });

    private final BiFunction<String, Integer, Integer> hashFunction;

    HashFunc(BiFunction<String, Integer, Integer> hashFunction) {
        this.hashFunction = hashFunction;
    }

    public int hash(String key, int capacity) {
        return hashFunction.apply(key, capacity);
    }
}
