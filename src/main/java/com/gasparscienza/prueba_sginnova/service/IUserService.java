package com.gasparscienza.prueba_sginnova.service;

import java.util.List;
import java.util.Optional;

import com.gasparscienza.prueba_sginnova.model.User;

public interface IUserService {
    public void addUser(User user);
    public List<User> getUsers();
    public void delUser(Long id);
    public Optional<User> findUser(Long id);
    public void editUser(Long id, String username, String email);
    public Boolean existsByUsername(String username);
    public Optional<User> findByUsername(String username);
}
