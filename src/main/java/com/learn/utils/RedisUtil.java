package com.learn.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.net.URI;

public class RedisUtil {
    private static JedisPool pool = null;
    public static JedisPool getInstance() {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(64);
            config.setMaxIdle(64);
            config.setMinIdle(64);
            config.setTestOnBorrow(true);
            config.setTestOnReturn(true);
            config.setMaxWaitMillis(3000);
            config.setMinEvictableIdleTimeMillis(60);
            config.setTimeBetweenEvictionRunsMillis(30);
            config.setBlockWhenExhausted(false);
            URI uri = URI.create("redis://127.0.0.1:6379");  
            pool = new JedisPool(config, uri, 2000, 2000);
        }
        return pool;
    }

    public static void setVal(String key, String val) {
        final Jedis resource = getInstance().getResource();
        try {
            resource.set(key, val);
            resource.expire(key, 30*60);
        } finally {
            resource.close();
        }
    }

    public static String getVal(String key) {
        final Jedis resource = getInstance().getResource();
        try {
            return resource.get(key);
        } finally {
            resource.close();
        }
    }

    public static void deleteVal(String key) {
        final Jedis resource = getInstance().getResource();
        try {
            resource.del(key);
        } finally {
            resource.close();
        }
    }
}
