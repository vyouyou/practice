package spring.controller.login;

import lombok.extern.java.Log;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.request.LoginRequest;
import spring.shiro.IncorrectCaptchaException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author qisy01
 * @create 18-11-2
 * @since 1.0.0
 */
@Controller
@Log
public class HomeController {


    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(HttpServletRequest request, Map<String, Object> map) {
        Object exception = request.getAttribute("shiroLoginFailure");
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.isInstance(exception)) {
                msg = "-->账号不存在";
            } else if (IncorrectCredentialsException.class.isInstance(exception)) {
                msg = "-->密码不正确";
            } else if (IncorrectCaptchaException.class.isInstance(exception)) {
                msg = "-->验证码不正确";
            } else {
                msg = "-->未知错误";
            }
            log.info("msg" + msg);
            map.put("msg", msg);
            return "login";
        }
        return "index";
    }
}
