package com.github.alhenk.bidding.monolithic.services;

import com.github.alhenk.bidding.monolithic.aspect.Announce;
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
    //TODO extract the properties in configuration properties
    public static final String ZONE_ID = "Asia/Almaty";
    public static final float PRICE = 10.0F;
    public static final int DAYS = 1;
    public static final int DICE_SIDES = 6;

    private final OfferService offerService;

    private final SecretValueService secretValueService;

    public DealerServiceImpl(OfferService offerService, SecretValueService secretValueService) {
        this.offerService = offerService;
        this.secretValueService = secretValueService;
    }

    @Override
    public Offer createOffer() {
        ZoneId zoneId = ZoneId.of(ZONE_ID);
        Period period = Period.ofDays(DAYS);
        LocalDateTime currentDate = LocalDateTime.now(zoneId);
        LocalDateTime expirationDate = currentDate.plus(period);
        ZonedDateTime expirationZdt = expirationDate.atZone(zoneId);
        ZonedDateTime currentDateZdt = currentDate.atZone(zoneId);
        Offer offer = Offer.builder()
                .offerId(UUID.randomUUID().toString())
                .price(PRICE)
                .creationDate(currentDateZdt)
                .expirationDate(expirationZdt)
                .description("Guess a dice roll")
                .build();
        offerService.saveOffer(offer);
        return offer;
    }

    @Override
    public SecretValue createSecretValue(Offer offer) {
        final int secret = new Random().nextInt(DICE_SIDES - 1 + 1) + 1;
        SecretValue secretValue = SecretValue.builder()
                .offerId(offer.getOfferId())
                .secretValue(Integer.toString(secret))
                .build();
        secretValueService.saveSecretValue(secretValue);
        return secretValue;
    }

    @Override
    @Announce
    public Offer FaitesVosJeux(Offer offer) {
        LOGGER.info("Croupier: Faites Vos Jeux!");
        return offer;
    }
}
