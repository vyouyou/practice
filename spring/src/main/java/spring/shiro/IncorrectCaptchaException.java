package spring.shiro;

import org.apache.shiro.authc.AuthenticationException;

/**
 * @author qisy01
 * @create 18-11-6
 * @since 1.0.0
 */
public class IncorrectCaptchaException extends AuthenticationException {
    private static final long serialVersionUID = 1L;

    public IncorrectCaptchaException() {
        super();
    }

    public IncorrectCaptchaException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectCaptchaException(String message) {
        super(message);
    }

    public IncorrectCaptchaException(Throwable cause) {
        super(cause);
    }
}
