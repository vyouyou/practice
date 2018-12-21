package youyou.redisprj.net.response;

import lombok.Getter;
import lombok.Setter;
import youyou.redisprj.net.request.ChatGroupRequest;

/**
 * @author qisy01
 * @create 18-11-28
 * @since 1.0.0
 */
@Setter
@Getter
public class ChatMessageResponse extends ChatGroupRequest {
    private String message;
}
