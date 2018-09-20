package spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@PropertySource(value = {"classpath:application.yml"})
public class ValueController {

    @Value("${user.sex}")
    private String defaultValue;

    @GetMapping(value = "/default-value")
    public String defaultValue(){
        System.out.printf("i am "+defaultValue);
        return defaultValue;
    }
}
