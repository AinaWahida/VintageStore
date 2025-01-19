package com.myweb.dao;

import com.myweb.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private List<User> users = new ArrayList<>();

    @Override
    public User getUserByUsername(String username) {
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElse(null);
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }
}
