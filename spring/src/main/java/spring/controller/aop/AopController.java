package spring.controller.aop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qisy01
 * @create 18-11-19
 * @since 1.0.0
 */
@RestController
public class AopController {
    @GetMapping("/aop-controller")
    public String aopController() {
        return "aop-controller";
    }
}
