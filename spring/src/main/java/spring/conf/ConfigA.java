package spring.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import spring.beans.BaseBImpl;
import spring.event.EmailService;

@Configuration
public class ConfigA {
    @Bean
    public BaseBImpl getBaseB() {
        return new BaseBImpl();
    }

    @Bean
    @Lazy(value = false)
    public EmailService getEmailService(){
        return new EmailService();
    }
}
