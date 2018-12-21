package spring.controller;

import lombok.extern.java.Log;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import spring.annotation.Qual;
import spring.beans.*;
import spring.dto.TestDTO;
import spring.event.BlackListNotifier;
import spring.event.EmailService;

import java.util.Date;

@RestController
@Log
public class TestController {
    private BaseA baseA;

    @Autowired
    @Lazy
    private BaseB baseB;

    @Qual(value = "baseCImpl1")
    @Autowired
    private BaseC baseC;

    @GetMapping("/")
    public String h() {
        baseB.baseB();
        return "h";
    }

    @PostMapping("/date")
    public TestDTO getDate(@RequestBody TestDTO testDTO) {
        testDTO.setStartDate(new Date());
        return testDTO;
    }

    @GetMapping("/aop-test")
    public void aopTest() {
        log.info("i am aop test");
        //调用了编织后的方法
        //        ((TestController)AopContext.currentProxy()).h();
        //此时的h()是没有编织的
        //        h();
    }

    @GetMapping("/send-mail")
    public void sendMail(EmailService emailService, BlackListNotifier blackListNotifier) {
        emailService.sendEmail("hahaha");
    }

    @Autowired
    public void autoWiredTest(BaseA baseA) {
        this.baseA = baseA;
    }
}
