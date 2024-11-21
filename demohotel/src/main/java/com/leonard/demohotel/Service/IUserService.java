package com.leonard.demohotel.Service;

import com.leonard.demohotel.Model.User;

import java.util.List;

public interface IUserService {
    User registerUser(User user);
    List<User> getUsers();
    void deleteUser(String email);
    User getUser(String email);
}
