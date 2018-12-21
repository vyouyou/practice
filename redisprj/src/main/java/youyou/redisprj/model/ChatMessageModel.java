package youyou.redisprj.model;

import lombok.Getter;
import lombok.Setter;
import youyou.redisprj.net.request.ChatMessageRequest;

/**
 * @author qisy01
 * @create 18-11-29
 * @since 1.0.0
 */
@Setter
@Getter
public class ChatMessageModel extends ChatMessageRequest {
    private Long unreadNum;
}
