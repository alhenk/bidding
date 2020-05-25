package com.github.alhenk.bidding.monolithic.services;

import com.github.alhenk.bidding.monolithic.domain.Offer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FaitesVosJeuxAspect {
    private static final Logger LOGGER = LogManager.getLogger(OfferService.class.getName());

    @After("@annotation(com.github.alhenk.bidding.monolithic.aspect.Announce)")
    public void logStartingGame(JoinPoint joinPoint)
    {
        final Offer offer =(Offer) joinPoint.getArgs()[0];
        //LOGGER.info("FaitesVosJeuxAspect.logStartingGame() : " + joinPoint.getSignature().getName());
        LOGGER.info("Game started : " + offer.getDescription());
    }
}
