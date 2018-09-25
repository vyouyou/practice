package effectivejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class cp25 {
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6);
        reduce(integerList, (arg1, arg2) -> arg1 + arg2, 0);
    }

    static <E> E reduce(List<E> list, Function<E> f, E initVal) {
        E[] snapshot = (E[]) list.toArray();
        E result = initVal;
        for (E e : snapshot)
            result = f.apply(result, e);
        return result;
    }

    interface Function<T> {
        T apply(T arg1, T arg2);
    }
}
