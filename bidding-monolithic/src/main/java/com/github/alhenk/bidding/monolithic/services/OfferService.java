package com.github.alhenk.bidding.monolithic.services;

import com.github.alhenk.bidding.monolithic.domain.Offer;

public interface OfferService {

    Iterable<Offer> listAllOffers();
    Offer saveOffer(Offer offer);
    Offer getOfferById(Integer id);
}
