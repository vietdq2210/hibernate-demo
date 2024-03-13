package com.vn.dao.impl;

import com.vn.dao.IGenericDao;
import com.vn.utils.HibernateUtils;
import lombok.Setter;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

@Setter
public class AbtractGenericDao <E> implements IGenericDao<E> {

    public Class<?> entityType;
    @Override
    public E create(E entity) {
        try (
                Session session = HibernateUtils.getSessionFactory().openSession()
        ){
            session.getTransaction().begin();
            session.save(entity);
            //session.persist(user);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public List<E> readAll() {
        List<E> entities = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            session.getTransaction().begin();
            //PLQL
            Query query = session.createQuery("SELECT e FROM " + entityType.getName() + " e",entityType);
            entities = query.getResultList();
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return entities;
    }

    @Override
    public E readById(Integer id) {
        E entity = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            session.getTransaction().begin();
            Query query = session.createQuery("SELECT e FROM " + entityType.getName() + " e WHERE e.id = ?1" , entityType);
            query.setParameter(1,id);
            entity = (E) query.getSingleResult();
            session.getTransaction().commit();
        }
        return entity;
    }

    @Override
    public E update(E entity) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            session.getTransaction().begin();
            //merge using to update
            session.merge(entity);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public E deleteById(Integer id) {
        E entity = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            session.getTransaction().begin();
            entity = (E) session.find(entityType,id);
            session.delete(entity);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return entity;
    }
}
