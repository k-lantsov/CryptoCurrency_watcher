package com.idfinance.cryptoapp.mapper;

import com.idfinance.cryptoapp.entity.CoinLoreCurrency;
import com.idfinance.cryptoapp.entity.Currency;

public class FromCoinLoreCurrencyToCurrencyMapper implements Mapper<Currency, CoinLoreCurrency>{
    @Override
    public Currency mapFrom(CoinLoreCurrency coinLoreCurrency) {
        return Currency.builder()
                .id(coinLoreCurrency.getId())
                .symbol(coinLoreCurrency.getSymbol())
                .cost(coinLoreCurrency.getPriceUsd())
                .build();
    }
}
