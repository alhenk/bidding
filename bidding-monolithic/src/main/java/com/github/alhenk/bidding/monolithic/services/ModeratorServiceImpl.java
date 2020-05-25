package com.github.alhenk.bidding.monolithic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service(value = "moderator")
@Qualifier("moderator")
public class ModeratorServiceImpl implements ModeratorService {

    @Autowired
    private OfferService offerService;
    @Autowired
    private BidService bidService;
    @Autowired
    private SecretValueService secretValueService;


}
