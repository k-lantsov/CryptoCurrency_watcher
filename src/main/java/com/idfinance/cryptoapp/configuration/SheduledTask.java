package com.idfinance.cryptoapp.configuration;

import com.idfinance.cryptoapp.entity.CoinLoreCurrency;
import com.idfinance.cryptoapp.entity.Currency;
import com.idfinance.cryptoapp.mapper.FromCoinLoreCurrencyToCurrencyMapper;
import com.idfinance.cryptoapp.service.CoinLoreService;
import com.idfinance.cryptoapp.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class SheduledTask {

    private final CurrencyService currencyService;

    private final CoinLoreService coinLoreService;

    @Autowired
    public SheduledTask(CurrencyService currencyService, CoinLoreService coinLoreService) {
        this.currencyService = currencyService;
        this.coinLoreService = coinLoreService;
    }

    @Scheduled(fixedRate = 60000)
    public void updateCoinsInfo() {
        List<Currency> currencies = currencyService.findAll();
        for (Currency currency: currencies) {
            CoinLoreCurrency coinLoreCurrency = coinLoreService.getCoinLoreCurrencyById(currency.getId());
            FromCoinLoreCurrencyToCurrencyMapper mapper = new FromCoinLoreCurrencyToCurrencyMapper();
            Currency updatedCurrency = mapper.mapFrom(coinLoreCurrency);
            currencyService.update(updatedCurrency);
        }
    }
}
