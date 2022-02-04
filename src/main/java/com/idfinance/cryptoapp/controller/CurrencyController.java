package com.idfinance.cryptoapp.controller;

import com.idfinance.cryptoapp.dto.ClientNotifyInfo;
import com.idfinance.cryptoapp.dto.CurrencyDto;
import com.idfinance.cryptoapp.entity.Client;
import com.idfinance.cryptoapp.entity.Currency;
import com.idfinance.cryptoapp.mapper.FromCurrencyToCurrencyDtoMapper;
import com.idfinance.cryptoapp.service.ClientService;
import com.idfinance.cryptoapp.service.CurrencyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class CurrencyController {

    private final CurrencyService currencyServiceImpl;

    private final ClientService clientServiceImpl;

    @Autowired
    public CurrencyController(CurrencyService currencyServiceImpl, ClientService clientServiceImpl) {
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

    @GetMapping("/currencies/{symbol}")
    public ResponseEntity<Currency> showCurrencyCost(@PathVariable String symbol) {
        Optional<Currency> currencyOptional = currencyServiceImpl.findCurrencyBySymbol(symbol);
        if (currencyOptional.isPresent()) {
            Currency currency = currencyOptional.get();
            return new ResponseEntity<>(currency, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/notify")
    public ResponseEntity<String> saveClient(@RequestBody ClientNotifyInfo clientNotifyInfo) {
        Client client = clientServiceImpl.save(clientNotifyInfo);
        if (client == null) {
            return new ResponseEntity<>("Please enter correct currency symbol!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
