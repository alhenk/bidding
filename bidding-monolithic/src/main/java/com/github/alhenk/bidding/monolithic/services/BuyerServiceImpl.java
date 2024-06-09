package com.github.alhenk.bidding.monolithic.services;

import com.github.alhenk.bidding.monolithic.domain.Bid;
import com.github.alhenk.bidding.monolithic.domain.Offer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Random;
import java.util.UUID;

@Service(value = "buyer")
@Qualifier("buyer")
@Aspect
public class BuyerServiceImpl implements BuyerService {
    private static final Logger LOGGER = LogManager.getLogger(DealerServiceImpl.class.getName());
    public static final float STAKE = 10.0F;
    public static final int DICE_SIDES = 6;
    public static final String ZONE_ID = "Asia/Almaty";

    private final BidService bidService;

    public BuyerServiceImpl(BidService bidService) {
        this.bidService = bidService;
    }

    @After("@annotation(com.github.alhenk.bidding.monolithic.aspect.Announce)")
    public void annonce(JoinPoint joinPoint)
    {
        final Offer offer =(Offer) joinPoint.getArgs()[0];
        final Bid bid = createBid(offer);
        LOGGER.info("Joueur : Annonce! Mon bet {}", bid.getGuessValue());
    }

    public Bid createBid(Offer offer){
        ZoneId zoneId = ZoneId.of(ZONE_ID);
        LocalDateTime currentDate = LocalDateTime.now( zoneId ) ;
        ZonedDateTime currentDateZdt = currentDate.atZone(zoneId);
        final int guess = new Random().nextInt(DICE_SIDES - 1 + 1) + 1;
        Bid bid = Bid.builder()
                .bidId(UUID.randomUUID().toString())
                .creationDate(currentDateZdt)
                .offerId(offer.getOfferId())
                .stake(STAKE)
                .guessValue(Integer.toString(guess))
                .build();
        bidService.saveBid(bid);
        return bid;
    }
}
