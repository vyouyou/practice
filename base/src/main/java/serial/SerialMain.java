package serial;

import java.io.*;

/**
 * @author qisy01
 * @create 18-10-14
 * @since 1.0.0
 */
public class SerialMain {
    public static void main(String[] args) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("/home/youyou/a.txt"))) {
            StudentPOJO studentPOJO = new StudentPOJO();
            studentPOJO.setName("ali");
            studentPOJO.setAge(17);
            studentPOJO.setSex("nv");
            outputStream.writeObject(studentPOJO);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("/home/youyou/a.txt"))) {
            StudentPOJO1 studentPOJO1 = (StudentPOJO1) inputStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
