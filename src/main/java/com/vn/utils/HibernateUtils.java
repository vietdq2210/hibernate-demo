package com.vn.utils;

import com.vn.entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class HibernateUtils {

    static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
        }
        return sessionFactory;
    }

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT e FROM Employee e", Employee.class);
        List<Employee> list = query.getResultList();

        for (Employee eml : list) {
            System.out.println(eml);
        }


        System.out.println("Done!");
    }
}
