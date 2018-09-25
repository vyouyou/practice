package reactive;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ReactorAsnyc {
    private static String getStringAsync(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "getStringAsync";
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Mono.fromCallable(ReactorAsnyc::getStringAsync)
                .subscribeOn(Schedulers.elastic())
                .subscribe(System.out::println,null,countDownLatch::countDown);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
