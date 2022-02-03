package com.idfinance.cryptoapp.repository;

import com.idfinance.cryptoapp.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
}
