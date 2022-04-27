package com.gokimpark.instaclone.aspect;

import com.gokimpark.instaclone.aspect.annotation.Trace;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TraceAspect {

    @Around("com.gokimpark.instaclone.aspect.Pointcuts.serviceAndRepoLayer()")
    public Object doTrace(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("[Trace begin] {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            log.info("[Trace end] {}", joinPoint.getSignature());
            return result;
        } catch (Exception e) {
            log.info("[Trace exception] {} -> {}", joinPoint.getSignature(), e.getMessage());
            throw e;
        }
    }
}
