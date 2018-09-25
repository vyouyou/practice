package effectivejava;

import java.util.HashSet;
import java.util.Set;

/**
 * 泛型
 */
public class cp23 {
    public static void main(String[] args) {
        Set<?> set = new HashSet<>();
        //set<?>无法加入任何元素
        //无限制统配类型
//        set.add(11);
    }
}
