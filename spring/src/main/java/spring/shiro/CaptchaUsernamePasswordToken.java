package spring.shiro;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.authc.UsernamePasswordToken;

import java.awt.peer.CanvasPeer;

/**
 * @author qisy01
 * @create 18-11-5
 * @since 1.0.0
 */
public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {
    private static final long serialVersionUID = 1L;

    @Setter
    @Getter
    private String captcha;

    public CaptchaUsernamePasswordToken(String username, String password,
                                        boolean rememberMe, String host, String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }
}
