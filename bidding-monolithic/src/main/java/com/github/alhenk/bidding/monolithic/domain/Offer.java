package com.github.alhenk.bidding.monolithic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Offer {
    private Integer id;
    private String offerId;
    private OfferType type;
    private Float price;
    private Date creationDate;
    private Date expirationDate;
    private String description;
}
