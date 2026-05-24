package com.app.DAO;

import com.app.model.Account;

import java.util.List;

public interface AccountDAO {


    void addAccount(Account account, String username);
    void updateAccount(Account account,String username);
    List<Account> fetchAllAccounts(String username);
    Account getAccountByUserName(String username);
    Account getAccountById(int id,String username);
    void deleteAccountById(int id,String username);

}

