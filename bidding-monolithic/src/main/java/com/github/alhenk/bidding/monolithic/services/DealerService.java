package com.github.alhenk.bidding.monolithic.services;

import com.github.alhenk.bidding.monolithic.domain.Offer;
import com.github.alhenk.bidding.monolithic.domain.SecretValue;

public interface DealerService {

    Offer createOffer();

    SecretValue createSecretValue(Offer offer);

    void publisOffer(Offer offer);
}
