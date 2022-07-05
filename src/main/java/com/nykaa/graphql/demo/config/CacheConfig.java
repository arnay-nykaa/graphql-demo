package com.nykaa.graphql.demo.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.nykaa.graphql.demo.cache.RequestKey;
import com.nykaa.graphql.demo.config.ApplicationProperties.CacheProperties;

import graphql.kickstart.servlet.cache.CachedResponse;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class CacheConfig {

    @Autowired
    private CacheProperties cacheProperties;

    @Bean
    public Cache<RequestKey, CachedResponse> responseCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofMinutes(cacheProperties.getTtlInMinutes()))
                .maximumSize(cacheProperties.getMaxSize())
                .removalListener((key, value, cause) -> 
                    log.info("Key {} with value {} was removed from the response cache. Cause {}", key, value, cause))
                .build();
    }

    @Bean
    public RedisTemplate<RequestKey, CachedResponse> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<RequestKey, CachedResponse> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
