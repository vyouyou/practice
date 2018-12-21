package proxy;

import java.lang.reflect.Proxy;

/**
 * @author qisy01
 * @create 18-11-18
 * @since 1.0.0
 */
public class ProxyMain {
    public static void main(String[] args) {
        IHello1 sayHello = (IHello1) Proxy.newProxyInstance(
                ProxyMain.class.getClassLoader(),
                new Class[]{IHello.class,IHello1.class},
                new MyInvocation());
        sayHello.sayHello("haha");
        sayHello.sayByebyte();
    }
}
