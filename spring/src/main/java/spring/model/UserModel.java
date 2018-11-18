package spring.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

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
public class UserModel implements Serializable{
    private Integer id;

    private String username;

    private String password;

    private Integer roleId;
}
