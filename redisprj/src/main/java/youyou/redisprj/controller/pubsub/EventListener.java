package youyou.redisprj.controller.pubsub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author qisy01
 * @create 18-11-24
 * @since 1.0.0
 */
@Component
@Slf4j
public class EventListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = new String(message.getChannel());
        String body = new String(message.getBody());
        log.info("channel is {} body is {}", channel, body);
    }
}
