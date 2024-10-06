package com.scm.scm.services.impl;

import java.util.*;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.scm.scm.entities.User;
import com.scm.scm.helper.Helper;
import com.scm.scm.repository.UserRepository;
import com.scm.scm.services.EmailServices;
import com.scm.scm.services.UserServices;

@Service
public class UserServicesImpl implements UserServices{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailServices emailServices;

    private Logger logger=LoggerFactory.getLogger(this.getClass());

    @Override
    public User savUser(User user) {
        String userId=UUID.randomUUID().toString();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(List.of("ROLE_USER"));
        String emailToken = UUID.randomUUID().toString();
        user.setEmailToken(emailToken);
        User savedUser = userRepository.save(user);
        String emailLink = Helper.getLinkForEmailVerificatiton(emailToken);
        emailServices.sendEmail(savedUser.getEmail(), "Verify Account : Smart  Contact Manager", emailLink);
        return savedUser;
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User user2=userRepository.findById(user.getUserId()).orElseThrow(()->new ResourceAccessException("User does not exist"));

        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setAbout(user.getAbout());
        user2.setPassword(user.getPassword());

        User saveUser=userRepository.save(user2);
        return Optional.ofNullable(saveUser);

    }

    @Override
    public void deleteUser(String id) {
        User user2=userRepository.findById(id).orElseThrow(()->new ResourceAccessException("User does not exist"));
        userRepository.delete(user2);
    }

    @Override
    public boolean isUserExist(String userId) {
        User user2=userRepository.findById(userId).orElseThrow(()->new ResourceAccessException("User does not exist"));
        return user2 != null ? true : false;

    }


    @Override
    public boolean isUserExistByMail(String email) {
        User user=userRepository.findByEmail(email).orElse(null);
        return user != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
         List<User> userList= userRepository.findAll();
         return userList;
    }

    @Override
    public User findByMail(String email) {
       return userRepository.findByEmail(email).orElse(null);   
    }

}
