package spring.aop;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log
public class WebLogAspect {

    // 第一个 * 返回值类型  *.代表所有的类或  *(..)代表方法的参数
    @Around("execution(* spring.controller.Test*..*(..))")
    public Object logInterceptor(ProceedingJoinPoint pjp) throws Throwable {
        log.info("request start");
        return pjp.proceed();
    }

    public void weaver(){
        ProxyFactory weaver = new ProxyFactory(new Object());
        NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
        advisor.setMappedName("execute");
        advisor.setAdvice(new PerformanceMonitorInterceptor());
    }
}
