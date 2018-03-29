package org.quark.microapidemo.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.sentinel.api.StatefulRedisSentinelConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.util.List;

@Configuration
public class RedisConfig {

    @Autowired(required = true)
    private RedisProperties properties;

    // Construct the RedisSentinelConfiguration using all the nodes in RedisSentinelNodes
    RedisURI createSentinelURI () {

        RedisURI.Builder builder = RedisURI.builder().withSentinelMasterId(properties.getSentinel().getMaster());

        List<String> nodes = properties.getSentinel().getNodes();

        for (int i = 0; i < nodes.size(); i++) {

            String node  = nodes.get(i);
            String[] ipAndPort = node.split(":");

            builder.withSentinel(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
        }

        return builder.build();
    }

    @Bean
    StatefulRedisSentinelConnection<String, String> redisConnectionFactory() {

        RedisClient client = RedisClient.create(createSentinelURI());
        StatefulRedisSentinelConnection<String, String> connection = client.connectSentinel();
        return connection;
    }

    // Construct the RedisSentinelConfiguration using all the nodes in RedisSentinelNodes
    RedisSentinelConfiguration sentinelConfiguration () {
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
                .master(properties.getSentinel().getMaster());

        List<String> nodes = properties.getSentinel().getNodes();

        for (int i = 0; i < nodes.size(); i++) {

            String node  = nodes.get(i);
            String[] ipAndPort = node.split(":");

            sentinelConfig.sentinel(ipAndPort[0],Integer.parseInt(ipAndPort[1]));
            sentinelConfig.setPassword(RedisPassword.of(properties.getPassword()));
        }

        return sentinelConfig;
    }

    @Bean
    @Qualifier("RedisConnectionFactory")
    @Scope("singleton")
    LettuceConnectionFactory getLettuceConnectionFactory() {

        return new LettuceConnectionFactory(sentinelConfiguration());
    }
}
