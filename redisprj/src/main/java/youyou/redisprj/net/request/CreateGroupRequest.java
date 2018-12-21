package youyou.redisprj.net.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author qisy01
 * @create 18-11-29
 * @since 1.0.0
 */
@Setter
@Getter
public class CreateGroupRequest {
    private String groupName;

    private List<String> membersName;
}
