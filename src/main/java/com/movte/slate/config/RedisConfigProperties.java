package com.movte.slate.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "spring.redis")
@ConstructorBinding
public class RedisConfigProperties {
    private final String host;
    private final int port;
}
