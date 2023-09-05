package com.movte.slate.domain.user.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    public void saveDataToRedisWithTTL(String key, String value, long ttlInSeconds) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, ttlInSeconds, TimeUnit.SECONDS);
    }

    public Optional<String> getDataFromRedis(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key));
    }
}
