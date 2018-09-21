package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.beans.ComponentFactoryBean;

@RestController
public class BeanFactoryController {
    @Autowired
    private ComponentFactoryBean factoryBean;

    @GetMapping("/bean-factory")
    public String beanFactory(){
        try {
            factoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "bean-factory";
    }
}
