package org.cth.gmweb.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redis
 * @author cth
 * @date 2019/06/28
 */
@Component
public class RedisUtil {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    // 过期时间 单位s
    public static final int EXPIRE_TIME = 3600;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void set(String key, String value) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    public void setSed(String key, String value) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value, EXPIRE_TIME, TimeUnit.SECONDS);
    }

    public boolean checkToken(String key) {
        log.info(">>>checkToken...");
        if (key == null) {
            log.info(">>>token为null");
            return false;
        }
        boolean existKey = redisTemplate.hasKey(key);
        if (existKey) {
            BoundValueOperations<String, String> boundValueOperations = redisTemplate.boundValueOps(key);
            Long expire = boundValueOperations.getExpire();
            String value = boundValueOperations.get();
            log.info(">>>expire:{},value:{}", expire, value);
            if (expire > 0) {
                log.info(">>>token 未过期...");
                // 刷新时间
//                boundValueOperations.set(value, EXPIRE_TIME, TimeUnit.SECONDS);
                return true;
            } else {
                log.info(">>>token 已过期,删除...");
                redisTemplate.delete(key);
                return false;
            }
        } else {
            log.info(">>>token 不存在");
            return false;
        }
    }
}

