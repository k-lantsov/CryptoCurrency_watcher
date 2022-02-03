package com.idfinance.cryptoapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinLoreCurrency {

    @JsonProperty("id")
    private int id;

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("price_usd")
    private double priceUsd;
}
