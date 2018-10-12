import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author qisy01
 * @create 18-9-28
 * @since 1.0.0
 */
public class Lambda {
    public static void main(String[] args) {
        List<String> ls = Arrays.asList("12", "13", "454");
        filter(ls,name->true);

        Optional<String> str = Optional.of("aaa");
        str.ifPresent(System.out::println);
        str.map(s -> s).orElse(null);
    }

    public static void filter(List<String> names, Predicate condition) {
        for (String name : names) {
            if (condition.and((name1)->name1.equals("454")).test(name))
                System.out.println(name);
        }
    }
}
