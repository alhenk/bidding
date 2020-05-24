package com.github.alhenk.bidding.monolithic.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OfferFireAspect {
    private static final Logger LOGGER = LogManager.getLogger(OfferService.class.getName());

    @After("@annotation(com.github.alhenk.bidding.monolithic.aspect.Loggable)")
    public void logAfterOfferFired(JoinPoint joinPoint)
    {
        LOGGER.info("OfferFireAspect.logAfterOfferFired() : " + joinPoint.getSignature().getName());
    }
}
