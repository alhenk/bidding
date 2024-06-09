package com.github.alhenk.bidding.monolithic.repositories;

import com.github.alhenk.bidding.monolithic.domain.Offer;
import org.springframework.data.repository.CrudRepository;

public interface OfferRepository extends CrudRepository<Offer, Integer> {
}
