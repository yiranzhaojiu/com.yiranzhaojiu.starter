package com.yiranzhaojiu;

import org.redisson.Redisson;

import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnClass(Redisson.class) //条件装配，只有项目中有引用Redisson类的包，才去装载下面的配置信息
@EnableConfigurationProperties(RedisProperties.class) //首先RedisProperties的Bean对象
@Configuration
public class RedisAutoCofiguration {

    @Bean
    RedissonClient getRedisClient(RedisProperties redisProperties){
        Config config=new Config();
        String prefix="redis://";
        if(redisProperties.isSsl()){ //是否加密
            prefix="rediss://";
        }
        config.useSingleServer().
                setAddress(prefix+redisProperties.getHost()+":"+redisProperties.getPort()).
                setConnectTimeout(redisProperties.getTimeout());
        return Redisson.create(config);
    }
}
