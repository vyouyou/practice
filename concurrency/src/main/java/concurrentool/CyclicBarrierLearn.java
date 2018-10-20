package concurrentool;

import lombok.extern.java.Log;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

@Log
public class CyclicBarrierLearn {
    private final CyclicBarrier barrier;

    CyclicBarrierLearn() {
        //必須要達到一定數量才會開始執行任務
        barrier = new CyclicBarrier(5, () -> {
            log.info("start");
        });
        //任務必須達到5才會執行
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    barrier.await();
                    log.info("num is:" + finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        new CyclicBarrierLearn();
    }

}
