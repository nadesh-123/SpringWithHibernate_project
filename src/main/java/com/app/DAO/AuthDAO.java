package com.app.DAO;

import com.app.model.User;

public interface AuthDAO {
    User login(String username, String password);
}
