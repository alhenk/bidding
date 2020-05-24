package com.github.alhenk.bidding.monolithic.services;

import com.github.alhenk.bidding.monolithic.aspect.Loggable;
import com.github.alhenk.bidding.monolithic.domain.Offer;
import com.github.alhenk.bidding.monolithic.domain.SecretValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Random;
import java.util.UUID;

@Service(value = "dealer")
@Qualifier("dealer")
public class DealerServiceImpl implements DealerService {
    private static final Logger LOGGER = LogManager.getLogger(DealerServiceImpl.class.getName());

    @Override
    public Offer createOffer() {
        ZoneId zoneId = ZoneId.of( "Asia/Almaty" );
        Period period = Period.ofDays( 1 ) ;
        LocalDateTime currentDate = LocalDateTime.now( zoneId ) ;
        LocalDateTime expirationDate = currentDate.plus( period ) ;
        ZonedDateTime expirationZdt = expirationDate.atZone(zoneId);
        ZonedDateTime currentDateZdt = currentDate.atZone(zoneId);
        return Offer.builder()
                .offerId(UUID.randomUUID().toString())
                .price(10.0F)
                .creationDate(currentDateZdt)
                .expirationDate(expirationZdt)
                .description("Guess a dice roll")
                .build();
    }

    @Override
    public SecretValue createSecretValue(Offer offer) {
        final int secret = new Random().nextInt(6 - 1 + 1) + 1;
        return SecretValue.builder()
                .offerId(offer.getOfferId())
                .secretValue(Integer.toString(secret))
                .build();
    }

    @Override
    @Loggable
    public Offer fireOffer(Offer offer) {
        LOGGER.info("Method fireOffer called");
        return offer;
    }
}
