import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

/**
 * @author qisy01
 * @create 18-9-26
 * @since 1.0.0
 */
public class ArrayUtilsTest {

    @Test
    public void testSub() {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = ArrayUtils.subarray(a, 2, 4);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
        for (int i = 0; i < b.length; i++) {
            System.out.println(b[i]);
        }
    }

}
