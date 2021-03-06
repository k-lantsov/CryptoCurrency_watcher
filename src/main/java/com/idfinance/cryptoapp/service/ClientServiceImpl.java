package com.idfinance.cryptoapp.service;

import com.idfinance.cryptoapp.dto.ClientNotifyInfo;
import com.idfinance.cryptoapp.entity.Client;
import com.idfinance.cryptoapp.entity.Currency;
import com.idfinance.cryptoapp.repository.ClientRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService{

    private static final Logger LOGGER = LogManager.getLogger();

    private static final double MAX_CHANGE = 1.0;

    private final ClientRepository clientRepository;

    private final CurrencyService currencyServiceImpl;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, CurrencyService currencyServiceImpl) {
        this.clientRepository = clientRepository;
        this.currencyServiceImpl = currencyServiceImpl;
    }

    @Override
    public Client save(ClientNotifyInfo clientNotifyInfo) {
        Optional<Currency> currencyOptional = currencyServiceImpl.findCurrencyBySymbol(clientNotifyInfo.getSymbol());
        if (currencyOptional.isPresent()) {
            Currency currency = currencyOptional.get();
            Client client = Client.builder()
                    .username(clientNotifyInfo.getUsername())
                    .symbol(clientNotifyInfo.getSymbol())
                    .registryCost(currency.getCost())
                    .build();
            clientRepository.save(client);
            return client;
        } else {
            return null;
        }
    }

    @Override
    public void compareClientCurrencyCostToActual(String symbol) {
        List<Client> clients = clientRepository.findAllBySymbol(symbol);
        Currency currency = currencyServiceImpl.findCurrencyBySymbol(symbol).get();

        for (Client client: clients) {
            double change = Math.abs(client.getRegistryCost()-currency.getCost())/client.getRegistryCost()*100;
            if (change > MAX_CHANGE) {
                LOGGER.warn(String.format("Coin: %s, username: %s, the price has changed by %.4f percents",
                        currency.getSymbol(), client.getUsername(), change));
            }
        }
    }
}
