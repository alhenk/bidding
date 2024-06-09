package com.github.alhenk.bidding.monolithic.services;

import com.github.alhenk.bidding.monolithic.domain.Offer;
import com.github.alhenk.bidding.monolithic.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {
    private OfferRepository offerRepository;

    @Autowired
    public void setOfferRepository(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public Iterable<Offer> listAllOffers() {
        return offerRepository.findAll();
    }

    @Override
    public Offer saveOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public Offer getOfferById(Integer id) {
        return offerRepository.findById(id).orElse(null);
    }

}
