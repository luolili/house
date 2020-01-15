package com.luo.house.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
@Slf4j
public class JedisUtil {
    @Autowired
    private JedisPool jedisPool;

    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.set(key, value);

        } catch (Exception e) {
            log.info("set key:[{}],value[{}],error:[{}]", key, value, e);
            return null;
        }
    }

    public String set(String key, String value, int expireTime) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.setex(key, expireTime, value);
        } catch (Exception e) {
            log.info("set key:[{}],value[{}],error:[{}]", key, value, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    public Long setnx(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.setnx(key, value);
        } catch (Exception e) {
            log.info("set key:[{}],value[{}],error:[{}]", key, value, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.get(key);

        } catch (Exception e) {
            log.info("set key:[{}],error:[{}]", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    public Long del(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.del(key);

        } catch (Exception e) {
            log.info("del key:[{}],error:[{}]", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    public Boolean exist(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.exists(key);
        } catch (Exception e) {
            log.error("expire key:{} error:[{}]", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    public Long expire(String key, int expireTime) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.expire(key, expireTime);
        } catch (Exception e) {
            log.error("expire key:{} error:[{}]", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    public Long ttl(String key) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.ttl(key);

        } catch (Exception e) {
            log.info("ttl key:[{}],error:[{}]", key, e);
            return null;
        } finally {
            close(jedis);
        }
    }

    private void close(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }
    }
}
