package com.ra.model.dao;

import com.ra.model.entity.Category;
import com.ra.model.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public Boolean save(User user) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
            return true;
        } catch (Exception exception){
            exception.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        Session session = sessionFactory.openSession();
        try {
            String hql = "FROM User as u where u.email=:email and u.password=:password";
            return session.createQuery(hql,User.class)
                    .setParameter("email",email)
                    .setParameter("password",password).uniqueResult();
        } catch (Exception exception){
            exception.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
}
