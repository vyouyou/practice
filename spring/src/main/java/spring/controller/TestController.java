package spring.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.annotation.Qual;
import spring.dto.TestDTO;

import java.util.Date;

@RestController
@RequestMapping("/")
@Log
public class TestController {
    @Qual(value = "baseCImpl1")
    @Autowired
    private BaseC baseC;

    private BaseA baseA;

    public TestController(){
        log.info("TestController");
    }

    @GetMapping("/")
    public String h(){
        if (baseA!=null){
            log.info("======= base a not null");
        }
        return "h";
    }

    @PostMapping("/date")
    public TestDTO getDate(@RequestBody TestDTO testDTO){
        testDTO.setStartDate(new Date());
        return testDTO;
    }

    @Autowired
    public void autoWiredTest(BaseA baseA){
        log.info("autowired");
        this.baseA = baseA;
    }
}
