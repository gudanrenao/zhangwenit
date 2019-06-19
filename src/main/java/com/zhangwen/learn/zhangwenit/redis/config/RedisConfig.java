package com.zhangwen.learn.zhangwenit.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description
 * @Author ZWen
 * @Date 2019/6/12 3:28 PM
 * @Version 1.0
 **/
@Configuration
public class RedisConfig {

    @Bean
    public HashOperations<String, String, String> hashOperations(StringRedisTemplate stringRedisTemplate) {
        return stringRedisTemplate.opsForHash();
    }


    /**
     * 定义缓存数据 key 生成策略的bean
     * 包名+类名+方法名+所有参数
     *
     * @return
     */
    @Bean
    public KeyGenerator wiselyKeyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    /**
     * 要启用spring缓存支持,需创建一个 CacheManager的 bean，CacheManager 接口有很多实现，
     * 这里Redis 的集成，用 RedisCacheManager这个实现类
     * Redis 不是应用的共享内存，它只是一个内存服务器，就像 MySql 似的，
     * 我们需要将应用连接到它并使用某种“语言”进行交互，
     * 因此我们还需要一个连接工厂以及一个 Spring 和 Redis 对话要用的 RedisTemplate，
     * 这些都是 Redis 缓存所必需的配置，把它们都放在自定义的 CachingConfigurerSupport 中
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        // 设置缓存有效期一小时
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofHours(1));
        // 对每个缓存空间应用不同的配置
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>(4);
        configMap.put("userRole", config.entryTtl(Duration.ofDays(1)));
        //设置一个初始化的缓存空间set集合
        Set<String> cacheNames = new HashSet<>(configMap.keySet());
        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(config)
                .initialCacheNames(cacheNames)
                .withInitialCacheConfigurations(configMap)
                .build();
    }

    /**
     * 项目启动时此方法先被注册成bean被spring管理
     *
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}