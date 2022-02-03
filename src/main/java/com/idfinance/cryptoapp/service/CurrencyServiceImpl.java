package com.idfinance.cryptoapp.service;

import com.idfinance.cryptoapp.entity.Currency;
import com.idfinance.cryptoapp.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CurrencyServiceImpl implements CurrencyService{

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }

    @Override
    public void update(Currency currency) {
        currencyRepository.saveAndFlush(currency);
    }

    @Override
    public Optional<Currency> findCurrencyBySymbol(String symbol) {
        return currencyRepository.findCurrencyBySymbol(symbol);
    }


}
