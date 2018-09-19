package spring.controller.impl;

import lombok.extern.java.Log;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import spring.controller.BaseC;

@Log
@Component
@Configuration
public class BaseCImpl1 implements BaseC {
    int i =1;

    public BaseCImpl1(){
        i =1;
        log.info("baseImpl1");
    }
}

