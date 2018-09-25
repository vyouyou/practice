package reactive;

import lombok.extern.java.Log;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

@Log
public class ReactorGenerotor {
    public static void main(String[] args) {
        Random random = new Random();
        Flux.generate(ArrayList::new,(list,sink)->{
            int value = random.nextInt();
            list.add(value);
            sink.next(value);
            if (list.size()==10){
                sink.complete();
            }
            return list;
        }).subscribe(System.out::println);
    }
}
