package com.idfinance.cryptoapp.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "client")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "registry_cost")
    private double registryCost;

    @Column(name = "actual_cost")
    private double actualCost;
}
