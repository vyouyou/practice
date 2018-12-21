package youyou.redisprj;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import youyou.redisprj.controller.fairlock.FairLockController;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisprjApplicationTests {
    @Autowired
    private FairLockController fairLockController;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testFairLock() {
        ExecutorService service = Executors.newFixedThreadPool(32);
        long time = System.currentTimeMillis();
        while ((System.currentTimeMillis() - 5000) < time) {
            service.submit(() -> {
                fairLockController.getLock(UUID.randomUUID().toString());
            });
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void rangeBy() {
        Set set = redisTemplate.getConnectionFactory().getConnection().zRangeByScore("chat:group:sd".getBytes(), "-inf", "+inf");
        log.info("size is {}", set.size());
    }
}
