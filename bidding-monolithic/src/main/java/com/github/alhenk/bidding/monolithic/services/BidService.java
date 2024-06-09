package com.github.alhenk.bidding.monolithic.services;

import com.github.alhenk.bidding.monolithic.domain.Bid;

public interface BidService {
    Iterable<Bid> listAllBids();
    Bid saveBid(Bid bid);
    Bid getBidById(Integer id);
}
