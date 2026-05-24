package com.app.DAOImpl;

import com.app.DAO.TransactionDAO;
import com.app.enums.TransactionStatus;
import com.app.model.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class TransactionDAOImpl implements TransactionDAO {
    @PersistenceContext
    EntityManager em;
    @Override
    public void insertTransaction(Transaction transaction) {
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
      em.persist(transaction);
    }
}
