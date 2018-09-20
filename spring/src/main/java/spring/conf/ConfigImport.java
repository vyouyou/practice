package spring.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 用来测试@Import的功能
 */
@Configuration
@Import(value = {ConfigA.class})
public class ConfigImport {
}
