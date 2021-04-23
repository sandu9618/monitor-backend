package com.sfarc.monitor.service.impl;

import com.sfarc.monitor.config.InMemoryHashTypes;
import com.sfarc.monitor.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author madhuwantha
 * created on 4/19/2021
 */
@Service
public class CacheServiceImpl<T> implements CacheService<T> {

    @Autowired
    private RedisTemplate<String, T> redisTemplate;


    private HashOperations<String, String, T> getHashOperations(){
        return redisTemplate.opsForHash();
    }

    @Override
    public void put(InMemoryHashTypes inMemoryHashTypes, String key, T val) {
        getHashOperations().put(String.valueOf(inMemoryHashTypes), key, val);
    }

    @Override
    public T get(InMemoryHashTypes inMemoryHashTypes, String key) {
        return getHashOperations().get(String.valueOf(inMemoryHashTypes), key);
    }
}
