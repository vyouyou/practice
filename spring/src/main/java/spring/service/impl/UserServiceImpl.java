package spring.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.model.UserModel;
import spring.repository.UserMapper;
import spring.request.LoginRequest;
import spring.service.IUserService;

import java.util.Optional;

/**
 * @author qisy01
 * @create 18-11-7
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Boolean login(LoginRequest loginRequest) {
        Boolean success = false;
        return Optional.ofNullable(userMapper.getUserByUsername(loginRequest.getUsername()))
                .map((user) -> {
                            if (!user.getPassword().equals(loginRequest.getPassword())) {
                                return false;
                            }
                            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
                            Subject subject = SecurityUtils.getSubject();
                            subject.login(token);
                            return true;
                        }
                ).orElse(false);
    }

    @Override
    public UserModel getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
