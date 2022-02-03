package com.idfinance.cryptoapp.service;

import com.idfinance.cryptoapp.entity.CoinLoreCurrency;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CoinLoreServiceImpl implements CoinLoreService{

    @Override
    public CoinLoreCurrency getCoinLoreCurrencyById(int id) {
        RestTemplate restTemplate = new RestTemplate();
        CoinLoreCurrency[] coinLoreCurrency =
                restTemplate.getForObject("https://api.coinlore.net/api/ticker/?id=" + id, CoinLoreCurrency[].class);
        return coinLoreCurrency[0];
    }
}
