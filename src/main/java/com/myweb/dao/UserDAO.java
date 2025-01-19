package com.myweb.dao;

import com.myweb.model.User;
import java.util.List;

public interface UserDAO {
    User getUserByUsername(String username);
    void addUser(User user);
}
