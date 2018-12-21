package youyou.redisprj.controller.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DefaultTuple;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;
import youyou.redisprj.net.request.ChatGroupRequest;
import youyou.redisprj.net.request.ChatMessageRequest;
import youyou.redisprj.net.request.CreateGroupRequest;
import youyou.redisprj.net.response.ChatMessageResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author qisy01
 * @create 18-11-28
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/chat")
public class ChatController {
    private static final String CHAT_GROUP = "chat:group:";

    private static final String CHAT_USER = "chat:user:";

    private static final String CHAT_MESSAGE = "chat:message:";

    private static final String IDS = "chat:ids:";

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping(path = "/create")
    @ApiOperation(value = "创建群组")
    public String createGroup(@RequestBody CreateGroupRequest createGroupRequest) {
        String groupName = createGroupRequest.getGroupName();
        String[] membersName = new String[createGroupRequest.getMembersName().size()];
        createGroupRequest.getMembersName().toArray(membersName);
        redisTemplate.executePipelined((RedisConnection connection) -> {
                    for (String memberName : membersName) {
                        redisTemplate.opsForZSet().add(CHAT_USER + memberName, groupName, 1);
                        redisTemplate.opsForZSet().add(CHAT_GROUP + groupName, memberName, 1);
                    }
                    redisTemplate.opsForValue().increment(IDS + groupName);
                    connection.closePipeline();
                    return null;
                }
        );
        return "success";
    }


    @PostMapping(path = "/join")
    @ApiOperation(value = "加入聊天")
    public String joinGroup(@RequestBody ChatGroupRequest chatGroupRequest) {
        String groupName = chatGroupRequest.getGroupname();
        String userName = chatGroupRequest.getUsername();
        redisTemplate.executePipelined((RedisConnection connection) -> {
                    Integer seenId = (Integer) redisTemplate.opsForValue().get(IDS + groupName);
                    connection.closePipeline();
                    connection.openPipeline();
                    redisTemplate.opsForZSet().add(CHAT_GROUP + groupName, userName, seenId);
                    redisTemplate.opsForZSet().add(CHAT_USER + userName, groupName, seenId);
                    connection.closePipeline();
                    return null;
                }
        );

        return "success";
    }

    @PostMapping(path = "/sendmessage")
    @ApiOperation(value = "发送消息")
    public String sendMessage(@RequestBody ChatMessageRequest chatMessageRequest) {
        String groupName = chatMessageRequest.getGroupname();
        redisTemplate.executePipelined((RedisConnection connection) -> {
            // messageid
            redisTemplate.opsForValue().increment(IDS + groupName);
            List<Object> list = connection.closePipeline();
            redisTemplate.opsForZSet().add(CHAT_MESSAGE + groupName, chatMessageRequest, Double.valueOf((Long) list.get(0)));
            return null;
        });
        return "sendSuccess";
    }

    @GetMapping(path = "/readMessage/{groupName}/{userName}")
    @ApiOperation(value = "接收消息")
    public List<ChatMessageResponse> readMessage(@PathVariable(value = "groupName") String groupName,
                                                 @PathVariable(value = "userName") String userName)

    {
        Double score = redisTemplate.opsForZSet().score(CHAT_GROUP + groupName, userName);
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        connection.openPipeline();
        connection.zRangeByScoreWithScores((CHAT_MESSAGE + groupName).getBytes(), score, Long.MAX_VALUE);
        connection.get((IDS + groupName).getBytes());
        List<Object> results = connection.closePipeline();
        Set<DefaultTuple> messages = (Set<DefaultTuple>) results.get(0);
        Long messageId = Long.valueOf(new String((byte[]) results.get(1)));
        ObjectMapper mapper = new ObjectMapper();
        List<ChatMessageResponse> chatMessageResponseList = messages.stream().map(typedTuple -> {
            try {
                return mapper.readValue(new String(typedTuple.getValue()), ChatMessageResponse.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        connection.openPipeline();
        connection.zAdd((CHAT_GROUP + groupName).getBytes(), messageId, userName.getBytes());
        connection.zAdd((CHAT_USER + userName).getBytes(), messageId, groupName.getBytes());
        connection.closePipeline();
        clearReadedMessage(groupName);
        return chatMessageResponseList;
    }

    private void clearReadedMessage(String groupName) {
        Set<ZSetOperations.TypedTuple> set =
                redisTemplate.opsForZSet().rangeByScoreWithScores(CHAT_GROUP + groupName, 0, Long.MAX_VALUE);
        redisTemplate.opsForZSet().removeRangeByScore(CHAT_MESSAGE + groupName, 0, set.iterator().next().getScore());

    }

    @PostMapping(path = "/leave")
    @ApiOperation(value = "离开群组")
    public void leaveGroup(@RequestBody ChatGroupRequest chatGroupRequest) {
        String groupName = chatGroupRequest.getGroupname();
        String userName = chatGroupRequest.getUsername();
        redisTemplate.executePipelined((RedisConnection connection) -> {
            redisTemplate.opsForZSet().remove(CHAT_GROUP + groupName, userName);
            redisTemplate.opsForZSet().remove(CHAT_USER + userName, groupName);
            connection.closePipeline();
            return null;
        });
    }
}
