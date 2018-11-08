package spring.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author qisy01
 * @create 18-11-7
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties({ShiroFilterChainConf.class})
public class ShiroConfiguration {
    @Autowired
    ShiroFilterChainConf shiroFilterChainConf;

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager manager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        // 设置manager
        factoryBean.setSecurityManager(manager);
        // 设置过滤链条
//        Map<String, String> filterChainMap = shiroFilterChainConf.getFilterChainDefinitions();
        Map<String, String> filterChainMap = new LinkedHashMap<>();
        filterChainMap.put("/login", "anon");
        filterChainMap.put("/**", "authc");
        factoryBean.setFilterChainDefinitionMap(filterChainMap);
        return factoryBean;
    }

    @Bean
    public SecurityManager getSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getMyShiroRealm());
        securityManager.setRememberMeManager(getRememberMeManager());
        return securityManager;
    }

    @Bean
    public SimpleCookie getCookieBean() {
        SimpleCookie cookie = new SimpleCookie("i am cookie");
        cookie.setMaxAge(3000000);
        return cookie;
    }

    @Bean
    public CookieRememberMeManager getRememberMeManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(getCookieBean());
        return rememberMeManager;
    }

    @Bean
    public MyShiroRealm getMyShiroRealm() {
        return new MyShiroRealm();
    }
}
