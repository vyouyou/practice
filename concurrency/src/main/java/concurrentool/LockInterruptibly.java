package concurrentool;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qisy01
 * @create 18-10-24
 * @since 1.0.0
 */
public class LockInterruptibly {
    Lock lock = new ReentrantLock();

    public boolean send() {
        try {
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
