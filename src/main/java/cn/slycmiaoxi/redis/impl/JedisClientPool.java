package cn.slycmiaoxi.redis.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import cn.slycmiaoxi.redis.JedisClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * <p>
 * redis单机版客户端服务实现类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-22
 */
public class JedisClientPool implements JedisClient {
    @Autowired
    private JedisPool jedisPool;
    
    @Value("${redis.keyPrefix}")
    private String KEY_PREFIX;
    
    @Override
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        key = setPrefix(key);
        String result = jedis.get(key);
        jedis.close();
        return result;
    }
    
    @Override
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        key = setPrefix(key);
        String string = jedis.set(key, value);
        jedis.close();
        return string;
    }
    
    @Override
    public long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        key = setPrefix(key);
        Long result = jedis.incr(key);
        jedis.close();
        return result;
    }
    
    @Override
    public Long hset(String hkey, String key, String value) {
        Jedis jedis = jedisPool.getResource();
        key = setPrefix(key);
        Long hset = jedis.hset(hkey, key, value);
        jedis.close();
        return hset;
    }
    
    @Override
    public String hget(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        key = setPrefix(key);
        String result = jedis.hget(hkey, key);
        jedis.close();
        return result;
    }
    
    @Override
    public Long del(String key) {
        Jedis jedis = jedisPool.getResource();
        key = setPrefix(key);
        Long result = jedis.del(key);
        jedis.close();
        return result;
    }
    
    @Override
    public Long hdel(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        key = setPrefix(key);
        Long result = jedis.hdel(hkey, key);
        jedis.close();
        return result;
    }
    
    @Override
    public Long expire(String key, int second) {
        Jedis jedis = jedisPool.getResource();
        key = setPrefix(key);
        Long result = jedis.expire(key, second);
        return result;
    }
    
    @Override
    public Long lpush(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        key = setPrefix(key);
        Long length = jedis.lpush(key, value);
        jedis.close();
        return length;
    }
    
    @Override
    public String lindex(String key, long index) {
        Jedis jedis = jedisPool.getResource();
        key = setPrefix(key);
        String str = jedis.lindex(key, index);
        jedis.close();
        return str;
    }
    
    @Override
    public Long rpush(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        key = setPrefix(key);
        Long length = jedis.rpush(key, value);
        jedis.close();
        return length;
    }
    
    @Override
    public boolean exist(String key) {
        Jedis jedis = jedisPool.getResource();
        key = setPrefix(key);
        return jedis.exists(key) ? true : false;
    }
    
    @Override
    public Long llen(String key) {
        Jedis jedis = jedisPool.getResource();
        key = setPrefix(key);
        Long length = jedis.llen(key);
        jedis.close();
        return length;
    }
    
    @Override
    public List<String> lrange(String key, long start, long end) {
        Jedis jedis = jedisPool.getResource();
        key = setPrefix(key);
        List<String> list = jedis.lrange(key, start, end);
        jedis.close();
        return list;
    }
    
    private String setPrefix(String key) {
        key = KEY_PREFIX + "_" + key;
        return key;
    }
    
}
