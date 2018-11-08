package spring.repository;

import spring.model.UserModel;

import java.util.List;

/**
 * @author qisy01
 * @create 18-11-7
 * @since 1.0.0
 */
public interface UserMapper {
    UserModel getUserByUsername(String username);
}
