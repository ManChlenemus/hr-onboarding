package com.example.hronboarding.audit;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class AuditAspect {

    @Around("@annotation(audit)")
    public Object logCall(ProceedingJoinPoint pjp, Audit audit) throws Throwable {
        log.info("AUDIT start: {} - {} args={}", audit.value(), pjp.getSignature(), Arrays.toString(pjp.getArgs()));
        Object result = pjp.proceed();
        log.info("AUDIT done: {} - {}", audit.value(), pjp.getSignature());
        return result;
    }
}
