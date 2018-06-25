package com.richie.backstage.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;

/**
 * @author richie on 2018.04.27
 */
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {

    @Bean
    public KeyGenerator wiselyKeyGenerator() {
        return new SimpleKeyGenerator() {
            /**
             * 对参数进行拼接, eg: com.richie.boots.service.UserCacheService.getUserById_1
             */
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName()).append("#");
                sb.append(method.getName()).append("(");

                StringBuilder paramsSb = new StringBuilder();
                for (Object param : params) {
                    if (param != null) {
                        paramsSb.append(param.toString()).append(",");
                    }
                }
                int length = paramsSb.length();
                if (length > 0) {
                    paramsSb.deleteCharAt(length - 1);
                }
                sb.append(paramsSb).append(")");
                return sb.toString();
            }
        };
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate<String, Object> redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        // 设置缓存默认过期时间（全局的），单位 秒
        rcm.setDefaultExpiration(60 * 60 * 60);
        return rcm;
    }

}
