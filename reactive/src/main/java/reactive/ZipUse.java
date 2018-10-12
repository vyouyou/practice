package reactive;

import lombok.extern.java.Log;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Log
public class ZipUse {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        /**
         * 每两千毫秒发射一个
         */
        Flux.zip((Flux<String>)Flux.fromArray(new String[] {"a","E"}),
                Flux.interval(Duration.ofMillis(2000)))
                .subscribe(t->log.info(t.getT1()),null,countDownLatch::countDown);
        try {
            countDownLatch.await(10,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
