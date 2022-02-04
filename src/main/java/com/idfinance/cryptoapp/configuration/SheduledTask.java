package com.idfinance.cryptoapp.configuration;

import com.idfinance.cryptoapp.entity.CoinLoreCurrency;
import com.idfinance.cryptoapp.entity.Currency;
import com.idfinance.cryptoapp.mapper.FromCoinLoreCurrencyToCurrencyMapper;
import com.idfinance.cryptoapp.service.ClientService;
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

    private final CurrencyService currencyServiceImpl;

    private final CoinLoreService coinLoreServiceImpl;

    private final ClientService clientServiceImpl;

    @Autowired
    public SheduledTask(CurrencyService currencyServiceImpl, CoinLoreService coinLoreServiceImpl, ClientService clientServiceImpl) {
        this.currencyServiceImpl = currencyServiceImpl;
        this.coinLoreServiceImpl = coinLoreServiceImpl;
        this.clientServiceImpl = clientServiceImpl;
    }

    @Scheduled(fixedRate = 60000)
    public void updateCoinsInfo() {
        List<Currency> currencies = currencyServiceImpl.findAll();
        for (Currency currency: currencies) {
            CoinLoreCurrency coinLoreCurrency = coinLoreServiceImpl.getCoinLoreCurrencyById(currency.getId());
            FromCoinLoreCurrencyToCurrencyMapper mapper = new FromCoinLoreCurrencyToCurrencyMapper();
            Currency updatedCurrency = mapper.mapFrom(coinLoreCurrency);
            currencyServiceImpl.update(updatedCurrency);
            clientServiceImpl.compareClientCurrencyCostToActual(currency.getSymbol());
        }
    }
}
