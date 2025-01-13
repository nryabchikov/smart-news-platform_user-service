package ru.clevertec.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.cache.Cache;
import ru.clevertec.cache.LFUCache;
import ru.clevertec.cache.LRUCache;
import ru.clevertec.domain.User;

import java.util.UUID;

@Configuration
public class CacheConfig {

    @Value("${cache.max-size:10}")
    private int maxSize;

    @Bean
    @ConditionalOnProperty(value = "cache.type", havingValue = "lru", matchIfMissing = true)
    public Cache<UUID, User> lruCache() {
        return new LRUCache<>(maxSize);
    }

    @Bean
    @ConditionalOnProperty(value = "cache.type", havingValue = "lfu")
    public Cache<UUID, User> lfuCache() {
        return new LFUCache<>(maxSize);
    }
}
