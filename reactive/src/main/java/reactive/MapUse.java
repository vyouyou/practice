package reactive;

import lombok.extern.java.Log;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Date;

@Log
public class MapUse {
    static void log(Object o) {
        log.info(new Date().toString() + o);
    }

    public static void main(String[] args) {
//        Flux.range(1,6)
//                .map(i->i+2)
//                .subscribe(MapUse::log);

        /**
         * 合并成一个大的数据流
         */
        new Thread(() -> {
            Flux.just("flux", "mono")
                    .flatMap(s -> {
                                log.info(s);
                                return Flux.fromArray(s.split("\\s*"))
                                        .delayElements(Duration.ofMillis(100));
                            }

                    )
                    .subscribe(MapUse::log);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //需要阻塞进程
//        while (true) {
//        }
    }
}
