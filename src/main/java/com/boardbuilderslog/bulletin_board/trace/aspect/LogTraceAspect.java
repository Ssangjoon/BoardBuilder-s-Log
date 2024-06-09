package com.boardbuilderslog.bulletin_board.trace.aspect;

import com.boardbuilderslog.bulletin_board.trace.TraceStatus;
import com.boardbuilderslog.bulletin_board.trace.log.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Aspect
@Component
public class LogTraceAspect {
    private final LogTrace logTrace;

    @Around("execution(* com.boardbuilderslog.bulletin_board.controller..*(..)) || execution(* com.boardbuilderslog.bulletin_board.service..*(..)) || execution(* com.boardbuilderslog.bulletin_board.repsoitory..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        TraceStatus status = null;
        // log.trace("target={}", joinPoint.getTarget()); //실제 호출 대상
        // log.trace("getArgs={}", joinPoint.getArgs()); //전달인자
        // log.trace("getSignature={}", joinPoint.getSignature()); //join point 시그니처
        try{
            String messeage = joinPoint.getSignature().toShortString();
            status = logTrace.begin(messeage);
            Object result = joinPoint.proceed();
            logTrace.end(status);
            return result;
        } catch (Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }
}
