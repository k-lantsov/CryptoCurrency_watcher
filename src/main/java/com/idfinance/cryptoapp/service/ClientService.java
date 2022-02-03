package com.idfinance.cryptoapp.service;

import com.idfinance.cryptoapp.dto.ClientNotifyInfo;
import com.idfinance.cryptoapp.entity.Client;

import java.util.Optional;

public interface ClientService {
    Optional<Client> findClientByUsernameAndSymbol(String username, String symbol);
    void save(ClientNotifyInfo clientNotifyInfo);
}
