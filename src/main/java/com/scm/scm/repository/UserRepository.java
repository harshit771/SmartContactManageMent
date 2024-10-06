package com.scm.scm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.scm.entities.User;
import java.util.*;


@Repository
public interface UserRepository extends JpaRepository<User,String>{


      Optional<User> findByEmail(String email);

     Optional<User> findByEmailToken(String token);
}
