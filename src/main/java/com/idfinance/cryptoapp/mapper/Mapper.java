package com.idfinance.cryptoapp.mapper;

public interface Mapper<T, E> {
    T mapFrom(E entity);
}
