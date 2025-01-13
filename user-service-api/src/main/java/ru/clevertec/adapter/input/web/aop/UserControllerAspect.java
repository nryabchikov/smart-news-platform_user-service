package ru.clevertec.adapter.input.web.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class UserControllerAspect {

    @Pointcut("execution(* ru.clevertec.adapter.input.web.user.UserController.*(..))")
    public void userControllerMethods() {}

    @Before("userControllerMethods()")
    public void beforeUserControllerMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("UserController.{}: invoke method with arguments: {}", methodName, args);
    }

    @AfterReturning(pointcut = "userControllerMethods()", returning = "result")
    public void afterReturningUserControllerMethod(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        log.debug("UserController.{}: finish method with result: {}", methodName, result);
    }

    @AfterThrowing(pointcut = "userControllerMethods()", throwing = "exception")
    public void afterThrowingUserControllerMethod(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        log.error("UserController.{}: throw {}", methodName, exception.getMessage(), exception);
    }
}
