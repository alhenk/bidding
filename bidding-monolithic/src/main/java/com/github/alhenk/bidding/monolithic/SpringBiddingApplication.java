package com.github.alhenk.bidding.monolithic;

import com.github.alhenk.bidding.monolithic.domain.Offer;
import com.github.alhenk.bidding.monolithic.services.DealerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class SpringBiddingApplication {
    private static final Logger LOGGER = LogManager.getLogger(SpringBiddingApplication.class.getName());
    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(SpringBiddingApplication.class, args);
        final DealerService dealerService = (DealerService)ctx.getBean("dealer");
        final Offer offer = dealerService.createOffer();
        LOGGER.info("Offer : " + offer);
        dealerService.fireOffer(offer);
    }
}
