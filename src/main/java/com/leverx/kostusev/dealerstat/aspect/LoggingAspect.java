package com.leverx.kostusev.dealerstat.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Log4j2
@Aspect
public class LoggingAspect {

    @Pointcut("execution(public * com.leverx.kostusev.dealerstat.service.*Service.*(..))")
    public void pointcutForServices() {
    }

    @Around("pointcutForServices()")
    public Object addLoggingToServices(ProceedingJoinPoint joinPoint) throws Throwable {
        infoLog(joinPoint, " started");
        Object result;

        try {
            result = joinPoint.proceed();
        } catch (Throwable exception) {
            errorLog(joinPoint, exception);
            throw exception;
        }

        infoLog(joinPoint, " successfully executed");

        return result;
    }

    private void infoLog(ProceedingJoinPoint joinPoint, String methodStatus) {
        log.info(getMessage(joinPoint) + methodStatus);
    }

    private void errorLog(ProceedingJoinPoint joinPoint, Throwable exception) {
        log.error(getMessage(joinPoint) + " threw an Exception " + exception);
    }

    private String getMessage(ProceedingJoinPoint joinPoint) {
        return "!!!!_____method \"" + joinPoint.getSignature().getName()
                + "\" with args " + Arrays.toString(joinPoint.getArgs())
                + " in Class " + joinPoint.getSignature().getDeclaringTypeName();
    }
}
