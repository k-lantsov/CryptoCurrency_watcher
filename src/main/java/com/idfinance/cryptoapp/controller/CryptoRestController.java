package com.idfinance.cryptoapp.controller;

import com.idfinance.cryptoapp.dto.ClientNotifyInfo;
import com.idfinance.cryptoapp.dto.CurrencyDto;
import com.idfinance.cryptoapp.entity.Currency;
import com.idfinance.cryptoapp.mapper.FromCurrencyToCurrencyDtoMapper;
import com.idfinance.cryptoapp.service.ClientService;
import com.idfinance.cryptoapp.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class CryptoRestController {

    private final CurrencyService currencyServiceImpl;

    private final ClientService clientServiceImpl;

    @Autowired
    public CryptoRestController(CurrencyService currencyServiceImpl, ClientService clientServiceImpl) {
        this.currencyServiceImpl = currencyServiceImpl;
        this.clientServiceImpl = clientServiceImpl;
    }

    @GetMapping("/currencies")
    public List<CurrencyDto> showAllCurrencies() {
        List<Currency> currencies = currencyServiceImpl.findAll();
        List<CurrencyDto> currencyDtos = new ArrayList<>();
        FromCurrencyToCurrencyDtoMapper fromCurrencyToCurrencyDtoMapper = new FromCurrencyToCurrencyDtoMapper();
        for (Currency currency: currencies) {
            currencyDtos.add(fromCurrencyToCurrencyDtoMapper.mapFrom(currency));
        }
        return currencyDtos;
    }

    @PostMapping("/notify")
    public void saveClient(@RequestBody ClientNotifyInfo clientNotifyInfo) {
        clientServiceImpl.save(clientNotifyInfo);
    }
}
