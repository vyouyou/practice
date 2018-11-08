package spring.shiro;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author qisy01
 * @create 18-11-7
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = ShiroFilterChainConf.prefix)
@Setter
@Getter
public class ShiroFilterChainConf {
    public static final String prefix = "shiro";

    private Map<String,String> filterChainDefinitions;
}
