package spring.service;

import spring.model.UserModel;
import spring.request.LoginRequest;

/**
 * @author qisy01
 * @create 18-11-7
 * @since 1.0.0
 */
public interface IUserService {

    Boolean login(LoginRequest loginRequest);

    UserModel getUserByUsername(String username);
}
