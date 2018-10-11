import jdk.nashorn.internal.runtime.options.Option;
import lombok.extern.java.Log;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;

/**
 * @author qisy01
 * @create 18-9-28
 * @since 1.0.0
 */
@Log
public class SteamTest {
    public static void main(String[] args) {
        String[] strArray = {"he", "she", "it", "they"};
        Stream<String> stream = Stream.of(strArray);
//        terminalTest(stream);
//        interminalTest(Stream.of(strArray));
        sortedTest(Stream.of(strArray));
        Optional<String> str = Optional.ofNullable(null);
    }

    private static void sortedTest(Stream<String> strArray) {
        String string = strArray.sorted((s1, s2) -> s2.compareTo(s1))
                .skip(1)
                .collect(Collectors.joining("->"));
        log.info("sortedtest " + string);
    }

    /**
     * 可以通过链式流动
     *
     * @param strArray
     */
    private static void interminalTest(Stream<String> strArray) {
        log.info(strArray.sorted().collect(Collectors.joining(",")));
    }

    private static void terminalTest(Stream<String> stream) {
        stream.forEach(item -> {
            log.info(item);
        });
        //同一个steam不能被遍历两次
        stream.forEach(item -> {
            log.info(item);
        });
    }
}
