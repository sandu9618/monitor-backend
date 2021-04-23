package com.sfarc.monitor.service;

import com.sfarc.monitor.config.InMemoryHashTypes;
import org.springframework.stereotype.Service;

/**
 * @author madhuwantha
 * created on 4/19/2021
 */

public interface CacheService<T> {
    void put(InMemoryHashTypes inMemoryHashTypes, String key, T val);
    T get(InMemoryHashTypes inMemoryHashTypes, String key);
}
