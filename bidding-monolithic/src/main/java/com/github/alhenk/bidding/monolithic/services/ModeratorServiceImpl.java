package com.github.alhenk.bidding.monolithic.services;

import com.github.alhenk.bidding.monolithic.domain.Bid;
import com.github.alhenk.bidding.monolithic.domain.Offer;
import com.github.alhenk.bidding.monolithic.domain.SecretValue;
import com.github.alhenk.bidding.monolithic.domain.Winner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service(value = "moderator")
@Qualifier("moderator")
public class ModeratorServiceImpl implements ModeratorService {
    private static final Logger LOGGER = LogManager.getLogger(ModeratorServiceImpl.class.getName());

    @Autowired
    private OfferService offerService;
    @Autowired
    private BidService bidService;
    @Autowired
    private SecretValueService secretValueService;
    @Autowired
    private WinnerService winnerService;

    @Override
    public List<Winner> findWinners(Offer offer) {
        List<Winner> winners = new LinkedList<>();
        final Iterable<SecretValue> secrets = secretValueService.listAllSecretValues();
        SecretValue secret = null;
        for (SecretValue secretValue : secrets) {
            if (secretValue.getOfferId().equals(offer.getOfferId())) {
                secret = secretValue;
                LOGGER.debug("Secret value : " + secret.getSecretValue() + ", offerId = " + offer.getOfferId());
                break;
            }
        }
        if (secret == null) {
            LOGGER.info ("No Secret value found for Offer " + offer.getOfferId() );
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
                .prize(10.0F)
                .build();
        winnerService.saveWinner(winner);
        return winner;
    }


}
