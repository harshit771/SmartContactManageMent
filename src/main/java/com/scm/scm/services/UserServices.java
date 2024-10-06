package com.scm.scm.services;


import java.util.*;
import com.scm.scm.entities.User;


public interface UserServices {

    User savUser(User user);
    Optional<User> getUserById(String id);
    Optional<User> updateUser(User user);
    void deleteUser(String id);
    boolean isUserExist(String userId);
    boolean isUserExistByMail(String email);
    List<User> getAllUsers();
    User findByMail(String email);

}
