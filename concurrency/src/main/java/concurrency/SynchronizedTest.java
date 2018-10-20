package concurrency;

import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * page61
 *
 * @author qisy01
 * @create 18-10-18
 * @since 1.0.0
 */
@Log
public class SynchronizedTest {

    public List<Integer> list
            = Collections.synchronizedList(new ArrayList<>());

    SynchronizedTest() {
        new Thread(() -> {
            synchronized (list) {
                list.add(1);
                try {
                    Thread.sleep(1000);
                    log.info("after sleep list is " + list.get(0));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
//        log.info("list is " + list.get(0));
    }

    public static void main(String[] args) {
        new SynchronizedTest();
    }
}
