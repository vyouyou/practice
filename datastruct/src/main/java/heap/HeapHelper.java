package heap;

/**
 * @author qisy01
 * @create 18-11-3
 * @since 1.0.0
 */
public class HeapHelper {
    public static boolean inHeap(int n, int i) {
        return (-1 < i) && i < n;
    }

    public static int getParent(int i) {
        if (i == 0) {
            return i;
        }
        return (i - 1) >> 1;
    }

    public static boolean isTop(int i) {
        return i == 0;
    }

    public static int lastInternal(int n) {
        return n - 1;
    }


    public static int leftChild(int i) {
        // 算术运算优先级高于位运算
        return 1 + (i << 1);
    }

    public static int rightChild(int i) {
        return (i + 1) << 1;
    }

}
