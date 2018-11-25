package proxy;

import java.lang.reflect.Proxy;

/**
 * @author qisy01
 * @create 18-11-18
 * @since 1.0.0
 */
public class ProxyMain {
    public static void main(String[] args) {
        IHello sayHello = (IHello) Proxy.newProxyInstance(
                ProxyMain.class.getClassLoader(),
                new Class[]{IHello.class},
                new MyInvocation());
        sayHello.sayHello("haha");
    }
}
