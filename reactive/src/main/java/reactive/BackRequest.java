package reactive;

import lombok.extern.java.Log;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/**
 * 回压或者背压
 */
@Log
public class BackRequest {
    public static void main(String[] args) {
        Flux.range(0, 9)
                .doOnRequest(num -> {
                    log.info("取出值的个数" + num);
                })
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        log.info("hookOnSubscribe" + subscription.toString());
                        //必须要取出一个来进行启动，否则无法触发hookonnext函数
                        request(1);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        log.info("hookOnNext " + value);
                        request(1);
                    }

                });
    }
}
