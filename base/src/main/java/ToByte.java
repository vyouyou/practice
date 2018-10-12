import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class ToByte {
    public static void main(String[] args) {
        byte[] bytes = intToByteArray(0xa);
        byte[] bytes1 = intToByteArray(0xa);
        System.out.println(bytes.equals(bytes1) + "" + Arrays.equals(bytes, bytes1));
        for (int i = 0; i < bytes.length; i++) {
            System.out.println("====>" + bytes[i]);
        }
    }

    public static byte[] intToByteArray(int a) {
        return new byte[]{
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF),
        };
    }
}
