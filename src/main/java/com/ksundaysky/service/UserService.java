package com.ksundaysky.service;

import com.ksundaysky.model.User;

import java.util.List;

public interface UserService {
    public User findUserByEmail(String email);
    public User findUserById(int id);
    public void saveUser(User user);
    public void updateUser(User user);
    public List<User> findAll();
   // public List<User> findSerwisants();
    public void deleteById(int id);
}