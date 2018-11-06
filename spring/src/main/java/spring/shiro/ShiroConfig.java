package spring.shiro;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.imageio.spi.ServiceRegistry;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author qisy01
 * @create 18-11-2
 * @since 1.0.0
 */
@Configuration
@Order(1)
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // securitymanager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //设置验证码拦截器i
        Map<String, Filter> filterMap = shiroFilterFactoryBean.getFilters();
        KaptchaFilter kaptchaFilter = new KaptchaFilter();
        filterMap.put("kaptchaFilter", kaptchaFilter);
        shiroFilterFactoryBean.setFilters(filterMap);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //不拦截的url
        filterChainDefinitionMap.put("/login", "kaptchaFilter");
        //登录跳转页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //配置记住我或认证通过可以访问的地址
        filterChainDefinitionMap.put("/index", "user");
        filterChainDefinitionMap.put("/", "user");
        //未登录页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    @Bean
    public MyRealm myRealm() {
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myRealm;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm());
        return securityManager;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        //数据库异常处理
        mappings.setProperty("DatabaseException", "databaseError");
        mappings.setProperty("UnauthorizedException", "403");
        r.setExceptionMappings(mappings);
        r.setDefaultErrorView("error");
        r.setExceptionAttribute("ex");
        return r;
    }

    @Bean
    public ServletRegistrationBean kaptchaServlet() {
        ServletRegistrationBean servlet = new ServletRegistrationBean(new KaptchaServlet(), "/kaptcha.jpg");
        servlet.addInitParameter(Constants.KAPTCHA_SESSION_CONFIG_KEY,
                Constants.KAPTCHA_SESSION_KEY);
//        servlet.addInitParameter(Constants.KAPTCHA_IMAGE_HEIGHT, "30");
//        servlet.addInitParameter(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "30");
        servlet.addInitParameter(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "1");
        return servlet;
    }

    @Bean
    public ExceptionResolver myExceptionResolver() {
        return new ExceptionResolver();
    }
}
