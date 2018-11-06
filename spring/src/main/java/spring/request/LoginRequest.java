package spring.request;

import lombok.Getter;
import lombok.Setter;

/**
 * 登录请求
 *
 * @author qisy01
 * @create 18-11-5
 * @since 1.0.0
 */
@Setter
@Getter
public class LoginRequest {
    private String username;

    private String password;
}
