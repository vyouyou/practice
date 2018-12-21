import java.util.HashMap;
import java.util.Map;

/**
 * @author qisy01
 * @create 18-12-14
 * @since 1.0.0
 */
public class MpaTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        System.out.println(map.putIfAbsent("1", "2"));
        System.out.println(map.putIfAbsent("1", "3"));
    }
}
