package spring.aop;

import org.aspectj.lang.annotation.Around;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.DebugInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author qisy01
 * @create 18-11-19
 * @since 1.0.0
 */
@Configuration
public class AopConfiguration {
    @Bean
    public DebugInterceptor debugInterceptor() {
        return new DebugInterceptor();
    }
    // 第一个 * 返回值类型  *.代表所有的类或  *(..)代表方法的参数
//    @Around("execution(* spring.controller.Test*..*(..))")
    @Bean
    public Advisor debugAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* aop*(..))");
//        pointcut.setExpression("execution(* spring.controller.aop.*(..))");
        return new DefaultPointcutAdvisor(pointcut, debugInterceptor());
    }
}
