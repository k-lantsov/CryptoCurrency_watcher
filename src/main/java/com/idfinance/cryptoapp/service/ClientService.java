package com.idfinance.cryptoapp.service;

import com.idfinance.cryptoapp.dto.ClientNotifyInfo;
import com.idfinance.cryptoapp.entity.Client;


public interface ClientService {
    Client save(ClientNotifyInfo clientNotifyInfo);
    void compareClientCurrencyCostToActual(String symbol);
}
