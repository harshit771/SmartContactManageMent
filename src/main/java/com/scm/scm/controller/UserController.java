package com.scm.scm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import com.scm.scm.services.UserServices;

@Controller
@RequestMapping("/user")
public class UserController {

     @Autowired
    private UserServices userServices;

    @RequestMapping(value = "/dashboard")
    public String userDashboard(){
        return "user/dashboard";
    }

    
    @RequestMapping("/profile")
    public String userProfile(Authentication authentication){
        return "user/profile";
    }

}
