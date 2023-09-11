package com.movte.slate.domain.user.application.service;

import com.movte.slate.domain.user.application.port.FindBlackListPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindAccessTokenInBlackListService implements FindBlackListPort {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean find(String token) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(token));
    }
}
