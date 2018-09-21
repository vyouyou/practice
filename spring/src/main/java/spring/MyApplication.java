package spring;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.beans.BaseBImpl;
import spring.conf.ConfigImport;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class MyApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
         ConfigurableApplicationContext ctx = SpringApplication.run(MyApplication.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MyApplication.class);
    }
}
