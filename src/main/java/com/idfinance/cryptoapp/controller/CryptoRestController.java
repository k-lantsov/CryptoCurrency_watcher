package com.idfinance.cryptoapp.controller;

import com.idfinance.cryptoapp.dto.CurrencyDto;
import com.idfinance.cryptoapp.entity.Currency;
import com.idfinance.cryptoapp.mapper.FromCurrencyToCurrencyDtoMapper;
import com.idfinance.cryptoapp.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class CryptoRestController {

    private final CurrencyService currencyService;

    @Autowired
    public CryptoRestController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/currencies")
    public List<CurrencyDto> showAllCurrencies() {
        List<Currency> currencies = currencyService.findAll();
        List<CurrencyDto> currencyDtos = new ArrayList<>();
        FromCurrencyToCurrencyDtoMapper fromCurrencyToCurrencyDtoMapper = new FromCurrencyToCurrencyDtoMapper();
        for (Currency currency: currencies) {
            currencyDtos.add(fromCurrencyToCurrencyDtoMapper.mapFrom(currency));
        }
        return currencyDtos;
    }
}
