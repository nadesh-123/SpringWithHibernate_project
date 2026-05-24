package com.app.DAOImpl;

import com.app.DAO.AccountDAO;
import com.app.DAO.CustomerDAO;
import com.app.exception.InvalidOwnershipException;
import com.app.exception.ResourceNotFoundException;
import com.app.model.Account;
import com.app.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class AccountDAOImpl implements AccountDAO {
    CustomerDAO customerDAO;
@Autowired
    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @PersistenceContext
    EntityManager entityManager;
    @Override
    public void addAccount(Account account, String username) {
      Customer customer= customerDAO.getCustomerByUserName(username);
      account.setCustomer(customer);
        entityManager.persist(account);
        System.out.println("Account created.");
    }

    @Override
    public void updateAccount(Account account,String username) {
        Account account1=getAccountByUserName(username);
        account1.setAccountType(account.getAccountType());
        account1.setBalance(account.getBalance());
        entityManager.merge(account1);
        System.out.println("account updated");
    }

    @Override
    public List<Account> fetchAllAccounts(String username) {
      TypedQuery<Account> query =  entityManager.createQuery("select a from Account a where a.customer.user.username=:username",Account.class);
      query.setParameter("username",username);
      List<Account> accounts=query.getResultList();
        return accounts;
    }

    @Override
    public Account getAccountByUserName(String username) {
        TypedQuery<Account> query =  entityManager.createQuery("select a from Account a where a.customer.user.username=:username", Account.class);
        query.setParameter("username",username);
        Account account=query.getSingleResult();
        return account;
    }

    @Override
    public Account getAccountById(int id,String username) {
        TypedQuery<Customer> query =  entityManager.createQuery("select c from Customer c where c.user.username=:username",Customer.class);
        query.setParameter("username",username);
        Customer customer=query.getSingleResult();
        if(!customer.getUser().getUsername().equals(username)){
            throw new InvalidOwnershipException("this account is not belongs to you");
        }
   Account account=entityManager.find(Account.class,id);
if(account==null){
    throw new ResourceNotFoundException("Invalid Id");
}

        return account;
    }

    @Override
    public void deleteAccountById(int id, String username) {
        Account account=getAccountById(id,username);

        entityManager.remove(account);
        System.out.println("Account deleted");
    }

}
