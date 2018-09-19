package spring.controller.impl;

import org.springframework.stereotype.Component;
import spring.controller.BaseA;

@Component
public class BaseAImpl implements BaseA {
    @Override
    public String sayHello() {
        return "hello";
    }
}
