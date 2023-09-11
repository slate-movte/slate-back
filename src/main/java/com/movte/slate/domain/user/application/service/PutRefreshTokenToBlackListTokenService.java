package com.movte.slate.domain.user.application.service;

import com.movte.slate.domain.user.application.port.PutRefreshTokenToBlackListTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class PutRefreshTokenToBlackListTokenService implements PutRefreshTokenToBlackListTokenPort {
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void blackList(String accessTokenValue, Date expiredDateTime, Date now) {
        long milliseconds = expiredDateTime.getTime() - now.getTime();
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(String.class));
        redisTemplate.opsForValue().set(accessTokenValue, "refresh_token", milliseconds, TimeUnit.MILLISECONDS);
    }
}