package threadpool;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author qisy01
 * @create 18-10-30
 * @since 1.0.0
 */
@Log
public class ScheduledExcutorServiceTest {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        Task task2 = new Task(2);
        service.schedule(new Task(1), 1, TimeUnit.SECONDS);
        service.schedule(task2, 1, TimeUnit.SECONDS);
        task2.cancel();
    }

    private static class Task extends TimerTask {
        int num;
        public boolean canceled = false;

        public Task(int num) {
            this.num = num;
        }

        @Override
        public void run() {
            if (!canceled) {
                log.info("num is " + num);
            }
        }

        @Override
        public boolean cancel() {
            canceled = true;
            return super.cancel();
        }
    }
}
