package spring.controller.login;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.request.LoginRequest;
import spring.service.IUserService;

/**
 * @author qisy01
 * @create 18-11-7
 * @since 1.0.0
 */
@RestController
@RequestMapping
@Log
public class UserController {
    @Autowired
    IUserService userService;

    @PostMapping(path = "/login")
    public void getByUsername(@RequestBody LoginRequest loginRequest) {
        userService.login(loginRequest);
    }
}
