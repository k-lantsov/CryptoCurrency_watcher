package com.idfinance.cryptoapp.service;

import com.idfinance.cryptoapp.dto.ClientNotifyInfo;
import com.idfinance.cryptoapp.entity.Client;
import com.idfinance.cryptoapp.entity.Currency;
import com.idfinance.cryptoapp.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService{

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
}
