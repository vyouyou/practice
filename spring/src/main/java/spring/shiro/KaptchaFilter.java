package spring.shiro;

import com.google.code.kaptcha.Constants;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author qisy01
 * @create 18-11-5
 * @since 1.0.0
 */
@Log
public class KaptchaFilter extends FormAuthenticationFilter {
    public static final String DEFAULT_CAPTCHA_PARAM = "captcha";

    @Setter
    @Getter
    private String captchaParam = DEFAULT_CAPTCHA_PARAM;

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        CaptchaUsernamePasswordToken token = createToken(request, response);
        String username = token.getUsername();
        try {
            doCaptchaValidate((HttpServletRequest) request, token);
            Subject subject = getSubject(request, response);
            subject.login(token);
            ((HttpServletRequest) request).getSession().setAttribute("name", username);
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, response);
        }
    }

    protected void doCaptchaValidate(HttpServletRequest request, CaptchaUsernamePasswordToken token) {
        String captcha = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (captcha == null || !captcha.equalsIgnoreCase(token.getCaptcha())) {
            log.info("captcha error");
            throw new IncorrectCaptchaException();
        }
    }

    @Override
    protected CaptchaUsernamePasswordToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        String captcha = getCaptcha(request);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);

        return new CaptchaUsernamePasswordToken(username, password, rememberMe, host, captcha);
    }

    public String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    @Override
    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
        request.setAttribute(getFailureKeyAttribute(), ae);
    }
}
