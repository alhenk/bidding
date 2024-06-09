package com.github.alhenk.bidding.monolithic.services;

import com.github.alhenk.bidding.monolithic.domain.Bid;
import com.github.alhenk.bidding.monolithic.domain.Offer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Random;
import java.util.UUID;

@Service(value = "buyer")
@Qualifier("buyer")
@Aspect
public class BuyerServiceImpl implements BuyerService {
    private static final Logger LOGGER = LogManager.getLogger(DealerServiceImpl.class.getName());

    @Autowired
    private BidService bidService;

    @After("@annotation(com.github.alhenk.bidding.monolithic.aspect.Announce)")
    public void annonce(JoinPoint joinPoint)
    {
        final Offer offer =(Offer) joinPoint.getArgs()[0];
        final Bid bid = createBid(offer);
        LOGGER.info("Joueur : Annonce! Mon bet " + bid.getGuessValue());
    }

    public Bid createBid(Offer offer){
        ZoneId zoneId = ZoneId.of( "Asia/Almaty" );
        LocalDateTime currentDate = LocalDateTime.now( zoneId ) ;
        ZonedDateTime currentDateZdt = currentDate.atZone(zoneId);
        final int guess = new Random().nextInt(6 - 1 + 1) + 1;
        Bid bid = Bid.builder()
                .bidId(UUID.randomUUID().toString())
                .creationDate(currentDateZdt)
                .offerId(offer.getOfferId())
                .stake(10.0F)
                .guessValue(Integer.toString(guess))
                .build();
        bidService.saveBid(bid);
        return bid;
    }
}
