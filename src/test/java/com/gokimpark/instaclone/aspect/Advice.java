package com.gokimpark.instaclone.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
class Advice {

    @Before("com.gokimpark.instaclone.aspect.Pointcuts.serviceAndRepoLayer()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("**[Trace begin(@Before)] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "com.gokimpark.instaclone.aspect.Pointcuts.serviceAndRepoLayer()", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result) {
        log.info("**[Trace end(@AfterReturning)] {}", joinPoint.getSignature());
    }

    @AfterThrowing(value = "com.gokimpark.instaclone.aspect.Pointcuts.serviceAndRepoLayer()", throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("**[exception(@AfterThrowing)] {} message = {}", joinPoint.getSignature(), ex.getMessage());
    }

    @After(value = "com.gokimpark.instaclone.aspect.Pointcuts.serviceAndRepoLayer()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("**[Trace end(@After)] {}", joinPoint.getSignature());
    }
}
