package spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.entity.UserInfo;
import spring.repository.UserInfoDao;
import spring.service.IUserInfoService;

/**
 * @author qisy01
 * @create 18-11-2
 * @since 1.0.0
 */
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo findByUsername(String username) {
        return userInfoDao.findByUsername(username);
    }
}
