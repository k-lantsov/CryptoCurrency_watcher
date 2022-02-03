package com.idfinance.cryptoapp.service;

import com.idfinance.cryptoapp.entity.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {

    List<Currency> findAll();

    void update(Currency currency);

    Optional<Currency> findCurrencyBySymbol(String symbol);
}
