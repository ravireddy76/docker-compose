package com.poc.es.util;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.time.Duration;
import java.time.Instant;

/**
 * Basic Logging Practices for all Controllers to Print out required monitoring logs
 *
 * @author Ravi Reddy
 * @CopyRight (C) All rights reserved to Ravi POC World Inc. It's Illegal to reproduce this code.
 */
@Aspect
@Slf4j
@Service
public class AppLogExtract {

    /** PointCut which will monitor all classes in the designated destination. */
    @Pointcut("within(com.poc.es.*)")
    public void loggingPointCut() {
    }

    /**
     * Will Start/Stop a stopwatch to determine duration of the Signature path
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Around("loggingPointCut()")
    public Object controllerBaseLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        Instant starts = Instant.now();
        Object jointPoint = joinPoint.proceed();
        Instant ends = Instant.now();

        Object target = joinPoint.getTarget();
        Signature signature = joinPoint.getSignature();
        log.info("processName: {}, processSignature: {}, processDuration: {} milliseconds", target.getClass().getSimpleName(),
                signature, Duration.between(starts, ends).toMillis());
        return jointPoint;
    }

    /**
     * Stopwatch Getter
     *
     * @return Stopwatch
     */
    public StopWatch getStopwatch(){
        return new StopWatch();
    }
}
