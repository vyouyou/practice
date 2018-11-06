package spring.repository;

import org.springframework.data.repository.CrudRepository;
import spring.entity.UserInfo;

/**
 * @author qisy01
 * @create 18-11-2
 * @since 1.0.0
 */
public interface UserInfoDao extends CrudRepository<UserInfo, Long> {
    public UserInfo findByUsername(String username);
}
