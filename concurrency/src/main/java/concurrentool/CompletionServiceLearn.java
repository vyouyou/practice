package concurrentool;

import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * 使用completeservice 可以完成不同的任务
 *
 * @author qisy01
 * @create 18-10-20
 * @since 1.0.0
 */
@Log
public class CompletionServiceLearn {
    private final CompletionService<Integer> completionService;

    /**
     * 使用completionService可以
     * 返回一个 {@link LinkedBlockingQueue }
     * 可以通过take方法取出{@link Future}
     */
    CompletionServiceLearn() {
        completionService = new ExecutorCompletionService(Executors.newCachedThreadPool());
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            completionService.submit((Callable) () -> {
                Thread.sleep(300 * (finalI));
                return finalI;
            });
        }
        for (int i = 0; i < 5; i++) {
            try {
                Future<Integer> future = completionService.take();
                log.info("now is " + System.currentTimeMillis() + future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 如果用future则通过一个list来执行
     */
    public void executorService() {
        ExecutorService service = Executors.newCachedThreadPool();
        List<Future<Integer>> futureList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            futureList.add(service.submit((Callable) () -> {
                Thread.sleep(1000 * (5 - finalI));
                return finalI;
            }));
        }
        futureList.forEach(future -> {
            try {
                log.info("executorService now is " + System.currentTimeMillis() + " is " + future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        new CompletionServiceLearn();
//        new CompletionServiceLearn().executorService();
    }
}
