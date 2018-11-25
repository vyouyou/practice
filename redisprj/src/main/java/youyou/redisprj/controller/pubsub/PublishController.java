package youyou.redisprj.controller.pubsub;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qisy01
 * @create 18-11-24
 * @since 1.0.0
 */
@RestController
@RequestMapping(path = "/event")
public class PublishController {

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/publish")
    @ApiOperation(value = "发布")
    public String publish(String event) {
        redisTemplate.convertAndSend("major.event", event);
        return "publish";
    }

    @PostMapping("/publish-dota")
    @ApiOperation(value = "发布dota")
    public String publishDota(String dota){
        redisTemplate.convertAndSend("major.dota",dota);
        return "dota publish";
    }
}
