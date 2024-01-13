package com.br.wellingtondev.api_.services.impl;

import com.br.wellingtondev.api_.domain.User;
import com.br.wellingtondev.api_.domain.dto.UserDTO;
import com.br.wellingtondev.api_.repositories.UserRepository;
import com.br.wellingtondev.api_.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


class UserServiceImplTest {

    public static final Integer ID = 1;
    public static final String NAME = "valdir";
    public static final String EMAIL = "valdir@mail.com";
    public static final String PASSWORD = "123";
    public static final int INDEX = 0;
    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;

    private UserDTO userDTO;

    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);

        User response = service.findById(ID);
        assertNotNull(response);

        assertEquals(User.class, response.getClass());
        assertEquals(ID,response.getId());
        assertEquals(NAME,response.getNome());
        assertEquals(EMAIL,response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException(){
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException("Objeto não encontrado!!"));

        try{
            service.findById(ID);
        }catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado!!",ex.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        when(repository.findAll()).thenReturn(List.of(user));
        List<User> response = service.findAll();
        assertNotNull(response);
        assertEquals(1,response.size());
        assertEquals(User.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getNome());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());

    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(repository.save(any())).thenReturn(user);
        User response = service.create(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getNome());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}