package com.app.DAOImpl;

import com.app.DAO.CustomerDAO;
import com.app.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

@Component
public class CusteomerDAOImpl implements CustomerDAO {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public Customer getCustomerByUserName(String username) {
        TypedQuery<Customer> query = entityManager.createQuery("select c from Customer c where c.user.username=:username", Customer.class);
        query.setParameter("username",username);
        Customer customer=query.getSingleResult();
        return customer;
    }
}
