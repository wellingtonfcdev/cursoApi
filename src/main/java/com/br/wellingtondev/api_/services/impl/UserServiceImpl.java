package com.br.wellingtondev.api_.services.impl;

import com.br.wellingtondev.api_.domain.User;
import com.br.wellingtondev.api_.repositories.UserRepository;
import com.br.wellingtondev.api_.services.UserService;
import com.br.wellingtondev.api_.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Override
    public User findById(Integer id){
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado!"));
    }
}
