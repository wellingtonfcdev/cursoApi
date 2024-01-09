package com.br.wellingtondev.api_.services;

import com.br.wellingtondev.api_.domain.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    List<User> findAll();
}
