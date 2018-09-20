package spring.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import spring.annotation.Qual;
import spring.beans.BaseA;
import spring.beans.BaseBImpl;
import spring.beans.BaseC;
import spring.dto.TestDTO;
import spring.event.BlackListNotifier;
import spring.event.EmailService;

import java.util.Date;

@RestController
@Log
public class TestController {
    @Qual(value = "baseCImpl1")
    @Autowired
    private BaseC baseC;

    private BaseA baseA;

    @Autowired
    private BaseBImpl baseB;

    @GetMapping("/")
    public String h(){
        baseB.sayIam();
        return "h";
    }

    @PostMapping("/date")
    public TestDTO getDate(@RequestBody TestDTO testDTO){
        testDTO.setStartDate(new Date());
        return testDTO;
    }

    @GetMapping("/send-mail")
    public void sendMail(EmailService emailService, BlackListNotifier blackListNotifier){
        emailService.sendEmail("hahaha");
    }

    @Autowired
    public void autoWiredTest(BaseA baseA){
        this.baseA = baseA;
    }
}
