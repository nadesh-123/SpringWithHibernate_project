package com.app.DAO;

import com.app.model.Customer;

public interface CustomerDAO {
    Customer getCustomerByUserName(String username);

}
