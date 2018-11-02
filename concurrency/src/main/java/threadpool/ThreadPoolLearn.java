package threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 *
 * @author qisy01
 * @create 18-10-21
 * @since 1.0.0
 */
public class ThreadPoolLearn {
    public static void main(String[] args) {
        new ThreadPoolExecutor(10,
                20,
                1000,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(20),
                //线程工厂类
                (r) -> new MyThread(r),
                //线程超出之后的处理策略
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    private static class MyThread extends Thread {
        public MyThread(Runnable runnable) {
            super(runnable);
            //对于uncatchError的处理
            setUncaughtExceptionHandler((t, e) -> {
                        System.out.println(t.getName() + e.getMessage());
                    }
            );
        }
    }
}
