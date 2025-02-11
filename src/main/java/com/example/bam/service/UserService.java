package com.example.bam.service;


import com.example.bam.entity.User;

import java.util.List;

public interface UserService {
    User findUserById(Long Id);
    List<User> findAll();
    User save (User user);
    void delete(Long id);
}
