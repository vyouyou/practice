package spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("spring.repository")
@EnableAspectJAutoProxy(exposeProxy = true)
public class MyApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
         ConfigurableApplicationContext ctx = SpringApplication.run(MyApplication.class,args);
    }
}
