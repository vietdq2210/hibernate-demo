package com.vn.dao;

import com.vn.entities.Order;

import java.util.List;

public interface IGenericDao <E>{
    E create(E entity);

    List<E> readAll();

    E readById(Integer id);

    E update(E entity);

    E deleteById(Integer id);

}
