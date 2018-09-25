package reactive;

import reactor.core.publisher.Flux;

public class ErrorHandler {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .map(item -> {
                    if (item == 6) throw new IllegalArgumentException();
                    return item;
                })
                //发生错误return
//                .onErrorReturn(1)
                //发生错误继续
                .onErrorContinue((throwable, object) -> System.out.println("error"))
                .subscribe(System.out::print, System.out::print);

        //如果正常结束  signalType是complete  如果错误是error
        Flux.range(1, 10)
                .map(item -> {
//                    if (item == 6)
//                        throw new IllegalArgumentException();
                    return item;
                })
                .doFinally(signalType -> {
                    System.out.print("i am final" + signalType.toString());
                })
                .subscribe(System.out::print, System.out::print);
    }
}
