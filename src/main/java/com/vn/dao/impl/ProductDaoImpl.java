package com.vn.dao.impl;

import com.vn.dao.ProductDao;
import com.vn.entities.Order;
import com.vn.entities.Product;
import com.vn.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDaoImpl extends AbtractGenericDao implements ProductDao {

    public ProductDaoImpl(){
        setEntityType(Product.class);
    }
    @Override
    public List<Order> findByProductId(Integer productId) {
        List<Order> orders = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            session.getTransaction().begin();

            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Product> findByPaging(Integer pageNumber, Integer pageSize) {
        List<Product> products = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            session.getTransaction().begin();
            Query<Product> query = session.createQuery("SELECT p FROM Product p");
            query.setFirstResult((pageNumber-1) * pageSize);
            query.setMaxResults(pageSize);
            products = query.getResultList();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return products;
    }
}
