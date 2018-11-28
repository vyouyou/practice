package youyou.redisprj.controller.fairlock;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author qisy01
 * @create 18-11-27
 * @since 1.0.0
 */
@RestController
@Slf4j
public class FairLockController {

    private static final String ACQ_LOCK = "acquire:fair:lock";

    private static final String FAIR_TOPIC_BUYER_NUM = "acquie:topic:buyer:num";

    private static final String FAIR_TOPIC_BUYER_TIME = "acquie:topic:buyer:time";

    private static final int LIMIT = 5;

    private static final int EXPIRE_TIME = 10;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping(value = "/getlock")
    @ApiOperation("获得公平锁")
    public String getLock(String identify) {
        if (!redisTemplate.opsForValue().setIfAbsent(ACQ_LOCK, 0, 1000, TimeUnit.MILLISECONDS)) {
            return "cannot get acq lock";
        }
        log.info("{} acquire", identify);
        Boolean result = getFairLock(identify);
        redisTemplate.delete(ACQ_LOCK);
        log.info("{} delete", identify);
        try {
            Thread.sleep(1000);
            removeLock(identify);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.valueOf(result);
    }

    private boolean getFairLock(String identify) {
        redisTemplate.executePipelined((RedisCallback<?>) connection -> {
            long currentTime = System.currentTimeMillis();
            redisTemplate.opsForZSet().rangeWithScores(FAIR_TOPIC_BUYER_TIME, 0, 0);
            Optional.ofNullable((Set<RedisZSetCommands.Tuple>) connection.closePipeline().get(0))
                    .map(set -> set.size() > 0 ? set.iterator().next() : null)
                    .map(typedTuple ->
                            currentTime > typedTuple.getScore() ? typedTuple.getValue() : null)
                    .ifPresent(key -> {
                        removeLock(new String(key));
                    });
            connection.openPipeline();
            //增加分数
            redisTemplate.opsForZSet().reverseRangeWithScores(FAIR_TOPIC_BUYER_NUM, 0, 0);
            List<Object> closeList = connection.closePipeline();
            connection.openPipeline();
            Set<RedisZSetCommands.Tuple> set = (Set<RedisZSetCommands.Tuple>) closeList.get(closeList.size() - 1);
            double score = 0;
            if (set != null && set.size() > 0) {
                score = set.iterator().next().getScore();
            }
            connection.zAdd(FAIR_TOPIC_BUYER_NUM.getBytes(), score + 1, identify.getBytes());
            connection.zAdd(FAIR_TOPIC_BUYER_TIME.getBytes(), currentTime + EXPIRE_TIME * 1000, identify.getBytes());
            connection.zRank(FAIR_TOPIC_BUYER_NUM.getBytes(), identify.getBytes());
            List<Object> list = connection.closePipeline();
            if ((long) list.get(list.size() - 1) > LIMIT) {
                removeLock(identify);
            }
            return null;
        });
        return true;
    }

    private void removeLock(String key) {
        redisTemplate.executePipelined((RedisCallback<?>) connection -> {
            connection.openPipeline();
            connection.zRem(FAIR_TOPIC_BUYER_TIME.getBytes(), key.getBytes());
            connection.zRem(FAIR_TOPIC_BUYER_NUM.getBytes(), key.getBytes());
            connection.closePipeline();
            return null;
        });
    }
}
