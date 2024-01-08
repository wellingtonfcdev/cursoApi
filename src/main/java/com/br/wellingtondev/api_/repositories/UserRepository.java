package com.br.wellingtondev.api_.repositories;

import com.br.wellingtondev.api_.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


}
