package com.trungtv.blogauth.service.impl;

import com.trungtv.blogauth.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {
    private final RedisTemplate<String, String> redisTemplate;


    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, String value, Integer timeout) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void remove(String key) {
        redisTemplate.delete(key);
    }
}
