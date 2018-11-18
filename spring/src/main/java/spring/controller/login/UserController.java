package spring.controller.login;

import lombok.Getter;
import lombok.extern.java.Log;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import spring.request.LoginRequest;
import spring.service.IUserService;

import javax.servlet.ServletRequest;

/**
 * @author qisy01
 * @create 18-11-7
 * @since 1.0.0
 */
@RestController
@Log
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping(path = "/login")
    public void getByUsername(LoginRequest loginRequest) {
        userService.login(loginRequest);
    }

}
