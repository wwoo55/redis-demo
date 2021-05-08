package com.biz.redisdemo.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @projectName: redis-demo
 * @className: RedisConnectionUtils
 * @description: 得到Redis连接
 * @author: xy
 * @time: 2021/5/7 15:39
 */
public class RedisConnectionUtils {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 6379;
    private static final int MAX_ACTIVE = 1024;
    private static final int MAX_IDLE = 200;
    private static final int MAX_WAIT = 10000;

    private static JedisPool jedisPool = null;

    /**
     * 初始化
     */
    private static void initPool() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            // 最大连接数
            config.setMaxTotal(MAX_ACTIVE);
            // 最大空闲连接数
            config.setMaxIdle(MAX_IDLE);
            // 获取可用连接的最大等待时间
            config.setMaxWaitMillis(MAX_WAIT);
            jedisPool = new JedisPool(config, HOST, PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取jedis实例
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool == null) {
                initPool();
            }
            Jedis jedis = jedisPool.getResource();
            // 密码
            /*jedis.auth("redis");*/
            return jedis;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
