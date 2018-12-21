package youyou.redisprj.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author qisy01
 * @create 18-11-24
 * @since 1.0.0
 */
@RestController
@Slf4j
@RequestMapping(value = "/redis")
public class TransicationController {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String INCRE_KEY = "incre:key";

    private static final String GAME_MONEY = "game:money";

    public void keyIncre() {
        RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
        RedisConnection connection = factory.getConnection();
        connection.openPipeline();
        connection.multi();
        connection.incr(INCRE_KEY.getBytes());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        connection.exec();
    }

    @ApiOperation("自增事务")
    @PostMapping("/increase")
    public String redisTrans() {
        redisTemplate.opsForValue().setIfAbsent(INCRE_KEY, 1);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 3; i > 0; i--) {
            service.submit(() -> {
                keyIncre();
            });
        }
        return "increase";
    }

    @ApiOperation("观察事务")
    @PostMapping("/watch")
    public String redisWatch() {
        redisTemplate.opsForValue().set(GAME_MONEY, 2000);
        new Thread(() -> {
            RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
            // watch 这个key以后，如果中途发生了变化，则事务不会执行 可以用unwatch取消
            //而事务是一直重试知道执行成功
            connection.watch(GAME_MONEY.getBytes());
            try {
                connection.multi();
                Thread.sleep(200);
                connection.set(GAME_MONEY.getBytes(), "7000".getBytes());
                List<Object> objectList = connection.exec();
                Boolean result = (Boolean) objectList.get(0);
                if (result != null && result) {
                    log.info("事务执行成功");
                } else {
                    log.info("事务执行失败了，需要重试");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(100);
                redisTemplate.opsForValue().set(GAME_MONEY, 5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
        return "watch";
    }
}
