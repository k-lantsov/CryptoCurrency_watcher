package com.idfinance.cryptoapp.mapper;

import com.idfinance.cryptoapp.dto.CurrencyDto;
import com.idfinance.cryptoapp.entity.Currency;

public class FromCurrencyToCurrencyDtoMapper implements Mapper<CurrencyDto, Currency> {

    @Override
    public CurrencyDto mapFrom(Currency currency) {
        return CurrencyDto.builder()
                .id(currency.getId())
                .symbol(currency.getSymbol())
                .build();
    }
}
