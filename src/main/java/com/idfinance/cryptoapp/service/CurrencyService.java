package com.idfinance.cryptoapp.service;

import com.idfinance.cryptoapp.entity.Currency;

import java.util.List;

public interface CurrencyService {
    List<Currency> findAll();
    void update(Currency currency);
}
