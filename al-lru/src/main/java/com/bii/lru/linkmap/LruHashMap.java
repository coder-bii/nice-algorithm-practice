package com.bii.lru.linkmap;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author bihaiyang
 * @desc
 * @since 2023/02/27
 */
public class LruHashMap<K, V> extends LinkedHashMap<K, V> {
    
    private final int capacity;
    
    public LruHashMap(int capacity){
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }
    
    @Override
    protected boolean removeEldestEntry(Map.Entry entry){
        return size() > capacity;
    }

}
