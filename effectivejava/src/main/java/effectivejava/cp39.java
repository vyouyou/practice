package effectivejava;

import java.util.Date;

/**
 * @author qisy01
 * @create 18-10-10
 * @since 1.0.0
 */
public class cp39 {
    //看起来people不可变
    static class People {
        final Date birthDay;

        public People(Date date) {
            birthDay = date;
        }
    }

    static class People1{
        final Date birthDay;

        public People1(Date date) {
            //进行了保护
            birthDay = new Date(date.getTime());
        }
    }


    public static void main(String[] args) {
        //如果这样做
        Date date = new Date();
        People p = new People(date);

        People1 p1 = new People1(date);
        //对p有影响 对p1没有影响
        date.setYear(1972);
    }
}
