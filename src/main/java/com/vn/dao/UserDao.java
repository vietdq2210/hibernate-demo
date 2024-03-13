package com.vn.dao;

import com.vn.entities.Order;
import com.vn.entities.Report;
import com.vn.entities.User;

import java.util.List;

public interface UserDao {
    //CRUD
    User create(User user);
    List<User> readAll();
    User readDetail(Integer userId);

    List<Object[]> reportOrder();

    List<Object[]> reportOrderJPQL();

    List<Report> reportOrderJPQL_V2();

    List<User> findByNameLike(String name);

    User findByOrderId(Order order);


    List<User> getInfo();
}
