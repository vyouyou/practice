package string;

import lombok.extern.java.Log;

import java.util.Arrays;

/**
 * kmp算法
 *
 * @author qisy01
 * @create 18-11-4
 * @since 1.0.0
 */
@Log
public class StringMain {
    public static void main(String[] args) {
        log.info(Arrays.toString(buildNext("ababcdab".toCharArray())));
    }

    static int match(char[] p, char[] t) {
        int[] next = buildNext(p);
        int i = 0, j = 0;
        while (j < p.length && i < t.length) {
            if (0 > j || t[i] == p[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        return i - j;
    }

    /**
     * buildnext中心思想在于，同一串中，有与开头位置
     * 相同的内容  如 abcxxxxxxxxabcyyy,那么如果匹配到第二个
     * abc后的y失败，那么重新开始下一次匹配的时候，
     * 则认为前三位已经匹配成功了
     *
     * @param p
     * @return
     */
    static int[] buildNext(char[] p) {
        int m = p.length;
        int[] n = new int[m];
        int t = n[0] = -1;
        int j = 0;
        while (j < m - 1) {
            if (0 > t || p[j] == p[t]) {
                j++;
                t++;
//                n[j] = t;
                //可以改进为
                n[j] = (p[j] != p[t]) ? t : n[t];
            } else {
                t = n[t];
            }
        }
        return n;
    }
}
