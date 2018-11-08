package spring.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户model
 *
 * @author qisy01
 * @create 18-11-7
 * @since 1.0.0
 */
@Setter
@Getter
@ToString
public class UserModel {
    private Integer id;

    private String username;

    private String password;

    private Integer roleId;
}
