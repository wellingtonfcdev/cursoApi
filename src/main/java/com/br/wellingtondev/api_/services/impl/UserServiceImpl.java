package com.br.wellingtondev.api_.services.impl;

import com.br.wellingtondev.api_.domain.User;
import com.br.wellingtondev.api_.domain.dto.UserDTO;
import com.br.wellingtondev.api_.repositories.UserRepository;
import com.br.wellingtondev.api_.services.UserService;
import com.br.wellingtondev.api_.services.exceptions.DataIntegratyViolationException;
import com.br.wellingtondev.api_.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public User findById(Integer id){
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado!!"));
    }

    public List<User> findAll(){
        return repository.findAll();
    }

    @Override
    public User create(UserDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, User.class));
    }

    @Override
    public User update(UserDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, User.class));
    }

    private void findByEmail(UserDTO obj){
        Optional<User> user = repository.findByEmail(obj.getEmail());

        if(user.isPresent() && !user.get().getId().equals(obj.getId())){
            throw new DataIntegratyViolationException("E-mail já cadastrado no sistema!");
        }
    }
}
