package com.idfinance.cryptoapp.repository;

import com.idfinance.cryptoapp.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findAllBySymbol(String symbol);
}
