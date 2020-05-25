package com.github.alhenk.bidding.monolithic.services;

import com.github.alhenk.bidding.monolithic.domain.Offer;
import com.github.alhenk.bidding.monolithic.domain.SecretValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value = "casino")
@Qualifier("casino")
public class Casino implements CasinoService {
    private static final Logger LOGGER = LogManager.getLogger(Casino.class.getName());

    @Autowired
    private DealerService dealer;
    @Autowired
    private ModeratorService moderator;

    @Override
    public void startGame() {

        final Offer offer = dealer.createOffer();
        LOGGER.info("Offer : " + offer);
        final SecretValue secretValue = dealer.createSecretValue(offer);
        LOGGER.info("Secret Value : " + secretValue);
        dealer.FaitesVosJeux(offer);
    }
}
