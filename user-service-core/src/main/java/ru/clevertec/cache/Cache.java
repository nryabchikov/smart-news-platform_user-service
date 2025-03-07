package ru.clevertec.cache;

import java.util.Optional;

public interface Cache<K, V> {
    Optional<V> get(K key);
    void put(K key, V value);
    void delete(K key);
}

