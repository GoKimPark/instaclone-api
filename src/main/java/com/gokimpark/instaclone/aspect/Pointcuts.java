package com.gokimpark.instaclone.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* com.gokimpark.instaclone.domain..*(..))")
    public void serviceAndRepoLayer(){}

    @Pointcut("within(com.gokimpark.instaclone.domain..*Service)")
    public void allService(){}

    @Pointcut("within(com.gokimpark.instaclone.web..*Controller)")
    public void allController(){}
}
