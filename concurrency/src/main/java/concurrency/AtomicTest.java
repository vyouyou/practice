package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
    static AtomicInteger symbol = new AtomicInteger(0);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i=0;i<5;i++){
            executorService.submit(()->{
               int j = 0;
               while (j<20000){
                   j++;
                   symbol.incrementAndGet();
               }
            });
        }
        try {
            Thread.sleep(2000);
            executorService.shutdown();
            System.out.println("symbol:=======>" + symbol);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
