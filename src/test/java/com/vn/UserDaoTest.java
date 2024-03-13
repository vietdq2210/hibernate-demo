package com.vn;

import com.vn.dao.UserDao;
import com.vn.dao.impl.UserDaoImpl;
import com.vn.entities.Order;
import com.vn.entities.Report;
import com.vn.entities.User;
import com.vn.entities.UserDetail;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

public class UserDaoTest {

    @Test
    public void UserDao_create_TC001_success(){
        UserDao userDao = new UserDaoImpl();

        //1 INPUT
        User user = new User();
        user.setName("viet");
        user.setUserName("vietdq26");
        user.setPassword("123123");
        user.setImage("dfsdf");

        UserDetail userDetail = new UserDetail();
        userDetail.setPhone("12312312313");
        userDetail.setCmndFront("123");
        userDetail.setCmndBack("456");
        userDetail.setBirthDay(new Date());


        user.setUserDetail(userDetail);
        userDetail.setUsers(user);


        userDao.create(user);
        System.out.println(userDao.readDetail(1).toString());
    }


    @Test
    public void UserDao_reportOrder_TC001_success(){
        UserDao userDao = new UserDaoImpl();
        List<Object[]> objects = userDao.reportOrder();
        for (Object[] obj : objects){
            for (Object o : obj){
                System.out.println(o);
            }
        }
    }


    @Test
    public void UserDao_reportOrder_TC001_jpql_V1_success(){
        UserDao userDao = new UserDaoImpl();
        List<Object[]> objects = userDao.reportOrderJPQL();
        for (Object[] obj : objects){
            for (Object o : obj){
                System.out.println(o);
            }
        }
    }

    @Test
    public void UserDao_reportOrder_TC001_jpql_V2_success(){
        UserDao userDao = new UserDaoImpl();
        List<Report> objects = userDao.reportOrderJPQL_V2();
        for (Report report : objects){
            System.out.println(report.toString());
        }
    }


    @Test
    public void UserDao_findByName_TC002_success(){
        UserDao userDao = new UserDaoImpl();
        List<User> objects = userDao.findByNameLike("viet2");

        for (User user : objects){
            System.out.println(user.toString());
        }
    }


    @Test
    public void UserDao_findByOrderId_TC003_success(){
        UserDao userDao = new UserDaoImpl();
        Order order = new Order();
        order.setOrderId(3);
        User user = userDao.findByOrderId(order);
        System.out.println(user.toString());
    }

    @Test
    public void UserDao_getInfo_TC004_success(){
        UserDao userDao = new UserDaoImpl();
        List<User> users = userDao.getInfo();
        for (User u : users){
            System.out.println(u.toString());
        }
    }


}
