package org.quark.microapidemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.lang.reflect.Method;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

    @Autowired
    @Qualifier(value = "RedisConnectionFactory")
    private LettuceConnectionFactory lettuceConnectionFactory;

    @Override
    public CacheManager cacheManager() {

        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
                .RedisCacheManagerBuilder
                .fromConnectionFactory(lettuceConnectionFactory);

        return builder.build();
    }

    @Override
    public KeyGenerator keyGenerator() {

        return  new KeyGenerator() {

            @Override
            public Object generate(Object target, Method method, Object... params) {

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(target.getClass().getName());
                stringBuilder.append(method.getName());

                for (Object object : params) {

                    if (object == null)
                        continue;

                    stringBuilder.append(object.toString());
                }

                return  stringBuilder.toString();
            }
        };
    }

    @Override
    public CacheResolver cacheResolver() {
        return null;
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return null;
    }
}
