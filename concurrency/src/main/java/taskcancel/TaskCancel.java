package taskcancel;

import lombok.extern.java.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * sleep是可以响应interrupt方法的
 * 会产生一个 InterruptedException 错误
 *
 * @author qisy01
 * @create 18-10-20
 * @since 1.0.0
 */
@Log
public class TaskCancel {
    public static void main(String[] args) {
//        Thread t = new Thread(() -> {
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                log.info(e.getMessage());
//                e.printStackTrace();
//            }
//        });
//        t.start();
//        t.interrupt();
    }
}
