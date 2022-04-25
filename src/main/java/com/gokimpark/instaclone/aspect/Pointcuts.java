package com.gokimpark.instaclone.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* com.gokimpark.instaclone.domain..*(..))")
    public void serviceAndRepoLayer(){}
}
