package com.br.wellingtondev.api_.services;

import com.br.wellingtondev.api_.domain.User;

public interface UserService {

    User findById(Integer id);
}
