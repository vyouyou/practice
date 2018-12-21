package youyou.redisprj.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import youyou.redisprj.controller.pubsub.DotaListener;
import youyou.redisprj.controller.pubsub.EventListener;

/**
 * @author qisy01
 * @create 18-11-21
 * @since 1.0.0
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Autowired
    private DotaListener dotaListener;

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //加上这行，会自动增加上classtype
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        serializer.setObjectMapper(objectMapper);

        //设置序列化方法
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);
        template.setHashKeySerializer(serializer);
        template.setHashValueSerializer(serializer);
        //开启事务
        template.setEnableTransactionSupport(true);

        template.afterPropertiesSet();
        return template;
    }


    @Bean
    public MessageListenerAdapter messageListenerAdapter(EventListener listener) {
        return new MessageListenerAdapter(listener);
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory factory,
                                                                       MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.addMessageListener(listenerAdapter, new PatternTopic("major.event"));
        container.addMessageListener(dotaListener, new PatternTopic("major.dota"));
        return container;
    }
}
