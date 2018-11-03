package com.spring.aop;

import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Log4j
@Component
public class LogAdvice {

    //                 접근제한자                 클래스이름 메서드이름
    @Before("execution(* com.spring.service.SampleService*.*(..))")    //Pointcut
    public void logBefore(){
        log.info("=====================");
    }

    //args를 사용하면 파라미터를 구할 수 있다.
    @Before("execution(* com.spring.service.SampleService*.doAdd(String,String)) && args(str1, str2)")
    public void logBeforeWithParam(String str1, String str2){
        log.info("str1: " + str1);
        log.info("str1: " + str2);
    }

    @AfterThrowing(pointcut = "execution(* com.spring.service.SampleService*.*(..))", throwing = "exception")
    public void logException(Exception exception){
        log.info("Exception.......!!!!!!");
        log.info("exception: " + exception);
    }

    // Around는 메서드를 실행할 수 있는 권한을 가지고 있고, 메서드의 실행 전과 실행 후에 처리가 가능하다.
    // Around 대상 메서드는 void타입이 아니여야 한다.
    @Around("execution(* com.spring.service.SampleService*.*(..))")
    public Object logTime(ProceedingJoinPoint pjp){

        long start = System.currentTimeMillis();

        log.info("Target: " + pjp.getTarget());
        log.info("Param: " + Arrays.toString(pjp.getArgs()));

        //invoke method
        Object result = null;

        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        log.info("TIME: " + (end - start));

        return result;
    }

}
