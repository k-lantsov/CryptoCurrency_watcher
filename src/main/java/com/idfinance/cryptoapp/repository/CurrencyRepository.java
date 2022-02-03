package com.idfinance.cryptoapp.repository;

import com.idfinance.cryptoapp.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    Optional<Currency> findCurrencyBySymbol(String symbol);
}
