package youyou.redisprj.net.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author qisy01
 * @create 18-11-28
 * @since 1.0.0
 */
@Setter
@Getter
public class ChatMessageRequest extends ChatGroupRequest {
    private String message;
}
