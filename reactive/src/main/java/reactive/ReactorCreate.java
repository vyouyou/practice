package reactive;

import lombok.extern.java.Log;
import reactor.core.publisher.Flux;

import java.util.logging.Logger;

@Log
public class ReactorCreate {
    public static void main(String[] args) {
        Flux<String> flux = Flux.create(fluxSink -> {
            for (int i = 0; i < 10; i++) {
                fluxSink.next(i+"");
            }
            fluxSink.complete();
        });
        flux.subscribe(item->log.info(item));
    }
}
