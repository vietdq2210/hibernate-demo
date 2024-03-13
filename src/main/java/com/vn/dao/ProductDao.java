package com.vn.dao;

import com.vn.entities.Order;
import com.vn.entities.Product;

import java.util.List;

public interface ProductDao extends IGenericDao{
    List<Order> findByProductId(Integer productId);

    List<Product> findByPaging(Integer pageNumber, Integer pageSize);
}
