import lombok.extern.java.Log;
import org.junit.Test;

/**
 * @author qisy01
 * @create 18-9-29
 * @since 1.0.0
 */
@Log
public class ConcurentTest {
    static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(ConcurentTest::consumer).start();
        new Thread(ConcurentTest::producer).start();
    }

    private static void producer() {
        try {
            synchronized (lock) {
                Thread.sleep(500);
                log.info("producer");
                lock.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void consumer() {
        try {
            synchronized (lock) {
                log.info("i am consumer lock");
//                while (true) {
                lock.wait();
                log.info("i am consumer");
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
