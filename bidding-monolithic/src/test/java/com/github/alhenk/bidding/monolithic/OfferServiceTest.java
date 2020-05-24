package com.github.alhenk.bidding.monolithic;

import com.github.alhenk.bidding.monolithic.domain.Offer;
import com.github.alhenk.bidding.monolithic.repositories.OfferRepository;
import com.github.alhenk.bidding.monolithic.services.OfferServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
public class OfferServiceTest {

    @Mock
    OfferRepository offerRepository;

    @InjectMocks
    OfferServiceImpl service;

    @Test
    void findOffers(){
        //given
        List<Offer> offerList = new ArrayList<>();
        given(offerRepository.findAll()).willReturn(offerList);
        //when
        Iterable<Offer> offers = service.listAllOffers();
        //then
        then(offerRepository).should().findAll();
        assertThat(offers).isNotNull();
    }

}
