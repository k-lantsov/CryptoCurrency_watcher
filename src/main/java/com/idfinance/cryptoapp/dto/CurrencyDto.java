package com.idfinance.cryptoapp.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CurrencyDto {

    private int id;
    private String symbol;
}
