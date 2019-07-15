package org.cth.gmweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis
 * @author cth
 * @date 2019/06/28
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        // 更改相关序列化,解决中文乱码
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //key序列化方式
        redisTemplate.setKeySerializer(redisSerializer);
        //value序列化
        redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        redisTemplate.setHashValueSerializer(redisSerializer);
        return redisTemplate;
    }
}
