package com.github.alhenk.bidding.monolithic.repositories;

import com.github.alhenk.bidding.monolithic.domain.Bid;
import org.springframework.data.repository.CrudRepository;

public interface BidRepository extends CrudRepository<Bid, Integer> {
}
