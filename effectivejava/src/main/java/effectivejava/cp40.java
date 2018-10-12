package effectivejava;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qisy01
 * @create 18-10-10
 * @since 1.0.0
 */

// {@literal M.S.}
public class cp40 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = -3; i < 3; i++) {
            list.add(i);
        }
        for (int i = 0; i < 3; i++) {
            // 此时remove并不是执行remove某个元素  list.remove((Integer) i)才可以
            list.remove(i);
        }
    }
}
