package effectivejava;

import lombok.extern.java.Log;

@Log
/**
 * 需要被覆盖的方法，不能在构造函数中被使用到
 */
public class cp17 {
    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.overrideMe();
    }

    public static class Sub extends Super{
        public Sub(){

        }

        @Override
        public void overrideMe() {
            log.info("i am sub");
        }
    }

    public static class Super{
        /**
         * 子类初始化过程中，父类的构造函数也会被调用到
         * 但是父类此时调用的是子类中重写后的方法，可能
         * 会出现意想不到的错误
         */
        public Super(){
            overrideMe();
        }

        public void overrideMe(){
            log.info("i am super");
        }
    }
}
