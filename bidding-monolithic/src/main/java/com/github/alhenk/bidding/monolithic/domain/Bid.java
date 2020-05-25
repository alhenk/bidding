package com.github.alhenk.bidding.monolithic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String offerId;
    private String buyerId;
    private String bidId;
    private Float stake;
    private ZonedDateTime creationDate;
    private ZonedDateTime expirationDate;
    private String guessValue;
}
