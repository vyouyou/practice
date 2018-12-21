package threadpool;

import lombok.extern.java.Log;

import java.util.concurrent.*;

/**
 * 自定义线程池
 *
 * @author qisy01
 * @create 18-10-21
 * @since 1.0.0
 */
@Log
public class ThreadPoolLearn {
    public static void main(String[] args) {
        ExecutorService service = new ThreadPoolExecutor(1,
                2,
                1,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1),
                //线程工厂类
                (r) -> new MyThread(r),
                //线程超出之后的处理策略
                new ThreadPoolExecutor.AbortPolicy()
        );
        service.execute(() -> {
            log.info("submit is {}" + Thread.currentThread().toString());
        });
        Future future = service.submit(() -> 1);
        try {
            int i = (int) future.get();
            log.info("i is " + i);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static class MyThread extends Thread {
        public MyThread(Runnable runnable) {
            super(runnable);
            log.info("lalala");
            //对于uncatchError的处理
            setUncaughtExceptionHandler((t, e) -> {
                        System.out.println(t.getName() + e.getMessage());
                    }
            );
        }
    }
}
