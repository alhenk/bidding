package com.github.alhenk.bidding.monolithic.services;

import com.github.alhenk.bidding.monolithic.domain.Bid;
import com.github.alhenk.bidding.monolithic.domain.Offer;
import com.github.alhenk.bidding.monolithic.domain.SecretValue;
import com.github.alhenk.bidding.monolithic.domain.Winner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service(value = "moderator")
@Qualifier("moderator")
public class ModeratorServiceImpl implements ModeratorService {
    private static final Logger LOGGER = LogManager.getLogger(ModeratorServiceImpl.class.getName());
    //TODO extract PRIZE in configuration properties
    public static final float PRIZE = 10.0F;

    private final OfferService offerService;
    private final BidService bidService;
    private final SecretValueService secretValueService;
    private final WinnerService winnerService;

    public ModeratorServiceImpl(OfferService offerService, BidService bidService, SecretValueService secretValueService, WinnerService winnerService) {
        this.offerService = offerService;
        this.bidService = bidService;
        this.secretValueService = secretValueService;
        this.winnerService = winnerService;
    }

    @Override
    public List<Winner> findWinners(Offer offer) {
        List<Winner> winners = new LinkedList<>(); //TODO reconsider collection type
        final Iterable<SecretValue> secrets = secretValueService.listAllSecretValues();
        SecretValue secret = null;
        for (SecretValue secretValue : secrets) {
            if (secretValue.getOfferId().equals(offer.getOfferId())) {
                secret = secretValue;
                LOGGER.debug("Secret value : {}, offerId = {}", secret.getSecretValue(), offer.getOfferId());
                break;
            }
        }
        if (secret == null) {
            LOGGER.info("No Secret value found for Offer {}", offer.getOfferId());
            return winners;
        }
        final Iterable<Bid> bids = bidService.listAllBids();
            for (Bid bid : bids) {
                if (bid.getGuessValue().equals(secret.getSecretValue())) {
                    Winner winner = createWinner(offer, bid);
                    winners.add(winner) ;
                }
            }
        return winners;
    }

    @Override
    public Winner createWinner(Offer offer, Bid bid) {
        Winner winner = Winner.builder()
                .offerId(offer.getOfferId())
                .bidId(bid.getBidId())
                .prize(PRIZE)
                .build();
        winnerService.saveWinner(winner);
        return winner;
    }


}
