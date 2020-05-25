package com.github.alhenk.bidding.monolithic.services;

import com.github.alhenk.bidding.monolithic.domain.Offer;
import com.github.alhenk.bidding.monolithic.domain.SecretValue;
import com.github.alhenk.bidding.monolithic.domain.Winner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

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
        LOGGER.info("Secret Value : " + secretValue.getSecretValue());
        dealer.FaitesVosJeux(offer);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOGGER.info("Rien ne va plus!");

        final List<Winner> winners = moderator.findWinners(offer);
        if (winners.size() != 0) {
            for (Winner winner : winners) {
                LOGGER.info("*************** THE WINNER *********************** ");
                LOGGER.info("Moderator: The " + winner);
                LOGGER.info("************************************************** ");
            }
        } else {
            LOGGER.info("Moderator: No Winners!");
        }
    }
}
