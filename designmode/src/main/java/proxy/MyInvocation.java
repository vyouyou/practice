package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author qisy01
 * @create 18-11-18
 * @since 1.0.0
 */
public class MyInvocation implements InvocationHandler {

    private IHello hello;

    public MyInvocation() {
        this.hello = new HelloImpl();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke" + args[0]);
        return method.invoke(hello, args);
    }
}
