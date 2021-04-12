package com.sfarc.monitor.web.exception;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author = madhuwantha
 * created on 4/11/2021
 */
public class ErrorMessage {
    static String generateMessage(String entity, Map<String, String> searchParams, String message) {
        return StringUtils.capitalize(entity) +
                message +
                " "+searchParams;
    }

    static <K, V> Map<K, V> toMap(
            Class<K> keyType, Class<V> valueType, Object... entries) {
        if (entries.length % 2 == 1)
            throw new IllegalArgumentException("Invalid entries");
        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }
}
