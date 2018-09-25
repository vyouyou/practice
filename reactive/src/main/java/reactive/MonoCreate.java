package reactive;

import lombok.extern.java.Log;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Log
public class MonoCreate {
    static void log(Object o) {
        log.info("" + o);
    }

    public static void main(String[] args) {
        Mono.fromSupplier(() -> "hello").subscribe(MonoCreate::log);
        Mono.justOrEmpty(Optional.of("hello")).subscribe(MonoCreate::log);
        Mono.create(monoSink -> monoSink.success("success")).subscribe(MonoCreate::log);
    }
}
