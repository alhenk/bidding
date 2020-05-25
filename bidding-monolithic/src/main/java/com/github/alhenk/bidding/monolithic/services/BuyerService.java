package com.github.alhenk.bidding.monolithic.services;

import com.github.alhenk.bidding.monolithic.domain.Bid;
import com.github.alhenk.bidding.monolithic.domain.Offer;
import org.aspectj.lang.JoinPoint;

public interface BuyerService {
    Bid createBid(Offer offer);
    void annonce(JoinPoint joinPoint);
}
