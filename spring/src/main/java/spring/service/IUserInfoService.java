package spring.service;

import spring.entity.UserInfo;

/**
 * @author qisy01
 * @create 18-11-2
 * @since 1.0.0
 */
public interface IUserInfoService {
    public UserInfo findByUsername(String username);
}
