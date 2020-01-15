package com.luo.house.common.config;

import com.luo.house.common.constants.JedisProp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig {
    private String host = JedisProp.HOST;

    private int port = JedisProp.PORT;

    private int maxIdle = JedisProp.MAX_IDLE;

    private long maxWait = JedisProp.MAX_WAIT;

    private int minIdle = JedisProp.MIN_IDLE;
    private int timeout = JedisProp.TIMEOUT;

    @Bean
    public JedisPool redisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        jedisPoolConfig.setMinIdle(minIdle);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);

        return jedisPool;
    }
}
