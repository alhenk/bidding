package com.github.alhenk.bidding.monolithic;

import com.github.alhenk.bidding.monolithic.domain.Offer;
import com.github.alhenk.bidding.monolithic.services.DealerService;
import com.github.alhenk.bidding.monolithic.services.DealerServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {SpringBiddingApplication.class})
class DealerServiceTest {
    private static final Logger LOGGER = LogManager.getLogger(DealerService.class.getName());

    @Autowired
    @Qualifier("dealer")
    private DealerServiceImpl dealerService;

    @Test
    void dealerFireOffer() {
        final Offer offer = dealerService.createOffer();
        LOGGER.info("Offer : " + offer);
        Assertions.assertNotNull(dealerService.FaitesVosJeux(offer));
    }
}
