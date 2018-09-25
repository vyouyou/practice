package reactive;


import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class ReactorBase {
    public static void main(String[] args) {
        Flux.just("hello,nihao").subscribe(System.out::print);
        Flux.fromArray(new Integer[] {1,2,3}).subscribe(System.out::print);
        Flux.empty().subscribe(System.out::print);
        Flux.range(1,10).subscribe(System.out::print);
        Flux.interval(Duration.of(10, ChronoUnit.SECONDS)).subscribe(System.out::print);
    }
}
