package spring.controller.login;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qisy01
 * @create 18-11-2
 * @since 1.0.0
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    @RequestMapping("/userList")
    @RequiresPermissions("userInfo:view")
    public String userInfo() {
        return "userlist";
    }
}
