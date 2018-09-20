package spring.aop;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log
public class WebLogAspect {

    @Around("execution(* spring.controller..*.*(..))")
    public Object logInterceptor(ProceedingJoinPoint pjp) throws Throwable {
        return pjp.proceed();
    }
}
