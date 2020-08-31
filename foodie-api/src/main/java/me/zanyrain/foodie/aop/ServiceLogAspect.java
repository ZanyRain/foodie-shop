package me.zanyrain.foodie.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class ServiceLogAspect {

    @Around("execution(* me.zanyrain.foodie.service..*.*(..))")
    public Object getRunningTime(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[{}.{}] Start Running",
                joinPoint.getTarget().getClass(),
                joinPoint.getSignature().getName()
        );

        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        long costTime = end - begin;

        log.info("[{}.{}] End Running, cost time {}",
                joinPoint.getTarget().getClass(),
                joinPoint.getSignature().getName(),
                costTime
        );

        return result;
    }
}
