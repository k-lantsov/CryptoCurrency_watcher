package com.idfinance.cryptoapp.service;

import com.idfinance.cryptoapp.dto.ClientNotifyInfo;
import com.idfinance.cryptoapp.entity.Client;
import com.idfinance.cryptoapp.entity.Currency;
import com.idfinance.cryptoapp.exception.CurrencyNotFoundException;
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
    public Optional<Client> findClientByUsernameAndSymbol(String username, String symbol) {
        return clientRepository.findClientByUsernameAndSymbol(username, symbol);
    }

    @Override
    public void save(ClientNotifyInfo clientNotifyInfo) {
        Currency currency = currencyServiceImpl.findCurrencyBySymbol(clientNotifyInfo.getSymbol())
                .orElseThrow(() -> new CurrencyNotFoundException(String.format("Currency %s not found", clientNotifyInfo.getSymbol())));

        Optional<Client> clientOptional = clientRepository.findClientByUsernameAndSymbol(clientNotifyInfo.getUsername(), clientNotifyInfo.getSymbol());
        clientOptional.ifPresentOrElse(client -> {
            client.setSymbol(currency.getSymbol());
            client.setRegistryCost(currency.getCost());
        }, () -> {
            Client client = Client.builder()
                    .username(clientNotifyInfo.getUsername())
                    .symbol(clientNotifyInfo.getSymbol())
                    .registryCost(currency.getCost())
                    .build();
            clientRepository.save(client);
        });
    }
}
