package effectivejava;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @author qisy01
 * @create 18-10-11
 * @since 1.0.0
 */
public class cp67Synchronized {
    private Object lock = new Object();

    public static void main(String[] args) {
        cp67Synchronized cp67Synchronized = new cp67Synchronized();
        cp67Synchronized.funa();
    }

    void funa() {
        synchronized (lock) {
            System.out.println(new Date());
            funb();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void funb() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future future = service.submit(() -> {
            synchronized (lock) {
                System.out.println("b" + new Date());
            }
        });
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
