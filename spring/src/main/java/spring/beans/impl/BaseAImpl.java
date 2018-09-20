package spring.beans.impl;

import org.springframework.stereotype.Component;
import spring.beans.BaseA;

@Component
public class BaseAImpl implements BaseA {
    @Override
    public String sayHello() {
        return "hello";
    }
}
