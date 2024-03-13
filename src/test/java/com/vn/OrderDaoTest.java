package com.vn;

import com.vn.dao.OrderDao;
import com.vn.dao.impl.OrderDaoImpl;
import com.vn.entities.Order;
import com.vn.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class OrderDaoTest {

    static OrderDao orderDao = null;

    @BeforeAll
    public static void setUp(){
        orderDao = new OrderDaoImpl();
    }


    @Test
    public void orderDao_readById_success(){
        Order order = (Order) orderDao.readById(1);
        System.out.println(order.getOrderCode());
        Assertions.assertNotNull(order);
    }


    @Test
    public void orderDao_create_success(){
        Order order = new Order();
        order.setOrderCode("KH001_ORDER2");
        order.setOrderDate(LocalDate.of(2024,03,07));
        User user = new User();
        user.setUserId(1);
        order.setUsers(user);

        orderDao.create(order);

        System.out.println(order.getOrderId());
    }

    @Test
    public void OrderDao_update_success(){
        Order order = new Order();
        order.setOrderId(2);
        order.setOrderCode("KH001_ORDER2");
        order.setOrderDate(LocalDate.of(2024,03,01));

        User user = new User();
        user.setUserId(1);
        order.setUsers(user);

        orderDao.update(order);
    }
}
