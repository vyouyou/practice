package concurrentool;

import lombok.extern.java.Log;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * 信号量
 */
@Log
public class SemaphoreLearn<T> {
    private final Set<T> set;
    private Semaphore sem;
    private int i = 0;

    SemaphoreLearn(){
        set = new HashSet<>();
        // semaphore 是許可的數量
        sem = new Semaphore(10);
    }

    public void removeSem(){
        try {
            //acquire會獲取許可，當許可為0，則會阻塞等待
            sem.acquire();
            log.info("remove time:"+i++);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //release會釋放許可
            sem.release();
        }
    }

    public static void main(String[] args) {
        SemaphoreLearn semaphoreLearn = new SemaphoreLearn();
        for (int i = 0;i<12;i++){
            semaphoreLearn.removeSem();
        }
    }

}
