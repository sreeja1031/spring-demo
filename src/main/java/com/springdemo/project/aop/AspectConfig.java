package com.springdemo.project.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AspectConfig {

    @Pointcut("execution(* com.springdemo.project.controller.*.*(..))")
    public  void controllerPointCut() {
        //empty body
    }

    @Pointcut("execution(* com.springdemo.project.services.*.*(..))")
    public  void servicePointCut() {
        //empty body
    }

    @Pointcut("execution(* com.springdemo.project.dao.*.*(..))")
    public  void daoPointCut() {
        //empty body
    }

    @Pointcut("controllerPointCut() || servicePointCut() ||  daoPointCut()")
    public  void all() {
        //empty body
    }


    @Before("all()")
    public void executeBefore(JoinPoint joinPoint)
    {
        String method = joinPoint.getSignature().toShortString();

        log.info("@before calling method : "+method);

        Object[] objects = joinPoint.getArgs();

        for(Object temp:objects)
        {
            log.info("Result : "+temp);
        }

    }


    @AfterReturning(pointcut = "all()", returning = "result")
    public void executeAfter(JoinPoint joinPoint, Object result)
    {
        String method = joinPoint.getSignature().toShortString();

        log.info("@after calling method : "+method);

        log.info("Result is : "+result);

    }





}
