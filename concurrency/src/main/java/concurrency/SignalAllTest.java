package concurrency;

import lombok.extern.java.Log;

/**
 * @author qisy01
 * @create 18-10-25
 * @since 1.0.0
 */
@Log
public class SignalAllTest {

    public static void main(String[] args) {
        Object lock = new Object();

        new Thread(()->{
            try {
                synchronized (lock){
                    lock.wait();
                }
                log.info("i am 1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                synchronized (lock){
                    lock.wait();
                }
                log.info("i am 2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            synchronized (lock){
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.notify();
            }
        }).start();
    }
}
