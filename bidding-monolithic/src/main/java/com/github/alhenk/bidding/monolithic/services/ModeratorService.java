package com.github.alhenk.bidding.monolithic.services;

import com.github.alhenk.bidding.monolithic.domain.Bid;
import com.github.alhenk.bidding.monolithic.domain.Offer;
import com.github.alhenk.bidding.monolithic.domain.Winner;

import java.util.List;

public interface ModeratorService {
    List<Winner> findWinners(Offer offer);
    Winner createWinner(Offer offer, Bid bid);

}
