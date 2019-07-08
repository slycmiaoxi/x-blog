package cn.slycmiaoxi.redis;

import java.util.List;

/**
 * <p>
 * redis服务实现类
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-22
 */
public interface JedisClient {
    
    String get(String key);
    
    String set(String key, String value);
    
    long incr(String key);
    
    Long hset(String hkey, String key, String value);
    
    String hget(String hkey, String key);
    
    Long del(String key);
    
    Long hdel(String hkey, String key);
    
    Long expire(String key, int second);
    
    Long lpush(String key, String value);
    
    String lindex(String key, long index);
    
    Long rpush(String key, String value);
    
    boolean exist(String key);
    
    Long llen(String key);
    
    List<String> lrange(String key, long start, long end);
    
}
