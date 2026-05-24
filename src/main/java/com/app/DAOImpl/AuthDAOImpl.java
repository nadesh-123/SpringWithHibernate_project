package com.app.DAOImpl;

import com.app.DAO.AuthDAO;
import com.app.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

@Component
public class AuthDAOImpl implements AuthDAO {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public User login(String username, String password) {
    TypedQuery<User> query=entityManager.createQuery("select u from User u where u.username=:username and u.password=:password",User.class);
    query.setParameter("username",username);
    query.setParameter("password",password);
    User user=query.getSingleResult();
        return user;
    }
}
