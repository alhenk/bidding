package com.github.alhenk.bidding.monolithic.services;

import com.github.alhenk.bidding.monolithic.domain.Bid;
import com.github.alhenk.bidding.monolithic.repositories.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidServiceImpl implements BidService {

    private BidRepository bidRepository;

    @Autowired
    public void setBidRepository(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    @Override
    public Iterable<Bid> listAllBids() {
        return bidRepository.findAll();
    }

    @Override
    public Bid saveBid(Bid bid) {
        return bidRepository.save(bid);
    }

    @Override
    public Bid getBidById(Integer id) {
        return bidRepository.findById(id).orElse(null);
    }
}
