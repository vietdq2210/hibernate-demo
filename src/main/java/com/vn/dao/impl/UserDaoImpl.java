package com.vn.dao.impl;

import com.vn.dao.UserDao;
import com.vn.entities.Order;
import com.vn.entities.Report;
import com.vn.entities.User;
import com.vn.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import javax.persistence.TypedQuery;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public User create(User user) {
        //TCL transaction control language
        try (
                Session session = HibernateUtils.getSessionFactory().openSession()
        ){
            session.getTransaction().begin();
            session.save(user);
            //session.persist(user);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> readAll() {
        List<User> users = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            session.getTransaction().begin();
            //PLQL
            TypedQuery<User> query = session.createQuery("SELECT u FROM User u",User.class);
            users = query.getResultList();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User readDetail(Integer userId) {
        User users = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            session.getTransaction().begin();
            //PLQL
            TypedQuery<User> query = session.createQuery("SELECT u FROM User u WHERE u.userId = userId",User.class);
            users = query.getSingleResult();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<Object[]> reportOrder() {
        List<Object[]> objects = null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" SELECT u.id , u.name , COUNT(o.order_id) AS countOrder, SUM(od.total_price) AS sumTotal");
        stringBuilder.append(" FROM Users u");
        stringBuilder.append(" LEFT JOIN Orders o ON u.id = o.user_id");
        stringBuilder.append(" LEFT JOIN OrderDetail od ON o.order_id = od.order_order_id");
        stringBuilder.append(" GROUP BY u.id , u.name");

        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            session.getTransaction().begin();
            NativeQuery<Object[]> query = session.createNativeQuery(stringBuilder.toString());
            objects = query.getResultList();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return objects;
    }

    //Java Persistence Query Language => JPQL
    @Override
    public List<Object[]> reportOrderJPQL() {
        List<Object[]> objects = null;
        String sql = """
                SELECT u.id , u.name , COUNT(o.orderId) AS countOrder, SUM(od.totalPrice) AS sumTotal
                FROM User u
                LEFT JOIN Order o ON u.id = o.orderId
                LEFT JOIN OrderDetail od ON o.orderId = od.order.orderId
                GROUP BY u.id , u.name
                """;
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            session.getTransaction().begin();
            Query<Object[]> query = session.createQuery(sql);
            objects = query.getResultList();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return objects;
    }


    @Override
    public List<Report> reportOrderJPQL_V2() {
        List<Report> objects = null;
        String sql = """
                SELECT 
                    new Report(u.userId , 
                    u.name , 
                    COUNT(o.orderId) , 
                    SUM(od.totalPrice))
                FROM User u
                LEFT JOIN Order o ON u.id = o.orderId
                LEFT JOIN OrderDetail od ON o.orderId = od.order.orderId
                GROUP BY u.id , u.name
                """;
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            session.getTransaction().begin();
            Query<Report> query = session.createQuery(sql);
            objects = query.getResultList();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return objects;
    }

    @Override
    public List<User> findByNameLike(String name) {
        List<User> users = null;
        String sql = """
                SELECT u
                FROM User u 
                WHERE u.name LIKE ?1
                """;
        try (Session session = HibernateUtils.getSessionFactory().openSession();){
            session.getTransaction().begin();
            Query<User> query = session.createQuery(sql);
            query.setParameter(1,"%"+name+"%");
            users = query.getResultList();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findByOrderId(Order order) {
        User user = null;
        //Cach viet 1
        String sql1 = """
                SELECT u.*
                FROM User u
                LEFT JOIN Order o ON u.userId = o.users.userId
                WHERE o.orderId LIKE ?1
                """;
        String sql2 = """
                SELECT o.users
                FROM Order o WHERE o.orderId = ?1
                """;
        try (Session session = HibernateUtils.getSessionFactory().openSession();){
            session.getTransaction().begin();
            Query<User> query = session.createQuery(sql2);
            query.setParameter(1,order.getOrderId());
            user = query.getSingleResult();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getInfo() {
        List<User> users = null;
        String sql = """
                SELECT new User (u.userId,u.name,u.image)
                FROM User u
                """;
        try (Session session = HibernateUtils.getSessionFactory().openSession();){
            session.getTransaction().begin();
            Query<User> query = session.createQuery(sql);
            users = query.getResultList();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }
}
